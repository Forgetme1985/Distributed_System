package week1.javaremoteinvocation;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RemoteClient {
    public  static  void main(String[] args)
    {
        try{
            Registry registry = LocateRegistry.getRegistry();
            RemoteUtility remoteUtility = (RemoteUtility) registry.lookup("MyUtilityRemote");
            String reply = remoteUtility.echo("Hello from RMI client");
            System.out.println("sum: " + remoteUtility.add(1,2));
            System.out.println(reply);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
