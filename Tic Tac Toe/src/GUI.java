

import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.lcd.*;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.utility.Delay;


public class GUI {
	
   protected Cell[][] cells; // the board's ROWS-by-COLS array of Cells
   protected Seed mySeed;    // computer's seed
   protected Seed oppSeed;   // opponent's seed	
   
   public int ROWS;
   public int COLS;
	
	public GUI(Board board) {
		cells = board.cells;
		ROWS = board.ROWS;
		COLS = board.COLS;
		
		//drawBoard();
	}

	public void drawBoard() {
		GraphicsLCD g = BrickFinder.getDefault().getGraphicsLCD();
		final int SW = g.getWidth();
		final int SH = g.getHeight();
		g.clear();
		g.setFont(Font.getLargeFont());
		g.setStrokeStyle(0);
		g.drawLine(0, SH/3, SW, SH/3);
		g.drawLine(0, 2*SH/3, SW, 2*SH/3);
		g.drawLine(SW/3, 0, SW/3, SH);
		g.drawLine(2*SW/3, 0, 2*SW/3, SH);

		int [][] pos_fields = {{SW/6,SW/2,5*SW/6},{7*SH/24,15*SH/24,23*SH/24}};
		for (int ix=0;ix<3;ix++) {
			for (int iy=0;iy<3;iy++) {
				if (cells[iy][ix].content != Seed.EMPTY) {
					if (cells[iy][ix].content == Seed.CROSS) {
						g.drawChar('X',pos_fields[0][ix],pos_fields[1][iy],GraphicsLCD.HCENTER|GraphicsLCD.BASELINE);
					} else if (cells[iy][ix].content == Seed.NOUGHT) {
						g.drawChar('O',pos_fields[0][ix],pos_fields[1][iy],GraphicsLCD.HCENTER|GraphicsLCD.BASELINE);
					}
				}
			}
		}
	}
	
	int[] humanMove() {
		int[] move_human = { 1 , 1 };
		GraphicsLCD g = BrickFinder.getDefault().getGraphicsLCD();
		final int SW = g.getWidth();
		final int SH = g.getHeight();
		int[][] pos_fields = { { SW/12, 5*SW / 12, 9 * SW / 12 }, { SH / 12, 5 * SH / 12, 9 * SH / 12 } };
		while (true) {
			drawBoard();
			g.setStrokeStyle(1);
			g.drawRect(pos_fields[0][move_human[1]], pos_fields[1][move_human[0]], 30, 30);
			int but = Button.waitForAnyPress();
            if ((but & Button.ID_ENTER) != 0) {
            	break;
            }
            if ((but & Button.ID_LEFT) != 0) {
            	move_human[1] = move_human[1]-1;
            	if (move_human[1] < 0) move_human[1] = move_human[1]+3;
            }
            if ((but & Button.ID_RIGHT) != 0) {
            	move_human[1] = move_human[1]+1;
            	if (move_human[1] > 2) move_human[1] = move_human[1]-3;
            }
            if ((but & Button.ID_UP) != 0) {
            	move_human[0] = move_human[0]-1;
            	if (move_human[0] < 0) move_human[0] = move_human[0]+3;
            }
            if ((but & Button.ID_DOWN) != 0) {
            	move_human[0] = move_human[0]+1;
            	if (move_human[0] > 2) move_human[0] = move_human[0]-3;
            }
		}
		return move_human;
		
	}
	
	/** Paint itself */
	public void paint() {
      for (int row = 0; row < ROWS; ++row) {
         for (int col = 0; col < COLS; ++col) {
            cells[row][col].paint();   // each cell paints itself
            if (col < COLS - 1) System.out.print("|");
         }
         System.out.println();
         if (row < ROWS - 1) {
            System.out.println("-----------");
         }
      }
   }



}
