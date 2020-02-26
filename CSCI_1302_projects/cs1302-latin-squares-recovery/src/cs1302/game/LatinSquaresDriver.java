package cs1302.game;


/**
 *This is the LatinSquaresDriver class.
 *It contains the main method.
 */
public class LatinSquaresDriver {

    /**
     *This is the main method. It creates an new latinSquaresGame and calls its play method.
     */
	public static void main(String[] args) {
	    	LatinSquaresGame game = new LatinSquaresGame(args[0]);
			game.play();
	}

}
