# CSCI 1302 - Lists v2018.fa

![Unrelated image from page 177 of "Punch" (1841)](https://i.imgur.com/7TdqL1v.jpg)

**FRI 2018-10-12 (OCT 12) @ 11:55 PM EST**.

This document contains the description for the Lists project
assigned to the students in the Fall 2018 CSCI 1302 classes
at the University of Georgia.

**Read the entirety of this file before beginning your project.**

**Seriously. Read this entire file *before* starting.**

## Table of Contents

* [Academic Honesty](#academic-honesty)
* [Updates](#updates)
* [Project Description](#project-description)
* [Project Requirements & Grading](#project-requirements--grading)
  * [Functional Requirements](#functional-requirements)
  * [Non-Functional Requirements](#non-functional-requirements)
* [How to Download the Project](#how-to-download-the-project)
* [Submission Instructions](#submission-instructions)
* [Appendix - FAQ](#appendix---faq)

## Academic Honesty

You agree to the Academic Honesty policy as outlined in the course syllabus. 
In accordance with this notice, I must caution you **not** to 
fork this repository on GitHub if you have an account. Doing so will more than
likely make your copy of the project publicly visible. Please follow the 
instructions contained in the 
[How to Download the Project](#how-to-download-the-project)
section below in order to do your development on nike. Furthermore, you must adhere
 to the copyright notice and licensing information at the bottom of this document.

## Updates

Updates will be posted here.
If there has been an update and you have already cloned the project to Nike, 
then you can update your copy of the project using the <code>$ git pull</code>
command while inside of your project directory.

## Project Description

In this project, you will be providing two different implementations of
a generic ```GenList``` interface, which defines different operations that one
should be able to do with a *generic list*. A list is simply an object that represents 
an ordered collection of elements. The list implementation can decide how the
elements are stored internally so long as users are able to interact with
those elements via the methods defined in the interface. In this way, a list
is an example of an *abstract data type* (ADT). To put it another way: while 
the implementor needs to undertand the specific details of the implementation
(in order to write the code to make it happen), the user of a list does not. 
The users simply interact with objects of the list implementation through
the methods defined in the interface. 

Each implementation will be a generic class with specific functional
and non-functional requirements. These classes need to implement ```GenList<T>```
and override its methods to provide their functionality. 

In addition to the Javadoc comments in 
<a href="src/main/java/cs1302/lists/GenList.java">```cs1302.lists.GenList```</a>,
the HTML documention for the ```GenList<T>``` interface is available 
<a href="http://cobweb.cs.uga.edu/~mec/cs1302/lists-apidocs/cs1302/lists/GenList.html">here</a>.
Implementors should make sure that each method functions or behaves as described
by its documentation, except in cases where a functional requirement changes
the behavior of the method. Whenever an overridden method's behavor differs from
how it's described in the ```GenList<T>``` interface, this new behavor should be 
documented using Javadoc. 

Implementors are always free to implement additional methods in addition
to the ones defined by the interface. However, they should not assume that
users will use them (even if declared with ```public``` visibility), since
they are not defined in the interface. These additional methods may help
avoid redundancy and promote code reuse within an implementation.

### Suggested Reading

* LDC Ch. 9 (Polymorhism)
* [Java Tutorial: Generics](https://docs.oracle.com/javase/tutorial/java/generics/index.html)

### Learning Outcomes

* Implement generic classes according to an interface (1302-LO1).
* Utilitze inheritance and polymorphism in a software project (1302-LO3-LO4).
* Test your implementation using unit tests (1302-LO9).

## Project Requirements & Grading

This assignment is worth 100 points. The lowest possible grade is 0, and the 
highest possible grade is 105 (due to extra credit).

### Functional Requirements

A functional requirement is *added* to your point total if satisfied.
There will be no partial credit for any of the requirements that simply 
require the presence of a method related a particular functionality. 
The actual functionality is tested using test cases.

* **```ArrayGenList<T>```:** Create the ```cs1302.lists.ArrayGenList``` generic class
  with type parameter ```T``` such that it properly implements the 
  ```cs1302.lists.GenList<T>``` interface with the requirements listed below.

  * The generic type parameter is not bounded.

  * The ```add(T)``` method adds an element to the end of the list. The Javadoc
    documentation for this methods should clearly indicate this behavior.

  * You must explicitly define a default constructor for this class. The
    initial size of an ```ArrayGenList<T>``` is ```0``` regardless of your
    actual internal array capacity. 

  * **Extra Credit (5 points):** Override the ```iterator()``` method for your
    ```ArrayGenList``` class as described in the ```GenList``` interface. This _may_ 
    require you to create an additional class that implements another interface.

    **NOTE:** You do not need to implement the ```iterator()``` method if you
    are not doing the extra credit.

* **```SortedArrayGenList<T extends Comparable<T>>```:** Create the 
  ```cs1302.lists.SortedArrayGenList``` generic class with bounded type parameter 
  ```T``` such that it properly implements the ```cs1302.lists.GenList<T>``` 
  interface with the requirements listed below. 

  * The generic type parameter is bounded by ```java.util.Comparable<T>```
    in order to enable comparisons between existing and or prospective 
    elements of the list.

  * The ```add(T)``` and ```add(int, T)``` methods are implemented such that 
    elements are added to the list in sorted order, regardless of the index 
    position specified. The Javadoc documentation for these methods should 
    clearly indicate this behavior.

  * You must explicitly define a default constructor for this class. The
    initial size of a ```SortedArrayGenList<T>``` is ```0``` regardless of your
    actual internal array capacity. 

  * **Design Choice:** Whether or not this class extends your ```ArrayGenList```
    class is entirely up to you. The only functional requirements are that
    it implements the interface as described above. 

  * **Extra Credit (5 points):** Override the ```iterator()``` method for your
    ```SortedArrayList``` class as described in the ```GenList``` interface. This 
    _may_ require you to create an additional class that implements another 
    interface.

    **NOTE:** You do not need to implement the ```iterator()``` method if you
    are not doing the extra credit.
    
* **(100 points) Test Cases**: The bulk of this project will be graded
  based on 50 JUnit test cases, each worth 2 points. This is the same as
  someone using the classes you wrote based purely on the interface
  definitions. If you implement the interface correctly, then you should
  pass the associated test cases. 
  
  Some test cases are presented here with the skeleton project in
  [`/src/test/java`](/src/test/java). You can run the test cases
  with the following command:
  ```
  $ mvn test
  ```

### Non-Functional Requirements

A non-functional requirement is *subtracted* from your point total if
not satisfied. In order to emphasize the importance of these requirements,
non-compliance results in the full point amount being subtracted from your
point total. That is, they are all or nothing. 

* **(100 points) Box Storage:** In general, when implementing an interface for a collection ADT
  (i.e., something that represents objects that contain other objects), the
  implementor gets to decide how the implementation stores the collection of
  elements. For this project, each list implementation should store its elements
  using an array of type ```Box<T>[]```. Ideally, we would want an array of type 
  ```T[]```, but Java does not allow the creation of generic arrays. The 
  ```Box<T>``` class provides a generic static method for the creation of arrays
  with type ```Box<T>[]```. The ```Box<T>``` class is  provided to you in 
  <a href="src/main/java/cs1302/lists/Box.java">```cs1302.lists.Box```</a>.
  The HTML documention for the ```Box<T>``` class is available 
  <a href="http://cobweb.cs.uga.edu/~mec/cs1302/lists-apidocs/cs1302/lists/Box.html">here</a>.
  Since users of an ADT implementation do not interact with the underlying
  storage directly (and only through the methods defined in the interface),
  this non-functional requirement is transparent to users. However, using an
  array of generic "boxes", each containing an element of type ```T```, does increase
  the level of abstraction for the implementor.  To create an array of type 
  ```Box<T>[]``` use the static 
  <a href="http://cobweb.cs.uga.edu/~mec/cs1302/lists-apidocs/cs1302/lists/Box.html#array-int-">```array```</a>
  method. Since, in this project,  a list implementation is using its internal 
  array as storage for its elements, the internal array capacity must always be at 
  least the size of the list that uses it. You should grow and shrink the array 
  as needed. It reccommended that you make support methods to help streamline this 
  process as needed. 
  
* **(100 points) Project Directory Structure:** The location of the default
  package for the source code should be a direct subdirectory called `src`.
  When the project is compiled using Maven, the the default package for compiled 
  code should be `target/classes`. 
  
  If you follow this structure, then you might type the following to compile 
  your code, assuming you are in the top-level project directory:
  ```
  $ mvn compile
  ```

* **(100 points) Development Environment:** This project must be implemented 
  in Java 8, and it *must compile and run* correctly on Nike using the specific
  version of Java 8 that is setup according to the instructions provided
  by your instructor (usually provided in the first homework assignment).
  
* **(100 points) No Static Variables:** Use of static variables is 
  not allowed for this assignment. Static constants are allowed. 
  
* **(25 points) Code Style Guidelines:** You should be consistent with the style 
  aspect of your code in order to promote readability. Besides consistency, the
  following conventions will be enforced:
  
  * **Reference type names are written in _UpperCamelCase_.** Class names are  
    typically nouns or noun phrases. For example, `Character` or `ImmutableList`. 
    Interface names may also be nouns or noun phrases (for example, `List`), but 
    may sometimes be adjectives or adjective phrases instead (for example, 
    `Readable`).
  
  * **Method names are written in _lowerCamelCase_.** Method names are also 
    typically verbs or verb phrases. For example, `sendMessage` or `stop`.
  
  * **Braces are always used where optional.** Braces should be used with `if`, 
    `else`, `for`, `do`, and `while` statements, even when the body is empty or 
    contains only a single statement.
    
  * **Column limit: 100.** You should limit the number of characters, including
    whitespace, on any given line to 100 characters. Except as noted below, any 
    line that would exceed this limit must be manually line-wrapped in a
    consistent manner. Exceptions to the column limit include:
    
    * Lines where obeying the column limit is not possible (for example, a long 
      URL in Javadoc, or a long JSNI method reference).
    * In `package` and `import` statements.
    * Command line input examples in a comment that may be cut-and-pasted into 
      a shell.
      
  * **Method height <= window height.** You should limit the number of lines for
    a method so that the entire method can be seen on the screen at once. This
    includes the line(s) with the method's signature and opening curly brace, all
    lines in the body of the mthod (including blank lines), and the line with
    the method's ending curly brace. 
    
    Of all the style guidelines, this is the probably the most subjective and 
    hardest to grade because everyone might have a different window size due
    to different terminal emulator and physical screen size configurations. 
    Therefore, graders will be checking for compliance with the spirit
    of this guideline, which is: methods that are too big and/or repetitive 
    should be refactored to include propor looping constructs and/or broken
    up into smaller methods to improve readability. 

* **(25 points) Javadoc Documentation:** Each method and class needs to be fully
  documented using Javadoc comments. Your comment should provide a description
  of the method's functionality in the first sentence of the comment.  This sentence
  needs to be a gramatically correct English sentence with proper punctuation. Further 
  description can be provided in subsequent sentence. The basic formatting of Javadoc 
  blocks is as seen in this example:
  ```java
  /**
   * Multiple lines of Javadoc text are written here,
   * wrapped normally...
   */
  public int method(String p1) { ... }
  ```
  ... or in this single-line example:
  ```java
  /** An especially short bit of Javadoc. */
  ```
  All method parameters and exceptions need to be documented.
  More information about Javadoc can be found
  [here](https://docs.oracle.com/javase/8/docs/technotes/tools/unix/javadoc.html).

* **(25 points) In-line Documentation:** Code blocks should be adequately documented
  using in-line comments. This is especially necessary when a block of code
  is not immediately understood by a reader (e.g., yourself or the grader).

### Grading

This project will be graded using unit tests that will not be made available
before the project deadline. It is paramount that you do not modify the
<code>GenList</code> interface provided by the project's skeleton code in any
way. It is also very important that you follow the package and naming
conventions for the <code>ArrayGenList</code> and <code>SortedArrayGenList</code>
classes described in this project description. Modifying the provided 
interface and or not following the naming conventions outlined in this
project description will cause the unit tests to automatically fail. 

*TL;DR* - Do not modify the provided interfaces and classes; Follow naming 
conventions. **If not, then your project will not be graded.**

## How to Download the Project

On Nike, execute the following terminal command in order to download the project
files into sub-directory within your present working directory:

```
$ git clone https://github.com/cs1302uga/cs1302-lists.git
$ chmod ugo-w cs1302-lists/src/main/java/cs1302/lists/Box.java
$ chmod ugo-w cs1302-lists/src/main/java/cs1302/lists/GenList.java
```

This should create a directory called <code>cs1302-lists</code> in
your present working directory that contains the project files. This
directory is an already-setup Maven project. To compile your code, 
simply type the following command while in top level of the 
<code>cs1302-lists</code> directory:

```
$ mvn compile
```

Furthermore, the `chmod` command removed write permission from two files that
you are not allowed to modify. If you modify the contents of these files in
any way, then your project will not be graded. Removing write permission, while
not technically required, is a safe way to prevent yourself from making changes
to the contents of those files by accident. 

If any updates to the project files are announced by your instructor, you can
merge those changes into your copy by changing into your project's directory
on Nike and issuing the following terminal command:

```
$ git pull
```

If you have any problems with any of these procedures, then please contact
your instructor.

## Submission Instructions

You will be submitting your project via Nike before the deadline indicated
near the top of this document. Make sure your project files
are on <code>nike.cs.uga.edu</code>. Change into the parent directory of your
project directory. If you've followed the instructions provided in this document, 
then the name of your project directory is likely <code>cs1302-lists</code>. 
While in your project's parent directory, execute the following command: 

```
$ submit cs1302-lists cs1302a
```

It is also a good idea to email a copy to yourself. To do this, simply execute 
the following command, replacing the email address with your email address:

```
$ tar zcvf cs1302-lists.tar.gz cs1302-lists
$ mutt -s "[cs1302] cs1302-lists" -a cs1302-lists.tar.gz -- your@email.com < /dev/null
```

If you have any problems submitting your project then please send a private
post to your instructor via the course Piazza as soon as possible. However, 
creating a post about something like this the day or night the project is due 
is probably not the best idea.

# Appendix - FAQ

Below are some frequently asked questions related to this project.


1. **What's the difference between ```throw``` and ```throws```?**
   
   From a user's perspective, when a method signature indicates that it ```throws```
   an exception, this is an indication to users of that method that they should 
   use a try-catch block when invoking that method, since an exceptional (and 
   usually documented) case can occur. From an implementor's perspective, the 
   ```throws``` keyword indicates that the method *may* ```throw``` an exception
   under certain exceptional (and usually documented) cases. For example, if
   a method's documentation indicates that it ```throws NullPointerException```
   when a parameter called ```elem``` is ```null```, then the implementor might
   place the following near the top of that method's implementaiton:

   ```java
   if (elem == null) throw new NullPointerException("elem cannot be null");
   ```

2. **How do I determine the type of ```Object list``` in the ```equals(Object)``` method?**

   Ideally, you would check that ```list``` is an instance of ```List<T>```.
   However, Java does not allow this since any ```GenList<T>``` gets erased to
   ```GenList``` during type erasure due to the involvement of a generic type 
   parameter. Instead, you need to check if ```list``` is an instance of the 
   raw type ```GenList```. This will result in all elements being of ```list```
   being treated as type ```Object```, regardless of their actual type. This
   is perfectly okay since you will be performing equality tests between
   elements of the current list and ```list``` using the ```equals(Object)```
   method. This also means that ```list``` can be an object of any 
   implementation of the ```GenList<T>``` interface. It does not have to the
   same implementation as the calling object.

   If ```list instanceof GenList``` is ```true```, then it is safe to cast the
   reference to a ```GenList``` (e.g., ```GenList l = (GenList) list```). This will
   give you access to the ```get(int)``` method via the casted reference
   (e.g., ```l.get(0)```) for, potentially, the purpose of comparing it to
   an element in the current list in the same position.

3. **How do I include the interface's Javadoc for a method in addition to my own without copy/paste?**

   You can use the ```{@inheritDoc}``` tag near the end of the description
   in your method's Javadoc comment to include the original documentation.
   For example, you might do the following:

   ```java
   /**
    * Short one line description. This is the rest of the description. It may
    * involve several sentences.
    *
    * <p>
    * {@inheritDoc}
    *
    * @param p string called p (never write this)
    */
   public void foo(String p) { 
       /** ... */ 
   } // foo
   ```

   It is common practice to include the more specific documentation first, 
   followed by more general documentation, if needed.
   
4. **Can I technically implement the methods first before I implement them correctly?**

   You may wish to write out the method signatures for the methods you are
   implementing from the interface with empty bodies in an attempt to get started.
   You will quickly discover that the methods that have a non-void return
   value actually need to return something. If you don't put a return statement,
   then this complicates trying to compile and test one method at a time.
   
   It is possible to _temporarily_ include a `throw` statement in the method
   until you commit to writing the return statement. I reccommend throwing
   an instance of [`UnsupportedOperationException`](https://docs.oracle.com/javase/8/docs/api/java/lang/UnsupportedOperationException.html)
   if you choose to do this. For example, you might write something like this for the `get(int)`
   method:
   ```
   public T get(int index) throws IndexOutOfBoundsException {
       throw new UnsupportedOperationException("not yet implemented");
   } // get
   ```

Have a question? Please post it on the course Piazza.

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
