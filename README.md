# Distributed_System
1) Explanation and the steps to run the system:

In order to run the system, in the terminal run the commands below:

Run registry: rmiregistry 

Run server: java assignment.calculator.CalculatorServer

Test client: java assignment.calculator.CalculatorClient testing.txt client 1 
			or java assignment.calculator.CalculatorClient testing.txt client 0


we have three parameters for the client program: the first one is the input file with some combinations of data for testing. The second one is the the ID of the client that is used to register with server and server will provide one stack for the client. The third paramenter to decide whether to use the same stack on server, 1 is "using the same stack" otherwise 0 is "using differnt stacks"

e.g. inside of the testing.txt file:

1,2,3,min,0

6,5,4,max,0

12,5,9,lcm,0

108,12,20,gcd,3000

The values are before the opeartors (min,max,lcm,gcd) that will be pushed into the server stack

The value is after the operators (min,max,lcm,gcd) that is the delay in milliseconds for popping operations 

note: in order to test many clients at the same time, we can run: 
java assignment.calculator.CalculatorClient testing1.txt client1 1 & java assignment.calculator.CalculatorClient testing2.txt client2  1 & java assignment.calculator.CalculatorClient testing3.txt client3 1

2) There are some already shell files for testing:
   
RMIRegistry

CalculatorServer

CalculatorClient

In order to run shell files in the mac terminal:

Testing case: all clients using the same stack

open the new terminal: sh RMIRegistry.sh

open the new terminal: sh CalculatorServer.sh

open the new terminal: sh CalculatorClient_SameStack.sh

note: CalculatorClient_SameStack.sh file is the script to run for testing 4 clients.
IDs: client1, client2, client3 and client4; also with 4 testings file: testing1.txt
testing2.txt, testing3.txt, testing4.txt using the same stack 

Tesing case: all clients using the different stacks

open the new terminal: sh RMIRegistry.sh

open the new terminal: sh CalculatorServer.sh

open the new terminal: sh CalculatorClient_DifferentStacks.sh

Testing case:  the client 1 & the client 2 using the same stack but the client 3 and the client 4 using their own stacks

open the new terminal: sh RMIRegistry.sh

open the new terminal: sh CalculatorServer.sh

open the new terminal: sh CalculatorClient_Combination.sh



