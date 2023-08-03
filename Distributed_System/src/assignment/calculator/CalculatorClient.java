package assignment.calculator;

import java.io.File;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class CalculatorClient {
    public static  void main(String[] args)
    {
        try{
            //look up server through registry
            Registry registry = LocateRegistry.getRegistry();
            Calculator calculator = (Calculator) registry.lookup("RemoteCalculator");
            //testing inputs from the testing.txt files
            if(calculator.registerID(args[1])) {
                File testingFile = new File(args[0]);
                Scanner readingScanner = new Scanner(testingFile);
                while (readingScanner.hasNextLine()) {
                    String[] lines = readingScanner.nextLine().split(",");
                    int delay = Integer.parseInt(lines[lines.length - 1]);
                    for (int i = 0; i < lines.length - 1; i++) {
                        if (lines[i].compareTo("min") == 0) {
                            calculator.pushOperation(args[1], lines[i]);
                            if (delay > 0) {
                                System.out.println(args[1] + ": Server Stack delays popping the min value in " + delay + " ms:" + calculator.delayPop(args[1], delay));
                            } else {
                                System.out.println(args[1] + ": Server Stack pops the min value: " + calculator.pop(args[1]));
                            }
                        } else if (lines[i].compareTo("max") == 0) {
                            calculator.pushOperation(args[1], lines[i]);
                            if (delay > 0) {
                                System.out.println(args[1] + ": Server Stack delays popping the max value in " + delay + " ms:" + calculator.delayPop(args[1], delay));
                            } else {
                                System.out.println(args[1] + ": Server Stack pops the max value: " + calculator.pop(args[1]));
                            }
                        } else if (lines[i].compareTo("lcm") == 0) {
                            calculator.pushOperation(args[1], lines[i]);
                            if (delay > 0) {
                                System.out.println(args[1] +": Server Stack delays popping the lcm value in " + delay + " ms:" + calculator.delayPop(args[1], delay));
                            } else {
                                System.out.println(args[1] +": Server Stack pops the lcm value: " + calculator.pop(args[1]));
                            }
                        } else if (lines[i].compareTo("gcd") == 0) {
                            calculator.pushOperation(args[1], lines[i]);
                            if (delay > 0) {
                                System.out.println(args[1] +": Server Stack delays popping the gcd value in " + delay + " ms:" + calculator.delayPop(args[1], delay));
                            } else {
                                System.out.println(args[1] +": Server Stack pops the gcd value: " + calculator.pop(args[1]));
                            }
                        } else {
                            calculator.pushValue(args[1], Integer.parseInt(lines[i]));
                        }

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
