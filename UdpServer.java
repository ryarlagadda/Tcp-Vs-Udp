//Author Ravi Teja Yarlagadda (800909854)
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.SocketException;

public class UdpServer {

	public static void main(String args[])
	{
		try {
			//creating a serverSocket
			DatagramSocket serverSocket=new DatagramSocket(8777);
			System.out.println("Server is running: "+serverSocket.getLocalSocketAddress());
			while(true)
			{
				byte[] msg=new byte[1024];
				byte[] display;
				DatagramPacket Packet=new DatagramPacket(msg,msg.length);
				try {
					//receing the data
					serverSocket.receive(Packet);
					display=Packet.getData();
					System.out.println("connected to: "+Packet.getSocketAddress());
					System.out.println(new String(display));	
					InetAddress ipAddress=Packet.getAddress();
					int port=Packet.getPort();
					Packet=new DatagramPacket(msg,msg.length,ipAddress,port);
					//sending the packet back to client
					serverSocket.send(Packet);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.err.println("cannot receive the packet from client/sender: "+e.getMessage());
				}
			}
		//	serverSocket.close();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			System.err.println("Cannot create the DatagramSocket: "+e.getMessage());
		}
		}
		
}
