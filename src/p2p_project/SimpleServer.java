package p2p_project;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
	
	private static final Runnable Socket = null;
	//initialize socket and input stream 
    private Socket          socket   = null; 
    private ServerSocket    server   = null; 
    private DataInputStream in       =  null; 
    private String directory = "C:\\Users\\Hassan Ishmam\\Downloads\\Simple Test 1\\";
  
    // constructor with port 
    public SimpleServer(int port) throws ClassNotFoundException 
    { 
    	
    	 try
         { 
    		              
             server = new ServerSocket(port); 
             System.out.println("Server started"); 
   
             System.out.println("Waiting for a client ..."); 
   
             socket = server.accept(); 
             System.out.println("Client accepted");
             
             System.out.println("Socket address "+socket.getRemoteSocketAddress().toString());
             
             //String [] fileNames = folder.list();
             
             File[] files = new File(directory).listFiles();
             BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
             DataOutputStream dos = new DataOutputStream(bos);
             dos.writeInt(files.length);
             
             //String[] fileNames = new String[1]; // Empty at the moment
             for(File file : files) {
            	 System.out.println("Writing Obj " +file.getName());
            	 long length = file.length();
            	 dos.writeLong(length);
            	 
            	 String name = file.getName();
            	 dos.writeUTF(name);
            	 
            	 FileInputStream fis = new FileInputStream(file);
            	 BufferedInputStream bis = new BufferedInputStream(fis);

            	 int theByte = 0;
            	 while((theByte = bis.read()) != -1) bos.write(theByte);

            	 bis.close();
             }
             //oos.writeObject(fileNames); 
             
             
             dos.close();
             
             System.out.println("Bye Bye");
             socket.close();
             
             
         }
    	 catch(IOException i) 
         { 
             System.out.println(i); 
         }
    }
    	 

	public static void main(String[] args) throws ClassNotFoundException {
		System.out.println("---Starting Server---");
		SimpleServer server = new SimpleServer(5000);
	}

}
