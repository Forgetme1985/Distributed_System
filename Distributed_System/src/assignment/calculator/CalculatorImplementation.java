package assignment.calculator;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;


public class CalculatorImplementation extends UnicastRemoteObject implements Calculator {

    //Implementation for all remote methods
    private HashMap<String,Stack<Integer>> stackHashMap;

    public  CalculatorImplementation() throws RemoteException {
        super();
        stackHashMap = new HashMap<String,Stack<Integer>>();
    }
    @Override
    public synchronized void pushValue(String id,int val) throws RemoteException {
        if(!isEmpty(id))
        {
            stackHashMap.get(id).push(val);
        }
    }

    @Override
    public synchronized void pushOperation(String id,String operator) throws RemoteException
    {
        if(!isEmpty(id))
        {
            System.out.println(id);
            switch (operator)
            {
                case "min":
                    pushValue(id,pushMinOfAllPoppedValues(id));
                    break;
                case "max":
                    pushValue(id,pushMaxOfAllPoppedValues(id));
                    break;
                case "lcm":
                    pushValue(id,pushLCMOfAllPoppedValues(id));
                    break;
                case "gcd":
                    pushValue(id,pushGCDOfAllPoppedValues(id));
                    break;
            }
        }
    }

    @Override
    public synchronized int pop(String id) throws RemoteException {
        if(!isEmpty(id))
            return  stackHashMap.get(id).pop();
        return Integer.MIN_VALUE;// stack is empty
    }

    @Override
    public synchronized boolean isEmpty(String id) throws RemoteException {
        if(!stackHashMap.isEmpty() && !stackHashMap.get(id).isEmpty())
            return stackHashMap.get(id).isEmpty();
        return  false;
    }
    @Override
    public synchronized int delayPop(String id,int millis) throws RemoteException {
        try {
            Thread.sleep(millis);
            return this.pop(id);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean registerID(String id) throws RemoteException
    {
        try {
            stackHashMap.put(id, new Stack<Integer>());
            System.out.println("Register successfully");
            return  true;
        }
        catch (Exception e)
        {
            System.out.println("Unsuccessful Registration for client " + id + " because ");
            e.printStackTrace();
            return  false;
        }

    }

    private synchronized int pushMinOfAllPoppedValues(String id) throws RemoteException {
        //Clear and pop all values of the stack
        List<Integer> poppedValues = new ArrayList<>();

            while (!stackHashMap.get(id).isEmpty()) {
                poppedValues.add(stackHashMap.get(id).pop());
            }
            for(int i = 0; i < poppedValues.size(); i++)
            {
                System.out.println("Popped values: " + poppedValues.get(i));
            }

            //Sort all popped values and push the min value int the stack
            Collections.sort(poppedValues);
            int min = poppedValues.get(0);
            System.out.println("min:" + min);
            return min;
    }
    private synchronized int pushMaxOfAllPoppedValues(String id) throws RemoteException
    {
        //Clear and pop all values of the stack
        List<Integer> poppedValues = new ArrayList<>();
        while (!stackHashMap.get(id).isEmpty())
        {
            poppedValues.add(stackHashMap.get(id).pop());
        }
        for(int i = 0; i < poppedValues.size(); i++)
        {
            System.out.println("Popped values: " + poppedValues.get(i));
        }
        //Sort all popped values and push the max value int the stack
        Collections.sort(poppedValues);
        int max = poppedValues.get(poppedValues.size() - 1);
        System.out.println("max:" + max);
        return max;

    }
    private synchronized int pushLCMOfAllPoppedValues(String id) throws RemoteException
    {
        //Clear and pop all values of the stack
        List<Integer> poppedValues = new ArrayList<>();
        while (!stackHashMap.get(id).isEmpty())
        {
            poppedValues.add(stackHashMap.get(id).pop());
        }
        for(int i = 0; i < poppedValues.size(); i++)
        {
            System.out.println("Popped values: " + poppedValues.get(i));
        }
        //Calculate LCM of popped values and push LCM in the stack
            int lcm = leastCommonMultiple(poppedValues.get(0), poppedValues.get(1));
            for (int i = 2; i < poppedValues.size(); i++) {
                lcm = leastCommonMultiple(lcm, poppedValues.get(i));
            }
            System.out.println("LCM: " + lcm);
            return lcm;

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
    private synchronized int pushGCDOfAllPoppedValues(String id) throws  RemoteException
    {
        //Clear and pop all values of the stack
        List<Integer> poppedValues = new ArrayList<>();
        while (!stackHashMap.get(id).isEmpty())
        {
            poppedValues.add(stackHashMap.get(id).pop());
        }
        for(int i = 0; i < poppedValues.size(); i++)
        {
            System.out.println("Popped values: " + poppedValues.get(i));
        }
        //Calculate GCD of popped values and push GCD in the stack
        int gcd = greatestCommonDivisor(poppedValues.get(0),poppedValues.get(1));
        for(int i = 2; i < poppedValues.size();i++)
        {
            gcd = greatestCommonDivisor(gcd,poppedValues.get(i));
        }
        System.out.println("GCD: " + gcd);
        return gcd;

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
}

