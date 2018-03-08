package tictactoe;

import lejos.hardware.BrickFinder;
import lejos.hardware.lcd.*;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.utility.Delay;
import java.util.Scanner;


public class GUI {

	public static void main(String[] args) {
		//Player 1 = Human (X)
		//Player 2 = Computer (O)

		//BEGIN GAME
		int turn = 1;
		int [] move;
		int [][] board = {{0,0,0},{0,0,0},{0,0,0}};
		int won = 0;
		boolean full = false;
		//drawBoard(board);
		do {
			if (turn == 1) {
				// Human's turn
				// Wait for input human
			    Scanner scan= new Scanner(System.in);
			    System.out.println("Move human:");
			    String text= scan.nextLine();
			    move = new int[] {Integer.parseInt(text.substring(0, 1)),Integer.parseInt(text.substring(1, 2))};
			} else {
				move = AIMove(board);
			}
			board[move[0]][move[1]] = turn;
			//drawBoard(board);
			won = someoneHasWon(board);
			full = boardIsFull(board);
			if (turn == 1) {
				turn = 2;
			} else {
				turn = 1;
			}
		} while (won == 0&&!full);
		if (won != 0) {
			System.out.println("Player " + Integer.toString(won)+" has won!");
		}
		//drawBoard(board);
		Delay.msDelay(10000);
	}
	public static void drawBoard(int[][] board) {
		GraphicsLCD g = BrickFinder.getDefault().getGraphicsLCD();
		final int SW = g.getWidth();
		final int SH = g.getHeight();
		g.clear();
		g.setFont(Font.getLargeFont());
		g.drawLine(0, SH/3, SW, SH/3);
		g.drawLine(0, 2*SH/3, SW, 2*SH/3);
		g.drawLine(SW/3, 0, SW/3, SH);
		g.drawLine(2*SW/3, 0, 2*SW/3, SH);

		int [][] pos_fields = {{SW/6,SW/2,5*SW/6},{7*SH/24,15*SH/24,23*SH/24}};
		for (int ix=0;ix<3;ix++) {
			for (int iy=0;iy<3;iy++) {
				if (board[iy][ix] != 0) {
					if (board[iy][ix] == 1) {
						g.drawChar('X',pos_fields[0][ix],pos_fields[1][iy],GraphicsLCD.HCENTER|GraphicsLCD.BASELINE);
					} else if (board[iy][ix] == 2) {
						g.drawChar('O',pos_fields[0][ix],pos_fields[1][iy],GraphicsLCD.HCENTER|GraphicsLCD.BASELINE);
					}
				}
			}
		}
	}
	public static int[] AIMove(int[][] board) {
		//What's the move of the computer?
		int [] move = {1,1};
		return move;
	}
	public static int someoneHasWon(int[][] board) {
		int count1 = 0, count2 = 0;
		for (int ix = 0; ix < 3; ix++) {
			for (int iy = 0; iy < 3; iy++) {
				if (board[iy][ix] == 1) count1++;
				if (board[iy][ix] == 2) count2++;
			}
			if (count1 == 3) return 1;
			if (count2 == 3) return 2;
			count1 = 0;
			count2 = 0;
		}
		for (int iy = 0; iy < 3; iy++) {
			for (int ix = 0; ix < 3; ix++) {
				if (board[iy][ix] == 1) count1++;
				if (board[iy][ix] == 2) count2++;
			}
			if (count1 == 3) return 1;
			if (count2 == 3) return 2;
			count1 = 0;
			count2 = 0;
		}
		for (int i = 0; i < 3; i++) {
			if (board[i][i] == 1) count1++;
			if (board[i][i] == 2) count2++;
		}
		if (count1 == 3) return 1;
		if (count2 == 3) return 2;
		count1 = 0;
		count2 = 0;
		for (int i = 0; i < 3; i++) {
			if (board[i][2-i] == 1) count1++;
			if (board[i][2-i] == 2) count2++;
		}
		if (count1 == 3) return 1;
		if (count2 == 3) return 2;
		return 0;
	}
	public static boolean boardIsFull(int [][] board) {
		int count = 0;
		for (int ix = 0; ix < 3; ix++) {
			for (int iy = 0; iy < 3; iy++) {
				if (board[iy][ix] == 0) count++;
			}
		}
		if (count == 0) return true;
		return false;

	}
}
