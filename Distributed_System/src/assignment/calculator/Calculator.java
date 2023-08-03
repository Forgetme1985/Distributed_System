package assignment.calculator;
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface Calculator extends Remote
{
    //Declare all remote methods
    void pushValue(String id,int val) throws  RemoteException;
    void pushOperation(String id,String operator) throws  RemoteException;
    int pop(String id) throws RemoteException;
    boolean isEmpty(String id) throws  RemoteException;
    int delayPop(String id,int millis) throws RemoteException;
    boolean registerID(String id) throws  RemoteException;
}
