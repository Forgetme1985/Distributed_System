package assignment.calculator;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CalculatorImplementation extends UnicastRemoteObject implements Calculator {

    //Implementation for all remote methods
    private  Stack<Integer> stack;
    private List<Integer> poppedValues = new ArrayList<Integer>();
    private  boolean isPopping;
    public  CalculatorImplementation() throws RemoteException {
        super();
        stack = new Stack<>();
    }
    @Override
    public  void pushValue(int val) throws RemoteException {
        stack.push(val);
    }

    @Override
    public  void pushOperation(String operator) throws RemoteException {
        if(!isEmpty() && !isPopping)
        {
            isPopping = true;
            switch (operator)
            {
                case "min":
                    pushMinOfAllPoppedValues();
                case "max":
                    pushMaxOfAllPoppedValues();
                    break;
                case "lcm":
                    pushLCMOfAllPoppedValues();
                    break;
                case "gcd":
                    pushGCDOfAllPoppedValues();
                    break;
            }
            isPopping = false;
        }
    }

    @Override
    public  int pop() throws RemoteException {
        if(!isEmpty())
        {
           return  stack.pop();
        }
        return 0;
    }

    @Override
    public  boolean isEmpty() throws RemoteException {
        return stack.isEmpty();
    }

    @Override
    public  int delayPop(int millis) throws RemoteException {
        try {
            Thread.sleep(millis);
            if(!isEmpty())
                return stack.pop();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }
    private  void pushMinOfAllPoppedValues()
    {
        //Clear and pop all values of the stack
        poppedValues.clear();
        while (!stack.isEmpty())
        {
            poppedValues.add(stack.pop());
        }
        //Sort all popped values and push the min value int the stack
        Collections.sort(poppedValues);
        stack.push(poppedValues.get(0));
    }
    private  void pushMaxOfAllPoppedValues()
    {
        //Clear and pop all values of the stack
        poppedValues.clear();
        while (!stack.isEmpty())
        {
            poppedValues.add(stack.pop());
        }
        //Sort all popped values and push the max value int the stack
        Collections.sort(poppedValues);
        stack.push(poppedValues.get(poppedValues.size() - 1));
    }
    private  void pushLCMOfAllPoppedValues()
    {
        //Clear and pop all values of the stack
        poppedValues.clear();
        while (!stack.isEmpty())
        {
            poppedValues.add(stack.pop());
        }
        //Calculate LCM of popped values and push LCM in the stack
        int lcm = leastCommonMultiple(poppedValues.get(0),poppedValues.get(1));
        for(int i = 2; i < poppedValues.size();i++)
        {
            lcm = leastCommonMultiple(lcm,poppedValues.get(i));
        }
        stack.push(lcm);
    }
    private  int leastCommonMultiple(int a, int b)
    {
        boolean isFoundLMC = false;
        int scaleFactor = 1;
        //finding LMC
        while(isFoundLMC == false)
        {
            int product = a * scaleFactor;
            if(product % b == 0)
            {
                return product;
            }
            else
            {
                scaleFactor++;
            }
        }
        return 0;
    }
    private  void pushGCDOfAllPoppedValues()
    {
        //Clear and pop all values of the stack
        poppedValues.clear();
        while (!stack.isEmpty())
        {
            poppedValues.add(stack.pop());
        }
        //Calculate GCD of popped values and push GCD in the stack
        int gcd = greatestCommonDivisor(poppedValues.get(0),poppedValues.get(1));
        for(int i = 2; i < poppedValues.size();i++)
        {
            gcd = greatestCommonDivisor(gcd,poppedValues.get(i));
        }
        stack.push(gcd);
    }
    private  int greatestCommonDivisor(int a, int b)
    {
        boolean isFoundGCD = false;
        int scaleFactor = 1;
        while(isFoundGCD == false)
        {
            int modulo = a % scaleFactor;
            int quotient = a / scaleFactor;
            if(modulo == 0 && b % quotient == 0)
            {
                return quotient;
            }
            else
            {
                scaleFactor++;
            }
        }
        return 0;
    }
}

