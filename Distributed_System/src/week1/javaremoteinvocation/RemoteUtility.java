package week1.javaremoteinvocation;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.ZonedDateTime;

public interface RemoteUtility extends Remote {
    String echo(String msg) throws RemoteException;
    ZonedDateTime getServertime() throws  RemoteException;
    int add(int n1,int n2) throws  RemoteException;
}
