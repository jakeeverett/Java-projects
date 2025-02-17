package cs1302.game;

import java.util.Scanner;
import java.io.File;

/**
 * This is the LatinSquaresGame class. 
 * It holds all of the methods necessary  to play a game of latin squares.
 */
public class LatinSquaresGame {

    private int n_size =0;//size of the game
	private int k =0;// this represents the num of pre determind locations
	private char[] Game_chars; //stores the chars that will be used to play the game
	private String[][] Game_square;
        private String placement;//stores the predetermined starting locations.
    Scanner kb1 = new Scanner(System.in);//the only scanner created for System.in
	
        private int row_guess;//these stroe the users imput from their guesses
        private int col_guess;
	private char char_guess;



/**
 * This is the default constroctor.
 * It sets the game board size, the playable game charachters, and inserts the starting positions into the game board.
 */	
    public LatinSquaresGame(String config){//this config file is pulling the config loc from the args[0] in the LSDriver main method
    	
		try {
			
		    File configFile = new File(config);
		    Scanner configScanner = new Scanner(configFile);
		    
		    n_size = configScanner.nextInt();
		   
		    Game_chars = new char[n_size];
		    Game_square = new String[n_size+1][n_size+1];//sets the size of the game square to 1 higher than N.
		    //This is so I can store the row and colum numbers in the main game square
		    
		    int i=0;
		    while(i<n_size) {//fills the game cha s array with the playable chars
		    	Game_chars[i] = configScanner.next().charAt(0);
		    	i++;
		    }

		    k = configScanner.nextInt();
		    placement = configScanner.nextLine();
		    
		 } catch (Exception e) {
		    System.out.println("something went wrong :" + e.getMessage());
		    System.exit(0);
		    //The print statment is for debugging 
		 } 
		for(int i =0;i<n_size+1;i++) {
	    	for(int j =0;j<n_size+1;j++) {
	    		Game_square[i][j] = " ";//this bit of code fills the array with a space in each position
		    }
		if (i>=1) {//this fills the outside row and col with the correct outside values (ie numbering the rows and cols)
		    	Game_square[i][0] = Integer.toString(i-1);
		    	Game_square[0][i] = Integer.toString(i-1);	    		
	    	}
	    }
		

		for(int r=0;r<(k*3-1);r++) {//this next section inserts the predetermined posisions into the game square
			int loc_x =ordnalIndexOf(placement, " ", r*3+1);//finds the space before the row
			int loc_y = ordnalIndexOf(placement, " ", r*3+2);//finds the space before the col
			int loc_char = ordnalIndexOf(placement, " ", r*3+3);//finds the space before the char
			if ((loc_x>=0)&&(loc_y>=0)) {
				int x = Integer.parseInt(placement.substring(loc_x+1, loc_y));//pulls the row num
				int y = Integer.parseInt(placement.substring(loc_y+1,loc_char));//pulls the col num
				Game_square[x+1][y+1] = Character.toString(placement.charAt(loc_char+1));
			}
		}
		
    }
	
 /**
 * This method prints the welcome message and tells the player
 *  the game size, playable chars, and the number of preset starting positions
 */
    void printWelcome() {
    	System.out.println("  _           _   _        _____");
    	System.out.println(" | |         | | (_)      / ____|");
    	System.out.println(" | |     __ _| |_ _ _ __ | (___   __ _ _   _  __ _ _ __ ___  ___");
    	System.out.println(" | |    / _` | __| | '_ \\ \\___ \\ / _` | | | |/ _` | '__/ _ \\/ __|");
    	System.out.println(" | |___| (_| | |_| | | | |____) | (_| | |_| | (_| | | |  __/\\__ \\");
    	System.out.println(" |______\\__,_|\\__|_|_| |_|_____/ \\__, |\\__,_|\\__,_|_|  \\___||___/");
    	System.out.println("                          CSCI 1302 | | v2018.fa");
    	System.out.println("                                    |_|");
    	System.out.print("n = "+ n_size+" { ");
    	int count=0;
    	for(count=0;count<n_size-1;count++) {//loop to print the n variables
    		System.out.print(Game_chars[count]+", ");
    	}
    	System.out.println(Game_chars[count]+" }");
    	System.out.println("k = "+k);
    }


/**
 * This next method finds the nth occurance of a substring within a string 
 */
    //code it like you stole it
    //taken from stackextchange, Supa Mike just said to make a comment denoting where I got it.
    public static int ordnalIndexOf(String str,String substring, int n) {
    	int pos = str.indexOf(substring);
    	while (--n>0 && pos !=-1) {
	    pos = str.indexOf(substring, pos+1);//sets position to the next occurence till it hits n
    	}
    	return pos;
    }
    


/**
 * The next method prints the contents of the square.
 * It also calls the ordinalIndexOf method
 */
    //Prepare yourself for the horror that is the next method.
    //This is what happens when you start a project and then halfway throuh you relize you forgot to acount for some stuff
    //but you dont want to rewrite everything so you add a bunch of if statments.
  void displaySquare() {//also this method totally fits on my screen /s
    	for(int i =0;i<n_size+1;i++) {
	    for(int j =0;j<n_size+1;j++) { //cycles throught the entier gamesquare	
	    		int test=0;
	    		if ((j>=1)&&(i>=1)) {//pulls only the sections of the game square that contain the acctual game
			        for(int r=0;r<(k*3-1);r++) {//this for loop is ment to cycle through the placement string
				//It uses k*(3-1) because that is the number of space present in the placement string
				//I am doing this because in order to put [] around the predetermined locations i need to know where they are
	    				int loc_x =ordnalIndexOf(placement, " ", r*3+1);
	    				int loc_y = ordnalIndexOf(placement, " ", r*3+2);
	    				int loc_char = ordnalIndexOf(placement, " ", r*3+3);
	    				int x=-45; int y=-45;//these are initaliesed to neg vals so that it dosent cause and error
	    				if ((loc_x>=0)&&(loc_y>=0)) {//so that it dosnt get a null pointer
	    					x = Integer.parseInt(placement.substring(loc_x+1, loc_y));
	    					y = Integer.parseInt(placement.substring(loc_y+1,loc_char));
	    				}
		    			if (((i-1)==x)&&((j-1)==y)) {//this sets test equal to one if the location we are currently cycling through
					    // is the same as one of the predeterrmined starting locations
					    test =1;
		    			}
		    		}
		    		if (n_size>=11) {//this is so the game is printed differently if there are double digit numbers
				    if (test==1){//adds []
			    			System.out.print("["+Game_square[i][j]+"] |");
			    		}
			    		else {
			    			System.out.print(" "+Game_square[i][j]+"  |");
			    		}
		    		}
		    		else {
		    			if (test==1){
			    			System.out.print("["+Game_square[i][j]+"]|");
			    		}
			    		else {
			    			System.out.print(" "+Game_square[i][j]+" |");
			    		}
		    		}
	    		}
	    		else if((j==0)&&(i>=1)) {//this if only pulls the stuff in the first colum that is lower than the first row
			    if( n_size>=11) {//prints diferently if game has double digits
	    				if (i>=11) {
		    				System.out.print(Game_square[i][j]+" |");
		    			}
		    			else {
		    				System.out.print(" "+Game_square[i][j]+" |");
		    			}
	    			}
	    			else {
	    				System.out.print(Game_square[i][j]+" |");
	    			}
	    		}
	    		else{//this section should only apply to the stuff in the first row
			    if (n_size>=11) {//prints diferently for double digits
	    				if (i==0 && j==0) {//isolates the first square of the array
	    					System.out.print(Game_square[i][j]+"   ");
	    				}
	    				else if (i==0 && j>=11) {//changes the top rows format if the num cols go past 9
	    					System.out.print(" "+Game_square[i][j]+"  ");
	    				}
	    				else {
	    					System.out.print(" "+Game_square[i][j]+"   ");
	    				}
	    			}
	    			else {
	    				System.out.print(Game_square[i][j]+"   ");
	    			}
	    		}	
		    }
	    	System.out.println();
	    }
    }

/**
 * This method test wheather a specific section of a string Is an Int using a try catch loop.
 * the method also inclueds several other tests to make writeing the promt User method easier.
 * Additionaly this method sets the row_guess equal to the users input.
 */
    public boolean isInt(String input,int n_end) {
    	boolean test =true;
    	int pos = ordnalIndexOf(input," ",n_end);
    	if (pos!=-1) {//this tells us if the user formated their imput in the correct manor, IE row_col_char
    		String holder = input.substring(0,pos);
        	int length = holder.length();
        	try { 
    			row_guess = Integer.parseInt(holder);
    			if ((row_guess<0)||(row_guess>n_size-1)) {//makes sure the row guess is in the playable area
    				test = false;
    			}
    			if ((row_guess<10)&&(length>1)) {//this is so we can make sure the user dosent input 02 01 x
    				test = false;
    			}
    		 } catch (Exception e) {
    		    test = false;
    		 }
    	}
    	else {
    		test = false;
    	}
    	
    	
    	return test;
    }
    
/**
 * This  is an overloaded version of the isInt method that allows me to check the second input in the string or any specified position.
 * This also sets the col_guess equal to the users input.
 */
    public boolean isInt(String input,int n_end,int m_start) {
    	boolean test =true;
    	int pos_end = ordnalIndexOf(input," ",n_end);
    	int pos_start = ordnalIndexOf(input," ",m_start)+1;//plus one so it dosnt start on the " "
    	if ((pos_start!=-1)&&(pos_end!=-1)) {
    		String holder = input.substring(pos_start,pos_end);
        	int length = holder.length();
        	try { 
    			col_guess = Integer.parseInt(holder);
    			if ((col_guess<0)||(col_guess>n_size-1)) {//makes sure the col guess is in the playable area
    				test = false;
    			}
    			if ((col_guess<10)&&(length>1)) {//this is so we can make sure the user dosent input 02 01 x
    				test = false;
    			}
    		 } catch (Exception e) {
    		    test = false;
    		 }
    	}
    	else {
    		test = false;
    	}
    	return test;
    }
    
 /**
 * This method promts the usser for their imput and then
 * check to see if it is valid.
 */   
    void promtUser() {
    	int test = 0;
    	while (test==0){
    		System.out.print("latin-squares> ");
    		String user_input = kb1.nextLine();
    		int length = user_input.length();
    		if ((user_input.charAt(0)=='q')&&(length==1)) {
        		System.exit(0);
        	}
    		else if ((this.isInt(user_input,1)&&(this.isInt(user_input,2,1)))) {//checks to make sure that what the user inputed
		    //for row and col are both numbers
		    int n = ordnalIndexOf(user_input," ",2)+1;//this gets the location of their char guess
		    String player_char_guess = user_input.substring(n);
		    int guess_length = player_char_guess.length();
		    if ((guess_length==1)&&(row_guess<=n_size-1)&&(col_guess<=n_size-1)&&(Character.isDigit(user_input.charAt(n))==false)) {
    				int l = Game_chars.length;
    				boolean isplayable_char = false;
    				for (int i=0;i<l;i++) {//makes shure that the char they entered is playable
    					if (user_input.charAt(n)==Game_chars[i]) {
    						isplayable_char = true;
    					}
    				}
    				int char_guess_size = 0;
    				//this section of code might be removed, it makes it so that the player cannont overwrite the starting positions
    				for(int r=0;r<(k*3-1);r++) {
    					int loc_x =ordnalIndexOf(placement, " ", r*3+1);//finds the space before the row
    					int loc_y = ordnalIndexOf(placement, " ", r*3+2);//finds the space before the col
    					int loc_char = ordnalIndexOf(placement, " ", r*3+3);//finds the space before the char
    					if ((loc_x>=0)&&(loc_y>=0)) {
    						int x = Integer.parseInt(placement.substring(loc_x+1, loc_y));//pulls the row num
    						int y = Integer.parseInt(placement.substring(loc_y+1,loc_char));//pulls the col num
    						if ((row_guess==x)&&(col_guess==y)) {
    							System.out.println("error> invalid input!");
    						}
    					}
    				}
    				if (isplayable_char==true){
				    char_guess = user_input.charAt(n);
				    test =1;
    				}
    				else {
    					System.out.println("error> invalid input!");
    				}
    			}
    			else {
    				System.out.println("error> invalid input!");
    			}
    		}
        	else {
        		System.out.println("error> invalid input!");
        	}
    	}
    }

/**
 * This method checks to see if some thing is a latin Square.
 */
    boolean isLatinSquare() {
    	boolean isLSquare = true;
    	for(int i=1;i<n_size+1;i++) {
			for(int j=1;j<n_size+1;j++) {
				if (Game_square[i][j].equals(" ")==true) {
					isLSquare = false;
				}
				char char_tocheck = Game_square[i][j].charAt(0);//this section should take each char in the game square and the compair it to all of the other chars in its row  and col to check if it is the same
				for(int x=1;x<n_size+1;x++) {
					if (x!=i) {//these check if the char_tocheck matches another char in its row or colum
						if (char_tocheck==Game_square[x][j].charAt(0)) {
							isLSquare =false;
						}
					}
					if (x!=j) {
						if (char_tocheck==Game_square[i][x].charAt(0)) {
							isLSquare = false;
							}
						}
				}
			}
		}
    	return isLSquare;
    }
    
/**
 * This method prints the wining statment.
 */
    public void printWin(){
	System.out.println("                                .''.");
	System.out.println("       .''.             *''*    :_\\/_:     . ");
	System.out.println("      :_\\/_:   .    .:.*_\\/_*   : /\\ :  .'.:.'.");
	System.out.println("  .''.: /\\ : _\\(/_  ':'* /\\ *  : '..'.  -=:o:=-");
	System.out.println(" :_\\/_:'.:::. /)\\*''*  .|.* '.\\'/.'_\\(/_'.':'.'");
	System.out.println(" : /\\ : :::::  '*_\\/_* | |  -= o =- /)\\    '  *");
	System.out.println("  '..'  ':::'   * /\\ * |'|  .'/.\\'.  '._____");
	System.out.println("      *        __*..* |  |     :      |.   |' .--\"|");
	System.out.println("       _*   .-'   '-. |  |     .--'|  ||   | _|    |");
	System.out.println("    .-'|  _.|  |    ||   '-__  |   |  |    ||      |");
	System.out.println("    |' | |.    |    ||       | |   |  |    ||      |");
	System.out.println(" ___|  '-'     '    \"\"       '-'   '-.'    '`      |____");
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	System.out.println("    CONGRATULATIONS! YOU COMPLETED THE LATIN SQUARE!");
    }

/**
 * This method invokes the other methods in the order needed to play the game.
 */
void play() {
    	this.printWelcome();
    	boolean is_gameover =false;
    	while (is_gameover==false) {
    		this.displaySquare();
    		this.promtUser();
    		Game_square[row_guess+1][col_guess+1]= Character.toString(char_guess);//+1 because the matrix is one bigger to acomodate the numbered rows
    		if (this.isLatinSquare()) {
    			is_gameover =true;
    		}
    		boolean square_full =true;
    		for(int i =1;i<n_size+1;i++) {
    	    	for(int j =1;j<n_size+1;j++) {
    	    		if (Game_square[i][j].equals(" ")){
    	    			square_full=false;
    	    		}
    	    	}
    		}
    		if ((square_full==true)&&(this.isLatinSquare()==true)) {
    			this.displaySquare();
    			this.printWin();
    			System.exit(0);
    		}
    		
    	}
    }
}