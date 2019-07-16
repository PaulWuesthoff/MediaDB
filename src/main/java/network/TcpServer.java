package network;

//Quelle für den Grundaufbau des Servers: https://www.admfactory.com/socket-example-in-java/

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class TcpServer extends Thread {
    private static final int PORT_NUMBER = 8080;
    private Socket socket;
    private static FlagHandler flagHandler;


    public TcpServer(Socket socket) {
        this.socket = socket;
        System.out.println("New client connected from " + socket.getInetAddress().getHostAddress());
        start();
    }

    public void run() {

        InputStream in = null;
        OutputStream out = null;
        try {
            in = socket.getInputStream();
            out = socket.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String request;
            while ((request = br.readLine()) != null) {
                String response = "";
                System.out.println("Message received from port " + socket.getPort() + ": " + request);
                if (request.startsWith("uploader: ")) {
                    answerClient(flagHandler.handleUserInteraction(request), out);
                } else if (request.startsWith("audio: ")) {
                    answerClient(flagHandler.handleUserInteraction(request), out);
                } else if (request.startsWith("video: ")) {
                    answerClient(flagHandler.handleUserInteraction(request), out);
                } else if (request.startsWith("audioVideo: ")) {
                    System.out.println(request);
                    answerClient(flagHandler.handleUserInteraction(request), out);
                } else if (request.startsWith("lAudio: ")) {
                    answerClient(flagHandler.handleUserInteraction(request), out);
                } else if (request.startsWith("lVideo: ")) {
                    answerClient(flagHandler.handleUserInteraction(request), out);
                } else if (request.startsWith("lAudioVideo: ")) {
                    answerClient(flagHandler.handleUserInteraction(request), out);
                } else if (request.startsWith("delUploader: ")) {
                    answerClient(flagHandler.handleUserInteraction(request), out);
                } else if (request.startsWith("dataFromType: ")) {
                    answerClient(flagHandler.handleUserInteraction(request), out);
                } else if (request.startsWith("addressAndDate: ")) {
                    answerClient(flagHandler.handleUserInteraction(request), out);
                } else if (request.startsWith("delContent: ")) {
                    answerClient(flagHandler.handleUserInteraction(request), out);
                } else if (request.startsWith("amountOneUploader: ")) {
                    answerClient(flagHandler.handleUserInteraction(request), out);
                } else if (request.startsWith("print: ")) {
                    answerClient(flagHandler.handleUserInteraction(request), out);
                } else {
                    response = flagHandler.handleFlag(request);
                    response += '\n';
                    out.write(response.getBytes());
                }
            }
        } catch (IOException ex) {
            System.out.println("Unable to get streams from client");
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


    private void answerClient(String answer, OutputStream outputStream) {
        answer += '\n';
        try {
            outputStream.write(answer.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        flagHandler = new FlagHandler();
        ServerSocket server = null;
        try {
            server = new ServerSocket(PORT_NUMBER);
            System.out.println("Server is Online...");
            while (true) {
                new TcpServer(server.accept());
            }
        } catch (IOException ex) {
            System.out.println("Unable to start server.");
        } finally {
            try {
                if (server != null)
                    server.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
