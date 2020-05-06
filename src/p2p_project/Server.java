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
        
    	//Transfers only specified file types	v1.0
    	
        try
        { 
        	
            server = new ServerSocket(port); 
            System.out.println("Server started"); 
  
            System.out.println("Waiting for a client ..."); 
  
            socket = server.accept(); 
            System.out.println("Client accepted");
            
            
            File [] listOfFiles = getListOfFiles(directory);
            FileInputStream fin;
            OutputStream os;
            os = socket.getOutputStream();
            
            for(int i = 0; i<listOfFiles.length; i++) {
            	
            	System.out.println("Getting file " + listOfFiles[i].getName());
            	
            	fin = new FileInputStream(directory + listOfFiles[i].getName());
            	
            	System.out.println("Converting to byte file " + listOfFiles[i].getName());
                
                byte byteArr[] = new byte[2000];
                fin.read(byteArr, 0, byteArr.length);
                
                System.out.println("Sending file " + listOfFiles[i].getName());
                
                
                os.write(byteArr, 0, byteArr.length);
                os.flush();
                
                System.out.println("Sent file " + listOfFiles[i].getName());
                
                fin.close();
                
            	
            }
            
            os.close();
            
            System.out.println("Closing Connection..");
            
            socket.close();
           
            
        }
        
        catch(IOException i) 
        { 
            System.out.println(i); 
        }
        
    	
    	//v2.0
    	/*
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
    		
    		new ClientHandler(Socket socket).start();
    		//new ServerTestClass(socket,globalArray).start();
    	}
    	
         
        
    }
    
    */
    }
    
    public File[] getListOfFiles(String path) {
    	
    	File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
    	
    	

		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {
		    System.out.println("File " + listOfFiles[i].getName());
		  } else if (listOfFiles[i].isDirectory()) {
		    System.out.println("Directory " + listOfFiles[i].getName());
		  }
		}
		
    	
    	return listOfFiles;
    }
    
    
}
/*
class ClientHandler extends Thread {
	
	public void run() {
		
	}

}
*/