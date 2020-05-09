package p2p_project;

import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class Server {
	
	public static ArrayList<FileInfo> globalArray = new ArrayList<FileInfo>();
	
	private static final Runnable Socket = null;
	//initialize socket and input stream 
    private Socket          socket   = null; 
    private ServerSocket    server   = null; 
    private DataInputStream in       =  null; 
  
    // constructor with port 
    public Server() throws ClassNotFoundException 
    { 
    	
    	try {
    		server = new ServerSocket(5000);
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
    		new ClientHandler(socket,globalArray).start();
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

class ClientHandler extends Thread {
	protected Socket socket;
	ArrayList<FileInfo> globalArray;
	public String directory = "C:\\Users\\Hassan Ishmam\\Downloads\\Test2\\";
	public ClientHandler(Socket clientSocket,ArrayList<FileInfo> globalArray)
	{
		this.socket=clientSocket;
		this.globalArray=globalArray;
	}
	
	public void run() {

		receiveFile();
	}
	
	private void receiveFile() {
		
		try {
			
			//Print out the clients address
	         System.out.println("Connected to client: "+socket.getRemoteSocketAddress().toString());
	         
	         //Declaring input streams for reading data sent from client over socket
			 BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
	         DataInputStream dis = new DataInputStream(bis);
	         
	         //Each peer sends the number of files in their directory
	         int filesCount = dis.readInt();
	         File[] files = new File[filesCount];
	         
	         //Receiving files from client
	         for(int i = 0; i < filesCount; i++) {
	        	
	        	//Reads the files name and length 
	        	long fileLength = dis.readLong();
                String fileName = dis.readUTF();
                System.out.println("Received file: " +fileName);
                
                //Creates a new empty file
                files[i] = new File(directory  + fileName);
                
                //Writes the info into files
                FileOutputStream fos = new FileOutputStream(files[i]);
                BufferedOutputStream bos = new BufferedOutputStream(fos);

                for(int j = 0; j < fileLength; j++) 
                	bos.write(bis.read());

	            bos.close();
	        	 
	         }
	         
	         dis.close();
	         socket.close();
	            
		}
		catch(IOException i) 
        { 
            System.out.println(i); 
        }
	}

}
