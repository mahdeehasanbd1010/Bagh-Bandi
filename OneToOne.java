package application;
	
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import static application.Main.screenWidth;
import static application.Main.screenHeight;

public class OneToOne extends Application {
	
	Scene scene; 
	Pane root;
	AlquerqueBoard alquerqueBoard;
	
	int flagForReturnToMenu=0;
	int next;
	
	boolean click1ForGoat=true,click2ForGoat=false;
	boolean click1ForTiger=false,click2ForTiger=false;
	int selectGoat,selectTiger,selectPosition;
	
	int []tempIndex = new int[25];
	
	public void backToMenu(Stage primaryStage) {
		
		Main main = new Main();
		main.start(primaryStage);
		
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		screenWidth = gd.getDisplayMode().getWidth();
		screenHeight = gd.getDisplayMode().getHeight();
		
		root = new Pane();
		
		alquerqueBoard = new AlquerqueBoard();
		root = alquerqueBoard.getRoot();
		
		
		scene = new Scene(root,screenWidth,screenHeight);
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Bagh Bandi");
		primaryStage.show();
		
		root.setOnMouseClicked( e -> {
			
        	if(flagForReturnToMenu==1) backToMenu(primaryStage);
			
			int mouseClickX=(int)e.getX();
        	int mouseClickY=(int)e.getY();
        	
        	for(int i=0;i<25;i++) {
        		
        		if( i<alquerqueBoard.getGoat().size() 
        				&& (mouseClickX < (alquerqueBoard.getGoat().get(i).getCenterX()+20) && 
        						mouseClickX > (alquerqueBoard.getGoat().get(i).getCenterX()-20))
        				&& (mouseClickY < (alquerqueBoard.getGoat().get(i).getCenterY()+20) && 
        						mouseClickY > (alquerqueBoard.getGoat().get(i).getCenterY()-20))
        				&& click1ForGoat) {
        			
        			for(int j=i+1;j<alquerqueBoard.getGoat().size();j++){
        				
        				if(alquerqueBoard.getGoatIndex().get(i)==
        						alquerqueBoard.getGoatIndex().get(j)) i++;
        				
        				else break;
        				
        			}
        			
        			if(click2ForGoat) {
        				
        				alquerqueBoard.getGoat().get(selectGoat).setStroke(Color.BLACK);
        				
        			}
        			
        			alquerqueBoard.getGoat().get(i).setStroke(Color.valueOf("#90ee90"));
        			
        			selectGoat=i;
        			next=alquerqueBoard.getGoatIndex().get(i);
        			
        			System.out.println("select goat " + i + " at " + 
        			alquerqueBoard.getGoatIndex().get(i));
        			
        			click1ForGoat=true;
        			click2ForGoat=true;
        			
        			System.out.println("Break1");
        			break;
        		
        		}
        		
        		
        		if(i<alquerqueBoard.getTiger().size() 
        			&& (mouseClickX < (alquerqueBoard.getTiger().get(i).getCenterX()+20) &&
        					mouseClickX > (alquerqueBoard.getTiger().get(i).getCenterX()-20))
            		&& (mouseClickY < (alquerqueBoard.getTiger().get(i).getCenterY()+20) &&
            				mouseClickY > (alquerqueBoard.getTiger().get(i).getCenterY()-20))
            		&& click1ForTiger) {
            		
        			if(click2ForTiger) {
        				
        				alquerqueBoard.getTiger().get(selectTiger).setStroke(Color.BLACK);
        			}
        			
        			alquerqueBoard.getTiger().get(i).setStroke(Color.valueOf("#FF4500"));
        			
        			selectTiger=i;
        			next=alquerqueBoard.getTigerIndex().get(i);
        			
        			System.out.println("select tiger " + i + " at " + 
        					alquerqueBoard.getTigerIndex().get(i));
        			
        			click2ForTiger=true;
        			click1ForTiger=true;
        			
        			System.out.println("Break2");
        			break;	
            	
        		}
        		
        		
        		if((mouseClickX < (alquerqueBoard.getBoardPoint()[i][0]+20) &&
        				mouseClickX > (alquerqueBoard.getBoardPoint()[i][0]-20)) 
        			&&(mouseClickY < (alquerqueBoard.getBoardPoint()[i][1]+20) &&
        				mouseClickY > (alquerqueBoard.getBoardPoint()[i][1]-20))
        			&& click2ForGoat) {
        			
        			
        			if(alquerqueBoard.getBoardPoint()[i][2]!=0) {
        				
        				continue;
        			}
        			
        			for(int j=0;j<8;j++) {
        			
        				if(alquerqueBoard.getPath()[next][j]==i 
        						&& alquerqueBoard.getBoardPoint()[alquerqueBoard.getPath()[next][j]][2]==0) {
        					
        					System.out.println("click2 at point "+
        							alquerqueBoard.getPath()[next][j]+" previous--> "+next+" next--> "+
        							i + " goat("+selectGoat+")");
        					
        					selectPosition=i;
    	        			
        					alquerqueBoard.getBoardPoint()[alquerqueBoard.getPath()[next][j]][2]++;
        					alquerqueBoard.getBoardPoint()[next][2]--;
        					
        					killedTheTiger();
        					
        					alquerqueBoard.getGoatIndex().set(selectGoat,selectPosition);
    	        			alquerqueBoard.getGoat().get(selectGoat).setStroke(Color.BLACK);
    	        			
    	        			click2ForGoat=false;
    	        			click1ForGoat=false;
    	        			
    	        			click1ForTiger=true;
    	        			goatWon();
    	        			
    	        			System.out.println("Break3sub");
    	        			break;
    	        			
        				}
        					
        			}
        			
        			
        			System.out.println("Break3");
        			break;
        		}
        		    		
        		
        		
        		if( (mouseClickX < (alquerqueBoard.getBoardPoint()[i][0]+20) &&
        				mouseClickX > (alquerqueBoard.getBoardPoint()[i][0]-20)) 
        			&&(mouseClickY<(alquerqueBoard.getBoardPoint()[i][1]+20) &&
        				mouseClickY>(alquerqueBoard.getBoardPoint()[i][1]-20)) && click2ForTiger) {
        			
        			if(alquerqueBoard.getBoardPoint()[i][2]!=0) {
        				
        				continue;
        			}
        			
        			
        			for(int j=0;j<8;j++) {
        				
        				
        				if( alquerqueBoard.getPath()[next][j]>=0 ) {
        						
        					if(i==alquerqueBoard.getPath()[alquerqueBoard.getPath()[next][j]][j] 
        					&& alquerqueBoard.getBoardPoint()[i][2]==0 
        					&& alquerqueBoard.getBoardPoint()[alquerqueBoard.getPath()[next][j]][2]!=0) {
        								
        						selectPosition=i;
            	        			
        						alquerqueBoard.getBoardPoint()[i][2]+=100;
        						alquerqueBoard.getBoardPoint()[next][2]-=100;
               	               					
                				alquerqueBoard.getBoardPoint()[alquerqueBoard.getPath()[next][j]][2]--;
            	        			
            	        		killTheGoat(alquerqueBoard.getPath()[next][j]);
            	        		
            	        		alquerqueBoard.getTigerIndex().set(selectTiger,selectPosition);
            	        		alquerqueBoard.getTiger().get(selectTiger).setStroke(Color.BLACK);
            	        		
            					System.out.println("click2 at point "+
            					alquerqueBoard.getPath()[alquerqueBoard.getPath()[next][j]][j]+
            					" previous--> "+next+" next--> "+ i + " tiger("+selectTiger+")");
            					
            	        		click2ForTiger=false;
            	        		click1ForTiger=false;
            	        		
            	        		click1ForGoat=true;
            	        		
            	        		tigerWon();
            	        		
								System.out.println("Break4sub2");
            	        		break;
            	        		
        					}
        						
        				}
        					
        				
        				
        				if(alquerqueBoard.getPath()[next][j]==i 
        					&& alquerqueBoard.getBoardPoint()[alquerqueBoard.getPath()[next][j]][2]==0) {
        					
        					selectPosition=i;
    	        			
        					alquerqueBoard.getBoardPoint()[alquerqueBoard.getPath()[next][j]][2]+=100;
        					alquerqueBoard.getBoardPoint()[next][2]-=100;
    	        			
    	        			
        					alquerqueBoard.getTigerIndex().set(selectTiger,selectPosition);
        					alquerqueBoard.getTiger().get(selectTiger).setStroke(Color.BLACK);
        					
    	        			System.out.println("click2 at point "+
    	        					alquerqueBoard.getPath()[next][j]+" previous--> "+
    	        					next+" next--> "+ i + " tiger("+selectTiger+")");

    	        			
    	        			click2ForTiger=false;
    	        			click1ForTiger=false;
    	        			
    	        			click1ForGoat=true;
    	        			
    	        			System.out.println("Break4sub1");
    	        			break;
        				}
        						
        			}
        			
        			System.out.println("Break4");
        			break;
        		}
        		
        	}
        	
        	for(int j=0;j<25;j++) {
    			
    			System.out.println(j+"  "+alquerqueBoard.getBoardPoint()[j][2]);
    				
    		}
             
             
         });
		
		
		
		new AnimationTimer() {
			
			public void handle(long currentNanoTime)
            {
            	
				updateBorard();
            }
		}.start();
	}
	
	
	public void updateBorard() {
		
		for(int i=0;i<25;i++) tempIndex[i]=0;
    	
    	for(int i=0;i<alquerqueBoard.getGoat().size();i++) {
    		            		
    		alquerqueBoard.getGoat().get(i).setCenterX
    		(alquerqueBoard.getBoardPoint()[alquerqueBoard.getGoatIndex().get(i)][0]);
    		
    		alquerqueBoard.getGoat().get(i).setCenterY
    		(alquerqueBoard.getBoardPoint()[alquerqueBoard.getGoatIndex().get(i)][1]
    				-tempIndex[alquerqueBoard.getGoatIndex().get(i)]*6);
    		
    		tempIndex[alquerqueBoard.getGoatIndex().get(i)]++;
    		
    	}
    	
    	for(int i=0;i<alquerqueBoard.getTiger().size();i++){
    		alquerqueBoard.getTiger().get(i).setCenterX
    		(alquerqueBoard.getBoardPoint()[alquerqueBoard.getTigerIndex().get(i)][0]);
    	
    		alquerqueBoard.getTiger().get(i).setCenterY
    		(alquerqueBoard.getBoardPoint()[alquerqueBoard.getTigerIndex().get(i)][1]);
    	}
    	
	}
	
	
	public void killTheGoat(int index) {
		
		System.out.println("Goat killed at " + index);
		
		for(int i=0;i<alquerqueBoard.getGoat().size();i++) {
			
			if(alquerqueBoard.getGoatIndex().get(i)==index) {
				
				for(int j=i+1;j<alquerqueBoard.getGoat().size();j++)
				{
					if(alquerqueBoard.getGoatIndex().get(j)==index) i++;
					
					else break;
					
				}
				
				root.getChildren().remove(alquerqueBoard.getGoat().get(i));
				alquerqueBoard.getGoat().remove(i);
				alquerqueBoard.getGoatIndex().remove(i);
			
				System.out.println("Number of the goat killed  " + i);
				break;
			}
			
		}
		
	}
	
	public void tigerWon() {
		
		if(alquerqueBoard.getGoat().size()==0) {
			
			Label label = new Label();  
			
			label.relocate((double)alquerqueBoard.getBoardPoint()[6][0],
					(double)alquerqueBoard.getBoardPoint()[6][1]);
			
			label.setText("Game Over\nTiger Win");
			label.setTextFill(Color.DARKBLUE);
			label.setFont(new Font("Arial",60));
			root.getChildren().add(label);
			
			flagForReturnToMenu=1;
			
			click1ForGoat=false;
			click2ForGoat=false;
			click1ForTiger=false;
			click2ForTiger=false;
			
			System.out.println("Tiger win");
			
		}
	}
	
	public void killedTheTiger() {
		
		int flag=1;
		
		for(int i=0;i<alquerqueBoard.getTiger().size();i++) {
			
			for(int j=0;j<8;j++) {
				
				if(alquerqueBoard.getPath()[alquerqueBoard.getTigerIndex().get(i)][j]>=0) {
					
					if(alquerqueBoard.getBoardPoint()
							[alquerqueBoard.getPath()[alquerqueBoard.getTigerIndex().get(i)][j]] [2]==0) {
						
						flag=0;
						break;
					}
					
					if(alquerqueBoard.getPath()
							[alquerqueBoard.getPath()[alquerqueBoard.getTigerIndex().get(i)][j]][j]>=0) {
						
						if( alquerqueBoard.getBoardPoint()
								[alquerqueBoard.getPath()[alquerqueBoard.getPath()[alquerqueBoard.getTigerIndex().get(i)][j]][j]] [2]==0) {
							
							flag=0;
							break;
							
						}
					}
					
					
				}
				
				
			}
			
			if(flag==1) {
				
				alquerqueBoard.getBoardPoint()[alquerqueBoard.getTigerIndex().get(i)][2]-=100;
				
				root.getChildren().remove(alquerqueBoard.getTiger().get(i));
				alquerqueBoard.getTiger().remove(i);
				alquerqueBoard.getTigerIndex().remove(i);
			
				System.out.println("Number of the Tiger killed  " + i);
				
				break;
			}
			
		}
		
		
		
	}
	
	public void goatWon() {
		
		
		if(alquerqueBoard.getTiger().size()==0) {
			
			Label label = new Label();  
			
			label.relocate((double)alquerqueBoard.getBoardPoint()[6][0],
					(double)alquerqueBoard.getBoardPoint()[6][1]);
			label.setText("Game Over\nGoat Win");
			label.setTextFill(Color.DARKBLUE);
			label.setFont(new Font("Arial",60));
			
			root.getChildren().add(label);
			
			flagForReturnToMenu=1;
			
			click1ForGoat=false;
			click2ForGoat=false;
			click1ForTiger=false;
			click2ForTiger=false;
			
			System.out.println("Goat win");
			
		}
		
	}
	
	

}







