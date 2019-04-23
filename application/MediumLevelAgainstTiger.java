package application;
	
import static application.Main.screenHeight;
import static application.Main.screenWidth;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MediumLevelAgainstTiger extends Application {
	
	Scene scene;
	Pane root;
	AlquerqueBoard alquerqueBoard;
	
	Button quit;
	
	Label tigerCounter,goatCounter;
	
	int flagForReturnToMenu=0;
	int next;
	int initialNumberOfTiger=0,initialNumberOfGoat=0;
	
	boolean click1ForGoat=true,click2ForGoat=false;
	boolean click1ForTiger=false,click2ForTiger=false;
	int selectGoat,selectTiger,selectPosition;
	
	ArrayList<Integer> tempTigerIndexTokill = new ArrayList<Integer>();
	ArrayList<Integer> arrayForKilledGoat = new ArrayList<Integer>();
	ArrayList<Integer> forKillingGoatToGo = new ArrayList<Integer>();
	ArrayList<Integer> numberOfTigerToKill = new ArrayList<Integer>();
	
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
		
		quit = new Button(); 
		quit.setText("quit");
		
		quit.setPrefSize(130, 50);
		quit.setLayoutX(7*(screenWidth/9));
		quit.setLayoutY(screenHeight/6);
		
		root.getChildren().add(quit);
		
		scene = new Scene(root,screenWidth,screenHeight);
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Bagh Bandi");
		primaryStage.setMaximized(true);
		primaryStage.show();
		
		
		quit.setOnMouseClicked(e1->{
			
			backToMenu(primaryStage);
			
		});
		
		
		
		goatCounter = new Label();
		goatCounter.setText("Goat's turn");
		goatCounter.relocate((5*(double)screenWidth)/8,
				(1*(double)screenHeight)/12);
		
		goatCounter.setTextFill(Color.GREEN);
		goatCounter.setFont(new Font("Arial",30));
		
		root.getChildren().add(goatCounter);
			
		
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
        			
        			//click1ForGoat=true;
        			click2ForGoat=true;
        			
        			System.out.println("Break1");
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
    	        			
    	        			
    	        			tigerCounter = new Label();
    	        			tigerCounter.setText("Tiger's turn");
    	        			tigerCounter.relocate((5*(double)screenWidth)/8,
    	        					(1*(double)screenHeight)/12);
    	        			
    	        			tigerCounter.setTextFill(Color.RED);
    	        			tigerCounter.setFont(new Font("Arial",30));
    	        			
    	        			root.getChildren().remove(goatCounter);
    	        			root.getChildren().add(tigerCounter);
    	        				
    	        			
    	        			goatWon();
    	        			
    	        			System.out.println("Break2sub");
    	        			break;
    	        			
        				}
        					
        			}
        			
        		}
        		    		
        		if(click1ForTiger) {
            		
        			selectTiger=selectionOfTiger();
        			
        			System.out.println("Tiger number : " + selectTiger + " at " + 
        					alquerqueBoard.getTigerIndex().get(selectTiger));
        			
        			alquerqueBoard.getTiger().get(selectTiger).setStroke(Color.valueOf("#FF4500"));
        			
        			next=alquerqueBoard.getTigerIndex().get(selectTiger);
        			
        			click2ForTiger=true;
        			
        		}
        		
        		
        		if(click2ForTiger) {
        			
        			selectPosition=nextStepForTiger(next);
        			
        			System.out.println("Tiger " + selectTiger + " to go at : " + selectPosition);
        			
					alquerqueBoard.getBoardPoint()[selectPosition][2]+=100;
					alquerqueBoard.getBoardPoint()[next][2]-=100;
					
					if(flagForSelectingTiger==1) killTheGoat(arrayForKilledGoat.get(choice));
        			
					alquerqueBoard.getTigerIndex().set(selectTiger,selectPosition);
					alquerqueBoard.getTiger().get(selectTiger).setStroke(Color.BLACK);
        			
        			click2ForTiger=false;
        			click1ForTiger=false;
        			
        			click1ForGoat=true;
        			
        			goatCounter = new Label();
	    			goatCounter.setText("Goat's turn");
	    			goatCounter.relocate((5*(double)screenWidth)/8,
	    					(1*(double)screenHeight)/12);
	    			
	    			goatCounter.setTextFill(Color.GREEN);
	    			goatCounter.setFont(new Font("Arial",30));
	    			
	    			root.getChildren().remove(tigerCounter);
	    			root.getChildren().add(goatCounter);
	    			
	    			tigerWon();
	    			
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
	
	
	int flagForSelectingTiger=0;
	int choice;
	
	public int selectionOfTiger() {
		
		arrayForKilledGoat.clear();
		forKillingGoatToGo.clear();
		numberOfTigerToKill.clear();
		
		flagForSelectingTiger=0;
		
		System.out.println("possible to kill the goat : ");
		for(int i=0;i<alquerqueBoard.getTiger().size();i++) {
			
			int point=alquerqueBoard.getTigerIndex().get(i);
			System.out.println("Tiger index at " + point);
			
			for(int j=0;j<8;j++) {
				
				if(alquerqueBoard.getPath()[point][j]>=0) {
					
					if(alquerqueBoard.getBoardPoint()[alquerqueBoard.getPath()[point][j]][2]!=0 && 
							alquerqueBoard.getBoardPoint()[alquerqueBoard.getPath()[point][j]][2]!=100) {
						
						if(alquerqueBoard.getPath()[alquerqueBoard.getPath()[point][j]][j]>=0) {
							
							if(alquerqueBoard.getBoardPoint()[alquerqueBoard.getPath()
							                                  [alquerqueBoard.getPath()[point][j]][j]][2]==0) {
								
								arrayForKilledGoat.add(alquerqueBoard.getPath()[point][j]);
								forKillingGoatToGo.add(alquerqueBoard.getPath()[alquerqueBoard.getPath()[point][j]][j]);
								numberOfTigerToKill.add(i);
								System.out.println( alquerqueBoard.getPath()[alquerqueBoard.getPath()[point][j]][j] +" "
										+ alquerqueBoard.getPath()[point][j] + " "+ j +" tiger number : " + i );
								
								flagForSelectingTiger=1;
							}
							
						}
						
					}
				
				}
					
			}
			
		
		}
		
		//print the triple parameter
		for(int i=0;i<forKillingGoatToGo.size();i++) {
					
			System.out.println("forKillingGoatToGo "+forKillingGoatToGo.get(i)+" arrayForKilledGoat " 
					+arrayForKilledGoat.get(i) + " numberOfTigerToKill " + numberOfTigerToKill.get(i));
					
		}
		
		
		if(flagForSelectingTiger==1) {
			
			Random rand = new Random();
			
			choice = rand.nextInt(arrayForKilledGoat.size());
			
			int selectedTiger = numberOfTigerToKill.get(choice);
			
			alquerqueBoard.getBoardPoint()[arrayForKilledGoat.get(choice)][2]--;
			
			
			return selectedTiger;
			
		}
		
		
		else {
			
			Random rand = new Random();
			
			int selectedTiger = rand.nextInt(alquerqueBoard.getTiger().size());
			
			return selectedTiger;
			
		}
		
	}
	
	
	
	public int nextStepForTiger(int point) {
		
		if(flagForSelectingTiger==1) {
			
			return forKillingGoatToGo.get(choice);
			
		}
		
		else {
			
			
			Random rand = new Random();
			
			while(true) {
				
				int pointToGo = rand.nextInt(8);
				
				if(alquerqueBoard.getPath()[point][pointToGo]>=0) {
					
					if(alquerqueBoard.getBoardPoint()[alquerqueBoard.getPath()[point][pointToGo]][2]==0) {
						
						return alquerqueBoard.getPath()[point][pointToGo];
						
					}
				
				}
			
			}
			
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
		
		if(alquerqueBoard.getGoat().size()<5) {
			
			Label label = new Label();  
			
			label.relocate((double)alquerqueBoard.getBoardPoint()[6][0],
					(double)alquerqueBoard.getBoardPoint()[6][1]);
			
			label.setText("Game Over\nTiger Win");
			label.setTextFill(Color.DARKBLUE);
			label.setFont(new Font("Arial",60));
			
			root.getChildren().add(label);
			root.getChildren().remove(goatCounter);
			  
			flagForReturnToMenu=1;
			
			click1ForGoat=false;
			click2ForGoat=false;
			click1ForTiger=false;
			click2ForTiger=false;
			
			System.out.println("Tiger win");
			
		}
	}
		
	
	public void killedTheTiger() {
		
		int count=0,marker=0,flag;
		
		for(int i=0;i<alquerqueBoard.getTiger().size();i++) {
			
			flag=1;
			
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
			
			if(flag==1)
			{
				marker=i;
				count++;
			}
		
		}
		
		if(count==1) {
			
			alquerqueBoard.getBoardPoint()[alquerqueBoard.getTigerIndex().get(marker)][2]-=100;
					
			root.getChildren().remove(alquerqueBoard.getTiger().get(marker));
			alquerqueBoard.getTiger().remove(marker);
			alquerqueBoard.getTigerIndex().remove(marker);
				
			System.out.println("Number of the Tiger killed  " + marker);
			
		}
		
		else if(count==2) {
				
			alquerqueBoard.getBoardPoint()[alquerqueBoard.getTigerIndex().get(0)][2]-=100;
				
			root.getChildren().remove(alquerqueBoard.getTiger().get(0));
			alquerqueBoard.getTiger().remove(0);
			alquerqueBoard.getTigerIndex().remove(0);
			
			System.out.println("Number of the Tiger killed  " + 0);
			
			alquerqueBoard.getBoardPoint()[alquerqueBoard.getTigerIndex().get(0)][2]-=100;
			
			root.getChildren().remove(alquerqueBoard.getTiger().get(0));
			alquerqueBoard.getTiger().remove(0);
			alquerqueBoard.getTigerIndex().remove(0);
			
			System.out.println("Number of the Tiger killed  " + 0);
			
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
			root.getChildren().remove(tigerCounter);
			
			flagForReturnToMenu=1;
			
			click1ForGoat=false;
			click2ForGoat=false;
			click1ForTiger=false;
			click2ForTiger=false;
			
			System.out.println("Goat win");
			
		}
		
	}
	
	public void bestMove(int[][] arr,Queue<Integer> queue,
			int controlDoubleployForSource,int controlDoubleployForDestination) {
		
		int bestValue=-100000;
		
		int[][] boardPoint = new int[25][3];
		
		for(int i=0;i<25;i++){
			
			for(int j=0;j<3;j++){
				
				boardPoint[i][j]=arr[i][j];
			}
		}
		
		int count1=0,count2=0;
		   
		for(int i=0;i<boardPoint.length;i++) {
				
				if(boardPoint[i][2]!=100 && boardPoint[i][2]!=0) count1+=boardPoint[i][2];
				if(boardPoint[i][2]==100) count2++;
				
		}
		
		initialNumberOfGoat=count1;
		initialNumberOfTiger=count2;
		
		boolean firstcheck1=false,firstcheck2=false;
		
		
		for(int i=0;i<alquerqueBoard.getTiger().size();i++) {
			
			int element = i;
			
			for(int j=7;j>=0;j--) {
				
				int temp=0,tempX1=0,tempX2=0;
				tempX1=element;
				temp=alquerqueBoard.getPath()[element][j];
				tempX2=temp;
				
				
			}
			
			
			
		}
		
	}
	
	

}







