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
            boolean attivo = true;
            byte[] bufferIN = new byte[1024]; //definisco i due buffer per i dati con dimensione di 1024
            byte[] bufferOUT = new byte[1024];
            
            System.out.println("SERVER AVVIATO...");
            while(attivo){
                DatagramPacket receivePacket = new DatagramPacket(bufferIN,bufferIN.length); //definisco il datagramma
                try {
                    serverSocket.receive(receivePacket); //attesa della ricezione, messaggio del client
                } catch (IOException ex) {
                    Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
                }
                String ricevuto = new String(receivePacket.getData());
                int numeroCaratteri = receivePacket.getLength();
                ricevuto = ricevuto.substring(0,numeroCaratteri);
                System.out.println("RICEVUTO: "+ricevuto);
                
                InetAddress IPClient = receivePacket.getAddress();
                int portaClient = receivePacket.getPort();
                
                String messDaSpedire = ricevuto.toUpperCase();
                bufferOUT = messDaSpedire.getBytes();
                
                DatagramPacket sendPacket = new DatagramPacket(bufferOUT,bufferOUT.length, IPClient, portaClient);
                try {
                    serverSocket.send(sendPacket);
                } catch (IOException ex) {
                    Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
     
        } catch (SocketException ex) {
            Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
