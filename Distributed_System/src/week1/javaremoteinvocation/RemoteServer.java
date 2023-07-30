package week1.javaremoteinvocation;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RemoteServer {
    public  static void main(String[] args)
    {
        try
        {
            RemoteUtilityImpl remoteUtility = new RemoteUtilityImpl();
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("MyUtilityRemote",remoteUtility);
            System.out.println("Server is ready");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
