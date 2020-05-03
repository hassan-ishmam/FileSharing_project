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
            
            byte[] byteArr = new byte[20002];
            InputStream is = socket.getInputStream();
            FileOutputStream fr = new FileOutputStream("C:\\Users\\Hassan\\Downloads\\Test2\\test2.txt");
            is.read(byteArr, 0, byteArr.length);
            fr.write(byteArr, 0, byteArr.length);
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
        
       // OutputStream out = new BufferedOutputStream(socket.getOutputStream());
        
   
        // keep reading until "Over" is input 
        
    } 
    
    public static void main(String[] args) throws IOException {
    	// TODO Auto-generated method stub
    	Client client = new Client("192.168.1.35", 5000); 
    }


}

