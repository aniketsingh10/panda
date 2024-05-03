import java.rmi.*;
import java.util.Scanner;

public class Client{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		try{
			String serverURL = "rmi://localhost/Server"; // URL of the remote server
			ServerIntf serverIntf = (ServerIntf) Naming.lookup(serverURL);
			
			System.out.print("Enter First String : ");
			String str1 = sc.nextLine();
			
			System.out.print("Enter Second String : ");
			String str2 = sc.nextLine();
			
			
		    System.out.print("Enter First Number : ");
			double n1= sc.nextDouble();
			
			System.out.print("Enter Second Number : ");
			double n2= sc.nextDouble();

			System.out.println("--------------- Results ---------------");
			System.out.println("Strings after joining is : " + serverIntf.stringJoin(str1, str2));
			System.out.println("Addition of Number is : "+serverIntf.addition(n1,n2));
			
		}catch(Exception e){
			System.out.println("Exception occurred at Client!" + e.getMessage());
		}
		
	}
}

// execution commands
// javac *.java (on 1st Terminal)
// rmiregistry 
// java Server (on 2nd Terminal)
// java Client (on 3rd Terminal)