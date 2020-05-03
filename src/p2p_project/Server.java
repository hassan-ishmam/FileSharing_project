package p2p_project;

import java.net.*; 
import java.io.*;

public class Server {
	
	//initialize socket and input stream 
    private Socket          socket   = null; 
    private ServerSocket    server   = null; 
    private DataInputStream in       =  null; 
  
    // constructor with port 
    public Server(int port) 
    { 
        // starts server and waits for a connection 
        try
        { 
            server = new ServerSocket(port); 
            System.out.println("Server started"); 
  
            System.out.println("Waiting for a client ..."); 
  
            socket = server.accept(); 
            System.out.println("Client accepted"); 
  
            FileInputStream fin = new FileInputStream("C:\\Users\\Hassan Ishmam\\Downloads\\Test\\test1.txt");
            
            byte byteArr[] = new byte[2000];
            fin.read(byteArr, 0, byteArr.length);
            
            OutputStream os = socket.getOutputStream();
            os.write(byteArr, 0, byteArr.length);
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        }
        
    }
    
    public static void main(String[] args) {
    	// TODO Auto-generated method stub
    	Server server = new Server(5000); 
    }


}
