import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ServerThread extends Thread {
	
	private Socket socket;
	
	public ServerThread(Socket socket) {
        this.socket = socket;
    }
	
	@Override
	public void run() {
		 try {
			 InputStream input = socket.getInputStream();
			 BufferedReader reader = new BufferedReader(new InputStreamReader(input));
 
			 OutputStream output = socket.getOutputStream();
			 PrintWriter writer = new PrintWriter(output, true);
 
			 InetSocketAddress socketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			 System.out.println("IP Client: " + socketAddress.getAddress());
			 String text;
 
			 do {
				 text = reader.readLine();
				 String reverseText = new StringBuilder(text).reverse().toString();
				 writer.println("Server: " + reverseText);
 
            } while (!text.equals("bye"));
 
            socket.close();
            
	        } catch (IOException ex) {
	        	System.out.println("Server exception: " + ex.getMessage());
	            ex.printStackTrace();
	        }
	}
}
