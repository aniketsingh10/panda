import java.rmi.*;

//methods write in this called remotely
interface ServerIntf extends Remote{

	//method declaration: access_specifier return_type method_name(arguments...){ return value}
	public String stringJoin(String str1, String str2) throws RemoteException;
	public double addition(double n1,double n2) throws RemoteException;
}
//when we declare interface we cant write body of method