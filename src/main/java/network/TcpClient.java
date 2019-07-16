package network;

//Quelle für den Grundaufbau des Clients: https://www.admfactory.com/socket-example-in-java/


import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient {
    private  FlagHandler flagHandler;


    public static void main(String args[]) {
        String host = "127.0.0.1"; // fells like home
        int port = 8080;
        new TcpClient(host, port);

    }


    private String mainManue() {
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


    public TcpClient(String host, int port) {
        flagHandler = new FlagHandler();
        try {
            System.out.println("Connecting to host " + host + " on port " + port + ".");

            Socket echoSocket = null;
            PrintWriter out = null;
            BufferedReader in = null;

            try {
                echoSocket = new Socket(host, 8080);
                out = new PrintWriter(echoSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            } catch (UnknownHostException e) {
                System.err.println("Unknown host: " + host);
                System.exit(1);
            } catch (IOException e) {
                System.err.println("Unable to get streams from server");
                System.exit(1);
            }

            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.println(mainManue());
                String userInput = stdIn.readLine();

                if (userInput.equals("1")) {
                    handleCommunication(out, in, stdIn, userInput);
                }
                if (userInput.equals("2")) {
                    handleCommunication(out, in, stdIn, userInput);
                }
                if (userInput.equals("3")) {
                    handleCommunication(out, in, stdIn, userInput);
                }
                if (userInput.equals("4")) {
                    handleCommunication(out, in, stdIn, userInput);
                }
                if (userInput.equals("5")) {
                    handleCommunication(out, in, stdIn, userInput);
                }
                if (userInput.equals("6")) {
                    handleCommunication(out, in, stdIn, userInput);
                }
                if (userInput.equals("7")) {
                    handleCommunication(out, in, stdIn, userInput);
                }
                if (userInput.equals("8")) {
                    handleCommunication(out, in, stdIn, userInput);
                }
                if (userInput.equals("9")) {
                    handleCommunication(out, in, stdIn, userInput);
                }
                if (userInput.equals("10")) {
                    handleCommunication(out, in, stdIn, userInput);
                }
                if (userInput.equals("11")) {
                    handleCommunication(out, in, stdIn, userInput);
                }
                if (userInput.equals("12")) {
                    handleCommunication(out, in, stdIn, userInput);
                }
                if (userInput.equals("13")) {
                    out.println(userInput);
                    out.println("print: ");
                    String answer = in.readLine();
                    System.out.println(answer);
                    String userList = in.readLine();
                    System.out.println(userList);
                }
                if ("q".equals(userInput)) {
                    break;
                }
            }
            out.close();
            in.close();
            stdIn.close();
            echoSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleCommunication(PrintWriter output, BufferedReader inputStream, BufferedReader stdIn, String input) {
        output.println(input);
        try {
            System.out.println(inputStream.readLine());
            String answer = flagHandler.setNewFlags(input) + stdIn.readLine(); //10
            output.println(answer);
            String test = inputStream.readLine();
            System.out.println(test);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}