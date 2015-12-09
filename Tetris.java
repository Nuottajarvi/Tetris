import javax.swing.*;
import java.awt.EventQueue;
import java.util.Random;

public class Tetris{
	
	public static void main(String[] args) {
	
		
		 EventQueue.invokeLater(new Runnable() {
	            
	            @Override
	            public void run() {                
	                JFrame JF = new JFrame();
	                JF.setSize(327,470);
	        		JF.setResizable(false);
	        		JF.setLocationRelativeTo(null);
	        		JF.setVisible(true);	
	            	JPanel JP = new Draw();
	                JP.setVisible(true); 
	                JF.add(JP);
	            }
	        });
	    }
		

	
	
	public static int[][] CreatePiece(){
		
		Random r = new Random();
		int randomtetrimino = r.nextInt(7);
		int[][] tetrimino = new int[4][2];
				
		//int[][0] = x value int[][1] = y value
		
		switch (randomtetrimino) {
		case 0://O
			Draw.currentPiece = 'O';
			tetrimino[0][0] = 0;
			tetrimino[0][1] = 0;
			tetrimino[1][0] = 0;
			tetrimino[1][1] = 1;
		    tetrimino[2][0] = 1;
		    tetrimino[2][1] = 0;
		    tetrimino[3][0] = 1;
		    tetrimino[3][1] = 1;
			break;
		case 1://I
			Draw.currentPiece = 'I';
			tetrimino[0][0] = 0;
			tetrimino[0][1] = 0;
			tetrimino[1][0] = 1;
			tetrimino[1][1] = 0;
		    tetrimino[2][0] = 2;
		    tetrimino[2][1] = 0;
		    tetrimino[3][0] = 3;
		    tetrimino[3][1] = 0;
			break;
		case 2://L
			Draw.currentPiece = 'L';
			tetrimino[0][0] = 0;
			tetrimino[0][1] = 1;
			tetrimino[1][0] = 1;
			tetrimino[1][1] = 1;
		    tetrimino[2][0] = 2;
		    tetrimino[2][1] = 1;
		    tetrimino[3][0] = 2;
		    tetrimino[3][1] = 0;
			break;
		case 3://J
			Draw.currentPiece = 'J';
			tetrimino[0][0] = 0;
			tetrimino[0][1] = 0;
			tetrimino[1][0] = 1;
			tetrimino[1][1] = 0;
		    tetrimino[2][0] = 2;
		    tetrimino[2][1] = 0;
		    tetrimino[3][0] = 2;
		    tetrimino[3][1] = 1;
			break;
		case 4://T
			Draw.currentPiece = 'T';
			tetrimino[0][0] = 0;
			tetrimino[0][1] = 0;
			tetrimino[1][0] = 1;
			tetrimino[1][1] = 0;
		    tetrimino[2][0] = 2;
		    tetrimino[2][1] = 0;
		    tetrimino[3][0] = 1;
		    tetrimino[3][1] = 1;
			break;
		case 5://S
			Draw.currentPiece = 'S';
			tetrimino[0][0] = 0;
			tetrimino[0][1] = 1;
			tetrimino[1][0] = 1;
			tetrimino[1][1] = 1;
		    tetrimino[2][0] = 1;
		    tetrimino[2][1] = 0;
		    tetrimino[3][0] = 2;
		    tetrimino[3][1] = 0;
			break;
		case 6://Z
			Draw.currentPiece = 'Z';
			tetrimino[0][0] = 0;
			tetrimino[0][1] = 0;
			tetrimino[1][0] = 1;
			tetrimino[1][1] = 0;
		    tetrimino[2][0] = 1;
		    tetrimino[2][1] = 1;
		    tetrimino[3][0] = 2;
		    tetrimino[3][1] = 1;
			break;
		default:
			break;
			
			
		}
		
		return tetrimino;
	}
	
}
