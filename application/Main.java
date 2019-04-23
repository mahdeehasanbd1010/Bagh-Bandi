package application;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {
	
	static int screenWidth,screenHeight;
	
	Button twoPlayerButton,exitButton,singlePlayerButton;
	
	@Override
	public void start(Stage primaryStage) {
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		screenWidth = gd.getDisplayMode().getWidth();
		screenHeight = gd.getDisplayMode().getHeight();
		
		singlePlayerButton = new Button();
		singlePlayerButton.setText("Single Player");
		singlePlayerButton.setPrefSize(screenWidth/5,screenHeight/14);
		singlePlayerButton.setLayoutX(2*(screenWidth/5));
		singlePlayerButton.setLayoutY(1*(screenHeight/8));
		
		twoPlayerButton = new Button(); 
		twoPlayerButton.setText("Two Player");
		twoPlayerButton.setPrefSize(screenWidth/5, screenHeight/14);
		twoPlayerButton.setLayoutX(2*(screenWidth/5));
		twoPlayerButton.setLayoutY(2*(screenHeight/8));
		
		exitButton = new Button();
		exitButton.setText("Exit");
		exitButton.setPrefSize(screenWidth/5, screenHeight/14);
		exitButton.setLayoutX(2*(screenWidth/5));
		exitButton.setLayoutY(3*(screenHeight/8));
		
		Pane root = new Pane();
		
		
		Image im = new Image("/application/tableImage.jpg",false);
        ImageView iv = new ImageView();
        iv.setImage(im);
        iv.setFitWidth(screenWidth);
        iv.setFitHeight(screenHeight);
		
        root.getChildren().add(iv);
		root.getChildren().addAll(singlePlayerButton,twoPlayerButton,exitButton);
		
		Scene scene = new Scene(root,screenWidth,screenHeight);
		
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Bagh Bandi");
		primaryStage.setMaximized(true);
		primaryStage.show();
		
		
		singlePlayerButton.setOnMouseClicked(e->{
			
			SinglePlayer SP = new SinglePlayer();
			
			try {
				SP.start(primaryStage);
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
			
		});
		
		
		twoPlayerButton.setOnAction(e->{
			
			TwoPlayer TP = new TwoPlayer();
			
			try {
				
				TP.start(primaryStage);
			
			} catch (Exception e1) {
				
				e1.printStackTrace();
				
			}
			
		});
		
		
		exitButton.setOnMouseClicked(e->{
			
			System.exit(0);
			
		});
		
	}
	
	
	public static void main(String[] args) {
		
		launch(args);
		
	}
	
	
}



