package EX2;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ServeurUDP {
    public static void main(String[] args) {
        int port = 0;
        Scanner keyb = new Scanner(System.in);

        try {
            System.out.println("Port d'écoute : ");
            port = keyb.nextInt();

            DatagramPacket packet;
            DatagramSocket socket = new DatagramSocket(port);

            byte[] data = new byte[1024];
            packet = new DatagramPacket(data, data.length);

            socket.receive(packet);

            // Conversion du tableau de bytes en objet Voiture
            ByteArrayInputStream bis = new ByteArrayInputStream(packet.getData(), packet.getOffset(), packet.getLength());
            ObjectInput in = new ObjectInputStream(bis);
            Voiture voiture = (Voiture) in.readObject();

            // Affichage des données reçues
            System.out.println("Reçu : " + voiture);

            // Envoi d'une réponse au client
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(bos);
            out.writeObject(new Voiture("Réponse", "Modèle2"));
            byte[] reponse = bos.toByteArray();

            packet.setData(reponse);
            packet.setLength(reponse.length);
            socket.send(packet);

        } catch (Exception e) {
            System.err.println("Erreur : " + e);
        }
    }
}
