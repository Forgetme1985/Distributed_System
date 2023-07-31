package collaborative.assignment;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SimpleOperation extends Remote {
    int sumOfTwoValues(int a, int b) throws RemoteException;
    boolean operOnArray(boolean isRmove,int indexFrom,int indexTo) throws  RemoteException;
}
