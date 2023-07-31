package assignment.calculator;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorServer {
    public static void main(String args[])
    {
        try {
            //Create server and bind to registry
            CalculatorImplementation calculatorImplementation = new CalculatorImplementation();
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("RemoteCalculator",calculatorImplementation);
            System.out.println("Server is ready");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}
