package collaborative.assignment;



import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SimpleOperationClient {
    public  static  void main(String[] args)
    {
        try {
            Registry registry = LocateRegistry.getRegistry();
            SimpleOperation simpleOperation = (SimpleOperation) registry.lookup("SimpleOperation");
            System.out.println(simpleOperation.sumOfTwoValues(3,3));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
