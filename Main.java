package application;
	
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;


public class Main extends Application {
	
	Scene scene; 
	Pane root;
	
	int screenWidth,screenHeight;
	int [][]boardPoint = new int[25][3];
	
	int [][] path = new int[25][8];
	
	ArrayList<Circle> goat = new ArrayList<Circle>();
	ArrayList<Integer> goatIndex = new ArrayList<Integer>();
	
	Circle[] tiger=new Circle[2];
	int []tigerIndex = new int[2];
	
	int height,width;
	int next;
	
	boolean click1ForGoat=true,click2ForGoat=false;
	boolean click1ForTiger=false,click2ForTiger=false;
	int selectGoat,selectTiger,selectPosition;
	
	int []tempIndex = new int[25];
	
	private void createPoint () {
		
		height = (int) screenHeight/6;
		width = (int) screenWidth/8;
		
		int index=0;
		
		for(int i=1;i<6;i++)
		{
			 
			for(int j=2;j<7;j++) {
				 
				 boardPoint[index][0] = width*j;
				 boardPoint[index][1] = height*i;
				 index++;
			 
			}
			
		}
		
		boardPoint[1][2]=5;
		boardPoint[3][2]=5;
		boardPoint[11][2]=5;
		boardPoint[13][2]=5;
		
		
		boardPoint[7][2]=100;
		boardPoint[17][2]=100;
		
		//System.out.println( screenWidth +" "+ screenHeight);
		
	}
	
	private void drawLine() {
		
		int index=0;
		
		for(int i=0;i<5;i++) {
			
			Line line = new Line(boardPoint[index][0],boardPoint[index][1],boardPoint[index+4][0],boardPoint[index+4][1]);
			line.setStrokeWidth(2);
			Line line2 = new Line(boardPoint[i][0],boardPoint[i][1],boardPoint[i+20][0],boardPoint[i+20][1]);
			line2.setStrokeWidth(2);
			root.getChildren().addAll(line,line2);
			index+=5;
		}
		
		Line line3 = new Line(boardPoint[0][0],boardPoint[0][1],boardPoint[24][0],boardPoint[24][1]);
		line3.setStrokeWidth(2);
		Line line4 = new Line(boardPoint[4][0],boardPoint[4][1],boardPoint[20][0],boardPoint[20][1]);
		line4.setStrokeWidth(2);
		Line line5 = new Line(boardPoint[2][0],boardPoint[2][1],boardPoint[10][0],boardPoint[10][1]);
		line5.setStrokeWidth(2);
		Line line6 = new Line(boardPoint[2][0],boardPoint[2][1],boardPoint[14][0],boardPoint[14][1]);
		line6.setStrokeWidth(2);
		Line line7 = new Line(boardPoint[22][0],boardPoint[22][1],boardPoint[10][0],boardPoint[10][1]);
		line7.setStrokeWidth(2);
		Line line8 = new Line(boardPoint[22][0],boardPoint[22][1],boardPoint[14][0],boardPoint[14][1]);
		line8.setStrokeWidth(2);
		
		root.getChildren().addAll(line3,line4,line5,line6,line7,line8);
		
		
	}
	
	private void designPoint() {
		
		for(int i=0;i<25;i++) {
			
			Circle circle = new Circle();
			circle.setRadius(10);
			circle.setCenterX(boardPoint[i][0]);
	        circle.setCenterY(boardPoint[i][1]);
			
	        root.getChildren().add(circle);
		}
		
	}
	
	
	private void createPiece() {
		
		
		for(int i=0;i<25;i++) {
			
			if(i==1) {
				
				for(int j=0;j<5;j++) {
					
					Circle circle = new Circle();
			        circle.setCenterX(boardPoint[i][0]);
			        circle.setCenterY(boardPoint[i][1]);
			        circle.setRadius(20);
			        circle.setFill(Color.valueOf("#fff9f4"));
			        circle.setStroke(Color.BLACK);
			        circle.setStrokeWidth(3);
			        root.getChildren().add(circle);
			        goat.add(circle);
			        goatIndex.add(i);
			        
				
				}
				
			}
			
			else if(i==3) {
				
				for(int j=0;j<5;j++) {
					
					Circle circle = new Circle();
			        circle.setCenterX(boardPoint[i][0]);
			        circle.setCenterY(boardPoint[i][1]);
			        circle.setRadius(20);
			        circle.setFill(Color.valueOf("#fff9f4"));
			        circle.setStroke(Color.BLACK);
			        circle.setStrokeWidth(3);
			        root.getChildren().add(circle);
			        goat.add(circle);
			        goatIndex.add(i);
			        
				}
				
			}
			
			else if(i==11) {
				
				for(int j=0;j<5;j++) {
					
					Circle circle = new Circle();
			        circle.setCenterX(boardPoint[i][0]);
			        circle.setCenterY(boardPoint[i][1]);
			        circle.setRadius(20);
			        circle.setFill(Color.valueOf("#fff9f4"));
			        circle.setStroke(Color.BLACK);
			        circle.setStrokeWidth(3);
			        root.getChildren().add(circle);
			        goat.add(circle);
			        goatIndex.add(i);
			        
				}
				
				
			}
			
			else if(i==13) {
				
				for(int j=0;j<5;j++) {
					
					Circle circle = new Circle();
			        circle.setCenterX(boardPoint[i][0]);
			        circle.setCenterY(boardPoint[i][1]);
			        circle.setRadius(20);
			        circle.setFill(Color.valueOf("#fff9f4"));
			        circle.setStroke(Color.BLACK);
			        circle.setStrokeWidth(3);
			        root.getChildren().add(circle);
			        goat.add(circle);
			        goatIndex.add(i);
			        
				}
				
			}
			
			else if(i==7) {
				
				Circle circle = new Circle();
		        circle.setCenterX(boardPoint[i][0]);
		        circle.setCenterY(boardPoint[i][1]);
		        circle.setRadius(20);
		        circle.setFill(Color.valueOf("#c40003"));
		        circle.setStroke(Color.BLACK);
		        circle.setStrokeWidth(3);
		        root.getChildren().add(circle);
		        tigerIndex[0]=i;
		        tiger[0]=circle;
		       
			}
			
			else if(i==17) {
				
				Circle circle = new Circle();
		        circle.setCenterX(boardPoint[i][0]);
		        circle.setCenterY(boardPoint[i][1]);
		        circle.setRadius(20);
		        circle.setFill(Color.valueOf("#c40003"));
		        circle.setStroke(Color.BLACK);
		        circle.setStrokeWidth(3);
		        root.getChildren().add(circle);
		        tigerIndex[1]=i;
		        tiger[1]=circle;
		       
			}
			    
		}
			
	}
	
	
	
	public void Graph() {
		
		for(int i=0;i<25;i++) {
			
			for(int j=0;j<8;j++) {
				
				if(j==0 && (i!=4 && i!=9 && i!=14 && i!=19 && i!=24)) {
					
					path[i][j]=i+1; 
				}
				
				else if(j==1 && (i%2==0 && (i!=0 && i!=2 && i!=4 && i!=14 && i!=24))) {
					
					path[i][j]=i-4;
				}
				
				else if(j==2 && (i!=0 && i!=1 && i!=2 && i!=3 && i!=4)) {
					
					path[i][j]=i-5; 
				}
				
				else if(j==3 && (i%2==0 && (i!=0 && i!=2 && i!=4 && i!=10 && i!=20))) {
					
					path[i][j]=i-6;
				}
				
				else if(j==4 && (i!=0 && i!=5 && i!=10 && i!=15 && i!=20)) {
					
					path[i][j]=i-1; 
				}
				
				else if(j==5 && (i%2==0 && (i!=0 && i!=10 && i!=20 && i!=22 && i!=24))) {
					
					path[i][j]=i+4;
				}
				
				else if(j==6 && (i!=20 && i!=21 && i!=22 && i!=23 && i!=24)) {
					
					path[i][j]=i+5; 
				}
				
				else if(j==7 && (i%2==0 && (i!=4 && i!=14 && i!=20 && i!=22 && i!=24))) {
					
					path[i][j]=i+6;
				}
				
				else path[i][j]=-1;
				
			}
		}
		
	}
	
	
	int flag=0;
	
	@Override
	public void start(Stage primaryStage) {
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		screenWidth = gd.getDisplayMode().getWidth();
		screenHeight = gd.getDisplayMode().getHeight();
		
		root = new Pane();
		
		scene = new Scene(root,screenWidth,screenHeight);
		
		Graph();
		
		createPoint();
		drawLine();
		designPoint();
		createPiece();
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Bagh Bandi");
		primaryStage.show();
		
		primaryStage.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {

        	int mouseClickX=(int)e.getX();
        	int mouseClickY=(int)e.getY();
        	
        	for(int i=0;i<25;i++) {
        		
        		if(i<goat.size() && (mouseClickX < (goat.get(i).getCenterX()+20) && mouseClickX > (goat.get(i).getCenterX()-20))
            			&& (mouseClickY < (goat.get(i).getCenterY()+20) && mouseClickY > (goat.get(i).getCenterY()-20)) && click1ForGoat) {
        			
        			for(int j=i+1;j<goat.size();j++){
        				
        				if(goatIndex.get(i)==goatIndex.get(j)) i++;
        				else break;
        				
        			}
        			
        			if(click2ForGoat) {
        				
        				goat.get(selectGoat).setStroke(Color.BLACK);
        				
        			}
        			
        			goat.get(i).setStroke(Color.ORANGERED);
        			
        			selectGoat=i;
        			next=goatIndex.get(i);
        			
        			System.out.println("select goat " + i + " at " + goatIndex.get(i));
        			
        			click1ForGoat=true;
        			click2ForGoat=true;
        			
        			System.out.println("Break1");
        			break;
        		
        		}
        		
        		
        		if(i<2 && (mouseClickX < (tiger[i].getCenterX()+20) && mouseClickX > (tiger[i].getCenterX()-20))
            			&& (mouseClickY < (tiger[i].getCenterY()+20) && mouseClickY > (tiger[i].getCenterY()-20)) && click1ForTiger) {
            		
        			if(click2ForTiger) {
        				
        				tiger[selectTiger].setStroke(Color.BLACK);
        			}
        			
        			tiger[i].setStroke(Color.valueOf("#90ee90"));
        			
        			selectTiger=i;
        			next=tigerIndex[i];
        			
        			System.out.println("select tiger " + i + " at " + tigerIndex[i]);
        			
        			click2ForTiger=true;
        			click1ForTiger=true;
        			
        			System.out.println("Break2");
        			break;	
            	
        		}
        		
        		
        		if((mouseClickX < (boardPoint[i][0]+20) && mouseClickX > (boardPoint[i][0]-20)) &&
        				(mouseClickY < (boardPoint[i][1]+20) && mouseClickY > (boardPoint[i][1]-20))&&click2ForGoat) {
        			
        			
        			if(boardPoint[i][2]!=0) {
        				
        				continue;
        			}
        			
        			for(int j=0;j<8;j++) {
        			
        				if(path[next][j]==i && boardPoint[path[next][j]][2]==0) {
        					
        					System.out.println("click2 at point "+path[next][j]+" previous--> "+next+" next--> "+ i + " goat("+selectGoat+")");
        					selectPosition=i;
    	        			
        					boardPoint[path[next][j]][2]++;
        					boardPoint[next][2]--;
        					
    	        			goatIndex.set(selectGoat,selectPosition);
    	        			goat.get(selectGoat).setStroke(Color.BLACK);
    	        			
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
        		    		
        		
        		
        		if( (mouseClickX < (boardPoint[i][0]+20) && mouseClickX > (boardPoint[i][0]-20)) &&
        				((mouseClickY<boardPoint[i][1]+20) && (mouseClickY>boardPoint[i][1]-20)) && click2ForTiger) {
        			
        			if(boardPoint[i][2]!=0) {
        				
        				continue;
        			}
        			
        			
        			for(int j=0;j<8;j++) {
        				
        				
        				if( path[next][j]>=0 ) {
        						
        					if(i==path[path[next][j]][j] && boardPoint[i][2]==0 && boardPoint[path[next][j]][2]!=0) {
        						
        							
        						selectPosition=i;
            	        			
            	        		boardPoint[i][2]+=100;
                				boardPoint[next][2]-=100;
                					
                					               					
                				//System.out.println("before point value : " + boardPoint[path[next][j]][2]);
            	        		boardPoint[path[next][j]][2]--;
            	        		//System.out.println("after point value : " + boardPoint[path[next][j]][2]);
            	        			
            	        		killTheGoat(path[next][j]);
            	        		
            	        		tigerIndex[selectTiger]=selectPosition;
            	        		tiger[selectTiger].setStroke(Color.BLACK);
            	        		
            					System.out.println("click2 at point "+path[path[next][j]][j]+" previous--> "+next+" next--> "+ i + " tiger("+selectTiger+")");
            					
            	        		click2ForTiger=false;
            	        		click1ForTiger=false;
            	        		
            	        		click1ForGoat=true;
            	        		
            	        		tigerWon();
            	        		
								System.out.println("Break4sub2");
            	        		break;
            	        		
        					}
        						
        				}
        					
        				
        				
        				if(path[next][j]==i && boardPoint[path[next][j]][2]==0) {
        					
        					selectPosition=i;
    	        			
    	        			boardPoint[path[next][j]][2]+=100;
        					boardPoint[next][2]-=100;
    	        			
    	        			
    	        			tigerIndex[selectTiger]=selectPosition;
    	        			tiger[selectTiger].setStroke(Color.BLACK);
        					
    	        			System.out.println("click2 at point "+path[next][j]+" previous--> "+next+" next--> "+ i + " tiger("+selectTiger+")");

    	        			
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
    			
    			System.out.println(j+"  "+boardPoint[j][2]);
    				
    		}
             
             
         });
		
		
		
		
		
		new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
            	
            	update();
            }
            
        }.start();
		
		
	}
	
	public void update() {
		
		for(int i=0;i<25;i++) tempIndex[i]=0;
    	
    	for(int i=0;i<goat.size();i++) {
    		            		
    		goat.get(i).setCenterX(boardPoint[goatIndex.get(i)][0]);
    		goat.get(i).setCenterY(boardPoint[goatIndex.get(i)][1]-tempIndex[goatIndex.get(i)]*6);
    		
    		tempIndex[goatIndex.get(i)]++;
    		
    	}
    	
    	
    	tiger[0].setCenterX(boardPoint[tigerIndex[0]][0]);
		tiger[0].setCenterY(boardPoint[tigerIndex[0]][1]);
    	
		tiger[1].setCenterX(boardPoint[tigerIndex[1]][0]);
		tiger[1].setCenterY(boardPoint[tigerIndex[1]][1]);
		
	}
	
	
	public void killTheGoat(int index) {
		
		System.out.println("Goat killed at " + index);
		
		for(int i=0;i<goat.size();i++) {
			
			if(goatIndex.get(i)==index) {
				
				for(int j=i+1;j<goat.size();j++)
				{
					if(goatIndex.get(j)==index) i++;
					
					else break;
					
				}
				
				root.getChildren().remove(goat.get(i));
				goat.remove(i);
				goatIndex.remove(i);
			
				System.out.println("Number of the goat killed  " + i);
				
			}
			
		}
		
	}
	
	public void tigerWon() {
		
		if(goat.size()==0) {
			
			Label label = new Label();  
			
			label.relocate((double)boardPoint[6][0], (double)boardPoint[6][1]);
			label.setText("Game Over\nTiger Win");
			label.setTextFill(Color.DARKBLUE);
			label.setFont(new Font("Arial",60));
			root.getChildren().add(label);
			
			click1ForGoat=false;
			click2ForGoat=false;
			click1ForTiger=false;
			click2ForTiger=false;
			System.out.println("Tiger win");
			
		}
	}
	
	public void goatWon() {
		
		int flag=1;
		
		for(int i=0;i<2;i++) {
			
			for(int j=0;j<8;j++) {
				
				if(path[tigerIndex[i]][j]>=0) {
					
					if(boardPoint[path[tigerIndex[i]][j]][2]==0) {
						
						flag=0;
						
					}
					
					if(path[path[tigerIndex[i]][j]][j]>=0) {
						
						if( boardPoint[path[path[tigerIndex[i]][j]][j]][2]==0) {
							
							flag=0;
							
						}
					}
					
					
				}
				
				
			}
			
		}
		
		if(flag==1) {
			
			Label label = new Label();  
			
			label.relocate((double)boardPoint[6][0], (double)boardPoint[6][1]);
			label.setText("Game Over\nGoat Win");
			label.setTextFill(Color.DARKBLUE);
			label.setFont(new Font("Arial",60));
			
			root.getChildren().add(label);
			
			click1ForGoat=false;
			click2ForGoat=false;
			click1ForTiger=false;
			click2ForTiger=false;
			
			System.out.println("Goat win");
			
		}
		
	}
	
	
	public static void main(String[] args) {
		
		launch(args);
		
	}
	
}



