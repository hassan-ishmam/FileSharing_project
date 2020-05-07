package p2p_project;

import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class Server {
	
	private static final Runnable Socket = null;
	//initialize socket and input stream 
    private Socket          socket   = null; 
    private ServerSocket    server   = null; 
    private DataInputStream in       =  null; 
    private String directory = "C:\\Users\\Hassan Ishmam\\Downloads\\Test2\\";
  
    // constructor with port 
    public Server(int port) throws ClassNotFoundException 
    { 
    	
    	try {
    		server = new ServerSocket(port);
			System.out.println("Server started!! ");
			System.out.println(" ");
			System.out.println("Waiting for the Client to be connected ..");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
    	
    	while(true)
    	{
    		try{
    			
    			socket = server.accept();
    				
    		}
    		catch(IOException e)
    		{
    			System.out.println("I/O error: " +e);
    		}
    		
    		//new ClientHandler(Socket socket).start();
    		//new ServerTestClass(socket,globalArray).start();
    	}
    	
    	/*Works with multiple files v3.0
    	 try
         { 
    		              
             server = new ServerSocket(port); 
             System.out.println("Server started"); 
   
             System.out.println("Waiting for a client ..."); 
   
             socket = server.accept(); 
             System.out.println("Client accepted");
             
             
             
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
    	 */

    	
    	/*
    	
         
        
    }
    
    */
        
        
       
    } 
    
}
/*
class ClientHandler extends Thread {
	
	public void run() {
		
	}

}
*/