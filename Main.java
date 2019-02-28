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
	
	Button playButton,exitButton;
	
	@Override
	public void start(Stage primaryStage) {
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		screenWidth = gd.getDisplayMode().getWidth();
		screenHeight = gd.getDisplayMode().getHeight();
		
		playButton = new Button(); 
		playButton.setText("Two Player");
		
		playButton.setPrefSize(screenWidth/5, screenHeight/14);
		playButton.setLayoutX(2*(screenWidth/5));
		playButton.setLayoutY(screenHeight/8);
		
		exitButton = new Button();
		exitButton.setText("Exit");
		
		exitButton.setPrefSize(screenWidth/5, screenHeight/14);
		exitButton.setLayoutX(2*(screenWidth/5));
		exitButton.setLayoutY(2*(screenHeight/8));
		
		Pane root = new Pane();
		
		Image im = new Image("/application/tigerGoatWallpaper.jpg",false);
        ImageView iv = new ImageView();
        iv.setImage(im);
        iv.setFitWidth(screenWidth);
        iv.setFitHeight(screenHeight);
		
        root.getChildren().add(iv);
		
		
		root.getChildren().addAll(playButton,exitButton);
		
		Scene scene = new Scene(root,screenWidth,screenHeight);
		
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Bagh Bandi");
		primaryStage.setMaximized(true);
		primaryStage.show();
		
		playButton.setOnAction(e->{
			
			OneToOne OTO = new OneToOne();
			try {
				OTO.start(primaryStage);
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



