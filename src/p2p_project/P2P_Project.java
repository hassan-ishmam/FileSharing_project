package p2p_project;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

//import com.sun.java.util.jar.pack.Package.File;

public class P2P_Project {

	public static void main(String[] args) throws ClassNotFoundException, IOException{
		// TODO Auto-generated method stub
		
		Scanner scan = new Scanner(System.in);
		
		
		
		System.out.print("1. Run Client\n2. Run Server\n\nEnter your choice:");
		int userChoice = scan.nextInt();
		System.out.println();
		
		if(userChoice == 1) {
			
			System.out.println("---Starting Client---");
			Client client = new Client(); 
		}
		
		else if(userChoice == 2) {
			System.out.println("---Starting Server---");
			Server server = new Server();
		}
			
		
		//Path path = Paths.get("C:\\Users\\Hassan Ishmam\\Downloads\\Test\\lab.pdf");
		/*
		try {
			byte [] data = Files.readAllBytes(path);
			System.out.println(Arrays.toString(data));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		/*
		File folder = new File("C:\\Users\\Hassan Ishmam\\Downloads\\Test");
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {
		    System.out.println("File " + listOfFiles[i].getName());
		  } else if (listOfFiles[i].isDirectory()) {
		    System.out.println("Directory " + listOfFiles[i].getName());
		  }
		}
		
		*/

	}

}
