package application;

import static application.Main.screenHeight;
import static application.Main.screenWidth;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class EasyLevelAgainstGoat  extends Application {
	
	Scene scene;
	Pane root;
	AlquerqueBoard alquerqueBoard;
	
	Button quit;
	Timer timer;
	Label tigerCounter,goatCounter;
	
	int flagForReturnToMenu=0;
	int next;
	
	boolean click1ForGoat=true,click2ForGoat=false;
	boolean click1ForTiger=false,click2ForTiger=false;
	int selectGoat,selectTiger,selectPosition;
	
	ArrayList<Integer> goatToMoveToPoint = new ArrayList<Integer>();
	ArrayList<Integer> goatCurrentPoint = new ArrayList<Integer>();
	ArrayList<Integer> tempGoatNumberToMove = new ArrayList<Integer>();
	
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
		
		
		
		
		if(click1ForGoat) {
			
			selectGoat=selectionOfGoat();
			
			if(flagForNotKillTheGoat==1) {
				
				alquerqueBoard.getGoat().get(tempGoatNumberToMove.get(selectGoat)).setStroke(Color.valueOf("#90ee90"));
    			
    			next=selectGoat;
    			
    			System.out.println(selectGoat +" select goat " + tempGoatNumberToMove.get(selectGoat) + " at " + 
    			alquerqueBoard.getGoatIndex().get(tempGoatNumberToMove.get(selectGoat)));
    			
    			click1ForGoat=true;
    			click2ForGoat=true;
    			
			}
			
			else {
    			alquerqueBoard.getGoat().get(selectGoat).setStroke(Color.valueOf("#90ee90"));
    			
    			next=alquerqueBoard.getGoatIndex().get(selectGoat);
    			
    			System.out.println("select goat " + selectGoat + " at " + 
    			alquerqueBoard.getGoatIndex().get(selectGoat));
    			
    			click1ForGoat=true;
    			click2ForGoat=true;
    			
    		}
			
		}
		
		
		if(click2ForGoat) {
			
			System.out.println("next "+next);
			
			if(flagForNotKillTheGoat==1) {
				
				selectPosition=goatToMoveToPoint.get(next);
			
				System.out.println("selectPosition "+selectPosition);
    			
    			System.out.println(next+" next--> "+selectPosition +
    					" goat("+tempGoatNumberToMove.get(selectGoat)+")");
				
				alquerqueBoard.getBoardPoint()[selectPosition][2]++;
				
				System.out.println("goatCurrentPoint "+goatCurrentPoint.get(next)+" next " +next);
				
				alquerqueBoard.getBoardPoint()[goatCurrentPoint.get(next)][2]--;
				
				killedTheTiger();
				
				alquerqueBoard.getGoatIndex().set(tempGoatNumberToMove.get(selectGoat),selectPosition);
    			alquerqueBoard.getGoat().get(tempGoatNumberToMove.get(selectGoat)).setStroke(Color.BLACK);
    			
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
    			
			
			}
			else {
    			selectPosition=nextStepForGoat(next);
    			
    			
    			System.out.println("selectPosition "+selectPosition);
    			
    			System.out.println(next+" next--> "+selectPosition + " goat("+selectGoat+")");
				
				alquerqueBoard.getBoardPoint()[selectPosition][2]++;
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
    		
			}
			
		}
		
		
		root.setOnMouseClicked( e -> {
			
			
        	if(flagForReturnToMenu==1) backToMenu(primaryStage);
			
			int mouseClickX=(int)e.getX();
        	int mouseClickY=(int)e.getY();
        	
        	for(int i=0;i<25;i++) {
        		
        		
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
        					&& alquerqueBoard.getBoardPoint()[alquerqueBoard.getPath()[next][j]][2]!=0
        					&& alquerqueBoard.getBoardPoint()[alquerqueBoard.getPath()[next][j]][2]!=100) {
        								
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
            	        		
            	        		
            	    			goatCounter = new Label();
            	    			goatCounter.setText("Goat's turn");
            	    			goatCounter.relocate((5*(double)screenWidth)/8,
            	    					(1*(double)screenHeight)/12);
            	    			
            	    			goatCounter.setTextFill(Color.GREEN);
            	    			goatCounter.setFont(new Font("Arial",30));
            	    			
            	    			root.getChildren().remove(tigerCounter);
            	    			root.getChildren().add(goatCounter);
            	    			
            	    				
            	    			tigerWon();
            	        		
            	        		System.out.println("Break2sub1");
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
    	        			
    	        			
    	        			goatCounter = new Label();
        	    			goatCounter.setText("Goat's turn");
        	    			goatCounter.relocate((5*(double)screenWidth)/8,
        	    					(1*(double)screenHeight)/12);
        	    			
        	    			goatCounter.setTextFill(Color.GREEN);
        	    			goatCounter.setFont(new Font("Arial",30));
        	    			
        	    			root.getChildren().remove(tigerCounter);
        	    			root.getChildren().add(goatCounter);
        	    			
    	        			
    	        			System.out.println("Break2sub2");
    	        			break;
    	        			
        				}
        						
        			}
        			
  
        		}
        		
        		
        		if(click1ForGoat) {
        			
        			selectGoat=selectionOfGoat();
        			
        			if(flagForNotKillTheGoat==1) {
        				
        				alquerqueBoard.getGoat().get(tempGoatNumberToMove.get(selectGoat)).setStroke(Color.valueOf("#90ee90"));
            			
            			next=selectGoat;
            			
            			System.out.println(selectGoat +" select goat " + tempGoatNumberToMove.get(selectGoat) + " at " + 
            			alquerqueBoard.getGoatIndex().get(tempGoatNumberToMove.get(selectGoat)));
            			
            			click1ForGoat=true;
            			click2ForGoat=true;
            			
        			}
        			
        			else {
            			alquerqueBoard.getGoat().get(selectGoat).setStroke(Color.valueOf("#90ee90"));
            			
            			next=alquerqueBoard.getGoatIndex().get(selectGoat);
            			
            			System.out.println("select goat " + selectGoat + " at " + 
            			alquerqueBoard.getGoatIndex().get(selectGoat));
            			
            			click1ForGoat=true;
            			click2ForGoat=true;
            			
            		}
        			
        		}
        		
        		
        		if(click2ForGoat) {
        			
        			System.out.println("next "+next);
        			
        			if(flagForNotKillTheGoat==1) {
        				
        				selectPosition=goatToMoveToPoint.get(next);
        			
        				System.out.println("selectPosition "+selectPosition);
            			
            			System.out.println(next+" next--> "+selectPosition +
            					" goat("+tempGoatNumberToMove.get(selectGoat)+")");
        				
        				alquerqueBoard.getBoardPoint()[selectPosition][2]++;
        				
        				System.out.println("goatCurrentPoint "+goatCurrentPoint.get(next)+" next " +next);
        				
        				alquerqueBoard.getBoardPoint()[goatCurrentPoint.get(next)][2]--;
        				
        				killedTheTiger();
        				
        				alquerqueBoard.getGoatIndex().set(tempGoatNumberToMove.get(selectGoat),selectPosition);
            			alquerqueBoard.getGoat().get(tempGoatNumberToMove.get(selectGoat)).setStroke(Color.BLACK);
            			
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
            			
        			
        			}
        			else {
            			selectPosition=nextStepForGoat(next);
            			
            			
            			System.out.println("selectPosition "+selectPosition);
            			
            			System.out.println(next+" next--> "+selectPosition + " goat("+selectGoat+")");
        				
        				alquerqueBoard.getBoardPoint()[selectPosition][2]++;
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
	
	int flagForNotKillTheGoat=0;
	
	public int selectionOfGoat() {
		
		flagForNotKillTheGoat=0;
		
		tempGoatNumberToMove.clear();
		goatToMoveToPoint.clear();
		goatCurrentPoint.clear();
		
		for(int i=0;i<alquerqueBoard.getGoat().size();i++) {
			
			for(int j=i+1;j<alquerqueBoard.getGoat().size();j++)
			{
				if(alquerqueBoard.getGoatIndex().get(j)==
						alquerqueBoard.getGoatIndex().get(i)) i++;
				
				else break;
				
			}
			
			int goatPosition=alquerqueBoard.getGoatIndex().get(i);
			
			for(int j=0;j<8;j++) {
				
				if(alquerqueBoard.getPath()[goatPosition][j]>=0) {
					
					if(alquerqueBoard.getBoardPoint()[alquerqueBoard.getPath()[goatPosition][j]][2]==0) {
						
						if(checkingForNotToKillTheGoat(alquerqueBoard.getPath()[goatPosition][j])==true) {
							
							tempGoatNumberToMove.add(i);
							goatCurrentPoint.add(goatPosition);
							goatToMoveToPoint.add(alquerqueBoard.getPath()[goatPosition][j]);
							
						}
					}
				}
			}
		}
		
		
		for(int i=0;i<alquerqueBoard.getGoat().size();i++) {
			
			for(int j=i+1;j<alquerqueBoard.getGoat().size();j++)
			{
				if(alquerqueBoard.getGoatIndex().get(j)==
						alquerqueBoard.getGoatIndex().get(i)) i++;
				
				else break;
				
			}
			
			checkingForDefenseTheGoat(i);
			
		}
		
		
		if(goatToMoveToPoint.size()>0) {
			
			flagForNotKillTheGoat=1;
			
			Random ran = new Random();
			
			int  selectedGaotSerial = ran.nextInt(tempGoatNumberToMove.size());
			
			for(int i=0;i<goatToMoveToPoint.size();i++) {
				
				System.out.println("goatToMoveToPoint "+goatToMoveToPoint.get(i)+" tempGoatNumberToMove " 
						+tempGoatNumberToMove.get(i)+" goatCurrentPoint "+goatCurrentPoint.get(i));
						
			}
			
			
			return selectedGaotSerial;
		}
		
		else {
			
		
			while(true) {
				
				int flag=0;
				Random rand = new Random();
				
				int selectedGaot = rand.nextInt(alquerqueBoard.getGoat().size());
				
				for(int j=selectedGaot+1;j<alquerqueBoard.getGoat().size();j++)
				{
					if(alquerqueBoard.getGoatIndex().get(j)==
							alquerqueBoard.getGoatIndex().get(selectedGaot)) selectedGaot++;
					
					else break;
					
				}
				
				for(int i=0;i<8;i++) {
					
					if(alquerqueBoard.getPath()[alquerqueBoard.getGoatIndex().get(selectedGaot)][i]>=0) {
						
						if(alquerqueBoard.getBoardPoint()
								[alquerqueBoard.getPath()[alquerqueBoard.getGoatIndex().get(selectedGaot)][i]][2]==0) {
							
							flag=1;
						
						}
						
					}
					
				}
				
				if(flag==1) return selectedGaot;
			}
		}
		
	}
	
	public int nextStepForGoat(int point) {
		
		Random rand = new Random();
		
		while(true) {
			
			int pointToGo = rand.nextInt(8);
			
			if(alquerqueBoard.getPath()[point][pointToGo]>=0) {
				
				if(alquerqueBoard.getBoardPoint()[alquerqueBoard.getPath()[point][pointToGo]][2]==0) {
					
					if(checkingForNotToKillTheGoat(alquerqueBoard.getPath()[point][pointToGo])==true)
						return alquerqueBoard.getPath()[point][pointToGo];
					
				}
			
			}
		
		}
		 
	}
	
	
	
	public void checkingForDefenseTheGoat(int goatNumber) {
		
		int goatPosition=alquerqueBoard.getGoatIndex().get(goatNumber);
		int flagForClean=1;
		
		
		for(int i=0;i<alquerqueBoard.getTiger().size();i++) {
			
			int tigerPosition=alquerqueBoard.getTigerIndex().get(i);
			
			for(int j=0;j<8;j++) {
				
				if(alquerqueBoard.getPath()[tigerPosition][j]==goatPosition) {
					
					if(alquerqueBoard.getPath()[goatPosition][j]>=0 && 
							alquerqueBoard.getBoardPoint()[alquerqueBoard.getPath()[goatPosition][j]][2]==0) {
						
						if(flagForClean==1) {
							
							tempGoatNumberToMove.clear();
							goatCurrentPoint.clear();
							goatToMoveToPoint.clear();
							
							flagForClean=0;
						}
						
						tempGoatNumberToMove.add(goatNumber);
						goatCurrentPoint.add(goatPosition);
						goatToMoveToPoint.add(alquerqueBoard.getPath()[goatPosition][j]);
						
							
					}
				}
				
			}
			
		}
		
	}
	
	
	
	public boolean checkingForNotToKillTheGoat(int point) {
		
		for(int i=0;i<alquerqueBoard.getTiger().size();i++) {
			
			int tigerPosition=alquerqueBoard.getTigerIndex().get(i);
			
			for(int j=0;j<8;j++) {
				
				if(alquerqueBoard.getPath()[tigerPosition][j]==point) {
					
					if(alquerqueBoard.getPath()[point][j]>=0 && 
							alquerqueBoard.getBoardPoint()[alquerqueBoard.getPath()[point][j]][2]==0) {
						
						return false;
					}
				}
				
			}
			
		}
		
		return true;
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
		
		int count=0,marker=0;
		
		for(int i=0;i<alquerqueBoard.getTiger().size();i++) {
			
			int flag=1;
			
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
	
	

}







