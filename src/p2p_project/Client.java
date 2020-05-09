package p2p_project;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client {
	
	// initialize socket and input output streams 
    private Socket socket            = null; 
    private DataInputStream  input   = null; 
    private DataOutputStream out     = null; 
  
    // constructor to put ip address and port 
    public Client() throws IOException, ClassNotFoundException
    {
    	
    	sendFile();

    }
    
    public void sendFile()
    {
    	try {
        	
        	//Transfers only specified file types	v1.0
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
			System.out.println("---Client Started---");
			System.out.print("Enter the port number: ");
			int port=Integer.parseInt(br.readLine());
			System.out.println("Connecting to server...."); 

        	
        	socket = new Socket("192.168.1.10", port); 
        	//socket = new Socket("localhost", 4333);
            System.out.println("Connected"); 
            
            System.out.println("Enter the directory: ");
            String directory = br.readLine();
            
            //String directory = "C:\\Users\\Hassan\\Downloads\\Test2\\";

            //ServerSocket serverSocket = ...;
            //Socket socket = serverSocket.accept();

             File[] files = new File(directory).listFiles();
             BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
             DataOutputStream dos = new DataOutputStream(bos);
             dos.writeInt(files.length);
             //System.out.println("")
             
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
             
             System.out.println("Files Sent!");
             socket.close();
	            
	}
	catch(IOException i) 
    { 
        System.out.println(i); 
    }
    }
    



}

