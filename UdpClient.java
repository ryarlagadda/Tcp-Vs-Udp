//Author Ravi Teja Yarlagadda (800909854)
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Scanner;

public class UdpClient {
	
	private void delayCalculation(String ipAddress) {
		// TODO Auto-generated method stub
		byte[] data=new byte[2048];
		long sendingTime=0;
		long recevingTime=0;
		long timeDifference[]=new long[1000];
		long temp=0;
		float averageTime=0;
		long maximumTime=0;
		int i=0;
	//	byte[] addr=ipAddress.getBytes();
		try{
		while(i<1000)
		{
			DatagramSocket clientSocket = new DatagramSocket(); 
		    data=(i+"AAAAAAAAAA").getBytes();
		  //to check the various test cases with a string of 200/1000 a change in the n<200/n<1000 would be required
			//also comment the previous statement i.e output.println(i+"AAAAAAAAAA");
		/*	
			String check="A";
			for(int n=1;n<200;n++)
			{
				check=check+"A";
			}
			data=(i+check).getBytes();*/
		    InetAddress Address=InetAddress.getByName(ipAddress);
		    //recording the sending time
			sendingTime=System.currentTimeMillis();
		    DatagramPacket sendingPacket = new DatagramPacket(data,data.length,Address, 8777);
			clientSocket.send(sendingPacket); 
			DatagramPacket receivingPacket = new DatagramPacket(data,data.length);
				clientSocket.receive(receivingPacket);
				//recording the receiving time
			recevingTime =System.currentTimeMillis();
			//calculating time difference i.e a round trip time
			timeDifference[i]=(recevingTime-sendingTime);
			i++;
			if(i==1000)
			{
				//closing client connection
				clientSocket.close();
			}
		}
		for(int j=0;j<timeDifference.length;j++)
		{
			temp=temp+timeDifference[j];
		}
		averageTime=(float)temp/timeDifference.length;
		Arrays.sort(timeDifference);
		maximumTime=timeDifference[timeDifference.length-1];
		System.out.println("average time is: "+averageTime);
		System.out.println("maximum time is: "+maximumTime);
	}catch (SocketException e) {
		System.out.println("socket cannot be created "+e.getMessage());
		System.exit(1);
	}catch (UnknownHostException e) {
		System.out.println("Ipaddress not reachable "+e.getMessage());
		System.exit(1);
	}catch (IOException e) {
		System.err.println("unable to get the inputs "+e.getMessage());
		System.exit(1);
	} 
	}

	public static void main(String args[])
	{
		System.out.println("Please enter the destination ip address/hostname: ");
		Scanner sc=new Scanner(System.in);
		String ipAddress=sc.nextLine();
		//System.out.println("PLease enter a 10 character string for Padding purpose : ");
		//String padding=sc.nextLine();
		UdpClient udp=new UdpClient();
		//calling the delayCalculation function
		udp.delayCalculation(ipAddress);
	}

}
