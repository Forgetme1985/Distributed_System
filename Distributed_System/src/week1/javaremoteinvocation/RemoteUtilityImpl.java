package week1.javaremoteinvocation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.ZonedDateTime;

public class RemoteUtilityImpl extends UnicastRemoteObject implements RemoteUtility {
    public RemoteUtilityImpl() throws RemoteException {
    }

    @Override
    public String echo(String msg) throws RemoteException {
        return msg;
    }

    @Override
    public ZonedDateTime getServertime() throws RemoteException {
        return ZonedDateTime.now();
    }

    @Override
    public int add(int n1, int n2) throws RemoteException {
        return (n1 + n2);
    }
}
