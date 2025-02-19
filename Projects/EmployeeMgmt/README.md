# BadEmployManagementSystem

_This might be the worst piece of java code I have ever seen._

It is a simple employee management system that is supposed to keep track of employees and their salaries. 
It is a console application that allows the user to add, remove, and list employees. 
The user can also give a raise to an employee.

And yet, the code is so bad due to the use of classes in ways classses should not be used.
Each of these classes should be a mere method in a larger, single class.

The information should be stored in an object of class _Employee_ and the methods should be in a class _EmployeeManagementSystem_.
_Employee_ should have a name and a salary, and _EmployeeManagementSystem_ should have a list of employees and methods to add, remove, list, and give a raise to an employee.

And I cannot fathom why it's important for us to track an employee's father's name. That's just weird.

## How to run the program

1. Compile it by running `javac *.java` in a terminal.
2. Run the compiled class by running `java BadEmployManagementSystem`.

## What to do

1. Refactor the code so that it uses classes in a more appropriate way.
2. Remove the father's name from the employee class.
3. Add a method to give a raise to an employee.
4. Add a method to list all employees and their salaries.


NOTICE that I am not calling the possible re-work as "GoodEmployManagementSystem" because it is still a bad piece of code. 
**It is just less bad than before.**
So we're going with calling it "BetterEmployManagementSystem" instead, such is _smelly code_ (Yeas, code **can smell**, and I want you to puzzle it out).
