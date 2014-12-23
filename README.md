#Excersie 2: Asynchronous method call
##Introduction
This lesson is about following skills:
* creating asynchronous methods in enterprise beans
* EJB self invocation
* concurrency

Expected result of this exercise is an EJB application which uses self invoked asynchronous method calculatePi.

##Before you start, read about...
* Asynchronous invocation: [http://docs.oracle.com/javaee/6/tutorial/doc/gkkqg.html]
* Session Context [http://docs.oracle.com/javaee/6/api/javax/ejb/SessionContext.html#getBusinessObject(java.lang.Class)]

##The exercise
To complete this exercise you need to follow these steps:
* Refactor calculatePi in `PiCalculator.java` to return promise (read Asynchronous invocation) instead of Object
* Make calculatePi asynchronous
* Annotate Session Context to be injected properly
* In piFullCalculator use sessionContext to recover business object of class `PiCalculator.java` and assign it to the variable
* Use business object for self invocation of asynchronous method calculatePi and pass 
iterations as parameter (Important note: You can't invoke private functions)
* Assign return value to existing variable piFuture

Good luck !
