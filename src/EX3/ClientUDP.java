package EX3;

import java.net.*;
import java.util.Arrays;

public class ClientUDP {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 1250;

        try {
            DatagramSocket socket = new DatagramSocket();

            byte[] requestData = new byte[0];
            DatagramPacket requestPacket = new DatagramPacket(requestData, requestData.length, InetAddress.getByName(serverAddress), serverPort);
            socket.send(requestPacket);

            // Waiting for the response
            byte[] responseData = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length);
            socket.receive(responsePacket);

            // Displaying the server response
            String reponse = new String(responsePacket.getData(), 0, responsePacket.getLength());
            System.out.println("Server's response : " + reponse);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
