//Author Ravi Teja Yarlagadda (800909854)
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.omg.CosNaming.IstringHelper;



class TcpServer{
	
	
	public static void main(String[] args) {
		
		try {
			ServerSocket server = new ServerSocket(8777,0,InetAddress.getByName("localhost"));
			System.out.println("Server is waiting for connections: "+server.getLocalSocketAddress());
			boolean acceptConnections=true;
			int i=48774;
			do
			{
				Socket clientSock = server.accept();
				System.out.println("connected to "+clientSock.getRemoteSocketAddress());
				i++;
				BufferedReader inStream = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
				PrintWriter outStream = new PrintWriter(clientSock.getOutputStream());
				String s;
	            while ((s = inStream.readLine()) != null) {
	            	System.out.println(s);
	            	outStream.println(s);
	            	outStream.println();
		            outStream.flush();
		            if(s.isEmpty())
		            {
		            	break;
		            }
	            }
			}while(acceptConnections);
    		System.exit(1);
		} catch (IOException e) {
			
			System.err.println("cannot create a server socket :"+e.getMessage());
		}
	}
}
