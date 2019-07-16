package network;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class UdpClient {
    private static DatagramSocket socket;
    private static InetAddress address;
    private static FlagHandler flagHandler;


    public UdpClient() {
        flagHandler = new FlagHandler();
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        try {
            address = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


    public static void close() {
        socket.close();
    }

    private static String mainManue() {
        return "[1] Add uploader\n" +
                "[2] Add Audio-Content\n" +
                "[3] Add Video-Content\n" +
                "[4] Add Audio-Video-Content\n" +
                "[5] Add licensed Audio-Content\n" +
                "[6] Add licensed Video-Content\n" +
                "[7] Add licensed Audio-Video-Content\n" +
                "[8] Remove uploader\n" +
                "[9] Get media data from type\n" +
                "[10] Get the address and the creation date\n" +
                "[11] Remove content from database\n" +
                "[12] Get the Amount of Data for one Uploader\n" +
                "[13] Print the Contentlist\n" +
                "[q] Quit the Application\n";

    }


    private static String getUserInput() {
        String userInput = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            userInput = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userInput;
    }

    private static String getAnswerFromServer() {
        byte[] received = new byte[1024];
        DatagramPacket packet = new DatagramPacket(received, received.length);
        try {
            socket.receive(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(packet.getData(), 0, packet.getLength());
    }

    private static void sendToServer(String msg) {
        byte[] sendToServer = new byte[1024];
        sendToServer = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(sendToServer, sendToServer.length, address, 4445);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleCommunication(String input) {
        sendToServer(input);
        System.out.println(getAnswerFromServer());
        sendToServer(flagHandler.setNewFlags(input) + getUserInput());
        System.out.println(getAnswerFromServer());
    }


    public static void main(String[] args) {

        UdpClient client = new UdpClient();

        while (true) {
            System.out.println(client.mainManue());
            String userInput = "";
            userInput = client.getUserInput();
            if (userInput.equals("1")) {
                handleCommunication(userInput);
            }
            if (userInput.equals("2")) {
                handleCommunication(userInput);
            }
            if (userInput.equals("3")) {
                handleCommunication(userInput);
            }
            if (userInput.equals("4")) {
                handleCommunication(userInput);
            }
            if (userInput.equals("5")) {
                handleCommunication(userInput);
            }
            if (userInput.equals("6")) {
                handleCommunication(userInput);
            }
            if (userInput.equals("7")) {
                handleCommunication(userInput);
            }
            if (userInput.equals("8")) {
                handleCommunication(userInput);
            }
            if (userInput.equals("9")) {
                handleCommunication(userInput);
            }
            if (userInput.equals("10")) {
                handleCommunication(userInput);
            }
            if (userInput.equals("11")) {
                handleCommunication(userInput);
            }
            if (userInput.equals("12")) {
                handleCommunication(userInput);
            }
            if (userInput.equals("13")) {
                sendToServer(userInput);
                sendToServer("print: ");
                String answer = getAnswerFromServer();
                System.out.println(answer);
                String userList = getAnswerFromServer();
                System.out.println(userList);
            }
            if (userInput.equals("q")) {
                close();
                break;
            }

        }
    }

}