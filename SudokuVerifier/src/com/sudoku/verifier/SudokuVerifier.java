package com.sudoku.verifier;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;




public class SudokuVerifier {
	
	public static void main(String[] args) throws Exception {
		if(args.length==0) {
			//new SudokuVerifier("sudokuGameInvalid.csv");
			throw new Exception("fileName argument not present");
		} else {
			new SudokuVerifier(args[0]);
		}
	}
	
	private SudokuBoard sboard = null;
	
	public SudokuVerifier(int[][] board) {
		sboard = new SudokuBoard(board);
		printStatus();
	}

	public SudokuVerifier(String fileName) throws Exception {
		sboard = new SudokuBoard(Paths.get(this.getClass().getResource(fileName).toURI()),false);
		printStatus();
	}
	
	private void printStatus() {
		if(isValid()) {
			System.out.println("VALID");
		} else {
			System.out.println("INVALID");
		}
	}
	
	public boolean isValid() {
		return sboard.validateSudockBoard();
	}

	class SudokuBoard {
		
		private int[][] grid = new int[9][];
		
		public SudokuBoard(int[][] grid) {
			this.grid = grid;
		}
		
		public SudokuBoard(Path inputFilePath, boolean skipHeader) {
			try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inputFilePath.toFile())))) {
			      List<int[]> matrix = null;
			      if(skipHeader) {
			    	 	matrix = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());
			      } else {
			    	  	matrix = br.lines().map(mapToItem).collect(Collectors.toList());
			      }		     
			      br.close();
			      for(int i=0;i<9;i++) {
			    	  	grid[i] = matrix.get(i);
			      }
		    } catch (Exception e) {
		      e.printStackTrace();
		    }	
		}
		
		private Function<String, int[]> mapToItem = (line) -> {
			  String[] p = line.trim().split(",");
			  int[] aux = new int[9];
			  for(int i=0;i<9;i++) {
				  aux[i] = Integer.valueOf(p[i]);
			  }
			  return aux;
		};

		public boolean validateSudockBoard() {
		    for (int f=0; f<9; f++) {
		        int[] row = new int[9];
		        int[] square = new int[9];
		        int[] column = grid[f].clone();
		        for (int e=0; e<9; e++) {
		            row[e] = grid[e][f];
		            square[e] = grid[(f/3)*3+e/3][f*3%9+e%3];
		        }
		        if (!(checkSet(column) && checkSet(row) && checkSet(square))) {
		            return false;
		        }
		    }
		    return true;
		}

		private boolean checkSet(int[] array) {
			Arrays.sort(array);
		    for(int i=1;i<10;i++) {
		    		if(array[i-1] != i)
		    			return false;
		    }
		    return true;
		}
		
	}

}
