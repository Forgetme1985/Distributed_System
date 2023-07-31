package collaborative.assignment;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class SimpleOperationImplementation extends UnicastRemoteObject implements SimpleOperation
{
    private List<Integer> lstValues;
    protected SimpleOperationImplementation() throws RemoteException {
        super();
       lstValues = new ArrayList<Integer>();
    }

    @Override
    public int sumOfTwoValues(int a, int b) throws RemoteException {

        return (a + b);
    }

    @Override
    public boolean operOnArray(boolean isRmove, int indexFrom, int indexTo) {
        if(!lstValues.isEmpty() &&
                indexFrom >= 0 && indexFrom <= lstValues.size() - 1 &&
                indexTo >= 0 && indexTo <= lstValues.size() - 1) {
            if (isRmove) {
                ;
            } else {
                ;
            }
        }
        return false;
    }
}
