package org.ritheshgopal.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleHttpClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		
		String host = "127.0.0.1";
		int port = 8081;
		
		//Create a TCP socker and connect to the host:port
		System.out.println("Creating socket...");
		Socket socket = new Socket(host, port);
		
		//Create the input and output streams for the network socket.
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
		
		//Sent request to the HTTP server.
		System.out.println("Sending request to server:");
		System.out.println("Host: "+host+" Port: "+port);
		out.println("GET / HTTP/1.1");
		out.println(); // blank line separating header & body
		out.flush();
		System.out.println("Request sent.. Waiting for response..");
		
		//Ready the response and display on console.
		String line;
		// readLine() returns null if server close the network socket.
		while((line = in.readLine()) != null) {
			System.out.println(line);
		}
		if(in.readLine() == null)
			System.out.println("Connection to server closed..");
		//Close the I/O streams.
		in.close();
		out.close();
	}
}
