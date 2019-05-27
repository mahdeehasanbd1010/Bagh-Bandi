package application;

import static application.Main.screenHeight;
import static application.Main.screenWidth;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class AlquerqueBoard {
	
	private Pane root = new Pane();
	
	private int [][]boardPoint = new int[25][3];
	private int [][] path = new int[25][8];
	
	private ArrayList<Circle> goat = new ArrayList<Circle>();
	private ArrayList<Integer> goatIndex = new ArrayList<Integer>();
	
	private ArrayList<Circle> tiger = new ArrayList<Circle>();
	private ArrayList<Integer> tigerIndex = new ArrayList<Integer>();
	
	private int height,width;
	
	
	
	public AlquerqueBoard() {
		
		Image im = new Image("/application/tableImage.jpg",false);
        ImageView iv = new ImageView();
        iv.setImage(im);
        iv.setFitWidth(screenWidth);
        iv.setFitHeight(screenHeight);
		
        root.getChildren().add(iv);
		
		
		createPoint();
		drawLine();
		designPoint();
		createPiece();
		Graph();
		
	}
	
	
	
	
	
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
		
		boardPoint[7][2]=5;
		boardPoint[9][2]=5;
		boardPoint[17][2]=5;
		boardPoint[19][2]=5;
		
		boardPoint[11][2]=100;
		boardPoint[13][2]=100;
		
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
			
			if(i==7) {
				
				for(int j=0;j<5;j++) {
					
					Circle circle = new Circle();
			        circle.setCenterX(boardPoint[i][0]);
			        circle.setCenterY(boardPoint[i][1]);
			        circle.setRadius(20);
			        circle.setFill(Color.valueOf("#fff9f4"));
			        circle.setStroke(Color.BLACK);
			        circle.setStrokeWidth(3);
			        
			        Image im = new Image("/application/goat.jpg",false);
			        ImageView myImageView = new ImageView();
			        myImageView.setImage(im);
			        
			        circle.setFill(new ImagePattern(im));
			     
			        root.getChildren().add(circle);
			        goat.add(circle);
			        goatIndex.add(i);
			        
				
				}
				
			}
			
			else if(i==9) {
				
				for(int j=0;j<5;j++) {
					
					Circle circle = new Circle();
			        circle.setCenterX(boardPoint[i][0]);
			        circle.setCenterY(boardPoint[i][1]);
			        circle.setRadius(20);
			        circle.setFill(Color.valueOf("#fff9f4"));
			        circle.setStroke(Color.BLACK);
			        circle.setStrokeWidth(3);
			        
			        Image im = new Image("/application/goat.jpg",false);
			        ImageView myImageView = new ImageView();
			        myImageView.setImage(im);
			        
			        circle.setFill(new ImagePattern(im));
			        
			        root.getChildren().add(circle);
			        goat.add(circle);
			        goatIndex.add(i);
			        
				}
				
			}
			
			else if(i==17) {
				
				for(int j=0;j<5;j++) {
					
					Circle circle = new Circle();
			        circle.setCenterX(boardPoint[i][0]);
			        circle.setCenterY(boardPoint[i][1]);
			        circle.setRadius(20);
			        circle.setFill(Color.valueOf("#fff9f4"));
			        circle.setStroke(Color.BLACK);
			        circle.setStrokeWidth(3);
			        
			        Image im = new Image("/application/goat.jpg",false);
			        ImageView myImageView = new ImageView();
			        myImageView.setImage(im);
			        
			        circle.setFill(new ImagePattern(im));
			        
			        root.getChildren().add(circle);
			        goat.add(circle);
			        goatIndex.add(i);
			        
				}
				
				
			}
			
			else if(i==19) {
				
				for(int j=0;j<5;j++) {
					
					Circle circle = new Circle();
			        circle.setCenterX(boardPoint[i][0]);
			        circle.setCenterY(boardPoint[i][1]);
			        circle.setRadius(20);
			        circle.setFill(Color.valueOf("#fff9f4"));
			        circle.setStroke(Color.BLACK);
			        circle.setStrokeWidth(3);
			        
			        Image im = new Image("/application/goat.jpg",false);
			        ImageView myImageView = new ImageView();
			        myImageView.setImage(im);
			        
			        circle.setFill(new ImagePattern(im));
			        
			        root.getChildren().add(circle);
			        goat.add(circle);
			        goatIndex.add(i);
			        
				}
				
			}
			
			else if(i==11) {
				
				Circle circle = new Circle();
		        circle.setCenterX(boardPoint[i][0]);
		        circle.setCenterY(boardPoint[i][1]);
		        circle.setRadius(20);
		        //circle.setFill(Color.valueOf("#c40003"));
		        circle.setStroke(Color.BLACK);
		        circle.setStrokeWidth(3);
		        
		        Image im = new Image("/application/tiger.png",false);
		        ImageView myImageView = new ImageView();
		        myImageView.setImage(im);
		        
		        circle.setFill(new ImagePattern(im));
		        
		        root.getChildren().add(circle);
		        tiger.add(circle);
		        tigerIndex.add(i);
		        
			}
			
			else if(i==13) {
				
				Circle circle = new Circle();
		        circle.setCenterX(boardPoint[i][0]);
		        circle.setCenterY(boardPoint[i][1]);
		        circle.setRadius(20);
		        //circle.setFill(Color.valueOf("#c40003"));
		        circle.setStroke(Color.BLACK);
		        circle.setStrokeWidth(3);
		        
		        Image im = new Image("/application/tiger.png",false);
		        ImageView myImageView = new ImageView();
		        myImageView.setImage(im);
		        
		        circle.setFill(new ImagePattern(im));
		        
		        root.getChildren().add(circle);
		        tiger.add(circle);
		        tigerIndex.add(i);
		        
		       
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

	
	
	public Pane getRoot() {
		return root;
	}

	
	
	public int[][] getBoardPoint() {
		return boardPoint;
	}

	
	
	public int[][] getPath() {
		return path;
	}

	
	
	public ArrayList<Circle> getGoat() {
		return goat;
	}

	
	
	public ArrayList<Integer> getGoatIndex() {
		return goatIndex;
	}

	
	
	public ArrayList<Circle> getTiger() {
		return tiger;
	}

	
	
	public ArrayList<Integer> getTigerIndex() {
		return tigerIndex;
	}

	
	
	public int getHeight() {
		return height;
	}

	
	
	public int getWidth() {
		return width;
	}
	
	
}
