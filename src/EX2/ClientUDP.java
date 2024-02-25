package EX2;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ClientUDP {
    public static void main(String[] args) {
        int port = 0;
        String host = "";
        Scanner keyb = new Scanner(System.in);

        try {
            System.out.println("Adresse du serveur : ");
            host = keyb.next();
            System.out.println("Port d'écoute du serveur : ");
            port = keyb.nextInt();

            InetAddress adr = InetAddress.getByName(host);

            // L'objet voiture et la fixation du carburant
            Voiture voiture = new Voiture("Essence", "Modèle x");
            voiture.setCarburant(80);

            // Convertion de l'objet Voiture en tableau de bytes
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(bos);
            out.writeObject(voiture);
            byte[] data = bos.toByteArray();

            DatagramPacket packet = new DatagramPacket(data, data.length, adr, port);
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);

            // Réception de la réponse
            byte[] reponse = new byte[1024];
            packet.setData(reponse);
            packet.setLength(reponse.length);
            socket.receive(packet);

            // Conversion du tableau de bytes en objet Voiture
            ByteArrayInputStream bis = new ByteArrayInputStream(packet.getData());
            ObjectInput in = new ObjectInputStream(bis);
            Voiture reponseVoiture = (Voiture) in.readObject();

            // Réponse du serveur
            System.out.println("Reçu du serveur : " + reponseVoiture);

        } catch (Exception e) {
            System.err.println("Erreur : " + e);
        }
    }
}
