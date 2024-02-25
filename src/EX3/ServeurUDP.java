package EX3;


import java.net.*;
import java.util.Date;

public class ServeurUDP {
    public static void main(String[] args) {
        int port = 1250;

        try {
            DatagramSocket socket = new DatagramSocket(port);
            System.out.println("Server waiting on port:  " + port);

            while (true) {
                // Waiting for the reception of a datagram
                byte[] buffer = new byte[1024];
                DatagramPacket receptionPacket = new DatagramPacket(buffer, buffer.length);
                socket.receive(receptionPacket);

                // Getting the IP adress and the Port number
                InetAddress clientAddress = receptionPacket.getAddress();
                int clientPort = receptionPacket.getPort();

                // Getting the date and the hour
                String dateHeure = new Date().toString();
                byte[] reponseData = dateHeure.getBytes();

                // Creating a datagram for the response
                DatagramPacket reponsePacket = new DatagramPacket(reponseData, reponseData.length, clientAddress, clientPort);

                // Sending the response to the client
                socket.send(reponsePacket);

                System.out.println("Response sent to :  " + clientAddress + ":" + clientPort);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
