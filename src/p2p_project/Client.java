package p2p_project;

import java.net.*; 
import java.io.*;

public class Client {
	
	// initialize socket and input output streams 
    private Socket socket            = null; 
    private DataInputStream  input   = null; 
    private DataOutputStream out     = null; 
  
    // constructor to put ip address and port 
    public Client(String address, int port) throws IOException, ClassNotFoundException
    { 
		try {
		        	
	        	//Transfers only specified file types	v1.0
	        	
	        	socket = new Socket(address, port); 
	        	//socket = new Socket("localhost", 4333);
	            System.out.println("Connected"); 
	            
	  
	            String directory = "C:\\Users\\Hassan\\Downloads\\Test2\\";

	            //ServerSocket serverSocket = ...;
	            //Socket socket = serverSocket.accept();

	            BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
	            DataInputStream dis = new DataInputStream(bis);

	            int filesCount = dis.readInt();
	            File[] files = new File[filesCount];

	            for(int i = 0; i < filesCount; i++)
	            {
	                long fileLength = dis.readLong();
	                String fileName = dis.readUTF();

	                files[i] = new File(directory  + fileName);

	                FileOutputStream fos = new FileOutputStream(files[i]);
	                BufferedOutputStream bos = new BufferedOutputStream(fos);

	                for(int j = 0; j < fileLength; j++) bos.write(bis.read());

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

