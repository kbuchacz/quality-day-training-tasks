#Excersie 1: Create EJB
##Introduction
This lesson is about following skills:
* annotating classes as EJB
* writing custom functions
* creating startup EJB

Expected result of this exercise is an application which can calculate Pi number to the given precision in JBoss container.

##Before you start, read about...
* EJB types [http://www.careerride.com/EJB-types.aspx]
* startup EJB: [http://www.mastertheboss.com/javaee/ejb-3/how-to-create-an-ejb-startup-service]

##The exercise
To complete this exercise you need to follow these steps:
* `PiCalculator.java` should be an EJB with proper annotation for the purpose.
* Rewrite piLiteCalculator function inside `PiCalculator.java` to calculate pi using already prepared `calculatePi` function
and limit it to 100000 iterations(for greater function parameter it should return null)
* Rewrite piFullCalculator function inside `PiCalculator.java` to calculate pi using already prepared `calculatePi` function
* Annotate `Main.java` as startup EJB
* Main function in `Main.java` should be called after construction of Main EJB
* After startup in `Main.java` variable piResult should be instantiated with result of piCalculator.piFullCalculator(1000000)

Good luck !
