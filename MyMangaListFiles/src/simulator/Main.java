package simulator;

/**
 * ICS4U CPT
 * @author Ethan L.
 */

import simulator.ObjectClasses.*;
import simulator.MethodsClasses.*;
import java.util.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.geometry.HPos;
import javafx.geometry.Insets;

public class Main extends Application {
	public static String accountFile;
	public static String dataFile;
    /**
     * Start method. Runs when the application starts.
     * 
     * @param primaryStage - the stage that displays all the scenes.
     */
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("'My Manga List' by Ethan Lai");
        primaryStage.setHeight(500);
        primaryStage.setWidth(600);
        primaryStage.setResizable(false);
        accountFile = System.getProperty("user.dir") + "\\Accounts.txt";
        // accountFile = "Accounts.txt";
        dataFile = "AllManga.csv";
        System.out.print("Account File Path: " + accountFile);
        Account.signInScreen(primaryStage);
    }

    /**
     * Sets up a screen that displays the main menu.
     * 
     * @param primaryStage - the stage that displays all the scenes.
     * @param mangaList - an arraylist filled with Manga objects.
     * @param userList - an arraylist filled with UserManga objects
     * @param currentAccount - an Account object.
     */
    public static void mainMenuScreen(Stage primaryStage, ArrayList<Manga> mangaList, ArrayList<UserManga> userList, Account currentAccount) {

        // Refreshes the stage.
        primaryStage.setWidth(601);

        // Setting up gridpane to organize children.
        GridPane menuGrid = new GridPane();
        menuGrid.setVgap(40);
        menuGrid.setHgap(10);
        menuGrid.setGridLinesVisible(false);
        menuGrid.setAlignment(Pos.TOP_CENTER);
        menuGrid.setPadding(new Insets(25, 25, 25, 25));
        Font menuFont = Font.font("Comic Sans MS", FontWeight.BOLD, 12);

        // Creating label for title.
        Label mainTitle = new Label(currentAccount.getUsername() + "'s Manga List");
        mainTitle.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        menuGrid.add(mainTitle, 0, 0);
        GridPane.setHalignment(mainTitle, HPos.CENTER);

        // Creating my list button.
        Button myListBtn = new Button();
        menuGrid.add(myListBtn, 0, 1);
        GridPane.setHalignment(myListBtn, HPos.CENTER);
        myListBtn.setText("My List");
        myListBtn.setFont(menuFont);
        myListBtn.setWrapText(true);
        myListBtn.setMaxSize(100, 50);
        myListBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                MyList.myListScreen(primaryStage, mangaList, userList, currentAccount);
            }
        });

        // Creating database button.
        Button databaseBtn = new Button();
        menuGrid.add(databaseBtn, 0, 2);
        GridPane.setHalignment(databaseBtn, HPos.CENTER);
        databaseBtn.setText("Database");
        databaseBtn.setFont(menuFont);
        databaseBtn.setWrapText(true);
        databaseBtn.setMaxSize(100, 50);
        databaseBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Database.databaseScreen(primaryStage, mangaList, userList, currentAccount);
            }
        });

        // Creating game comparison button.
        Button compareBtn = new Button();
        menuGrid.add(compareBtn, 0, 3);
        GridPane.setHalignment(compareBtn, HPos.CENTER);
        compareBtn.setText("Graphs");
        compareBtn.setFont(menuFont);
        compareBtn.setWrapText(true);
        compareBtn.setMaxSize(100, 50);
        compareBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Compare.compareScreen(primaryStage, mangaList, userList, currentAccount);
            }
        });

        // Creating button to log off.
        Button logOffBtn = new Button();
        menuGrid.add(logOffBtn, 0, 4);
        GridPane.setHalignment(logOffBtn, HPos.CENTER);
        logOffBtn.setText("Save & Quit");
        logOffBtn.setFont(menuFont);
        logOffBtn.setWrapText(true);
        logOffBtn.setMaxSize(100, 50);
        logOffBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                // Try and catch method to read text file
                try {
                    Account.savingToAccount(primaryStage, accountFile, userList, currentAccount);
                    
                }catch (IOException e){
                    e.printStackTrace();
                }
                primaryStage.close();
            }
        });

        // Displaying gridpane to stage.
        primaryStage.setScene(new Scene(menuGrid));
        primaryStage.show();
    }

    /**
     * Main method. Launches the application.
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Application.launch(args);
    }
}
 
