# FindWord 

An example of why sometimes it is okay to use static in your classes.

Many times beginners have trouble understanding the concept of static in Java.
It's bad that `class` get used to define a class but cannot be used as modifiers for methods or variables.
So `static` is used to define methods and variables that belong to the class itself, not to instances of the class.

And since the `instance` methods and variables are the default, it's easy to confuse why static methods and variables exist.

This project is a simple example of why static methods are useful.

## BadFindWord and GoodFindWord.

What are the differences?

You can compile and run these sources by

`javac *.java` in a terminal and then run the compiled class. 
Make sure you are in the same directory as the source files. 
(do a `pwd` to check, or do an `ls` to ensure that the java and class files are in the same directory)

## BadFindWord

Run `javac BadFindWord.java` and then `java BadFindWord` to see the output.

## GoodFindWord

Run `javac GoodFindWord.java` and then `java GoodFindWord` to see the output.

