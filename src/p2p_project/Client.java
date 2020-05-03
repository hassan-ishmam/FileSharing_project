package p2p_project;

import java.net.*; 
import java.io.*;

public class Client {
	
	// initialize socket and input output streams 
    private Socket socket            = null; 
    private DataInputStream  input   = null; 
    private DataOutputStream out     = null; 
  
    // constructor to put ip address and port 
    public Client(String address, int port) throws IOException
    { 
        // establish a connection 
        try
        { 
            socket = new Socket(address, port); 
            System.out.println("Connected"); 
  
            // takes input from terminal 
            input  = new DataInputStream(System.in); 
  
            // sends output to the socket 
            out    = new DataOutputStream(socket.getOutputStream()); 
        } 
        catch(UnknownHostException u) 
        { 
            System.out.println(u); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
  
        // string to read message from input 
        String fileName = "CECS 475 Lab 7.pdf";
        String directoryPath = "C:\\Users\\Hassan Ishmam\\Downloads\\Test"; 
        
        File myFile = new File(directoryPath+"//"+fileName);
        int length;
        
        byte [] byte_arr = new byte[10];
        
        InputStream in = new BufferedInputStream(new FileInputStream(myFile));
       // OutputStream out = new BufferedOutputStream(socket.getOutputStream());
        
        try {
        	while((length = in.read(byte_arr))>0)
        		
        		out.write(byte_arr,0, length);
        }
        finally
        {
            out.flush();
            out.close();
            in.close();
            System.out.println("closing socket...");
            socket.close();
        }
  
        // keep reading until "Over" is input 
        
    } 
    
    public static void main(String[] args) throws IOException {
    	// TODO Auto-generated method stub
    	Client client = new Client("192.168.1.35", 5000); 
    }


}

