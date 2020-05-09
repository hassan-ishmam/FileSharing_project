package p2p_project;

import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class Server {
	
	//public static ArrayList<FileInfo> globalArray = new ArrayList<FileInfo>();
	
	//initialize socket 
    private Socket          socket   = null; 
    private ServerSocket    server   = null; 
  
    //Constructor 
    public Server() throws ClassNotFoundException 
    { 
    	
    	try {
    		//Listening port
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
    			
    			//Accepts connection from client
    			socket = server.accept();
    				
    		}
    		catch(IOException e)
    		{
    			System.out.println("I/O error: " +e);
    		}
    		
    		//Starts a new thread for each client
    		new ClientHandler(socket).start();
    	}
    	
       
    }
    
    //This is the main method
    public static void main(String[] args) throws ClassNotFoundException {
		System.out.println("---Starting Server---");
		Server server = new Server();
	}
    
}

//Handles Clients requests using thread

class ClientHandler extends Thread {
	protected Socket socket;
	//ArrayList<FileInfo> globalArray;
	
	//Directory of the folder where the files are stored
	public String directory = "C:\\Users\\Hassan Ishmam\\Downloads\\Test2\\";
	
	//Constructor
	public ClientHandler(Socket clientSocket)
	{
		this.socket=clientSocket;
		//this.globalArray=globalArray;
	}
	
	public void run() {
		
		//Receives and stores the files in the server
		receiveFileFromClient();
		
		
		try {
			sendFileToClient();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void receiveFileFromClient() {
		
		try {
			
			//Print out the clients address
	         System.out.println("Connected to client \nIP: "+socket.getInetAddress().toString()+ "\nPort: " +socket.getPort());
	         
	         //Declaring input streams for reading data sent from client over socket
			 BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
	         DataInputStream dis = new DataInputStream(bis);
	         
	         //Each peer sends the number of files in their directory
	         //So the receiver knows how many times to run the loop for receiving files
	         int filesCount = dis.readInt();
	         
	         //Declares an Array of files
	         File[] files = new File[filesCount];
	         
	         //Receiving files from client
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
	         System.out.println("File receiving complete from client having IP: " + socket.getInetAddress().toString()+ "\nPort: " +socket.getPort());
	         
	         //Closes the Input Stream and the socket
	         dis.close();
	         socket.close();
	            
		}
		catch(IOException i) 
        { 
            System.out.println(i); 
        }
	}
	
	public void sendFileToClient() throws IOException {
		
		File[] files = new File(directory).listFiles();
        
        //Gets the output stream from Socket
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
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
        dos.close();
        
        System.out.println("Files Sent!");
        
        //Closes Socket
        socket.close();
		
	}

}
