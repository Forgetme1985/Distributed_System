package assignment.calculator;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorClient {
    public static  void main(String[] args)
    {
        try{
            Registry registry = LocateRegistry.getRegistry();
            Calculator calculator = (Calculator) registry.lookup("RemoteCalculator");

            //Testing min
            calculator.pushValue(1);
            calculator.pushValue(2);
            calculator.pushValue(3);
            calculator.pushOperation("min");
            System.out.println("Value Min: " + calculator.pop());
            //Testing max
            calculator.pushValue(3);
            calculator.pushValue(2);
            calculator.pushValue(1);
            calculator.pushOperation("max");
            System.out.println("Value Max: " + calculator.pop());
            //Testing lcm
            calculator.pushValue(12);
            calculator.pushValue(5);
            calculator.pushValue(9);
            calculator.pushOperation("lcm");
            System.out.println("Value LCM: " + calculator.pop());
            //Testing gcd && delay POP

            calculator.pushValue(108);
            calculator.pushValue(12);
            calculator.pushValue(20);
            calculator.pushOperation("gcd");
            System.out.println("Value GCD: " + calculator.delayPop(3000));

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
