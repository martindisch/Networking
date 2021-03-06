package com.martin.dchat;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {

		final String server = "80.218.146.10";
		final int port = 1234;

		try {
			Socket clSocket = new Socket(server, port);

			new Thread(new ClientListener(clSocket)).start();

			final BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
					clSocket.getOutputStream(), "ISO-8859-1"));
			Scanner scanner = new Scanner(System.in);

			String text;
			while ((text = scanner.nextLine()) != null) {
				out.write(text);
				out.newLine();
				out.flush();
				if (text.toUpperCase().contentEquals("END")) {
					scanner.close();
					System.exit(0);
				}
			}
		}
		catch (IOException e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}

}
