package application;

import static application.Main.screenHeight;
import static application.Main.screenWidth;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SinglePlayer extends Application {

	Button easy,medium,hard;
	Scene scene;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		easy = new Button();
		easy.setText("Easy");
		easy.setPrefSize(screenWidth/5,screenHeight/14);
		easy.setLayoutX(2*(screenWidth/5));
		easy.setLayoutY(1*(screenHeight/8));
		
		medium = new Button(); 
		medium.setText("Medium");
		medium.setPrefSize(screenWidth/5, screenHeight/14);
		medium.setLayoutX(2*(screenWidth/5));
		medium.setLayoutY(2*(screenHeight/8));
		
		
		hard = new Button();
		hard.setText("Hard");
		hard.setPrefSize(screenWidth/5, screenHeight/14);
		hard.setLayoutX(2*(screenWidth/5));
		hard.setLayoutY(3*(screenHeight/8));
		
		Pane root = new Pane();
		
		Image im = new Image("/application/tableImage.jpg",false);
        ImageView iv = new ImageView();
        iv.setImage(im);
        iv.setFitWidth(screenWidth);
        iv.setFitHeight(screenHeight);
		
        root.getChildren().add(iv);
		
		root.getChildren().addAll(easy,medium,hard);
		
		
		scene = new Scene(root,screenWidth,screenHeight);
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Bagh Bandi");
		primaryStage.setMaximized(true);
		primaryStage.show();
		
		
		easy.setOnMouseClicked(e->{
			
		
			EasyLevel EL = new EasyLevel();
				
			try {
				
				EL.start(primaryStage);
			
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
			
		});

		
	}

}
