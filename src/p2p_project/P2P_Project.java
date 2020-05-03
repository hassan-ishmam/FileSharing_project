package p2p_project;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class P2P_Project {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scan = new Scanner(System.in);
		
		//System.out.print("1. Run Client\n2. Run Server\n\nEnter your choice:");
		//int userChoice = scan.nextInt();
		//System.out.print(userChoice);
		
		Path path = Paths.get("C:\\Users\\Hassan Ishmam\\Downloads\\Test\\lab.pdf");
		
		try {
			byte [] data = Files.readAllBytes(path);
			System.out.println(Arrays.toString(data));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
