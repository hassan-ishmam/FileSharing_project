package p2p_project;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client {
	
	// initialize socket 
    private Socket socket            = null;
    public String directory = null;
    
  //Gets the output stream from Socket
    BufferedOutputStream bos;
    DataOutputStream dos;
  
    public Client() throws IOException, ClassNotFoundException
    {
    	//Sends Files on client device to server
    	sendFileToServer();
    	receiveFileFromServer();

    }
    
    public void sendFileToServer()
    {
    	try {
        	
	        	//BufferReader for user inputs
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
				System.out.println("---Client Started---");
				
				//Takes an input from user to which port number to connect to
				System.out.print("Enter the port number: ");				
				int port=Integer.parseInt(br.readLine());
				
			
				System.out.println("Connecting to server...."); 
				
				//This is the IP address of my Server PC. Any user can replace this number with their desired Server's address
	        	socket = new Socket("192.168.1.10", port); 
	        	//socket = new Socket("localhost", 4333);
	            System.out.println("Connected"); 
	            
	            //Takes the Directory as input from user of the folder to sync
	            System.out.println("Enter the directory: ");
	            directory = br.readLine();
	            
	            //String directory = "C:\\Users\\Hassan\\Downloads\\Test2\\";
	            
	            
	            //Lists all the files in desired directory
	             File[] files = new File(directory).listFiles();
	             
	             //Gets the output stream from Socket
	             bos = new BufferedOutputStream(socket.getOutputStream());
	             DataOutputStream dos = new DataOutputStream(bos);
	             
	             //Sends the number of files in the folder as output stream, it will help receive the files
	             dos.writeInt(files.length);
	             
	             //Runs a loop to send all the files in the folder
	             for(File file : files) {
	            	 
	            	 //System.out.println("Writing Obj " +file.getName());
	            	 
	            	 //Sends the length of each file before sending the actual file 
	            	 //so the receiver knows how much memory to allocate
	            	 long length = file.length();
	            	 dos.writeLong(length);
	            	 
	            	 //Sends the name of each file before sending the actual file 
	            	 String name = file.getName();
	            	 dos.writeUTF(name);
	            	 
	            	 //In the following part, the file is converted and sent
	            	 FileInputStream fis = new FileInputStream(file);
	            	 BufferedInputStream bis = new BufferedInputStream(fis);
	
	            	 int theByte = 0;
	            	 while((theByte = bis.read()) != -1) bos.write(theByte);
	            	 System.out.println("Sent: " + name);
	            	 
	            	 //Closes buffered input stream
	            	 bis.close();
	             }
	             //oos.writeObject(fileNames); 
	             
	             //Closes DataOutputStream after all the files are sent
	             //dos.close();
	             
	             System.out.println("Files Sent!");
	             
	             //Closes Socket
	             //socket.close();
		            
		}
		catch(IOException i) 
	    { 
	        System.out.println(i); 
	    }
    }
    
    public void receiveFileFromServer() {
    	
    	try {
    		
			
			//Print out the clients address
	         System.out.println("Connected to Server \nIP: "+socket.getInetAddress().toString()+ "\nPort: " +socket.getPort());
	         
	         //Declaring input streams for reading data sent from Server over socket
			 BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
	         DataInputStream dis = new DataInputStream(bis);
	         
	         //Server sends the number of files in their directory
	         //So the receiver knows how many times to run the loop for receiving files
	         int filesCount = dis.readInt();
	         
	         //Declares an Array of files
	         File[] files = new File[filesCount];
	         
	         //Receiving files from Server
	         for(int i = 0; i < filesCount; i++) {
	        	
	        	//Reads the files name and length first so it know how much memory to allocate
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
	         
	         //Tells the user that receiving files has been complete
	         System.out.println("File receiving complete from Server having IP: " + socket.getInetAddress().toString()+ "\nPort: " +socket.getPort());
	         
	         //Closes the Input Stream and the socket
	         dis.close();
	         socket.close();
	            
		}
		catch(IOException i) 
        { 
            System.out.println("Error: " +i); 
        }
    	
    }
    
    public static void main(String[] args) throws ClassNotFoundException, IOException {
		
		Client client = new Client(); 

	}

}

