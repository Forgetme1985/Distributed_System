package assignment.calculator;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;


public class CalculatorImplementation extends UnicastRemoteObject implements Calculator {

    //Implementation for all remote methods
    private HashMap<String,Stack<Integer>> stackHashMap;

    //constructor
    public  CalculatorImplementation() throws RemoteException {
        super();
        stackHashMap = new HashMap<String,Stack<Integer>>();
    }
    /*pushValue method
    * inputs: the id of the client to identify its stack on the server
    * and the value that would be pushed in its stack
    * */
    @Override
    public synchronized void pushValue(String id,int val) throws RemoteException {
        if(!isEmpty(id))
        {
            stackHashMap.get(id).push(val);
        }
    }
    /*
    *pushOperation method
    *the inputs: the id of the client to identify its stack on the server and the operator that would be pushed in its stack
    *the output: popping all current values and pushing the min, the max, the lcm or the gcd in its stack
     */

    @Override
    public synchronized void pushOperation(String id,String operator) throws RemoteException
    {
        if(!isEmpty(id))
        {
            System.out.println(id);
            switch (operator)
            {
                case "min":
                    //popping all values and push the min value of all popped values
                    pushValue(id,pushMinOfAllPoppedValues(id));
                    break;
                case "max":
                    //popping all values and push the max value of all popped values
                    pushValue(id,pushMaxOfAllPoppedValues(id));
                    break;
                case "lcm":
                    //popping all values and push the lcm value of all popped values
                    pushValue(id,pushLCMOfAllPoppedValues(id));
                    break;
                case "gcd":
                    //popping all values and push the gcd value of all popped values
                    pushValue(id,pushGCDOfAllPoppedValues(id));
                    break;
            }
        }
    }
    /*
    * pop method
    * the input: the id of the client to identify its stack on the server
    * the output: popping the value
    * */
    @Override
    public synchronized int pop(String id) throws RemoteException {
        if(!isEmpty(id))
            return  stackHashMap.get(id).pop();
        return Integer.MIN_VALUE;// stack is empty
    }
    /*
     * isEmpty method
     * the input: the id of the client to identify its stack on the server
     * the output: checking its stack on server whether is empty
     * */
    @Override
    public synchronized boolean isEmpty(String id) throws RemoteException {
        if(!stackHashMap.isEmpty() && !stackHashMap.get(id).isEmpty())
            return stackHashMap.get(id).isEmpty();
        return  false;
    }
    /*
    * delayPop function
    * the input: the id of the client to identify its stack on the server
    * and the delay time in ms for popping operation
    * the output: delay popping in ms
    * */
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
    /*
    *registerID method
    * the input: the id of the client that will be registered with the server
    * and the client will be supplied its own stack
    * the output: it is registered successfully whether or not
    * */
    @Override
    public boolean registerID(String id) throws RemoteException
    {
        try {
            stackHashMap.put(id, new Stack<Integer>());
            System.out.println("Register successfully for " + id);
            return  true;
        }
        catch (Exception e)
        {
            System.out.println("Unsuccessful Registration for " + id + " because ");
            e.printStackTrace();
            return  false;
        }

    }
    /*
    *pushMinOfAllPoppedValues method
    *this method is the sub method of the min operation
    * the input: the id of the client to identify its stack on the server
    * the output: popping all values of its stack, sorting all values
    * and return the min value of all popped values
    *
    * */
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
    /*
     *pushMaxOfAllPoppedValues method
     *this method is the sub method of the max operation
     * the input: the id of the client to identify its stack on the server
     * the output: popping all values of its stack, sorting all values
     * and return the max value of all popped values
     *
     * */
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
    /*
     *pushLCMOfAllPoppedValues method
     * this method is the sub method of the LCM operation
     * the input: the id of the client to identify its stack on the server
     * the output: popping all values of its stack, calculating for the least
     * common multiple (LCM) and return the LCM value
     * */
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
    /*
     *leastCommonMultiple method
     *
     * the input: the two values a and b
     * the output: calculating and return the least common multiple value
     * of the two values a and b
     *
     * */
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
    /*pushGCDOfAllPoppedValues method
     * this method is the sub method of the GCD operation
     * the input: the id of the client to identify its stack on the server
     * the output: popping all values of its stack, calculating for the greatest
     * common divisor (GCD) and return the GCD value
     * */
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
    /*
     *greatestCommonDivisor method
     *
     * the input: the two values a and b
     * the output: calculating and return the greatest common divisor value
     * of the two values a and b
     *
     * */
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

