package p2p_project;

import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class Server {
	
	//initialize socket and input stream 
    private Socket          socket   = null; 
    private ServerSocket    server   = null; 
    private DataInputStream in       =  null; 
  
    // constructor with port 
    public Server(int port) throws ClassNotFoundException 
    { 
        // starts server and waits for a connection 
    	
    	
    	
    	ObjectOutputStream oos;
    	ObjectInputStream ois;
    	String str;
    	int index;
    	/*
    	
    	try
    	{  
    		server = new ServerSocket(port); 
            System.out.println("Server started"); 
  
            System.out.println("Waiting for a client ..."); 
  
            socket = server.accept(); 
            System.out.println("Client accepted"); 
    		
    		InputStream is=socket.getInputStream();
    		oos = new ObjectOutputStream(socket.getOutputStream());
    		ois = new ObjectInputStream(is);
    		
    		File myFile = new File("C:\\Users\\Hassan Ishmam\\Downloads\\Test\\census2020.pdf");
    		
    		byte byteArr[] = new byte[20002];
    		
    		FileInputStream FIS=new FileInputStream(myFile);
            BufferedInputStream objBIS = new BufferedInputStream(FIS);
            objBIS.read(byteArr,0,(int)myFile.length());
            
            oos.write(byteArr, 0, byteArr.length);
            
            System.out.println("Closing Connection from Server..");
            
            oos.close();
            ois.close();
            FIS.close();
            objBIS.close();
            socket.close();
            
            System.out.println("Closed from Server");
            
            
  
    	}
    	catch(IndexOutOfBoundsException e){
    		System.out.println("Index out of bounds exception");
    	}
    	catch(IOException e){
    		System.out.println("I/O exception");
    	}
    	*/
    	//Transfers only specified file types	v1.0
    	String directory = "C:\\Users\\Hassan Ishmam\\Downloads\\Test";
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
            
            for(int i = 0; i<listOfFiles.length; i++) {
            	
            	fin = new FileInputStream(directory+ "\\" + listOfFiles[i].getName());
                
                byte byteArr[] = new byte[2000];
                fin.read(byteArr, 0, byteArr.length);
                
                os = socket.getOutputStream();
                os.write(byteArr, 0, byteArr.length);
                
                fin.close();
                os.close();
            	
            }
            
            System.out.println("Closing Connection..");
            
            socket.close();
           
            
        }
        
        catch(IOException i) 
        { 
            System.out.println(i); 
        }
         
        
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
    
    public static void main(String[] args) throws ClassNotFoundException {
    	// TODO Auto-generated method stub
    	Server server = new Server(5000);
    	
    }


}
