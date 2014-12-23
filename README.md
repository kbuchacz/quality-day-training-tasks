#Excercise 3: Roles and permissions
##Introduction
This lesson is about following skills:
* security
* setting custom roles per function

Expected result of this exercise is an application which allows only specific roles to use piCalculator functions.

##Before you start, read about...
* Java roles [http://docs.oracle.com/javaee/6/tutorial/doc/gkbsz.html]

##The exercise
To complete this exercise you need to follow these steps:
* In `PiCalculator.java` annotate piFullCalculator function to allow it to be used only as Roles.ADMIN
* Annotate piLiteCalculator function to allow it to be used as Roles.ADMIN or Roles.User
* Annotate calculatePi to allow it to be used by previous functions
* Make sure to check and understand `Main.java` function calls

Good luck !
