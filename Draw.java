import javax.swing.*;

import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Draw extends JPanel{
	
	static boolean tetriminoCreated = false;
	static int[][] tetrimino = new int[4][2];
	static int ymoved;
	static int xmoved;
	static boolean[][] hasBlock = new boolean[12][22];
	static char currentPiece;
	static int state = 0;
	static int timerDelay = 50;
	static int moveDown = 0;
	static int score = 0;
	
	Timer t = new Timer(50, new ActionListener(){
		public void actionPerformed(ActionEvent e){
			t.setDelay(timerDelay);
			if(moveDown == 10){
			ymoved++;
			moveDown = 0;
			}
			if(moveDown == -1){
				ymoved++;
			}
			
			moveDown++;
			repaint();
			
			int a, b, a2, b2;
			for(a = 0; a < 12; a++){
				hasBlock[a][21] = true;
			}
			for(a = 0; a < 22; a++){
				hasBlock[0][a] = true;
				hasBlock[11][a] = true;
			}
			
			for(b = 0; b < 21; b++){
				int blockFullRow = 0;
				for(a = 1; a < 11; a++){
					if(hasBlock[a][b] == true){
						blockFullRow++;
					}
					if(blockFullRow == 10){
						score+=100;
						if(timerDelay>10){
						timerDelay--;
						}
						for(a2 = 1; a2 < 11; a2++){
						  hasBlock[a2][b] = false;
							 for(b2 = b; b2 > 0; b2-- ){
								 hasBlock[a2][b2] = hasBlock[a2][b2 - 1];
							 }
							 
							 
						}
					}
				}
			}
			
			
		}
		
		
	});
	
	
	Draw(){
	 t.start();
	 setFocusable(true);
	 addKeyLis();
	 addMouseLis();
	 	 
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics f = (Graphics2D) g;
		setBackground(Color.PINK);		
		f.setColor(Color.BLACK);
		
		int a,b;
		
		for(a = 0; a < 16; a++){
			for(b = 0; b < 22; b++){
				f.drawRect(a * 20, b * 20, 20, 20);
			}
		}
		
		
		Image bg = null;
		try {
		    bg = ImageIO.read(new File("img/bg.png"));
		} catch (IOException e) {
		}
		Image newgame = null;
		try {
		    newgame = ImageIO.read(new File("img/newgame.png"));
		} catch (IOException e) {
		}
		Image scoreboard = null;
		try {
		    scoreboard = ImageIO.read(new File("img/scoreboard.png"));
		} catch (IOException e) {
		}
				
		f.drawImage(bg, 20, 20, null);
		f.drawImage(newgame, 220, 40, null);
		f.drawImage(scoreboard, 220, 20, null);
		f.drawRect(20,20,200,400);
		//DRAW SCORE
		
		Font scoreFont = new Font("monospaced", Font.PLAIN, 20);
		f.setFont(scoreFont);
		f.drawString("" + score, 225, 38);
		
		//DRAW MOVING PIECE
		
		if(tetriminoCreated == false){
		
		tetrimino = Tetris.CreatePiece();
		xmoved = 4;
		ymoved = 1;
		tetriminoCreated = true;
		
		}
		
		f.setColor(Color.PINK);
		f.fillRect(20 * tetrimino[0][0] + 20 * xmoved , 20 * tetrimino[0][1] + 20 * ymoved, 20, 20);
		f.fillRect(20 * tetrimino[1][0] + 20 * xmoved, 20 * tetrimino[1][1] + 20 * ymoved, 20, 20);
		f.fillRect(20 * tetrimino[2][0] + 20 * xmoved, 20 * tetrimino[2][1] + 20 * ymoved, 20, 20);
		f.fillRect(20 * tetrimino[3][0] + 20 * xmoved, 20 * tetrimino[3][1] + 20 * ymoved, 20, 20);
		f.setColor(Color.BLACK);
		f.drawRect(20 * tetrimino[0][0] + 20 * xmoved , 20 * tetrimino[0][1] + 20 * ymoved, 20, 20);
		f.drawRect(20 * tetrimino[1][0] + 20 * xmoved, 20 * tetrimino[1][1] + 20 * ymoved, 20, 20);
		f.drawRect(20 * tetrimino[2][0] + 20 * xmoved, 20 * tetrimino[2][1] + 20 * ymoved, 20, 20);
		f.drawRect(20 * tetrimino[3][0] + 20 * xmoved, 20 * tetrimino[3][1] + 20 * ymoved, 20, 20);
		
		if(hasBlock[tetrimino[0][0] + xmoved][tetrimino[0][1] + ymoved] == true ){
			pieceLanded();
		}
		if(hasBlock[tetrimino[1][0] + xmoved][tetrimino[1][1] + ymoved] == true ){
			pieceLanded();
		}
		if(hasBlock[tetrimino[2][0] + xmoved][tetrimino[2][1] + ymoved] == true ){
			pieceLanded();
		}
		if(hasBlock[tetrimino[3][0] + xmoved][tetrimino[3][1] + ymoved] == true ){
			pieceLanded();
		}
		
		//DRAW PIECES THAT ARE STILL
		f.setColor(Color.PINK);
		for(a = 1; a < 11; a++){
			for(b = 0; b < 21; b++){
				if(hasBlock[a][b] == true){
					f.setColor(Color.PINK);
					f.fillRect(a * 20, b * 20, 20, 20);
					f.setColor(Color.BLACK);
					f.drawRect(a * 20, b * 20, 20, 20);
				}
			}
		}
		
	
	}
	
	public void addKeyLis(){
		
		
		addKeyListener(new KeyListener(){
	
		public void keyPressed(KeyEvent e){
			
			if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				if(checkCollisionRight() == false){
				xmoved++;
				}
				
			}
			
			if(e.getKeyCode() == KeyEvent.VK_LEFT){
				if(checkCollisionLeft() == false){
				xmoved--;
				}
			}
			
			if(e.getKeyCode() == KeyEvent.VK_UP){
				if(ymoved >= 2){
				spinPiece();
				}
			}
			
			if(e.getKeyCode() == KeyEvent.VK_DOWN){
				moveDown = -1;
			}
		}
        public void keyReleased(KeyEvent e){
			if(e.getKeyCode() == KeyEvent.VK_DOWN){
				moveDown = 10;
			}
		}
        
		public void keyTyped(KeyEvent e){
        
		}
	});	
		
	}
	public void addMouseLis(){
	addMouseListener(new MouseListener(){

		@Override
		public void mouseClicked(MouseEvent e) {	
			Point mousepoint = e.getPoint();
			Rectangle2D.Double bounds = new Rectangle2D.Double(220, 40, 100, 20);
	        if (bounds.contains(mousepoint)){
	        	timerDelay = 50;
	        	int a, b;
				for(a = 1; a < 11; a++){
					for(b = 1; b < 21; b++){
						hasBlock[a][b] = false;
						score = 0;
						tetrimino[0][0] = 0;
						tetrimino[0][1] = 0;
						tetrimino[1][0] = 0;
						tetrimino[1][1] = 0;
						tetrimino[2][0] = 0;
						tetrimino[2][1] = 0;
						tetrimino[3][0] = 0;
						tetrimino[3][1] = 0;
						tetriminoCreated = false;
					}
				}
	        	
	        }
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {	
		}
	});
	}
	
	public static void pieceLanded(){
		hasBlock[tetrimino[0][0] + xmoved][tetrimino[0][1] + ymoved - 1] = true;
		hasBlock[tetrimino[1][0] + xmoved][tetrimino[1][1] + ymoved - 1] = true;
		hasBlock[tetrimino[2][0] + xmoved][tetrimino[2][1] + ymoved - 1] = true;
		hasBlock[tetrimino[3][0] + xmoved][tetrimino[3][1] + ymoved - 1] = true;
		tetriminoCreated = false;
		state = 0;
	}
	
	public static void spinPiece(){
		switch (currentPiece) {
		case 'O':
			//do nothing
			break;
		case 'I':
			if(checkCollision4x4() == false){
			if(state == 0 || state == 2){
				tetrimino[0][0] = 1;
				tetrimino[0][1] = -1;
			    tetrimino[2][0] = 1;
			    tetrimino[2][1] = 1;
			    tetrimino[3][0] = 1;
			    tetrimino[3][1] = 2;
			}else{
				tetrimino[0][0] = 0;
				tetrimino[0][1] = 0;
			    tetrimino[2][0] = 2;
			    tetrimino[2][1] = 0;
			    tetrimino[3][0] = 3;
			    tetrimino[3][1] = 0;
			}
			}
			
			break;
		case 'L':
			if(checkCollision3x3() == false){
			if(state == 0 || state == 2){
				tetrimino[0][0] = 1;
				tetrimino[0][1] = 0;
			    tetrimino[2][0] = 1;
			    tetrimino[2][1] = 2;
			}else{
				tetrimino[0][0] = 0;
				tetrimino[0][1] = 1;
			    tetrimino[2][0] = 2;
			    tetrimino[2][1] = 1;
			}
			if(state == 0){
				tetrimino[3][0] = 2;
				tetrimino[3][1] = 2;
			}
		    if(state == 1){
		    	tetrimino[3][0] = 0;
				tetrimino[3][1] = 2;
		    }
		    if(state == 2){
		    	tetrimino[3][0] = 0;
				tetrimino[3][1] = 0;
		    }
		    if(state == 3){
		    	tetrimino[3][0] = 2;
				tetrimino[3][1] = 0;
		    }
			}
			
			break;
			
		case 'J':
			if(checkCollision3x3() == false){
			if(state == 0 || state == 2){
				tetrimino[0][0] = 1;
				tetrimino[0][1] = 0;
				tetrimino[1][1] = 1;
			    tetrimino[2][0] = 1;
			    tetrimino[2][1] = 2;
			}else{
				tetrimino[0][0] = 0;
				tetrimino[0][1] = 1;
				tetrimino[1][1] = 1;
			    tetrimino[2][0] = 2;
			    tetrimino[2][1] = 1;
			}
			if(state == 0){
				tetrimino[3][0] = 0;
				tetrimino[3][1] = 2;
			}
		    if(state == 1){
		    	tetrimino[3][0] = 0;
				tetrimino[3][1] = 0;
		    }
		    if(state == 2){
		    	tetrimino[3][0] = 2;
				tetrimino[3][1] = 0;
		    }
		    if(state == 3){
		    	tetrimino[3][0] = 2;
				tetrimino[3][1] = 2;
		    }
			}
			
			break;
		case 'T':
			if(checkCollision3x3() == false){
			if(state == 0){
				tetrimino[2][0] = 1;
			    tetrimino[2][1] = -1;
			}
			if(state == 1){
				tetrimino[3][0] = 2; 
				tetrimino[3][1] = 0;
			}
			if(state == 2){
				tetrimino[0][0] = 1;
				tetrimino[0][1] = 1;
			}
			if(state == 3){
				tetrimino[0][0] = 0;
				tetrimino[0][1] = 0;
				tetrimino[1][0] = 1;
				tetrimino[1][1] = 0;
			    tetrimino[2][0] = 2;
			    tetrimino[2][1] = 0;
			    tetrimino[3][0] = 1;
			    tetrimino[3][1] = 1;
			}
			}
			
			break;
		case 'S':
			if(checkCollision3x3() == false){
			if(state == 0 || state == 2){		
			tetrimino[0][1] = -1;
			tetrimino[3][0] = 0;
			}else{
			tetrimino[0][1] = 1;
			tetrimino[3][0] = 2; 
			}
			}
			break;
		case 'Z':
			if(checkCollision3x3() == false){
			if(state == 1 || state == 3){
			tetrimino[0][0] = 0;	
			tetrimino[3][1] = 1;
			}else{
		    tetrimino[0][0] = 2;
		    tetrimino[3][1] = -1;
			}
			}
			break;
		default:
			break;
		}
		if(state <= 3) state++;
		if(state == 4) state = 0;
	}
	
	public static boolean checkCollisionRight(){
		
		if(hasBlock[tetrimino[0][0] + xmoved + 1][tetrimino[0][1] + ymoved] == true ){
			return true;
		}
		if(hasBlock[tetrimino[1][0] + xmoved + 1][tetrimino[1][1] + ymoved] == true ){
			return true;
		}
		if(hasBlock[tetrimino[2][0] + xmoved + 1][tetrimino[2][1] + ymoved] == true ){
			return true;
		}
		if(hasBlock[tetrimino[3][0] + xmoved + 1][tetrimino[3][1] + ymoved] == true ){
			return true;
		}
		
		return false;
	}
	
	public static boolean checkCollisionLeft(){
		
		if(hasBlock[tetrimino[0][0] + xmoved - 1][tetrimino[0][1] + ymoved] == true ){
			return true;
		}
		if(hasBlock[tetrimino[1][0] + xmoved - 1][tetrimino[1][1] + ymoved] == true ){
			return true;
		}
    	if(hasBlock[tetrimino[2][0] + xmoved - 1][tetrimino[2][1] + ymoved] == true ){
			return true;
		}
		if(hasBlock[tetrimino[3][0] + xmoved - 1][tetrimino[3][1] + ymoved] == true ){
			return true;
		}
		
		return false;
	}
	
	public static boolean checkCollision3x3(){
		int a,b;
		for(a = 0; a < 3; a++){
			for(b = 0; b < 3; b++){
				if(hasBlock[tetrimino[1][0] + xmoved + a - 1][tetrimino[1][1] + ymoved + b - 1] == true){
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static boolean checkCollision4x4(){
		int a,b;
		for(a = 0; a < 4; a++){
			for(b = 0; b < 4; b++){
				if(hasBlock[tetrimino[1][0] + xmoved +a - 1][tetrimino[1][1] + ymoved + b - 1] == true){
					return true;
				}
			}
		}
		
		return false;
	}
}