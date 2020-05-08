package p2p_project;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SimpleClient {
	
	private Socket socket            = null; 
    private DataInputStream  input   = null; 
    private DataOutputStream out     = null; 
  
    // constructor to put ip address and port 
    public SimpleClient(String address, int port) throws IOException, ClassNotFoundException
    { 
		try {
		        	
		        	//Transfers only specified file types	v1.0
		        	
		        	socket = new Socket(address, port); 
		        	//socket = new Socket("localhost", 4333);
		            System.out.println("Connected"); 
		            
		  
		            String directory = "C:\\Users\\Hassan\\Downloads\\Simple Test 1\\";

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
		                System.out.println("Received file: " +fileName);

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
    


	public static void main(String[] args) throws ClassNotFoundException, IOException {
		
		System.out.println("---Starting Client---");
		SimpleClient client = new SimpleClient("192.168.1.10", 5000); 

	}

}
