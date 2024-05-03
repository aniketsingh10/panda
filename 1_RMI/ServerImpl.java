import java.rmi.*;
import java.rmi.server.*;

public class ServerImpl extends UnicastRemoteObject //we will call the methods remotly until server is start 
	implements ServerIntf {
	
		//constructor
		public ServerImpl() throws RemoteException{
		}
        
		//declare all the metehods here
		public String stringJoin(String str1, String str2) throws RemoteException{
			String result = str1 + str2;

			return result;
		}

		public double addition(double n1,double n2) throws RemoteException{
			return n1+n2;
		}
}
//UnicastRemoteObject is a class used in Java's RMI framework to export remote objects, and in your code, it enables the ServerImpl class to be accessed remotely by clients over the network.