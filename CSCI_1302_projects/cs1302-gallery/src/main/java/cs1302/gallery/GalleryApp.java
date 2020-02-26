package cs1302.gallery;

import java.util.Random;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.text.Text;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import java.net.URL;
import java.io.InputStreamReader;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import java.net.MalformedURLException;
import java.io.IOException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.geometry.Pos;

/**
 * The GalleryApp class searches the itunes API for album artwork and displayes it.
 * @author Jacob Everett
 */
public class GalleryApp extends Application {    
    
    TextField  query;//These are declared outside of a method so that
    //they can be referenced in several different methods.
    Button play_B;
    Button update_B;
    ProgressBar prog_Bar;//Declared outside of a method so that
    //it can be referenced in multiple different methods.
    
    GridPane gridly = new GridPane();
    String[] image_urls= new String[50];//has room for 50 different urls
    String new_user_search = " ";
    KeyFrame keyFrame;
    Timeline timeline= new Timeline();
    
    int play_pause_counter =0;//used in the playPauseButtonClick method
    //I just dont want it set to zero each time the method is called
    // so that why i didnt put it in it.
    
    
    /**
     * The userInputConversion method takes in a String input, making everything in that
     * string lowercase and replacing all spaces with '+'.
     * @param input The users search input (I.E. "Jack johnson"). 
     */
    public String userInputConversion(String input){
      input = input.toLowerCase();
      String holder = input;
      for(int i=0;i<input.length();i++){
        if(holder.charAt(i)==' '){//replacing spaces with +
          holder = holder.substring(0,i)+"+"+holder.substring(i+1);
        }
      }
      return holder;
    }//userInputConversion


    /**
     * The appleSearchImages method searches itunes API for image urls. 
     * It then fills a String array called image_urls with the results.
     * @param user_input what the user wants to search already formated( I.E. "jack+johnson").
     */
    public void appleSearchImages(String user_input) {
    //This method used to consit of a large for loop which was longer than the windows height.
    //It was broken up into the urlParser and urlTester methods.
      Thread t = new Thread(() -> {
        int j = 0;//This to values are inisalized and reset to zero here so that they can
        //be passed to the urlParser method and again to the urlTester method inside of urlParser.
        urlParser(j, user_input);
      });
      t.setDaemon(true);
      t.start();
    }//appleSearch
    
    
    //old interenals of the appleSearchImages try { }
    /**
     * The urlParser method searches itunes API for the users search and then passes the results
     * to the urlTester method.
     * @param j The index in the String array (image_url) where the image url should be placed.
     * @param user_input What the user wants to search already formated( I.E. "jack+johnson"). 
     */
    public void urlParser(int j, String user_input){
      try {
        String url_input = "https://itunes.apple.com/search?term="+user_input;
        //System.out.println("url_input "+url_input);
          URL url = new URL(url_input);
          InputStreamReader reader = new InputStreamReader(url.openStream());
          
          JsonParser jp = new JsonParser();
          JsonElement je = jp.parse(reader);
          
          JsonObject root = je.getAsJsonObject();                      // root of response
          JsonArray results = root.getAsJsonArray("results");          // "results" array
          int numResults = results.size();                             // "results" array size
          //System.out.println("numresults: " + numResults); old debugging line
          for (int i = 0; i < numResults; i++) {                       
              JsonObject result = results.get(i).getAsJsonObject();    // object i in array
              JsonElement artworkUrl100 = result.get("artworkUrl100"); // artworkUrl100 member
              if (artworkUrl100 != null) {                             // member might not exist
                 String artUrl = artworkUrl100.getAsString();        // get member as string
                 j = urlTester(j, artUrl, user_input);
                 //System.out.println(artUrl);                         // print the string
              } // if
          } // for
          
       }catch (MalformedURLException e) {
          
       }catch (IOException e) {
          
       }
       if(j>20){//checking if we have enough images to populate the grid
         Platform.runLater(() -> creatingImageGrid(image_urls));
       }else{
         //System.out.println("not enough urls");old debugging line
          Platform.runLater(() -> lertAlert());
          Platform.runLater(() -> prog_Bar.setProgress(1));
         //its so the prog goes to 100 if they enter in a invalid search
       }  
    }//urlParser
    
    /**
     * The lertAlert method creats an alert box if the url search doesn't return enough urls.
     */
    public void lertAlert(){
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Error");
      alert.setHeaderText("Error message");
      alert.setContentText("Not enough urls!");
      alert.showAndWait(); 
    }
    
    
    
    //This method is ment to be called within the urlParser for loop.
    //This is also taken from the for loop that used to be in the apple search images method
    //and which was then put in the urlParser method.
    /**
     * The urlTester method test the results of the urlParser method for duplicate urls.
     * If a url is not a duplicate it is added to the image_url array.
     * The method also calls the updateProg method.
     * @param j the index in the String array (image_url) where the image url should be placed
     * @param artUrl the url of the image found searching itunes
     * @param user_input what the user wants to search already formated( I.E. "jack+johnson"). 
     */
    public int urlTester(int j, String artUrl, String user_input){
      if(j<50){//to avoid a null pointer 
      //only apliciable if the num of returnd results is greater than 50
         boolean test = true;
         for (int x=0;x<j;x++){//kicks out duplicate urls
           if(image_urls[x].equals(artUrl)){
             test = false;
           }
         }
         if (test){//if it passes the test the url gets added to the array
           image_urls[j] = artUrl;
           j++;
           //percentcounter =j;
         }//if 
       }//if
      return j;
    }//urlTester
    
    /**
     * The updataProg updates the progress bar bassed on the apple API search.
     * @param j the index in the String array (image_url) where the image url should be placed
     */
    public void updateProg(int j){//come back to this tomorrow
      double index = j;        
      double percent_complete = (index/20);//we only care about the progress up to 21
      //because that is the minimum number that we need
      boolean search_finished = false;
      if (!search_finished){
        prog_Bar.setProgress(percent_complete);
      }
      if (percent_complete ==1){
        search_finished = true;
        prog_Bar.setProgress(1);
      }//if
    }//updateProg
    
    
    
    
    
    /**
     * The creatingImageGrid method populates the GridPane with the images found on itunes.
     * It also calls the update progress (updateProg) method to increment the pogress as
     * images are added to the grid. 
     * @param urls_of_images an array contaning the urls for the images
     */
    public void creatingImageGrid(String[] urls_of_images){
    gridly.getChildren().clear();//clears the old images
      int j =0;
      for (int i =0;i<21;i++){
	      Image image = new Image(urls_of_images[i]);
        gridly.add(new ImageView(image), i%5, j);
        updateProg(i); //I decided to call updateProg here
        //becuse this is the section that should take the most time.
        if (i%5==0  && i!=0){//Incrementing j, the row number, so that
        //it only increments after each column in the row has been filled.
          j++;
        }//if
      }//for
    }//creatingImageGrid
    
    
    
    
    /**
     * The start method calls the methods to instatiate
     * the different parts of the VBox we are using.
     * Then they are all added to the VBox.
     * @param stage where the GUI is displaied
     */
    @Override
    public void start(Stage stage) {
      VBox pane = new VBox();
      MenuBar menuBar = createMenuBar();
      ToolBar toolBar = createToolBar();
      HBox progbox = new HBox();//box for prog_Bar and texty
      prog_Bar = new ProgressBar();
      Text texty = new Text("Images provided courtesy of itunes");
      progbox.getChildren().addAll(prog_Bar, texty);
      progbox.setAlignment(Pos.CENTER_LEFT);//centering texty
      pane.getChildren().addAll(menuBar, toolBar, gridly, progbox);
      Scene scene = new Scene(pane);
      stage.setWidth(500);
      stage.setHeight(490);
      stage.setTitle("[XYZ] Gallery!");
      stage.setScene(scene);
      //stage.sizeToScene(); no longer need this since im seting the size
      stage.show();
	    appleSearchImages("pop");
    } // start
        

    /**
     * The createKeyFrame method instatiates the keyFrame object.
     * It also replaces random images in the gridpane with unused images.
     */
    public void createKeyFrame(){
     keyFrame = new KeyFrame(Duration.seconds(2), e -> {
       Random randy = new Random();
       int unused_urls =0;
       for (int i=0;i<image_urls.length;i++){//counting unused urls
         if (i>19 && image_urls[i]!= null){//this should get the number of unsed urls
           unused_urls++;
        }
       }
       int place = randy.nextInt(unused_urls)+20;
       //System.out.println("place " +place); old debugging line
       int col = randy.nextInt(5);
       int row = randy.nextInt(4);
       //removing node at specific location
       gridly.getChildren().remove(getNodeByRowColumnIndex(row,col,gridly));
       Image imagey = new Image(image_urls[place]);//geting a random image
       gridly.add(new ImageView(imagey), col, row);//replacing the removed node with a new one
     });//end KeyFrame lambda
    }//createKeyFrame

    /**
     * The getNodeByRowColumnIndex method finds a specific node in a GridPane
     * given its row and column.
     * This method was taken from
     * https://stackoverflow.com/questions/20825935/javafx-get-node-by-row-and-column
     * and slightly modified by me. 
     * @param row the row of the node in the gridpane you are trying to find.
     * @param column the column of the node in the gridpane you are trying to find.
     * @param gridPane the GridPane you are searching.
     */
    public Node getNodeByRowColumnIndex(final int row,final int column,GridPane gridPane){
    Node result = null;
    for(Node node : gridPane.getChildren()) {//cycling through all of the nodes in the gridpane
        if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
            result = node;
            break;
        }
    }
    return result;
  }//getNodeByRowColumnIndex



    
    /**
     * The setTimeline method is so the two lines dont throw
     *  <identifier> expected errors.
     * There is no reason other than this.
     * Yes its idiotic but after 2 hours in 307 with multiple TA's
     * this is still the only way to fix the problem.
     */
    private void setTimeline(){
    	timeline.setCycleCount(Timeline.INDEFINITE);
    	timeline.getKeyFrames().add(keyFrame);
    }
    
    /**
     * The createToolBar method simpliy instatiates objects used in the tool bar and adds them
     * to the toolBar.
     */
    private ToolBar createToolBar(){
      play_B = new Button("Play");
      play_B.setOnAction(event -> playPauseButtonClick());
      update_B = new Button("Update Images");
      update_B.setOnAction(event -> updateButtonClick());
      Text text = new Text("Search Query");
      query = new TextField("Search Bar");
      ToolBar toolBar = new ToolBar(
         play_B,
         text,
         query,
         update_B
      );
      return toolBar;
    }// createToolBar
    

    
    /**
     * The playPauseButtonClick calls the createKeyFrame and setTimeline methods
     * So that when toggeled the play pause button replaces random images 
     * in the gridpane every 2 seconds and changes the buttons text.
     */
    private void playPauseButtonClick(){
      if(play_B.getText() == "Pause"){
        play_B.setText("Play");
        timeline.stop();
      }
      else{
        play_B.setText("Pause");
        if(image_urls.length>20){//we need atleast 21 urls to shuffle
          if (play_pause_counter ==0){//beacsus these methods should only ever be run once
          //if these methods execute more than once multiple images get replaced each time
            createKeyFrame();
            setTimeline();
          }
          timeline.play();
          play_pause_counter++;
        }
      }
    }//playPauseButtonClick
    
    
    
    
    /**
     * The updateButtonClick method converts the users search input to a readable form
     * using the userInputConversion method.
     * It then passes it to the appleSearchImages method.
     */
    private void updateButtonClick(){
      prog_Bar.setProgress(0);
      new_user_search = userInputConversion(query.getText());
      appleSearchImages(new_user_search);
    }//updateButtonClick
    
    
    /**
     * The createToolBar method simpliy instatiates objects used in the menu bar and adds them.
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
     * The main method runs the program.
     * @param args the user input when they run the class file.
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

} // GalleryApp

