package collaborative.assignment;



import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SimpleOperationServer {
    public  static  void main(String[] args)
    {
        try{
            SimpleOperation simpleOperation = new SimpleOperationImplementation();
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("SimpleOperation",simpleOperation);
            System.out.println("Server is ready");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
