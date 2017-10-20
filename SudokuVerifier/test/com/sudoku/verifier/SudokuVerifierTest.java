package com.sudoku.verifier;

import org.junit.Assert;
import org.junit.Test;




public class SudokuVerifierTest {

	@Test
	public void sudokuInvalid() {
		int[][] board={
                {5,3,7,6,4,8,9,1,2},
                {6,7,2,1,9,5,3,4,8},
                {1,9,7,3,4,2,5,6,8},
                {8,5,9,7,6,1,4,2,3},
                {4,2,1,8,5,3,7,9,3},
                {7,1,3,9,2,4,8,5,6},
                {9,6,1,5,3,7,2,8,4},
                {2,9,7,4,1,9,6,3,5},
                {3,4,6,2,8,6,1,7,9}};
		
		SudokuVerifier sVer = new SudokuVerifier(board);
		Assert.assertFalse(sVer.isValid());
	}
	
	@Test
	public void sudokuValid() {
		int[][] board={
				{4,8,3,9,2,1,6,5,7},
				{9,6,7,3,4,5,8,2,1}, 
				{2,5,1,8,7,6,4,9,3}, 
				{5,4,8,1,3,2,9,7,6},
				{7,2,9,5,6,4,1,3,8},
				{1,3,6,7,9,8,2,4,5}, 
				{3,7,2,6,8,9,5,1,4},
				{8,1,4,2,5,3,7,6,9}, 
				{6,9,5,4,1,7,3,8,2}};
		
		SudokuVerifier sVer = new SudokuVerifier(board);
		Assert.assertTrue(sVer.isValid());
	}

}
