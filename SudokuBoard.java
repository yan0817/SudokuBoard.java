# SudokuBoard.java
Sudoku Solver Project

/**
This is the GameBoard Class that will be used by the Sudoku Solver class to solve Sudoku Puzzles
@author Yanni Angelides
@version 12/2/15
*/

import java.io.*;
import java.util.Scanner;
import java.lang.String;

public class SudokuBoard
{
	public int[][] board;
	
	public SudokuBoard()
	{
		readCSV();
	}
	/**
	Constructor that updates the board. Makes a new board that s a complete copy of the old one and adds the new value at the given position
	@param int[][] the old game board, int value to be added, int row it is supposed to be added to, int column it is supposed ot be added to 
	*/
	public SudokuBoard(int[][] gBoard, int value, int row, int col)
	{
		for(int i = 0; i < gBoard.length; i++)
		{
			for(int j = 0; j < gBoard.length; j++)
			{
				board[i][j] = gBoard[i][j];	
			}
		}
		board[row][col] = value;
	}
	
	/**
	Reads the csv file that has the initial game board and turns that csv file into an array of String that is then transfered into the board class field
	*/
	public void readCSV()
	{
		String pathname = "SudokuTest.csv";
		File file = new File(pathname);	
		Scanner input = null;
		try
		{
			input = new Scanner(file);
		}
		catch (FileNotFoundException ex)
		{
			System.out.println(" Cannot open " + pathname );
			System.exit(1);
		}
		String[] arr2;
		board = new int[9][9]; 
		while(input.hasNextLine())
		{              
			for(int j = 0; j < board.length ; j++) 
			{
				arr2 = input.nextLine().split(","); //organizes the values in that line of the csv into an array of string
				for (int i = 0; i < board[j].length; i++)
				{
					char check = arr2[i].charAt(0);
					if(((int)(check)) > 48 && ((int)(check)) < 58) //checks that the value in that spot of the csv file is a digit
					{                                   
						board[j][i] = Integer.parseInt(arr2[i]);
					}
					else
					{
						board[j][i] = 0;
					}
				}
			}	 		
		}
		
	}
	
	/**
	Prints out a picture of the current board
	*/
	public void displayBoard()
	{
		for (int i = 0; i < board.length; i++) //loop that goes through the values of the board
		{
			int len = board[i].length;
			System.out.print("-"); //printing boarder 
			while (len > 0)
			{
				System.out.print("----"); //printing boarder
				len--;
			}
			System.out.println();
			for (int a = 0; a < board[i].length; a++)
			{
				if (a==0)
				{
					System.out.print("| " + board[i][a] + " | ");
				}
				else
				{
				System.out.print(board[i][a] + " | ");
				}
			}
			System.out.println();
		}
		int len = board[board.length - 1].length;
		System.out.print("-");
		while (len > 0)
		{
			System.out.print("----");
			len--;
		}
		System.out.println();
	}
	
	
	public void place(int row, int col, int val)
	{
		board[row][col] = val;
	}
	
	public int get(int row, int col)
	{
		return board[row][col];
	}
	
	public void remove(int row, int col)
	{
		board[row][col] = 0;
	}
	
	/**
	Method that checks if a value can be placed at a certain spot 
	@param int row that the value is to be placed in, int column the value is to be placed in, int value that is to be placed
	@return boolean indicating if the value can be placed in the specified spot
	*/
	public boolean canPlace(int row, int col, int val)
	{
		if(checkRow(row, val) == true && checkCol(col, val) == true && checkBox(row, col, val) == true) //this method uses helper methods that return true if the value can be placed, so if all helper methods return true then the value can be placed in that spot 
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	checks if the value can be placed in the specified row
	@param int row the that needs to be checked, int value that needs to be checked
	@return boolean indicating if the value can be placed in the row
	*/
	public boolean checkRow(int row, int val)
	{
		for (int i = 0; i < board.length; i++) //goes through all the values in the row
		{
			if(board[row][i] == val)
			{
				return false;
			}
		}
		return true;
	}
	
	/**
	Checks if the value can be placed in the specified column
	@param int column that needs to be checked, int value that needs to be checked
	@return boolean indicating if the value can be placed in that column
	*/
	public boolean checkCol(int col, int val)
	{
		for (int i = 0; i < board.length; i++) //for loop that goes through the column
		{
			if(board[i][col] == val)
			{
				return false;
			}
		}
		return true;
	}
	
	/**
	Checks if the value can be placed in the 3 by 3 box within the sudoku board that the specified spot is in
	@param int row of spot, int column of spot, int value that needs to be placed
	@return boolean indicating if the value can be placed in the 3 by 3 box
	*/
	public boolean checkBox(int row, int col, int val)
	{
		int startR = (row/3) * 3; //Algorithm that puts the for loop at the top left corner of the bow the spot is in so that it can check the whole thing 
		int startC = (col/3) * 3;
		for(int i = startR; i < startR + 3; i++) //nested for loop that checks all the values in the box 
		{
			for(int j = startC; j < startC + 3; j++)
			{
				if(board[i][j] == val)
				{
					return false;
				}
			}
		}
		return true;		
	}
	
	/**
	Checks if the board is solved
	@return boolean indicating if the board is solved 
	*/
	public boolean solved()
	{
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board.length; j++)
			{
				if(board[i][j] == 0)
				{
					return false; //if none of the values in the board are zero that means that a number has been added to all of them and the puzzle is solved
				}
			}
		}
		return true;
	}
	public static void main(String [] args)
	{
		SudokuBoard a = new SudokuBoard();
		a.displayBoard();
		System.out.println(a.canPlace(0,1,3));
		System.out.println(a.canPlace(1,7,8));
		System.out.println(a.canPlace(0,1,3));
	}
}
