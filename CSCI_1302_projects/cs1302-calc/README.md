# CSCI 1302 - Calc App v2018.fa

![?](https://www.maa.org/sites/default/files/images/cms_upload/abacus250457.jpg)

**MON 2018-11-26 (NOV 26) @ 11:55 PM EST**.

This document contains the description for the Calc App project
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

* **2018-11-07:** Added some notes about the mockup image directly below the
  mockup image.

* **2018-11-06:** Added more detail to the "Implementation Restrictions" 
  non-functional requirement.

## Project Description

In this project, you will develop a "programmer's" calculator app in Java with 
a graphical user interface (GUI) powered by JavaFX. You will be responsible for 
designing and implementing the user interface for your project (following a few 
guidelines). 

This project is also designed to help you better understand the usefulness of good
class design. While you can technically write your entire JavaFX-based
GUI application entirely in the `start` method, this will make your code messy, 
hard to read, possibly redundant, and likely more prone to errors.
Before you write any code, you should plan out your application's scene graph
(i.e., the containment hierarchy), and design custom components as needed.
If you find that you are writing a lot of code related to a specific component
(e.g., setting styling, adding event handlers, etc.), then it's probably 
a good idea to make a custom version of that component in order to reduce
clutter.

![CalcApp Mockup](https://imgur.com/kxpqNGV.png)

### Some Notes about the Mockup Image

* The bit toggle is made up of 32 `Text`-like objects. Consider making a `Bit` 
  class that extends `Text`.
  
* The bits in the bit toggle were not hard coded. The designer used a loop.

* `Text` objects can be made clickable by using 
  [`setOnMouseClicked`](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#setOnMouseClicked-javafx.event.EventHandler-).
  
* The "S" in the bit toggle stands for the sign bit and is not intended to 
  be clickable since this app only operates on non-negative integers. 
  It's actual value would be 0. 
  
* The button colors were chosen from the list of official 
  [UGA brand colors](https://brand.uga.edu/color). However, their use 
  probably does not conform to the branding guidelines. 

### What is a "Programmer's" Calculator?

There are four (4) characteristics that differentiate this app from a standard
calculator.

1. **Unsigned Integer Operations:** While standard calculator's perform operations
   on real numbers, this "programmer's" calculator will be restricted to 
   operations on unsigned (i.e., non-negative) integers. This means that all
   inputs and outputs will be unsigned integers. This also means that operations
   (e.g., division) will be integer operations. Additionally, this calculator will 
   also support bit shifting operations.

2. **Expression Evaluation:** While standard calculator's can only perform
   one mathematical operation at a time, this "programmer's" calculator will
   allow users to build complex, multi-operaton expressions in a text field, 
   then click on an "Evaluate" button to have the calculator actually evaluate the
   expression. This application does not support expressions with parentheses.
   The code to evaluate an overally expression is already provided to
   you in `MathOpsEvaluator`, assuming you provide an implementation of 
   the `MathOps` interface. See the
   [FAQ](#appendix---faq) for more details.
   
2. **Implementation Toggle:** This "programmer's" calculator will provide a toggle
   for two different underlying implementations of the `MathOps` interface. 
   The two implementations are: iterative and recursive. This effectively provides two 
   mutually exclusive modes for the calculator. The current mode dictates which implementation 
   is passed to the code your application uses for expression evaluation. See the
   [FAQ](#appendix---faq) for more details.

3. **Bit Toggle:** This "programmer's" calculator will display a sequence of
   clickable bits in standard order (i.e., most significant to least significant)
   that shows the current binary representation of the result. When the result
   changes, the visible binary should also change. Furthermore, the user should
   be able to toggle (i.e., click) each bit on or off. When this is done, the
   result should change accordingly. See the
   [FAQ](#appendix---faq) for more details.

## Project Requirements & Grading

This assignment is worth 100 points. The lowest possible grade is 0, and the 
highest possible grade is 115 (due to extra credit).

### Functional Requirements

A functional requirement is *added* to your point total if satisfied.
There will be no partial credit for any of the requirements that simply 
require the presence of a method related a particular functionality. 
The actual functionality is tested using test cases.

* **(20 points) User Interface**: Your application needs to have the components 
  listed below. They need to function as described. If a certain aspect of a component 
  (e.g., style or behavior) is not specified in these requirements, then that
  aspect is at the discretion of the implementor. The screenshot provided earlier 
  in this project description is meant to serve as a reference. It is okay if
  implementors deviate visually from screenshot. In addition to the required 
  components, implementors should feel free to add more components and/or 
  functionality as long they do not distract too heavily from the functionality
  of the required components. Here are the required components:
  
  * **(5 points) Menu Bar**: Your application should have a menu bar
    with a "File" menu. The only required menu item is an "Exit" item
    that allows the user to exit the application.

  * **(5 points) Expression & Result Text Fields**: Your application
    should have two textfields, one where the expression will be built
    as the user interacts with the buttons and one where the result is
    places when the user clicks on the "Evaluate" button..

  * **(10 points) Calculator Buttons**: Your application needs to have
    buttons for the numbers zero (0) through (9), an implementation
    toggle, as well as a button for each of the operations listed below.
    The name of each operation is provided as well as the symbol(s)
    used to denote that operation in an expression.

    * Add (`+`)
    * Subtract (`-`)
    * Multiply (`*`)
    * Divide (`/`)
    * Exponentiation (`^^`)
    * Left Shift (`<<`)
    * Right Shift (`>>`)
    * Factorial (`!`)

    Additionally, your application needs to have the following buttons:
    
    * Evaluate: Evaluates the expression using `MathOpsEvaluator` along
      with an appropriate `MathOps` implementation corresponding to the
      current implementation mode. If `MathOpsEvaluator.evaluate` cannot
      evaluate the expression, then your application should show an
      application modal dialog with a user friendly error message. 
      
    * Backspace: Removes one non-whitespace character from the current
      expression, if applicable.
      
    * Clear: Clears the current expression.
    
  * **(10 points) Bit Toggle:** your application needs display a sequence of
   clickable bits in standard order (i.e., most significant to least significant)
   that shows the current binary representation of the result. When the result
   changes, the visible binary should also change. Furthermore, the user should
   be able to toggle (i.e., click) each bit on or off. When this is done, the
   result should change accordingly. See the
   [FAQ](#appendix---faq) for more details.
    
* **(20 points) `cs1302.calc.RecursiveMathOps`**: Implement the `cs1302.calc.MathOps`
  interface with this class. Each of the eight (8) methods from the interface should
  be implemented using recursion. This will be graded based on at least sixteen (16)
  JUnit test cases, each weighted equally. This is the same as
  someone using the class based purely on the interface definition. If you implement 
  the interface correctly, then you should pass the associated test cases. A spot
  check will be performed by the grader to make sure that you actually impement the
  methods using recursion and not some other method. If you do not use recursion, then
  all test case points are forfeit. Each method may depend on other implemented
  methods. Please note that this may have a cascading effect on the test cases for each
  method. No special consideration will be made concerning double jeopardy and test
  cases. Methods will be tested as they are provided upon submission. 
  
* **(20 points) `cs1302.calc.IterativeMathOps`**: Implement the `cs1302.calc.MathOps`
  interface with this class. Each of the eight (8) methods from the interface should
  be implemented using iteration. This will be graded based on at least sixteen (16)
  JUnit test cases, each weighted equally. This is the same as
  someone using the class based purely on the interface definition. If you implement 
  the interface correctly, then you should pass the associated test cases. A spot
  check will be performed by the grader to make sure that you actually impement the
  methods using iteration and not some other method. If you do not use iteration, then
  all test case points are forfeit. Each method may depend on other implemented
  methods. Please note that this may have a cascading effect on the test cases for each
  method. No special consideration will be made concerning double jeopardy and test
  cases. Methods will be tested as they are provided upon submission. 
  
* **(30 points) Expression Builder:** Your calculator needs to allow users
  to successfully build and evaluate complex, multi-operaton expressions in a text field
  by clicking on the buttons provided by the application. This will be graded based
  on at least thirty (30) test cases, each weighted equally, one of which is provided 
  in the mockup image near the top of this project description. 
  
* **Extra Credit 1 (5 points):** Add a "Help" menu to the menu bar. This menu
  should contain one menu item labeled "About" that displays an "About YOUR-NAME"
  window (where YOUR-NAME is replaced with your name). This window should
  contain an image of you as well as your name, email, and the version number
  for your application (version can be whatever you want). This window should
  be *application modal*, and it should be closable so that the user can return
  to the main part of the application. If you are doing both of the extra
  credit requirements, then please make the "Help" menu the right-most menu

* **Extra Credit 2 (5 points):** Add a "Theme" menu to the menu bar that lets
  the user switch between different stylings for the application. In order to
  receive points for this requirement, you should support at least one theme
  in addition to your application's default theme. HINT: You may want to explore
  having muliple CSS files for your application and loading/applying them as
  needed to facilitate this requirement.
  
* **Extra Credit 3 (5 points):** Add a "Window" menu to the menu bar with a 
  single menu item that lets the user hide or show the bit toggle. If the bit 
  toggle is currently being displayed, then the text on the menu item should
  be "Show Bit Toggle" and function accordingly. If the bit toggle is not
  currently being displayed, then the text on the menu item should be
  "Hide Bit Toggle" and function accordingly. The application's `Stage` should
  resize accordingly to accomodate the changes facilitated by this menu
  item.

### Non-Functional Requirements

A non-functional requirement is *subtracted* from your point total if
not satisfied. In order to emphasize the importance of these requirements,
non-compliance results in the full point amount being subtracted from your
point total. That is, they are all or nothing.  
  
* **(100 points) Project Structure:** The location of the default
  package for the source code should be a direct subdirectory called `src/main/java`.
  When the project is compiled using Maven, the the default package for compiled 
  code should be `target/classes`. 
  
  If you follow this structure, then you might type the following to compile 
  your code, assuming you are in the top-level project directory:
  ```
  $ mvn compile
  ```
  
  The driver class should be `cs1302.calc.CalcApp`.

  The three math implementations (one is provided) should be:

  * `cs1302.calc.BasicMathOps` (basic implementation; provided)
  * `cs1302.calc.RecursiveMathOps` (recursive implementation)
  * `cs1302.calc.IterativeMathOps` (iterative implementation)

  If you modify the following, then your project will not be graded:

  * `cs1302.calc.MathOps` (interface)
  * `cs1302.calc.MathOpsEvaluator` (class)

* **(100 points) Implementation Restrictions:** Use of the following operators
  is not allowed in student implementations of the `MathOps` interface:
  
  * `+`
  * `-`
  * `*`
  * `/`
  * `<<`
  * `>>`
  
  Instead of using the operators above, student implementations should 
  call the default methods `succ` (successor) and `pred` (predecessor) 
  as well as other methods from the interface, as neeeded. Please note
  that this may have a cascading effect on the test cases for each
  method. Alternatives to the `succ` (successor) and `pred` (predecessor) default 
  methods are provided by the `inc` (increment) and `dec` (decrement) default
  methods for those who prefer to use them.
  
  Use of `java.lang.Math` is also prohibited. 

* **(100 points) Development Environment:** This project must be implemented 
  in Java 8, and it *must compile and run* correctly on Nike using the specific
  version of Java 8 that is setup according to the instructions provided
  by your instructor (usually provided in the first homework assignment).
  
* **(100 points) No Static Variables:** Use of static variables is 
  not allowed for this assignment. Static constants are allowed. 
  
* **(100 points) No FXML or use of Scene Builder:** FXML and SceneBuilder
  are advanced tools that are not currently covered in this course. Use of
  either for this project is prohibited. Please note that the project is
  not easier with these tools. In most cases, they actually make the
  project harder, especially since those topics have not been covered.
  
* **(25 points) User-Friendly Experience:** Except for reasonable delays resulting from X forwarding, 
  your application should not hang/freeze or crash during execution.
  
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
    contains only a single statement. Single line lambda expressions are
    permitted. 
    
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
    should be refactored to include proper looping constructs and/or broken
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

## How to Download the Project

On Nike, execute the following terminal command in order to download the project
files into sub-directory within your present working directory:

```
$ git clone https://github.com/cs1302uga/cs1302-calc.git
```

This should create a directory called `cs1302-calc` in
your present working directory that contains the project files. This
directory is an already-setup Maven project. To compile your code, 
simply type the following command while in top level of the 
`cs1302-calc` directory:

```
$ mvn compile
```

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
are on `nike.cs.uga.edu`. Change into the parent directory of your
project directory. If you've followed the instructions provided in this document, 
then the name of your project directory is likely `cs1302-calc`. 
While in your project's parent directory, execute the following command: 

```
$ submit cs1302-calc cs1302a
```

It is also a good idea to email a copy to yourself. To do this, simply execute 
the following command, replacing the email address with your email address:

```
$ tar zcvf cs1302-lists.tar.gz cs1302-calc
$ mutt -s "[cs1302] cs1302-calc" -a cs1302-calc.tar.gz -- your@email.com < /dev/null
```

If you have any problems submitting your project then please send a private
post to your instructor via the course Piazza as soon as possible. However, 
creating a post about something like this the day or night the project is due 
is probably not the best idea.

# Appendix - FAQ

Below are some frequently asked questions related to this project.

1. **How do I evaluate an expression?**

   Expression evaluation is performed uing the `MathOpsEvaluator.evaluate` method.
   Suppose you have an the following expression:
   ```java
   String expr = "2 + 12 / 2 - 3 !";
   ```
   It is *very important* that the string containing the expression is trimmed
   (i.e., no leading or trailing whitespace) and contains only a single space
   between operators and operands.
   
   Now suppose you have a `MathOps` implementation:
   ```java
   MathOps basicMathOps = new BasicMathOps();
   ```
   
   Together, these can be used to evaluate the expression using the 
   `MathOpsEvaluator.evaluate` method as follows:
   ```java
   try {
       int result = MathOpsEvaluator.evaluate(basicMathOps, expr);
       System.out.printf("%s = %d \n", expr, result);
   } catch (Exception e) {
       System.err.println(e);
       System.err.println("something went wrong!");
   } // try
   ```
   
   Please note that the `BasicMathOps` implementation is provided only for testing
   purposes. Your calculator should use `RecursiveMathOps` or `IterativeMathOps`
   depending on the calculator's mode.
   
2. **In the mockup, what does the "S" stand for?**

   That "S" represents the sign bit. Since this "programmer's" calculator only supports non-negative
   integers, there is no reason to actually include the sign bit. However, the mockup creator
   included it because they wanted there to be visual symmetry in the most significant byte of the 
   bit toggle.
   
3. **How do I create an event handler for a mouse click with `setOnAction`?**

   It is usually possible to explicitly set an event handler for a mouse click via a node's
   [`setOnMouseClicked`](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#setOnMouseClicked-javafx.event.EventHandler-)
   method. This might be useful for making the bits in your bit toggle clickable if they are
   not buttons. 
   
4. **In the mockup, how is the implementation toggle implemented?**

   In the mockup, when a user clicks on the "Recursion" button, suqare brackets are added to the 
   button text to indicate that the calculator is in recursion mode. The same thing would be done
   with the "Iteration" button for iteration mode. You are free to implement this behavior however
   you want as long as it is very clear to the user. 

5. **How do implement bit shifting using recursion and/or iteration?**

   The _left shift_ and _right shift_ operations can be interpreted as repeated integer 
   multiplication and division, respectively, by two. Their problem decomposition is very 
   similar in form to the problem decomposition for exponentiation.

6. **How do I pass around objects effectively?**

   From time to time, you may need to access one part of your app from another part of your app.
   You used a good design (e.g., classes and inheritance), but you find that you're passing a lot
   of reference variales around, perhaps through constructors. If it were one or two variables, 
   then it would not be a big deal. However, you are likely reading this question because you
   are passing a lot of variables around. Your first thought might be to make those variables 
   static, but that is not good for a couple different reasons--recall what it means for
   a variable to be static as well as the non-functional requirements for this project.
   
   The reccommended strategy is to add all those variables to your driver class as instance variables
   with getters and setters, then pass a reference to your driver object around as needed.
   This way, you are only passing around one variable instead of many! In the other parts of your
   app, simply call on the getter and setters methods to access and change the things you 
   were previously passing around.
   
   Remember, if you are currently in your driver class's `start` method, then you can pass
   a reference to the current driver object into a method using the `this` reference
   variable. 

Have a question? Please post it on the course Piazza.

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
