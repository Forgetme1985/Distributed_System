package assignment.calculator;

import java.io.File;
import java.nio.file.Path;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CalculatorClient {
    public static  void main(String[] args)
    {
        try{
            //look up server through registry
            Registry registry = LocateRegistry.getRegistry();
            Calculator calculator = (Calculator) registry.lookup("RemoteCalculator");
            //testing inputs from the testing.txt files
            File testingFile = new File(args[0]);
            Scanner readingScanner = new Scanner(testingFile);
            while (readingScanner.hasNextLine())
            {
                 String[] lines = readingScanner.nextLine().split(",");
                 int delay = Integer.parseInt(lines[lines.length - 1]);
                 for(int i = 0; i < lines.length - 1;i++)
                 {
                    if(lines[i].compareTo("min") == 0)
                    {
                        System.out.println("min: ");
                        calculator.pushOperation(lines[i]);
                        if(delay > 0)
                        {
                            System.out.println("Server Stack delays popping the min value in " + args[1] + " ms:");
                            System.out.println(calculator.delayPop(delay));
                        }
                        else
                        {
                            System.out.println("Server Stack pops the min value: " );
                            System.out.println(calculator.pop());
                        }
                    }
                    else if(lines[i].compareTo("max") == 0)
                    {
                        calculator.pushOperation(lines[i]);
                        if(delay > 0)
                        {
                            System.out.println("Server Stack delays popping the max value in " + args[1] + " ms:");
                            System.out.println(calculator.delayPop(delay));
                        }
                        else
                        {
                            System.out.println("Server Stack pops the max value: " );
                             System.out.println(calculator.pop());
                        }
                    }
                    else if(lines[i].compareTo("lcm") == 0)
                    {
                        calculator.pushOperation(lines[i]);
                        if(delay > 0)
                        {
                            System.out.println("Server Stack delays popping the LCM value in " + args[1] + " ms:");
                            System.out.println(calculator.delayPop(delay));
                        }
                        else
                        {
                            System.out.println("Server Stack pops the LCM value: " );
                            System.out.println(calculator.pop());
                        }
                    }
                    else if(lines[i].compareTo("gcd") == 0)
                    {
                        calculator.pushOperation(lines[i]);
                        if(delay > 0)
                        {
                            System.out.println("Server Stack delays popping the GCD value in " + delay + " ms:");
                            System.out.println(calculator.delayPop(delay));
                        }
                        else
                        {
                            System.out.println("Server Stack pops the GCD value: " );
                            System.out.println(calculator.pop());
                        }
                    }
                    else
                    {
                        calculator.pushValue(Integer.parseInt(lines[i]));
                    }

                 }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
