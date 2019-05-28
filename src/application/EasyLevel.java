package application;

import static application.Main.screenHeight;
import static application.Main.screenWidth;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EasyLevel extends Application{

	Scene scene;
	Button tiger , goat , back;
	
	
	public void backToSinglePlayer(Stage primaryStage) throws Exception {
		
		SinglePlayer SP = new SinglePlayer();
		SP.start(primaryStage);
		
	}
	
	@Override
	public void start(Stage primaryStage) {
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		screenWidth = gd.getDisplayMode().getWidth();
		screenHeight = gd.getDisplayMode().getHeight();
		
		tiger = new Button();
		tiger.setText("Tiger");
		tiger.setPrefSize(screenWidth/5,screenHeight/14);
		tiger.setLayoutX(2*(screenWidth/5));
		tiger.setLayoutY(2*(screenHeight/8));
		
		goat = new Button(); 
		goat.setText("Goat");
		goat.setPrefSize(screenWidth/5, screenHeight/14);
		goat.setLayoutX(2*(screenWidth/5));
		goat.setLayoutY(1*(screenHeight/8));
		
		
		back = new Button(); 
		back.setText("Back");
		back.setPrefSize(screenWidth/5, screenHeight/14);
		back.setLayoutX(2*(screenWidth/5));
		back.setLayoutY(3*(screenHeight/8));
		
		
		
		Pane root = new Pane();
		
		Image im = new Image("/application/tableImage.jpg",false);
        ImageView iv = new ImageView();
        iv.setImage(im);
        iv.setFitWidth(screenWidth);
        iv.setFitHeight(screenHeight);
		
        root.getChildren().add(iv);
		
		root.getChildren().addAll(tiger,goat,back);
		
		
		scene = new Scene(root,screenWidth,screenHeight);
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Bagh Bandi");
		primaryStage.setMaximized(true);
		primaryStage.show();
		
		
		goat.setOnMouseClicked(e->{
			
		
			EasyLevelAgainstTiger ELAT = new EasyLevelAgainstTiger();
				
			try {
				
				ELAT.start(primaryStage);
			
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
			
			
		});
		

		tiger.setOnMouseClicked(e->{
			
			
			EasyLevelAgainstGoat ELAG = new EasyLevelAgainstGoat();
				
			try {
				
				ELAG.start(primaryStage);
			
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
			
		});
		
		back.setOnMouseClicked(e->{
			
			try {
				backToSinglePlayer(primaryStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		
	}

}
