package assignment.calculator;

import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class CalculatorClient {
    private  Calculator calculator;
    public static  void main(String[] args)
    {
        try{
            //look up server through registry
            Registry registry = LocateRegistry.getRegistry();
            Calculator calculator = (Calculator) registry.lookup("RemoteCalculator");
            //testing inputs from the *.txt files
            if(calculator.registerID(args[1])) {
                int isUseTheSameStack = Integer.parseInt(args[2]);
                if(isUseTheSameStack == 1) {
                    System.out.println("Testing using the same stack");
                    testingCalculator(calculator, args[0], args[1], true);
                }
                else
                {
                     System.out.println("Testing using the different stacks");
                     testingCalculator(calculator,args[0],args[1],false);
                }

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private static  void testingCalculator(Calculator calculator,String arg0,String arg1,boolean useSameStack) {
        try{
            File testingFile = new File(arg0);
            Scanner readingScanner = new Scanner(testingFile);
            while (readingScanner.hasNextLine()) {
                String[] lines = readingScanner.nextLine().split(",");
                int delay = Integer.parseInt(lines[lines.length - 1]);
                for (int i = 0; i < lines.length - 1; i++) {
                    if (lines[i].compareTo("min") == 0) {
                        calculator.pushOperation(arg1, lines[i],useSameStack);
                        if (delay > 0) {
                            System.out.println(arg1 + ": Server Stack delays popping the min value in " + delay + " ms:" + calculator.delayPop(arg1, delay,useSameStack));
                        } else {
                            System.out.println(arg1 + ": Server Stack pops the min value: " + calculator.pop(arg1,useSameStack));
                        }
                    } else if (lines[i].compareTo("max") == 0) {
                        calculator.pushOperation(arg1, lines[i],useSameStack);
                        if (delay > 0) {
                            System.out.println(arg1 + ": Server Stack delays popping the max value in " + delay + " ms:" + calculator.delayPop(arg1, delay,useSameStack));
                        } else {
                            System.out.println(arg1 + ": Server Stack pops the max value: " + calculator.pop(arg1,useSameStack));
                        }
                    } else if (lines[i].compareTo("lcm") == 0) {
                        calculator.pushOperation(arg1, lines[i],useSameStack);
                        if (delay > 0) {
                            System.out.println(arg1 +": Server Stack delays popping the lcm value in " + delay + " ms:" + calculator.delayPop(arg1, delay,useSameStack));
                        } else {
                            System.out.println(arg1+": Server Stack pops the lcm value: " + calculator.pop(arg1,useSameStack));
                        }
                    } else if (lines[i].compareTo("gcd") == 0) {
                        calculator.pushOperation(arg1, lines[i],useSameStack);
                        if (delay > 0) {
                            System.out.println(arg1 +": Server Stack delays popping the gcd value in " + delay + " ms:" + calculator.delayPop(arg1, delay,useSameStack));
                        } else {
                            System.out.println(arg1+": Server Stack pops the gcd value: " + calculator.pop(arg1,useSameStack));
                        }
                    } else {
                        calculator.pushValue(arg1, Integer.parseInt(lines[i]),useSameStack);
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
