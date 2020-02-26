package cs1302.calc;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The CalcApp class builds the calculators user interface and calls either
 * the Basic, recursive, or iterativeMathOps to solve expresions.
 */
public class CalcApp extends Application {

  GridPane gridly = new GridPane();//for the calculator buttons
  TextField input;
  TextField output;
  VBox number_screen = new VBox();
  MathOps basicMathOps = new BasicMathOps();
  MathOps iterativeMathOps = new IterativeMathOps();
  MathOps recursiveMathOps = new RecursiveMathOps();
  String input_expr = "";
  
  //creating the buttons for the numberpad
    Button zero_B = new Button("0");
    Button one_B = new Button("1");
    Button two_B = new Button("2");
    Button three_B = new Button("3");
    Button four_B = new Button("4");
    Button five_B = new Button("5");
    Button six_B = new Button("6");
    Button seven_B = new Button("7");
    Button eight_B = new Button("8");
    Button nine_B = new Button("9");
    Button add_B = new Button("+");
    Button subtract_B = new Button("-");
    Button mult_B = new Button("*");
    Button divide_B = new Button("/");
    Button exp_B = new Button("^^");
    Button lshift_B = new Button("<<");
    Button rshift_B = new Button(">>");
    Button factorial_B = new Button("!");
    Button backspace_B = new Button("Backspace");
    Button evaluate_B = new Button("Evaluate");
    Button clear_B = new Button("Clear");
    Button iterative_B = new Button("Iterative");
    Button recursive_B = new Button("Recursive");
    
  //This is for the iterative and recursive buttons.
    //It is ment to be passed to to the solveIT method
    //it is changed by the swapToIterarive and the swapToRecursive methods
    MathOps choice =  basicMathOps;

  //instantiating the bitBar and the text objects it uses
    HBox bitBar = new HBox(3);
    Text t0 = new Text("0");
    Text t1 = new Text("0");
    Text t2 = new Text("0");
    Text t3 = new Text("0");
    Text t4 = new Text("0");
    Text t5 = new Text("0");
    Text t6 = new Text("0");
    Text t7 = new Text("0");
    Text t8 = new Text("0");
    Text t9 = new Text("0");
    Text t10 = new Text("0");
    Text t11 = new Text("0");
    Text t12 = new Text("0");
    Text t13 = new Text("0");
    Text t14 = new Text("0");
    Text t15 = new Text("0");
    Text t16 = new Text("0");
    Text t17 = new Text("0");
    Text t18 = new Text("0");
    Text t19 = new Text("0");
    Text t20 = new Text("0");
    Text t21 = new Text("0");
    Text t22 = new Text("0");
    Text t23 = new Text("0");
    Text t24 = new Text("0");
    Text t25 = new Text("0");
    Text t26 = new Text("0");
    Text t27 = new Text("0");
    Text t28 = new Text("0");
    Text t29 = new Text("0");
    Text t30 = new Text("0");
    Text t31 = new Text("S");

    /**
     * The start method serves as the main entry point for all JavaFX applications.
     *
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        // Construct GUI;
        bitBar = createBitBar();
        VBox pane = new VBox();
        MenuBar menuBar = createMenuBar();
        createNumpad();
        createNumScreen();
        pane.getChildren().addAll(menuBar, number_screen, bitBar, gridly);
        Scene scene = new Scene(pane);
        stage.setMaxWidth(640);
        stage.setMaxHeight(480);
        stage.setTitle("cs1302-calc!");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    } // start
    
    /**
     * The createToolBar method simply instatiates objects used in the menu bar and adds them.
     */
    private MenuBar createMenuBar() {
	    MenuItem exitMenuItem = new MenuItem("Exit");
      exitMenuItem.setOnAction(event -> {//lambda for the exit button
		    System.out.println("bye bye :(");
		    Platform.exit();
		    System.exit(0);
	    });
	    Menu fileMenu = new Menu("File");//creating file menu
	    fileMenu.getItems().addAll(exitMenuItem);
	    MenuBar menuBar = new MenuBar();
	    menuBar.getMenus().add(fileMenu);
	    return menuBar;
    } // createMenuBar


    /**
     * The createNumpad method creates the funtions of the nonumeric buttons,
     * calls another method to create the functions of the 0-9 buttons, and
     * sizes the buttons by calling a method.
     * Finally it adds the buttons to a GridPane.
     *
     */
    private void createNumpad(){
        createNumpadNumbersFunctions();
        add_B.setOnAction(event -> input.setText(input.getText()+" +"));
        subtract_B.setOnAction(event -> input.setText(input.getText()+" -"));
        mult_B.setOnAction(event -> input.setText(input.getText()+" *"));
        divide_B.setOnAction(event -> input.setText(input.getText()+" /"));
        exp_B.setOnAction(event -> input.setText(input.getText()+" ^^"));
        lshift_B.setOnAction(event -> input.setText(input.getText()+" <<"));
        rshift_B.setOnAction(event -> input.setText(input.getText()+" >>"));
        factorial_B.setOnAction(event -> input.setText(input.getText()+" !"));
        backspace_B.setOnAction(event -> deleteIt());
        evaluate_B.setOnAction(event -> solveIt(choice));
        clear_B.setOnAction(event -> clearNumpad());
        iterative_B.setOnAction(event -> swapToIterarive());
        recursive_B.setOnAction(event -> swapToRecursive());
        ColumnConstraints size  = new ColumnConstraints(95);//seting the width of the buttons
        gridly.getColumnConstraints().addAll(size, size,size,size);
        sizeButtons();//makes sure all of the buttons fill to 95
        gridly.addRow(0, iterative_B, recursive_B, backspace_B, clear_B);
        gridly.addRow(1, lshift_B, rshift_B, exp_B, factorial_B);
        gridly.addRow(2, seven_B, eight_B, nine_B, divide_B);
        gridly.addRow(3, four_B, five_B, six_B, mult_B);
        gridly.addRow(4, one_B, two_B, three_B, subtract_B);
        gridly.add(zero_B, 0,5);
        gridly.add(evaluate_B, 1,5,2,1);
        gridly.add(add_B,3,5);

    }

    /**
     * The sizeButtons method sizes the numberpads buttons.
     */
    private void sizeButtons(){
            evaluate_B.setMaxWidth(Double.MAX_VALUE);
            add_B.setMaxWidth(Double.MAX_VALUE);
            subtract_B.setMaxWidth(Double.MAX_VALUE);
            factorial_B.setMaxWidth(Double.MAX_VALUE);
            exp_B.setMaxWidth(Double.MAX_VALUE);
            clear_B.setMaxWidth(Double.MAX_VALUE);
            divide_B.setMaxWidth(Double.MAX_VALUE);
            mult_B.setMaxWidth(Double.MAX_VALUE);
            lshift_B.setMaxWidth(Double.MAX_VALUE);
            rshift_B.setMaxWidth(Double.MAX_VALUE);
            recursive_B.setMaxWidth(Double.MAX_VALUE);
            iterative_B.setMaxWidth(Double.MAX_VALUE);
            backspace_B.setMaxWidth(Double.MAX_VALUE);
            zero_B.setMaxWidth(Double.MAX_VALUE);
            one_B.setMaxWidth(Double.MAX_VALUE);
            two_B.setMaxWidth(Double.MAX_VALUE);
            three_B.setMaxWidth(Double.MAX_VALUE);
            four_B.setMaxWidth(Double.MAX_VALUE);
            five_B.setMaxWidth(Double.MAX_VALUE);
            six_B.setMaxWidth(Double.MAX_VALUE);
            seven_B.setMaxWidth(Double.MAX_VALUE);
            eight_B.setMaxWidth(Double.MAX_VALUE);
            nine_B.setMaxWidth(Double.MAX_VALUE);
    }//sizeButtons


    /**
     * The clearNumpad method clears provides functionality for
     * the clear_B(utton) when called in a lambda expretion.
     * It clears the calculators input expresion.
     */
    private void clearNumpad(){
        input.clear();
        //old lines
        //output.clear();
        //for(int i=1;i<=31;i++){
        //    getNodeAtIndex(i).setText("0");
        //}
    }//clearNumpad

    


    /**
     * The createNumpadNumbersFunctions adds functinality to the 0-9 numberpad buttons.
     */
    public void createNumpadNumbersFunctions(){
        zero_B.setOnAction(event -> zeroButtonPress());
        one_B.setOnAction(event -> oneButtonPress());
        two_B.setOnAction(event -> twoButtonPress());
        three_B.setOnAction(event -> threeButtonPress());
        four_B.setOnAction(event -> fourButtonPress());
        five_B.setOnAction(event -> fiveButtonPress());
        six_B.setOnAction(event -> sixButtonPress());
        seven_B.setOnAction(event -> sevenButtonPress());
        eight_B.setOnAction(event -> eightButtonPress());
        nine_B.setOnAction(event -> nineButtonPress());
    }//createNumpadNumbersFunctions

    /**
     *  The zeroButtonPress method is ment to be called by a lambda expression.
     *  It prints the desired number to the input TextField and changes the spacing
     *  of the inserted number.
     */
    private void zeroButtonPress(){
        if(input.getText().equals("")||isPreviousNumber()){//if its the first number no spaces
            input.setText(input.getText()+"0");
        }
        else{
            input.setText(input.getText()+" 0");
        }
    }//zeroButtonPress

    /**
     *  The oneButtonPress method is ment to be called by a lambda expression.
     *  It prints the desired number to the input TextField and changes the spacing
     *  of the inserted number.
     */
    private void oneButtonPress(){
        if(input.getText().equals("")||isPreviousNumber()){//if its the first number no spaces
            input.setText(input.getText()+"1");
        }
        else{
            input.setText(input.getText()+" 1");
        }
    }//oneButtonPress

    /**
     *  The twoButtonPress method is ment to be called by a lambda expression.
     *  It prints the desired number to the input TextField and changes the spacing
     *  of the inserted number.
     */
    private void twoButtonPress(){
        if(input.getText().equals("")||isPreviousNumber()){//if its the first number no spaces
            input.setText(input.getText()+"2");
        }
        else{
            input.setText(input.getText()+" 2");
        }
    }//twoButtonPress

    /**
     *  The threeButtonPress method is ment to be called by a lambda expression.
     *  It prints the desired number to the input TextField and changes the spacing
     *  of the inserted number.
     */
    private void threeButtonPress(){
        if(input.getText().equals("")||isPreviousNumber()){//if its the first number no spaces
            input.setText(input.getText()+"3");
        }
        else{
            input.setText(input.getText()+" 3");
        }
    }//threeButtonPress

    /**
     *  The fourButtonPress method is ment to be called by a lambda expression.
     *  It prints the desired number to the input TextField and changes the spacing
     *  of the inserted number.
     */
    private void fourButtonPress(){
        if(input.getText().equals("")||isPreviousNumber()){//if its the first number no spaces
            input.setText(input.getText()+"4");
        }
        else{
            input.setText(input.getText()+" 4");
        }
    }//fourButtonPress

    /**
     *  The fiveButtonPress method is ment to be called by a lambda expression.
     *  It prints the desired number to the input TextField and changes the spacing
     *  of the inserted number.
     */
    private void fiveButtonPress(){
        if(input.getText().equals("")||isPreviousNumber()){//if its the first number no spaces
            input.setText(input.getText()+"5");
        }
        else{
            input.setText(input.getText()+" 5");
        }
    }//fiveButtonPress

    /**
     *  The sixButtonPress method is ment to be called by a lambda expression.
     *  It prints the desired number to the input TextField and changes the spacing
     *  of the inserted number.
     */
    private void sixButtonPress(){
        if(input.getText().equals("")||isPreviousNumber()){//if its the first number no spaces
            input.setText(input.getText()+"6");
        }
        else{
            input.setText(input.getText()+" 6");
        }
    }//sixButtonPress

    /**
     *  The sevenButtonPress method is ment to be called by a lambda expression.
     *  It prints the desired number to the input TextField and changes the spacing
     *  of the inserted number.
     */
    private void sevenButtonPress(){
        if(input.getText().equals("")||isPreviousNumber()){///if its the first number no spaces
            input.setText(input.getText()+"7");
        }
        else{
            input.setText(input.getText()+" 7");
        }
    }//sevenButtonPress

    /**
     *  The eightButtonPress method is ment to be called by a lambda expression.
     *  It prints the desired number to the input TextField and changes the spacing
     *  of the inserted number.
     */
    private void eightButtonPress(){
        if(input.getText().equals("")||isPreviousNumber()){//if its the first number no spaces
            input.setText(input.getText()+"8");
        }
        else{
            input.setText(input.getText()+" 8");
        }
    }//eightButtonPress

    /**
     *  The nineButtonPress method is ment to be called by a lambda expression.
     *  It prints the desired number to the input TextField and changes the spacing
     *  of the inserted number.
     */
    private void nineButtonPress(){
        if(input.getText().equals("")||isPreviousNumber()){//if its the first number no spaces
            input.setText(input.getText()+"9");
        }
        else{
            input.setText(input.getText()+" 9");
        }
    }//nineButtonPress

    /**
     * The isPreviousNumber method checks whether the last character in
     * the input TextFeild is a digit.
     * This method is ment to be called when determining
     * spacing for the numberic buttons.
     *
     * @return True if the last character is a digit.
     */
    private boolean isPreviousNumber(){
        int length = input.getText().length();
        String holder = input.getText();
        if (length>0){
            if(Character.isDigit(holder.charAt(length-1))){
                return true;
            }
        }
        return false;
    }//isPreviousNumber

    /**
     * The secondToLastIsNumber method checks whether the second to
     * last character in the input TextFeild is a digit.
     * This method is ment to be called when determining
     * how many spaces to remove in the deleteIt method
     *
     * @return True if the last character is a digit.
     */
    private boolean secondToLastIsNumber(){
        int length = input.getText().length();
        String holder = input.getText();
        if (length>1){
            if(Character.isDigit(holder.charAt(length-2))){
                return true;
            }
        }
        return false;
    }

    /**
     * The deleteIt method provides functionality for
     * the backspace button when called in a lambda expression.
     * It removes input from the last button pressed
     */
    public void deleteIt(){//this shit works!!!!!
        String holder = input.getText();
        if (holder.length()!=0){
        //i test the previous two numbers to decide if i should only delet the previous char
            if(isPreviousNumber()&&secondToLastIsNumber()){
                input.deleteText(holder.length()-1,holder.length());
            }
            else{//for romoving things that arnt numbers
                try{
                    int last = holder.lastIndexOf(' ');//geting loc of last space
                    //case where the user hit a Opp button first without any numbers
                    if(holder.charAt(0)==' '){
                        input.clear();
                    }
                    input.deleteText(last, holder.length());
                }catch(IndexOutOfBoundsException e){
                    input.clear();//should be the case where there is only a number
                }
            }

        }
    }//deleteIt

    
    /**
     * The method swapToIterarive swaps the Mathops opject choice to iterativeMathOps.
     * It is ment to be called in a lambda expresion on the iterative button event.
     */
    public void swapToIterarive(){ 
      choice = iterativeMathOps;
      if(recursive_B.getText().equals("[Recursive]")){
            iterative_B.setText("[Iterative]");
            recursive_B.setText("Recursion");
        }else if(iterative_B.getText().equals("[Iterative]")){
            iterative_B.setText("Iterative");
            choice = basicMathOps;
        }else{
            iterative_B.setText("[Iterative]");
        }
      }//swapToIterarive

    /**
     * The method swapToRecursives swaps the Mathops opject choice to recursiveMathOps.
     * It is ment to be called in a lambda expresion on the recursive button event.
     */
    public void swapToRecursive(){ 
      choice = recursiveMathOps; 
      if(iterative_B.getText().equals("[Iterative]")){
            iterative_B.setText("Iterative");
            recursive_B.setText("[Recursive]");
        }else if(recursive_B.getText().equals("[Recursive]")){
            recursive_B.setText("Recursive");
            choice = basicMathOps;
        }else{
            recursive_B.setText("[Recursive]");
        }
    }//swapToRecursive

    /**
     * The method solveIt solves the current expresion in the input TextField.
     *
     * @param choice The way(recursive|iterative) in which you would like to solve the problem.
     */
    public void solveIt(MathOps choice){
        for(int i=1;i<=31;i++){//clearing the bitbar
            getNodeAtIndex(i).setText("0");
        }
        //System.out.println("solving using "+choice);//tells the user how it was solved
        input_expr = input.getText();
        //System.out.println(input_expr);
        try {
            //choice will be either iterativeMathOps | recursiveMathOps |  basicMathOps
            int result = MathOpsEvaluator.evaluate(choice, input_expr);
          printToBitBar(toBinary(result));
          //System.out.printf("%s = %d \n", input_expr, result);
        String ans = ""+result;
        output.setText(ans);
        } catch (Exception e) {
          System.err.println(e);
          System.err.println("something went wrong!");
        } // try
    }//solveIt

    /**
     * The createNumScreen method creates the input
     * and output TextFields and adds them to the screen.
     */
    public void createNumScreen(){
        input = new TextField("");
        output = new TextField("");
        number_screen.getChildren().addAll(input, output);
    }//createNumScreen

    

    /**
     * The method createBitBar fills the bitBar with its text object
     * and enables there functionality.
     *
     * @return the bitBar
     */
    public HBox createBitBar(){
        bitBar.getChildren().addAll(t31,t30,t29,t28,t27,t26,t25,t24,
        t23,t22,t21,t20,t19,t18,t17,t16,t15,t14,t13,t12,t11,t10,
        t9,t8,t7,t6,t5,t4,t3,t2,t1,t0);
        createBitBarFunctions();
        return bitBar;
    }//createBitBar

    /**
     * The method createBitBarFunctions establishes the ability for the bitBar
     * to change the calulators output as well as alowing
     * the bits to be toggled between zero and one.
     */
    public void createBitBarFunctions(){//can i create a way
        t30.setOnMouseClicked(event -> textFunction(t30));
        t29.setOnMouseClicked(event -> textFunction(t29));
        t28.setOnMouseClicked(event -> textFunction(t28));
        t27.setOnMouseClicked(event -> textFunction(t27));
        t26.setOnMouseClicked(event -> textFunction(t26));
        t25.setOnMouseClicked(event -> textFunction(t25));
        t24.setOnMouseClicked(event -> textFunction(t24));
        t23.setOnMouseClicked(event -> textFunction(t23));
        t22.setOnMouseClicked(event -> textFunction(t22));
        t21.setOnMouseClicked(event -> textFunction(t21));
        t20.setOnMouseClicked(event -> textFunction(t20));
        t19.setOnMouseClicked(event -> textFunction(t19));
        t18.setOnMouseClicked(event -> textFunction(t18));
        t17.setOnMouseClicked(event -> textFunction(t17));
        t16.setOnMouseClicked(event -> textFunction(t16));
        t15.setOnMouseClicked(event -> textFunction(t15));
        t14.setOnMouseClicked(event -> textFunction(t14));
        t13.setOnMouseClicked(event -> textFunction(t13));
        t12.setOnMouseClicked(event -> textFunction(t12));
        t11.setOnMouseClicked(event -> textFunction(t11));
        t10.setOnMouseClicked(event -> textFunction(t10));
        t9.setOnMouseClicked(event -> textFunction(t9));
        t8.setOnMouseClicked(event -> textFunction(t8));
        t7.setOnMouseClicked(event -> textFunction(t7));
        t6.setOnMouseClicked(event -> textFunction(t6));
        t5.setOnMouseClicked(event -> textFunction(t5));
        t4.setOnMouseClicked(event -> textFunction(t4));
        t3.setOnMouseClicked(event -> textFunction(t3));
        t2.setOnMouseClicked(event -> textFunction(t2));
        t1.setOnMouseClicked(event -> textFunction(t1));
        t0.setOnMouseClicked(event -> textFunction(t0));
    }//createBitBarFunctions

    /**
     * The getNodeIndex finds the index of given Text node in an HBox.
     *
     * @param boxy the HBox you are searching.
     * @param texty the Text you are searching for in the HBox.
     */
    public int getNodeIndex(HBox boxy, Text texty){
        int index = boxy.getChildren().indexOf(texty);
        return index;
    }//getNodeIndex


    /**
     * The method textFunction allows the Text objects in the bitbar
     * to change the calculator output when toggeled.
     * This method is ment to be called in a lambda expresion by bitBars text objects.
     *
     * @param texty The Text object that is being modified.
     */
    public void textFunction(Text texty){
        int index = getNodeIndex(bitBar, texty);
        if(texty.getText().equals("1")){
            texty.setText("0");
        }
        else{
            texty.setText("1");
        }
        //this next section i converet bitbart to string
        //then parse that to decimal and send it to the output TectFeild
        int holder = toDecimal(bitBarToString());
        output.setText(""+holder);
        //System.out.println("the box you clicked is located @: "+index);//for testing
    }//textFunction


    /**
     * This method converts a string representing a binary number to a decimal integer value.
     *
     * @param input A string representation of a binary number to be converted to an int.
     * @return the intiger value of the inout
     */
    private int toDecimal(String input){
        int output = Integer.parseInt(input,2);//converting input to a decimal number
        //System.out.println("the bitbar converted to decimal is: "+output);//for testing
        return output;
    }//toDecimal

    /**
     * The method bitBarToString converts the number displaied by the bitBar to a String.
     *
     * @return a string matching what the bitBar displays
     */
    private String bitBarToString(){
        String output = "";
        for(int index =1;index<=31;index++){
            output += getNodeAtIndex(index).getText();
        }
        //System.out.println(output);
        return output;
    }//bitBarToString


    /**
     * This method gets the node at a given index in the bitBar.
     *
     * @param index the intger representation of the index.
     * @return the Text node at the given index.
     */
    public Text getNodeAtIndex(int index){
        Text result = null;
        for(Node texty : bitBar.getChildren()){//cycles through all of the nodes in bitBar
            //I can typecast because all objects in bitBar are of Text
            if(index == getNodeIndex(bitBar,(Text) texty)){
                result = (Text) texty;
                break;
            }
        }
        return result;
    }//getNodeAtIndex


    /**
     * The toBinary method converts an integer value to it binary value and stores it.
     *
     * @param value the integer value to be converted.
     * @return the binary string representation of the inouted value.
     */
    private String toBinary(int value){//gets the value of the answer as a binary string
        //System.out.println(Integer.toBinaryString(value));for testing prints the binary value
        if(value<=2147483647){
            return Integer.toBinaryString(value);
        }
        else{
            return "0";
        }
    }//toBinary

    /**
     * The method printToBitBar takes a string storing a binary number
     * and changes the bitBar so that it matches the number.
     *
     * @param binary the string representation of the binary number to be displaied.
     */
    private void printToBitBar(String binary){
        int a = binary.length();
        int bitbar_loc = 31;//since the rightmost bit in the bitbar is at index 31
        //System.out.println("the num in binary is "+binary+" length "+a);//testing out.println
        if(a>31){
            System.out.println("number to large");
        }
        for(int i=(a-1); i>=0;i--){//Cycles through the binary string
            //checking it to the bitbar location from back to front.
            if(binary.charAt(i)=='1'){
                getNodeAtIndex(bitbar_loc).setText("1");
            }
            bitbar_loc--;
        }
    }

    /**
     * The main method launches the application.
     *
     * @param args contains the supplied comand line arguments.
     */
    public static void main(String[] args) {
	try {
	    Application.launch(args);
	} catch (UnsupportedOperationException e) {
	    System.out.println(e);
	    System.err.println("If this is a DISPLAY problem, then your X server connection");
	    System.err.println("has likely timed out. This can generally be fixed by logging");
	    System.err.println("out and logging back in.");
	    System.exit(1);
	} // try
    } // main

} // CalcApp

