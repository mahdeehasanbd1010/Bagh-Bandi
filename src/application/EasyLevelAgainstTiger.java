package application;
	
import static application.Main.screenHeight;
import static application.Main.screenWidth;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

public class EasyLevelAgainstTiger extends Application {
	
	int initialNumberOfTiger=0,initialNumberOfGoat=0;
	int time=0;
	int currentSource,currentDestination,direction;
	String team;
	int callbySource,callbyDestination;
	
	int[] sourceArray;
	
	int source,destination,directionDestination;
	int[] index=new int[2];
	
	
	Scene scene;
	Pane root;
	AlquerqueBoard alquerqueBoard;
	
	Button quit,start;
	Label tigerCounter,goatCounter,numberOfGoat,numberOfTiger;
	
	int flagForKillTheTiger=0;
	int flagForReturnToEasyLevel=0;
	int next;
	
	boolean startOn=true,play=false;
	
	boolean click1ForGoat=true,click2ForGoat=false;
	boolean click1ForTiger=false,click2ForTiger=false;
	int selectGoat,selectTiger,selectPosition;
	
	ArrayList<Integer> tempTigerIndexTokill = new ArrayList<Integer>();
	ArrayList<Integer> arrayForKilledGoat = new ArrayList<Integer>();
	ArrayList<Integer> forKillingGoatToGo = new ArrayList<Integer>();
	ArrayList<Integer> numberOfTigerToKill = new ArrayList<Integer>();
	
	int []tempIndex = new int[25];
	
	public void backToEasyLevel(Stage primaryStage) {
		
		EasyLevel EL = new EasyLevel();
		EL.start(primaryStage);
		
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
		
		start = new Button(); 
		start.setText("start");
		
		start.setPrefSize(150, 50);
		start.setLayoutX(7*(screenWidth/9));
		start.setLayoutY(3*(screenHeight/6)/2);
		
		
		root.getChildren().addAll(quit,start);
		
		scene = new Scene(root,screenWidth,screenHeight);
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Bagh Bandi");
		primaryStage.setMaximized(true);
		primaryStage.show();
		
		
		quit.setOnMouseClicked(e1->{
			
			backToEasyLevel(primaryStage);
			
		});
		
		
		start.setOnMouseClicked(e->{
			
			
			if(startOn==true) {
				
				goatCounter = new Label();
				goatCounter.setText("Goat's turn");
				goatCounter.relocate((5*(double)screenWidth)/8,
						(1*(double)screenHeight)/12);
				
				goatCounter.setTextFill(Color.GREEN);
				goatCounter.setFont(new Font("Arial",30));
				
				root.getChildren().add(goatCounter);
				
				
				
				
				numberOfGoat = new Label();
				numberOfGoat.setText("Goat : " + alquerqueBoard.getGoat().size());
				numberOfGoat.relocate((2*(double)screenWidth)/8,
						(1*(double)screenHeight)/12);
				
				numberOfGoat.setTextFill(Color.GREEN);
				numberOfGoat.setFont(new Font("Arial",30));
				
				
				numberOfTiger = new Label();
				numberOfTiger.setText("Tiger : " + alquerqueBoard.getTiger().size());
				numberOfTiger.relocate((3*(double)screenWidth)/8,
						(1*(double)screenHeight)/12);
				
				numberOfTiger.setTextFill(Color.RED);
				numberOfTiger.setFont(new Font("Arial",30));
				
				root.getChildren().addAll(numberOfGoat,numberOfTiger);
				
				
				
				startOn=false;
				
			}
			
			if(play==true) {
				
				play=false;
				start.setText("start");
			}
			
			else {
				
				play=true;
				start.setText("pause");
			}	
					
    		
			
		});
		
		
			
		
		root.setOnMouseClicked( e -> {
			
			
        	if(flagForReturnToEasyLevel==1) backToEasyLevel(primaryStage);
			
          if(play==true) {
                	
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
        			
        			numberOfGoat.setText("Goat : " + alquerqueBoard.getGoat().size());
        	    	numberOfTiger.setText("Tiger : " + alquerqueBoard.getTiger().size());
        	    	
        			
        		}
        		    		
        		if(click1ForTiger) {
            		
        			sourceArray =  bestMove(alquerqueBoard.getBoardPoint());
        			
        				
        			System.out.println("sourceArray[0] : " + sourceArray[0] + " sourceArray[1] : "
        					+ sourceArray[1]);
        			
        			for(int k=0;k<alquerqueBoard.getTiger().size();k++) {
        				
        				
        				if(alquerqueBoard.getTigerIndex().get(k)==sourceArray[0]) {
        					
        					selectTiger=k;
        					
        					System.out.println("alquerqueBoard.getTigerIndex().get(k) : " 
        					+ alquerqueBoard.getTigerIndex().get(k) 
        							+ " sourceArray[0] : "
                					+ sourceArray[0]);
        					
        					break;
        				}
        				
        			}
        				
        			
        			System.out.println("Tiger number : " + selectTiger + " at " + 
        					alquerqueBoard.getTigerIndex().get(selectTiger));
        			
        			System.out.println("selectTiger : " + selectTiger);
        			
        			alquerqueBoard.getTiger().get(selectTiger).setStroke(Color.valueOf("#FF4500"));
        			
        			next=alquerqueBoard.getTigerIndex().get(selectTiger);
        			
        			click2ForTiger=true;
        			
        			
        		}
        		
        		
        		if(click2ForTiger) {
        			
        			
        			Line colorLine;
        			
        			
        			colorLine = new Line(alquerqueBoard.getBoardPoint()[sourceArray[0]][0],
	        	    		alquerqueBoard.getBoardPoint()[sourceArray[0]][1],
	        	    		alquerqueBoard.getBoardPoint()[destination][0],
	        	    		alquerqueBoard.getBoardPoint()[destination][1]);
	        	    	
	        		colorLine.setFill(Color.valueOf("#FF4500"));
	        	    colorLine.setStroke(Color.valueOf("#FF4500"));
	        	    colorLine.setStrokeWidth(5);
	        	    
	        	    root.getChildren().add(colorLine);
	        	    	
        			
        			
        			
        			Task<Void> sleeper = new Task<Void>() {
        	            @Override
        	            protected Void call() throws Exception {
        	                try {
        	                    Thread.sleep(1500);
        	                } 
        	                
        	                catch (InterruptedException e) {
        	                }
        	                return null;
        	            }
        	        };
        	        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
        	            @Override
        	            public void handle(WorkerStateEvent event) {
        	            	 
        	            	
        	            	selectPosition = destination;
                			
                			System.out.println("Tiger " + selectTiger + " to go at : " + selectPosition);
                			
        					alquerqueBoard.getBoardPoint()[selectPosition][2]+=100;
        					alquerqueBoard.getBoardPoint()[next][2]-=100;
        					
        					if(alquerqueBoard.getPath()[sourceArray[0]][sourceArray[1]]!=destination) {
        						
        						killTheGoat(alquerqueBoard.getPath()[sourceArray[0]][sourceArray[1]]);
        						
        						alquerqueBoard.getBoardPoint()[alquerqueBoard.getPath()
        						                               [sourceArray[0]][sourceArray[1]]][2]--;
        					}
        					
        					else;
        					
        					
        					if(flagForSelectingTiger==1) killTheGoat(arrayForKilledGoat.get(choice));
                			
        					alquerqueBoard.getTigerIndex().set(selectTiger,selectPosition);
        					alquerqueBoard.getTiger().get(selectTiger).setStroke(Color.BLACK);
                			
                			click2ForTiger=false;
                			click1ForTiger=false;
                			
                			click1ForGoat=true;
                			
                			
                			root.getChildren().remove(colorLine);
        	            	
                			
                			
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
                			
        	            	
        	    			numberOfGoat.setText("Goat : " + alquerqueBoard.getGoat().size());
                	    	numberOfTiger.setText("Tiger : " + alquerqueBoard.getTiger().size());
                	    	
        	        		
        	        		
        	        	}
        	        		
        	            	
        	            
        	        });
        	        
        	        
        	        new Thread(sleeper).start();
        			
        			
        			break;
        			
	    			
        	}
        		
        		
        	
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
			//System.out.println("Tiger index at " + point);
			
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
								
								/*System.out.println( alquerqueBoard.getPath()[alquerqueBoard.getPath()[point][j]][j] +" "
										+ alquerqueBoard.getPath()[point][j] + " "+ j +" tiger number : " + i );
								*/
								
								flagForSelectingTiger=1;
							}
							
						}
						
					}
				
				}
					
			}
			
		
		}
		
		//print the triple parameter
		/*for(int i=0;i<forKillingGoatToGo.size();i++) {
					
			System.out.println("forKillingGoatToGo "+forKillingGoatToGo.get(i)+" arrayForKilledGoat " 
					+arrayForKilledGoat.get(i) + " numberOfTigerToKill " + numberOfTigerToKill.get(i));
					
		}*/
		
		
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
		
		//System.out.println("Goat killed at " + index);
		
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
			
				//System.out.println("Number of the goat killed  " + i);
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
			  
			flagForReturnToEasyLevel=1;
			
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
			
			flagForReturnToEasyLevel=1;
			
			click1ForGoat=false;
			click2ForGoat=false;
			click1ForTiger=false;
			click2ForTiger=false;
			
			System.out.println("Goat win");
			
		}
		
	}
	
	public int[] bestMove(int[][] arr) {
		
		int bestValue=-100000;
		
		System.out.println("Best Move!!!");
		
		team="AI";
		
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
		
		System.out.println("count1 : " + count1 +" count2 : " + count2);
		
		for(int i=0;i<25;i++) {
			
			if(boardPoint[i][2]==100) {
				
				int element = i;
			
				for(int j=0;j<8;j++) {
				
					int firstStep = alquerqueBoard.getPath()[element][j];
				
					if(firstStep>=0) {
					
						System.out.println("oneStep : " + firstStep);
						
						if(boardPoint[firstStep][2]==0) {
							
							System.out.println("Tiger at " + element
									+ " firstStep " + firstStep );
								
							
							
							boardPoint[element][2]=0;
							boardPoint[firstStep][2]=100;
							
							
							currentSource=element;
		                    currentDestination=firstStep;
							
		                    callbySource=element;
			                callbyDestination=firstStep;
		                    
		                    int moveValue = minimax(boardPoint,-1000000,1000000,1,false);
		                    
		                    
		                    boardPoint[element][2]=100;
							boardPoint[firstStep][2]=0;
							time++;
							
							if (moveValue > bestValue) {
		                    	
								System.out.println("move value : " +moveValue 
		                    			+ " best value : " + bestValue );
		                    	
		                    	index[0]=element;
		                    	index[1]=j;
			                    bestValue = moveValue;
			                    
			                    source=element;
			                    destination=firstStep;
			                    
		                    	
		                    }
							
							
						}
						
						
						else if(boardPoint[firstStep][2]!=0&&boardPoint[firstStep][2]!=100) {
						
							System.out.println("vitore oneStep : " + firstStep);
						
							int secondStep = alquerqueBoard.getPath()[firstStep][j];
						
							if(secondStep>=0) {
							
								System.out.println("secondStep : " + secondStep);
							
								if(boardPoint[secondStep][2]==0) {
								
									System.out.println("vitore secondStep : " + secondStep);
								
									System.out.println("Tiger at " + element
										+ " firstStep " + firstStep + " secondStep " + secondStep);
								
								
									
									
									boardPoint[element][2]=0;
									boardPoint[firstStep][2]--;
									boardPoint[secondStep][2]=100;
									
									
									currentSource=element;
				                    currentDestination=secondStep;
									
				                    callbySource=element;
					                callbyDestination=secondStep;
					                
				                    
				                    int moveValue = minimax(boardPoint,-1000000,1000000,1,false);
				                    
				                    
				                    boardPoint[element][2]=100;
									boardPoint[firstStep][2]++;
									boardPoint[secondStep][2]=0;
									time++;
									
									if (moveValue > bestValue) {
				                    	
										System.out.println("move value : " +moveValue 
				                    			+ " best value : " + bestValue );
				                    	
				                    	index[0]=element;
				                    	index[1]=j;
					                    bestValue = moveValue;
					                    
					                    source=element;
					                    destination=secondStep;
					                    
					                    
				                    	
				                    }
				                    
				                    
								}
								
							}
							
						}
					}
				}
				
			}
			
		}
		
		System.out.println("The Optimal Move is   source: "+index[0]+"   desination: "+destination
				+" direction: " + index[1]);
	    System.out.println("final max value: medium level "+bestValue);
	    
	    
	    System.out.println("return from: "+team);
		
		return index;
		
	}
	
	
	public int minimax(int[][] boardPoint,int alpha,int beta, int depth, boolean isMax) {
		
		time++;

		System.out.println("Call by "+team);
		
		System.out.println("callbySource: "+callbySource+" callbydestination: "+callbyDestination);
		
		
		int count1=0,count2=0;
		
		for(int i=0;i<boardPoint.length;i++) {
			
			
			if(boardPoint[i][2]!=100 && boardPoint[i][2]!=0) count1+=boardPoint[i][2];
			if(boardPoint[i][2]==100) count2++;
				
		}
		
		System.out.println("minimax -> count1 : " + count1 +" count2 : " + count2);
		
		
		if(depth==0) {
			
			int rate=rating(boardPoint,alquerqueBoard.getPath());
			
			return rate;
		}
		
		if(isMax) {
			
			for(int i=0;i<25;i++) {
				
				int element=i;
				
				if(boardPoint[element][2]==100) {
					
					for(int j=0;j<8;j++) {
						
						int firstStep = alquerqueBoard.getPath()[element][j];
						
						
						if(firstStep >= 0) {
							
							int secondStep=alquerqueBoard.getPath()[firstStep][j];
							
							if(boardPoint[firstStep][2]==0) {
								
								boardPoint[element][2]=0;
								boardPoint[firstStep][2]=100;
								
								team="AI";
								
								currentSource=element;
			                    currentDestination=firstStep;
			                    direction=j;
								
								
								alpha= Math.max( alpha,minimax(boardPoint,alpha,beta, depth-1, !isMax) );
								
								
								
								boardPoint[element][2]=100;
								boardPoint[firstStep][2]=0;
								time++;
								
								if(alpha>=beta) {
									
									return alpha;
								}
								
							}
							
							else if(secondStep>=0 && boardPoint[firstStep][2]!=0
									&& boardPoint[firstStep][2]!=100 && boardPoint[secondStep][2]==0) {
								
								boardPoint[element][2]=0;
								boardPoint[firstStep][2]--;
								boardPoint[secondStep][2]=100;
									
								team="AI";
								
								currentSource=element;
			                    currentDestination=secondStep;
			                    direction=j;
								
								
								alpha= Math.max( alpha,minimax(boardPoint,alpha,beta, depth-1, !isMax) );
								
								boardPoint[element][2]=100;
								boardPoint[firstStep][2]++;
								boardPoint[secondStep][2]=0;
								
								
								if(alpha>=beta) {
									 
									 return alpha;
								 }
								
							}
							
						}	
						
						
					}
						
					
				}
				
				
			}
		
			team="AI";
	        
	        return alpha;
		}
		
		else {
			
			for(int i=0;i<25;i++) {
				
				int element=i;
				
				if(boardPoint[element][2]!=0 && boardPoint[element][2]!=100) {
					
					for(int j=0;j<8;j++) {
						
						int firstStep = alquerqueBoard.getPath()[element][j];
						
						if(firstStep>=0) {
							
							if(boardPoint[firstStep][2]==0) {
								
								boardPoint[element][2]--;
								boardPoint[firstStep][2]++;
								
								if(flagForKillTheTiger==1)
								{	
									int[] tigerToKillAtPoint = killedTheAITiger(boardPoint);
								
								
									for(int k=0;k<2;k++) {
										
										if(tigerToKillAtPoint[k]>=0) {
											
											boardPoint[tigerToKillAtPoint[k]][2]=0;
										}
									}
								
								}
								
								team="Human";
								
								currentSource=element;
			                    currentDestination=firstStep;
			                    direction=j;
								
			                    beta= Math.min(beta,minimax(boardPoint,alpha,beta, depth-1, !isMax) );
			                    
			                    
			                    if(flagForKillTheTiger==1) {
									
									int[] tigerToKillAtPoint = killedTheAITiger(boardPoint);
									
									for(int k=0;k<2;k++) {
										
										if(tigerToKillAtPoint[k]>=0) {
											
											boardPoint[tigerToKillAtPoint[k]][2]=100;
										}
									}
									
								}
			                    
			                    boardPoint[element][2]++;
								boardPoint[firstStep][2]--;
								
								
								
								if(alpha>=beta) {
									 
									 return beta;
								}
								
								
							}
							
						}
						
					}
					
				}
				
			}
			
			team="Human";
			
	        return beta;
	        
		}
		
		
		
	
	
	}
	
	
	public int rating(int[][] arr,int[][] path) {
		
		int[][] boardPoint = new int[25][3];
		
		for(int i=0;i<25;i++){
			
			for(int j=0;j<3;j++) {
				
				boardPoint[i][j] = arr[i][j];
			}
		}
		
		int count1=0,count2=0;
		for(int i=0;i<boardPoint.length;i++) {
			
			
			if(boardPoint[i][2]!=100 && boardPoint[i][2]!=0) count1+=boardPoint[i][2];
			if(boardPoint[i][2]==100) count2++;
			
		}
		
		count1=initialNumberOfGoat-count1;
		count2=initialNumberOfTiger-count2;
		
		int AIScore=0,humanScore=0;
		
		System.out.println("rating -> count1 : " + count1 +" count2 : " + count2);
		
		for(int i=0;i<25;i++) {
			
			int element = i;
			
			if(boardPoint[element][2]==100) {
				
				for(int j=0;j<8;j++) {
					
					int firstStep = path[element][j];
					
					if(firstStep>=0) {
						
						if(boardPoint[firstStep][2]!=0) {
							
							int secondStep = path[element][j];
							
							if(secondStep>=0) {
								
								if(boardPoint[secondStep][2]!=0) humanScore+=5;
								
								else if(boardPoint[secondStep][2]==0) AIScore+=5;
							}
							
							if(secondStep==-1) {
								
								humanScore+=5;
							}
							
						}
						
						else if(boardPoint[firstStep][2]==0) {
							
							AIScore+=5;
						}
						
					}
					
					if(firstStep==-1) humanScore+=5;
					
				}
				
			}
			
			else if(boardPoint[element][2]!=0 && boardPoint[element][2]!=100) {
				
				for(int j=0;j<8;j++) {
					
					int firstStep = path[element][j];
					
					if(firstStep>=0) {
						
						if(boardPoint[firstStep][2]==100) {
							
							int secondStep = path[element][(j+4)%8];
							
							if(secondStep>=0) {
								
								if(boardPoint[secondStep][2]==0) {
									
									AIScore+=50;
								}
								
								else if(boardPoint[secondStep][2]!=0 && boardPoint[secondStep][2]!=100) {
									
									humanScore+=10;
								}
								
							}
							
						}
					}
					
				}
				
			}
			
		}
		
		
		System.out.println("AI TOTAL SCORE: 1: "+AIScore+" 2:"+count1*100);	
		System.out.println("HUMAN TOTAL SCORE: 1: "+humanScore+" 2:"+count2*100);	
		
	
		int total=(AIScore-humanScore)+count1*100-count2*100;
		
		
		return total;
		
	}
	
	
	
	public int[] killedTheAITiger(int[][] arr) {
		
		flagForKillTheTiger=0;
		
		int[] tigerToKillAtPoint = new int[2];
		
		int count=0,flag;
		int[][] boardPoint =  new int[25][3];
		
		for(int i=0;i<2;i++) tigerToKillAtPoint[i]=-1;
		
		for(int i=0;i<25;i++)
		{
			for(int j=0;j<3;j++) {
				
				boardPoint[i][j]=arr[i][j];
				
			}
		}
		
		for(int i=0;i<25;i++) {
			
			flag=1;
			
			if(boardPoint[i][2]==100) {
			
				int element=i;
				
				for(int j=0;j<8;j++) {
					
					int firstStep = alquerqueBoard.getPath()[element][j]; 
					
					if(firstStep>=0) {
						
						if(boardPoint[firstStep][2]==0) {
							
							flag=0;
							break;
							
						}
						
						if(firstStep >= 0) {
							
							int secondStep = alquerqueBoard.getPath()[firstStep][j];
							
							if(secondStep>=0) {
								if( boardPoint[secondStep][2]==0) {
									
									flag=0;
									break;
									
								}
							}
							
						}
							
					}	
					
				}
				
				if(flag==1)
				{
					tigerToKillAtPoint[count++]=i;
					flagForKillTheTiger=1;
				}
		
			}
			
		}
		
		return tigerToKillAtPoint;
		
		
	}

	
}







