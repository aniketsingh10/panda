import java.util.*;

public class TokenRing {
    public static void main(String args[]){

        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the no of nodes : ");
        int n=sc.nextInt();
        System.out.println("Ring formed is as below : ");
        for(int i=0;i<n;i++){
            System.out.print(i +" ");
        }
        System.out.println("0");

        int choice=0;
        do{
            System.out.print("Enter Sender node : ");
            int sender=sc.nextInt();

            System.out.print("Enter the Receiver node : ");
            int receiver=sc.nextInt();

            System.out.print("Enter Data to Send : ");
            String data=sc.next();

            int token=0;
            System.out.print("token is passing : ");
            for(int i=token;i<sender;i++){
                System.out.print(i+"->"+" ");
            }

            System.out.println(sender);
            System.out.println("Sender "+sender+" is sending data : " +data);
            for(int i=sender;i!=receiver;i=(i+1)%n){
                System.out.println("Data is forwarded "+data+" "+"by"+" "+ +i);
            }

            System.out.println("Receiver "+receiver+" received "+"Data : "+data);
            token=sender;
            System.out.println("Do you want to send data again if YES press 1 if No press 0");
            choice=sc.nextInt();
            
        }
        while(choice==1);

    }
}



// Running commands

// javac TokenRing.java 
// java TokenRing
