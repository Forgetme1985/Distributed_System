package assignment.calculator;
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface Calculator extends Remote
{
    //Declare all remote methods
    void pushValue(String id,int val,boolean useSameStack) throws  RemoteException;
    void pushOperation(String id,String operator,boolean useSameStack) throws  RemoteException;
    int pop(String id,boolean useSameStack) throws RemoteException;
    boolean isEmpty(String id,boolean useSameStack) throws  RemoteException;
    int delayPop(String id,int millis,boolean useSameStack) throws RemoteException;
    boolean registerID(String id) throws  RemoteException;
}
