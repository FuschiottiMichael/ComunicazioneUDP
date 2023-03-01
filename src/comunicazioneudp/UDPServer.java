/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package comunicazioneudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author michaelfuschiotti
 */
public class UDPServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(2000);
            boolean attivo = true; //utilizzo questa variabile come condizione di ripetizione/uscita dal ciclo
            byte[] bufferIN = new byte[1024]; //definisco i due buffer per i dati con dimensione di 1024
            byte[] bufferOUT = new byte[1024];
            
            System.out.println("SERVER AVVIATO...");
            while(attivo){
                DatagramPacket receivePacket = new DatagramPacket(bufferIN,bufferIN.length); //definisco il datagramma e successivamente mi metto in attesa di ricezione dei dati da parte del client 
                try {
                    serverSocket.receive(receivePacket); //attesa della ricezione, messaggio del client
                } catch (IOException ex) {
                    Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
                    System.err.println("Errore di I/O!");
                }
                String ricevuto = new String(receivePacket.getData()); //analizzo il pacchetto ed estraggo il messaggio. IL PACCHETTO ARRIVA IN ARRAY DI BYTE, CHE LI CONVERTO IN UNA STRINGA
                int numeroCaratteri = receivePacket.getLength(); //individuo la lunghezza impostata alla trasmissione
                ricevuto = ricevuto.substring(0, numeroCaratteri); //elimino i caratteri superflui presenti nel buffer 
                System.out.println("RICEVUTO: "+ricevuto);
                
                InetAddress IPClient = receivePacket.getAddress(); //sempre dal pacchetto ricevuto, individuo i parametri per la trasmissione della risposta, QUA INDIVIDUO L'INDIRIZZO
                int portaClient = receivePacket.getPort(); //INDIVIDUO LA PORTA 
                
                String messDaSpedire = ricevuto.toUpperCase(); //preparo la risposta, trasformando il messaggio ricevuto in maiuscolo
                bufferOUT = messDaSpedire.getBytes(); //Converto la risposta da String a byte e successivamente vado a creare un datagramma per l'uscita del pacchetto 
                
                DatagramPacket sendPacket = new DatagramPacket(bufferOUT,bufferOUT.length, IPClient, portaClient);
                try {
                    serverSocket.send(sendPacket);
                } catch (IOException ex) {
                    Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
                    System.err.println("Errore di I/O!");
                }
                
                
                if(ricevuto.equals("fine"));
                {
                    System.out.println("SERVER IN CHIUSURA... Buona giornata!!!");
                    attivo = false;
                }
                
                
            }
         serverSocket.close();
        } catch (SocketException ex) {
            Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Errore nel socket!");
        }
        
    }
    
}
