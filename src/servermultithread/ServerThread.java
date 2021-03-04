/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servermultithread;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * 
 * // sviluppare un applicativo client server che genri tani thread quanti dichiarati dall'utente attraverso un thread a lui dedicato ogni thread sommera ad una variabile globale contenuta nel server il proprio valore incrementale assegnatoli dal server 
 * @author tresoldi.lorenzo
 */
public class ServerThread implements Runnable {

    private Socket clientSocket;

    public ServerThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        System.out.println("Serverino  partito: "
                + clientSocket.getInetAddress());
        try {

            PrintWriter out
                    = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            String richiesta = "";
            while (!richiesta.equals("exit")) {
                System.out.println("serverino in ascolto...");
                richiesta = in.readLine();
                System.out.println("caratteri: " + richiesta);
                out.println("caratteri: " + richiesta.length());
            }

            out.close();
            clientSocket.close();

            System.out.println("chiusura connessione effettuata");

        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
