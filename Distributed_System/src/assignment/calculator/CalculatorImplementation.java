package assignment.calculator;

import com.sun.source.tree.YieldTree;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CalculatorImplementation extends UnicastRemoteObject implements Calculator {

    //Implementation for all remote methods
    private  Stack<Integer> stack;
    //private Queue<Stack<Integer>> queueOfClients = new ArrayDeque<Stack<Integer>>();

    private  boolean isPushingValue;
    private  int pushingValue;
    private   boolean isPoppingValue;
    private  int poppingValue;
    private  boolean isPushingOperation;
    private  boolean isCheckingEmpty;
    private  boolean checkingEmpty;
    private String operator;
    public  CalculatorImplementation() throws RemoteException {
        super();
        stack = new Stack<Integer>();
    }
    @Override
    public synchronized void pushValue(int val) throws RemoteException {
       stack.push(val);
    }

    @Override
    public synchronized void pushOperation(String operator) throws RemoteException {
        try{
            while (stack.isEmpty())
                wait();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        notify();
        if(!isEmpty())
        {
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
        }
    }

    @Override
    public synchronized int pop() throws RemoteException {
        try{
            while (stack.isEmpty())
                wait();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        notify();
        return  stack.pop();
    }

    @Override
    public synchronized boolean isEmpty() throws RemoteException {
        return stack.isEmpty();
    }
   /* private  void waitForPushing()
    {
        try{
            if (stack.isEmpty())
                wait();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        notify();
    }*/
    @Override
    public synchronized int delayPop(int millis) throws RemoteException {
        try {
            Thread.sleep(millis);
            return this.pop();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }
    private synchronized void pushMinOfAllPoppedValues() throws RemoteException {
        //Clear and pop all values of the stack
        List<Integer> poppedValues = new ArrayList<>();

        while (!this.isEmpty())
        {
            poppedValues.add(this.pop());
        }
        //Sort all popped values and push the min value int the stack
        Collections.sort(poppedValues);
        this.pushValue(poppedValues.get(0));

    }
    private synchronized void pushMaxOfAllPoppedValues() throws RemoteException
    {
        //Clear and pop all values of the stack
        List<Integer> poppedValues = new ArrayList<>();
        while (!this.isEmpty())
        {
            poppedValues.add(this.pop());
        }
        //Sort all popped values and push the max value int the stack
        Collections.sort(poppedValues);
        this.pushValue(poppedValues.get(poppedValues.size() - 1));

    }
    private synchronized void pushLCMOfAllPoppedValues() throws RemoteException
    {
        //Clear and pop all values of the stack
        List<Integer> poppedValues = new ArrayList<>();
        while (!this.isEmpty())
        {
            poppedValues.add(this.pop());
        }
        //Calculate LCM of popped values and push LCM in the stack
        int lcm = leastCommonMultiple(poppedValues.get(0),poppedValues.get(1));
        for(int i = 2; i < poppedValues.size();i++)
        {
            lcm = leastCommonMultiple(lcm,poppedValues.get(i));
        }
        this.pushValue(lcm);

    }
    private synchronized int leastCommonMultiple(int a, int b)
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
    private synchronized void pushGCDOfAllPoppedValues() throws  RemoteException
    {
        //Clear and pop all values of the stack
        List<Integer> poppedValues = new ArrayList<>();
        while (!this.isEmpty())
        {
            poppedValues.add(this.pop());
        }
        //Calculate GCD of popped values and push GCD in the stack
        int gcd = greatestCommonDivisor(poppedValues.get(0),poppedValues.get(1));
        for(int i = 2; i < poppedValues.size();i++)
        {
            gcd = greatestCommonDivisor(gcd,poppedValues.get(i));
        }
        this.pushValue(gcd);

    }
    private synchronized int greatestCommonDivisor(int a, int b)
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

   /* @Override
    public synchronized void run() {

        Stack<Integer> stack = new Stack<>();
        while (true)
        {
            try{
                Thread.sleep(1000);
                System.out.println("isPushingValue: " + isPushingValue);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            if(isPushingValue)
            {
                System.out.println("isPushingValue");
                stack.push(pushingValue);
                //isPushingValue = false;
            }
            else if(isPoppingValue)
            {
                System.out.println("isPoppingValue");
                if(!stack.isEmpty())
                {
                    poppingValue = stack.pop();
                    isPoppingValue = false;
                }
            }
            else if(isCheckingEmpty)
            {
                checkingEmpty = stack.isEmpty();
                isCheckingEmpty = false;
            }
            else if(isPushingOperation)
            {
                try {
                    switch (operator) {
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
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                isPushingOperation = false;
            }
            notify();
        }
    }*/
}

