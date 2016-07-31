//Author Ravi Teja Yarlagadda (800909854)
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

import javax.print.attribute.standard.OutputDeviceAssigned;


public class TcpClient{
	
	public void delayCalculation(String ipAddress)throws IOException{
		int i=0;
		long timeDifference[]=new long[1000];
		long sendingTime=0;
		long receivingTime=0;
		float averageTime=0;
		long maximumTime=0;
		long temp=0;
		String msg;
		Socket clientSocket;
		while(i<1000)
		{
			//creating a client socket
		clientSocket=new Socket(ipAddress,8777);
		InputStreamReader isr=new InputStreamReader(clientSocket.getInputStream());
		BufferedReader input=new BufferedReader(isr);
		PrintWriter output=new PrintWriter(clientSocket.getOutputStream());
		//recording the sending time
		sendingTime=System.currentTimeMillis();
		//sending the message
		output.println(i+"AAAAAAAAAA");
		//to check the various test cases with a string of 200/1000 a change in the n<200/n<1000 would be required
		//also comment the previous statement i.e output.println(i+"AAAAAAAAAA");
	/*	
		String check="A";
		for(int n=1;n<200;n++)
		{
			check=check+"A";
		}
		output.println(i+check);*/
		output.flush();
		while((msg=input.readLine())!=null)
		{
			if(msg.isEmpty())
			{
				break;
			}
		}
		//recording the receiving time
		receivingTime=System.currentTimeMillis();
		//calculating time difference i.e a round trip time
		timeDifference[i]=receivingTime-sendingTime;
		sendingTime=0;
		receivingTime=0;
		input.close();
		output.close();
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
		//calculating the average time
		averageTime=(float)temp/timeDifference.length;
		Arrays.sort(timeDifference);
		maximumTime=timeDifference[timeDifference.length-1];
		System.out.println("average time is: "+averageTime);
		System.out.println("maximum time is: "+maximumTime);
	//	clientSocket.close();
		//Socket clientSock=new Socket("localhost",8777);
		//PrintWriter output=new PrintWriter(clientSock.getOutputStream());
		//output.println("end");
		
	}
	public static void main(String[] args)throws IOException {
		
		System.out.println("Please enter the destination ip address: ");
		Scanner sc=new Scanner(System.in);
		String ipAddress=sc.nextLine();
		//System.out.println("PLease enter a 10 character string for Padding purpose : ");
		//String padding=sc.nextLine();
		TcpClient tcp=new TcpClient();
		//calling the function
		tcp.delayCalculation(ipAddress);
	
	}
}
