/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package comunicazioneudp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author michaelfuschiotti
 */
public class UDPClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            int portaServer = 2000;
            InetAddress IPServer = InetAddress.getByName("localhost");
            
            byte[] bufferOUT = new byte[1024];
            byte[] bufferIN = new byte[1024];
         
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            
            try {
                //creo un socket e faccio inserire un dato all'utente
                DatagramSocket clientSocket = new DatagramSocket();
                System.out.println("Client pronto - inserisci un dato da inviare:");
                
                String daSpedire = input.readLine(); //leggo la stringa inserita dall'utente e successivamente predispongo il buffer di uscita
                bufferOUT = daSpedire.getBytes();
                
                //creo il datagram packet con il messaggio da inviare e i parametri del server
                DatagramPacket sendPacket = new DatagramPacket(bufferOUT, bufferOUT.length, IPServer, portaServer);
                clientSocket.send(sendPacket);
                
                DatagramPacket receivePacket = new DatagramPacket(bufferIN, bufferIN.length);
                clientSocket.receive(receivePacket);
                String ricevuto = new String(receivePacket.getData());
                
                int numCaratteri = receivePacket.getLength();
                ricevuto = ricevuto.substring(0, numCaratteri);
                System.out.println("dal SERVER:" +ricevuto);
                
                clientSocket.close();
                
            } catch (SocketException ex) {
                Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Errore nel socket!");
            }
            catch (IOException ex) {
                Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Errore di I/O!");
            }
            
   
        } catch (UnknownHostException ex) {
            Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Errore DNS!");
        }
        
        
        
        
        
        
    }
    
}
