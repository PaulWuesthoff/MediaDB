package network;

import eventBasedCli.EventHandler;
import management.EventManager;


import java.io.IOException;
import java.net.*;


public class UdpServer extends Thread {

    private DatagramSocket socket;
    private boolean running = true;
    private byte[] buf = new byte[256];
    private FlagHandler flagHandler;


    public UdpServer() {
        flagHandler = new FlagHandler();
        try {
            socket = new DatagramSocket(4445);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        DatagramPacket packetFromClient;
        while (running) {

            packetFromClient = getMsgFromClient();
            String in = new String(packetFromClient.getData(), 0, packetFromClient.getLength());
            System.out.println("Received message from port :" + socket.getPort() + " " + in);
            if (in.startsWith("uploader: ")) {
                sendMsgToClient(packetFromClient, flagHandler.handleUserInteraction(in));
            } else if (in.startsWith("audio: ")) {
                sendMsgToClient(packetFromClient, flagHandler.handleUserInteraction(in));
            } else if (in.startsWith("video: ")) {
                sendMsgToClient(packetFromClient, flagHandler.handleUserInteraction(in));
            } else if (in.startsWith("audioVideo: ")) {
                sendMsgToClient(packetFromClient, flagHandler.handleUserInteraction(in));
            } else if (in.startsWith("lAudio: ")) {
                sendMsgToClient(packetFromClient, flagHandler.handleUserInteraction(in));
            } else if (in.startsWith("lVideo: ")) {
                sendMsgToClient(packetFromClient, flagHandler.handleUserInteraction(in));
            } else if (in.startsWith("lAudioVideo: ")) {
                sendMsgToClient(packetFromClient, flagHandler.handleUserInteraction(in));
            } else if (in.startsWith("delUploader: ")) {
                sendMsgToClient(packetFromClient, flagHandler.handleUserInteraction(in));
            } else if (in.startsWith("dataFromType: ")) {
                sendMsgToClient(packetFromClient, flagHandler.handleUserInteraction(in));
            } else if (in.startsWith("addressAndDate: ")) {
                sendMsgToClient(packetFromClient, flagHandler.handleUserInteraction(in));
            } else if (in.startsWith("delContent: ")) {
                sendMsgToClient(packetFromClient, flagHandler.handleUserInteraction(in));
            } else if (in.startsWith("amountOneUploader: ")) {
                sendMsgToClient(packetFromClient, flagHandler.handleUserInteraction(in));
            } else if (in.startsWith("print: ")) {
                sendMsgToClient(packetFromClient, flagHandler.handleUserInteraction(in));
            } else {
                String response = flagHandler.handleFlag(in);
                response += '\n';
                sendMsgToClient(packetFromClient, response);
            }
        }

        packetFromClient = getMsgFromClient();
        running = checkForClosingFlag(packetFromClient);
    }


    private synchronized DatagramPacket getMsgFromClient() {
        byte[] msgFromClient = new byte[1024];
        DatagramPacket packet = new DatagramPacket(msgFromClient, msgFromClient.length);

        try {
            socket.receive(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return packet;
    }

    private synchronized void sendMsgToClient(DatagramPacket p, String answer) {
        byte[] msgToClient = answer.getBytes();
        int port = p.getPort();
        InetAddress inetAddress = p.getAddress();
        DatagramPacket packet = new DatagramPacket(msgToClient, msgToClient.length, inetAddress, port);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized boolean checkForClosingFlag(DatagramPacket packet) {
        String packageContent = new String(packet.getData(), 0, packet.getLength());
        if (packageContent.startsWith("q")) {
            System.out.println("Shutting down Server now.. ");
            return false;
        } else {
            return true;
        }
    }

    private synchronized boolean checkForErrorFlag(String errorMsg) {
        if (errorMsg.equals("error")) {
            return true;
        } else return false;
    }


    public static void main(String[] args) {
        UdpServer server = new UdpServer();
        server.run();
    }
}