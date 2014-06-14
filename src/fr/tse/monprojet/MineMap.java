package fr.tse.monprojet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 
 * @author yankuangshi
 *
 */
public class MineMap {
	
	private char[][] a; 		// two-dimensional array to define an explicit map which can be 9*9, 16*16 or 16*30
	private char[][] b; 		// two-dimensional array to define an implicit map which to be compared with the explicit map
	private int count;    		// define number of mines marked by an user
	private int nbMine;  		// total number of mines
	private int r;				// row number
	private int c;				// column number
	private String s;			// user's input string
	private boolean exploded;	// boolean to define if exploded

	/**
	 * Initial the game with row * column and mine number
	 * @param row : number of rows
	 * @param column : number of columns
	 * @param nbMine : number of mines
	 */
	MineMap(int row, int column, int nbMine) {
		a = new char [row][];
		for(int i = 0; i<row; i++ ) {
			a[i] = new char [column];
		}
		b = new char [row][];
		for(int i = 0; i<row; i++ ) {
			b[i] = new char [column];
		}
		this.nbMine = nbMine;
		count = 0;
		exploded = false;
		for (int i = 0; i<a.length; i++) {
			for (int j = 0; j<a[i].length; j++) {
				a[i][j] = 'o';
			}
		}
	}
	
	/**
	 * initial a map  
	 */
	public void initialMap() {
		for (int i = 0; i<b.length; i++) {
			for (int j = 0; j<b[i].length; j++) {
				b[i][j] = '0';
			}
		}
		int nbr = 0;
		while (nbr < nbMine) {
			int row = (int)(Math.random()*(b.length));
			int column = (int)(Math.random()*(b[1].length));
			if (b[row][column] != '*') {
				b[row][column] = '*';
				nbr++;
			
				if (row == 0 && column == 0) { 
					if (b[row][column+1] != '*') 
						b[row][column+1] = Character.forDigit(Character.digit(b[row][column+1], 10)+1, 10);
					if (b[row+1][column] != '*') 
						b[row+1][column] = Character.forDigit(Character.digit(b[row+1][column], 10)+1, 10);
					if (b[row+1][column+1] != '*') 
						b[row+1][column+1] = Character.forDigit(Character.digit(b[row+1][column+1], 10)+1, 10);
				}
				else if (row == b.length-1 && column == 0) {
					if (b[row-1][column] != '*') 
						b[row-1][column] = Character.forDigit(Character.digit(b[row-1][column], 10)+1, 10);
					if (b[row-1][column+1] != '*') 
						b[row-1][column+1] = Character.forDigit(Character.digit(b[row-1][column+1], 10)+1, 10);
					if (b[row][column+1] != '*') 
						b[row][column+1] = Character.forDigit(Character.digit(b[row][column+1], 10)+1, 10);
				}
				else if (row == 0 && column == b[0].length-1) {
					if (b[row][column-1] != '*') 
						b[row][column-1] = Character.forDigit(Character.digit(b[row][column-1], 10)+1, 10);
					if (b[row+1][column-1] != '*') 
						b[row+1][column-1] = Character.forDigit(Character.digit(b[row+1][column-1], 10)+1, 10);
					if (b[row+1][column] != '*') 
						b[row+1][column] = Character.forDigit(Character.digit(b[row+1][column], 10)+1, 10);
				}
				else if (row == b.length-1 && column == b[0].length-1) {
					if (b[row-1][column-1] != '*') 
						b[row-1][column-1] = Character.forDigit(Character.digit(b[row-1][column-1], 10)+1, 10);
					if (b[row-1][column] != '*') 
						b[row-1][column] = Character.forDigit(Character.digit(b[row-1][column], 10)+1, 10);
					if (b[row][column-1] != '*') 
						b[row][column-1] = Character.forDigit(Character.digit(b[row][column-1], 10)+1, 10);
				}
				else if (row == 0 && column != 0 && column !=b[0].length-1) {
					if (b[row][column-1] != '*') 
						b[row][column-1] = Character.forDigit(Character.digit(b[row][column-1], 10)+1, 10);
					if (b[row][column+1] != '*') 
						b[row][column+1] = Character.forDigit(Character.digit(b[row][column+1], 10)+1, 10);
					if (b[row+1][column-1] != '*') 
						b[row+1][column-1] = Character.forDigit(Character.digit(b[row+1][column-1], 10)+1, 10);
					if (b[row+1][column] != '*') 
						b[row+1][column] = Character.forDigit(Character.digit(b[row+1][column], 10)+1, 10);
					if (b[row+1][column+1] != '*') 
						b[row+1][column+1] = Character.forDigit(Character.digit(b[row+1][column+1], 10)+1, 10);
				}
				else if (row == b.length-1 && column !=0 && column !=b[0].length-1) {
					if (b[row-1][column-1] != '*') 
						b[row-1][column-1] = Character.forDigit(Character.digit(b[row-1][column-1], 10)+1, 10);
					if (b[row-1][column] != '*') 
						b[row-1][column] = Character.forDigit(Character.digit(b[row-1][column], 10)+1, 10);
					if (b[row-1][column+1] != '*') 
						b[row-1][column+1] = Character.forDigit(Character.digit(b[row-1][column+1], 10)+1, 10);
					if (b[row][column-1] != '*') 
						b[row][column-1] = Character.forDigit(Character.digit(b[row][column-1], 10)+1, 10);
					if (b[row][column+1] != '*') 
						b[row][column+1] = Character.forDigit(Character.digit(b[row][column+1], 10)+1, 10);
				}
				else if (column == 0 && row !=0 && row != b.length-1) {
					if (b[row-1][column] != '*') 
						b[row-1][column] = Character.forDigit(Character.digit(b[row-1][column], 10)+1, 10);
					if (b[row-1][column+1] != '*') 
						b[row-1][column+1] = Character.forDigit(Character.digit(b[row-1][column+1], 10)+1, 10);
					if (b[row][column+1] != '*') 
						b[row][column+1] = Character.forDigit(Character.digit(b[row][column+1], 10)+1, 10);
					if (b[row+1][column] != '*') 
						b[row+1][column] = Character.forDigit(Character.digit(b[row+1][column], 10)+1, 10);
					if (b[row+1][column+1] != '*') 
						b[row+1][column+1] = Character.forDigit(Character.digit(b[row+1][column+1], 10)+1, 10);				
				}
				else if (column == b[0].length-1 && row !=0 && row != b.length-1) {
					if (b[row-1][column-1] != '*') 
						b[row-1][column-1] = Character.forDigit(Character.digit(b[row-1][column-1], 10)+1, 10);
					if (b[row-1][column] != '*') 
						b[row-1][column] = Character.forDigit(Character.digit(b[row-1][column], 10)+1, 10);
					if (b[row][column-1] != '*') 
						b[row][column-1] = Character.forDigit(Character.digit(b[row][column-1], 10)+1, 10);
					if (b[row+1][column-1] != '*') 
						b[row+1][column-1] = Character.forDigit(Character.digit(b[row+1][column-1], 10)+1, 10);
					if (b[row+1][column] != '*') 
						b[row+1][column] = Character.forDigit(Character.digit(b[row+1][column], 10)+1, 10);
				}
				else {
					if (b[row-1][column-1] != '*') 
						b[row-1][column-1] = Character.forDigit(Character.digit(b[row-1][column-1], 10)+1, 10);
					if (b[row-1][column] != '*') 
						b[row-1][column] = Character.forDigit(Character.digit(b[row-1][column], 10)+1, 10);
					if (b[row-1][column+1] != '*') 
						b[row-1][column+1] = Character.forDigit(Character.digit(b[row-1][column+1], 10)+1, 10);
					if (b[row][column-1] != '*') 
						b[row][column-1] = Character.forDigit(Character.digit(b[row][column-1], 10)+1, 10);
					if (b[row][column+1] != '*') 
						b[row][column+1] = Character.forDigit(Character.digit(b[row][column+1], 10)+1, 10);
					if (b[row+1][column-1] != '*') 
						b[row+1][column-1] = Character.forDigit(Character.digit(b[row+1][column-1], 10)+1, 10);
					if (b[row+1][column] != '*') 
						b[row+1][column] = Character.forDigit(Character.digit(b[row+1][column], 10)+1, 10);
					if (b[row+1][column+1] != '*') 
						b[row+1][column+1] = Character.forDigit(Character.digit(b[row+1][column+1], 10)+1, 10);
				}	
			}
		}
		for (int i = 0; i<b.length; i++) {
			for (int j = 0; j<b[i].length; j++) {
				if (b[i][j] == '0') {
					b[i][j] = ' ';
				}
			}
		}
	}

	/**
	 * read user's input, which could be a string or a number
	 * @throws IOException
	 */
	public void getString() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		s = reader.readLine();
	}

	/**
	 * read the first input caracter to decide to mark a mine or to look the result of a box
	 * @return the first input caracter
	 * @throws IOException
	 */
	public char getChar() throws IOException {
		return s.charAt(0);
	}

	/**
	 * seperate user's input to two numbers (row number, column number)
	 * @return test if the coordinate is out of size of map
	 * @throws IOException
	 */
	public boolean getInt() throws IOException {
		boolean flag = false;
		String[] str = s.split(",");
		int i = Integer.parseInt(str[0]);
		int j = Integer.parseInt(str[1]);
		if (i>=1 && i<=b.length && j>=1 && j<=b[0].length) {
			r = i;
			c = j;
			flag = true;
		}
		return flag;
	}
	
	/**
	 * pick a box
	 * @return test if all the mines are marked
	 * @throws IOException
	 */
	public boolean pickMine() throws IOException {
		boolean flag = false;
		boolean in = false;
		while (in == false) {
			System.out.println("Please input the row and the column in the form of \"x,x\", ex: 3,4 or if you want to mark a flag on a mine please input \"p\":");
			getString();
			if(getChar() == 'p') {
				System.out.println("Please input the position where you want to mark in the form of \"x,x\", ex: 3,4 :");
				getString();
				if(getInt() == true) {
					in = true;
					a[r-1][c-1] = 'p';
					count++;
				}
				else
					System.out.println("Please input the row between: 0-"+b.length+" and the column between: 0-"+b[0].length);
			}
			else {
				if(getInt() == true) {
					in = true;
					if (displayNbrOfMine(r-1, c-1) == false) {
						System.out.println("----------------------------------");
						System.out.println("*************Bomb!!!**************");
						System.out.println("************You fail!!!***********");
						System.out.println("----------------------------------");
						flag = true;
						exploded = true;
						for (int i = 0; i<b.length; i++) {
							for (int j = 0; j<b[0].length; j++) {
								if (b[i][j] == '*') a[i][j] = '*';
							}
						}
					}
				}				
				else
					System.out.println("Please input the row between: 0-"+b.length+" and the column between: 0-"+b[0].length);
			}
		}
		drawMap();
		if (count == nbMine) {
			flag = true;
		}
		return flag;
	}

	public boolean displayNbrOfMine(int i, int j) {
		boolean f = true;
		if (b[i][j] == ' ' && a[i][j] == 'o') {
			a[i][j] = b[i][j];
			if (i == 0 && j == 0){
				displayNbrOfMine(i, j+1);
				displayNbrOfMine(i+1, j+1);
				displayNbrOfMine(i+1, j);
			}
			else if (i == 0 && j == b[0].length-1) {
				displayNbrOfMine(i, j-1);
				displayNbrOfMine(i+1, j-1);
				displayNbrOfMine(i+1, j);
			}
			else if (i == b.length-1 && j == 0) {
				displayNbrOfMine(i-1, j);
				displayNbrOfMine(i-1, j+1);
				displayNbrOfMine(i, j+1);
			}
			else if (i == b.length-1 && j == b[0].length-1) {
				displayNbrOfMine(i-1, j-1);
				displayNbrOfMine(i-1, j);
				displayNbrOfMine(i, j-1);
			}
			else if (i == 0 && j != 0 && j != b[0].length-1) {
				displayNbrOfMine(i, j-1);
				displayNbrOfMine(i, j+1);
				displayNbrOfMine(i+1, j+1);
				displayNbrOfMine(i+1, j);
				displayNbrOfMine(i+1, j-1);
			}
			else if (i == b.length-1 && j != 0 && j != b[0].length-1) {
				displayNbrOfMine(i-1, j-1);
				displayNbrOfMine(i-1, j);
				displayNbrOfMine(i-1, j+1);
				displayNbrOfMine(i, j-1);
				displayNbrOfMine(i, j+1);
			}
			else if (j == 0 && i != 0 && i != b.length-1) {
				displayNbrOfMine(i-1, j);
				displayNbrOfMine(i-1, j+1);
				displayNbrOfMine(i, j+1);
				displayNbrOfMine(i+1, j+1);
				displayNbrOfMine(i+1, j);
			}
			else if (j == b[0].length-1 && i != 0 && i != b.length-1) {
				displayNbrOfMine(i-1, j-1);
				displayNbrOfMine(i-1, j);
				displayNbrOfMine(i+1, j);
				displayNbrOfMine(i+1, j-1);
				displayNbrOfMine(i, j-1);
			}
			else {
				displayNbrOfMine(i-1, j-1);
				displayNbrOfMine(i-1, j);
				displayNbrOfMine(i-1, j+1);
				displayNbrOfMine(i, j+1);
				displayNbrOfMine(i+1, j+1);
				displayNbrOfMine(i+1, j);
				displayNbrOfMine(i+1, j-1);
				displayNbrOfMine(i, j-1);
			}
		}
		else if (b[i][j] == '*') {
			f = false;
		}
		else {
			a[i][j] = b[i][j];
		}
		return f;
	}

	/**
	 * test if user has marked the right mines
	 * @return
	 */
	public boolean makeDecision() {
		boolean flag = false;
		if (exploded == true) {
			return false;
		}
		else {
			for (int i = 0; i<b.length; i++) {
				for (int j = 0; j<b[0].length; j++) {
					if (a[i][j] == 'p' && b[i][j] == '*') {
						flag = true;
					}
					else {
						return false;
					}
				}
			}
		}
		return flag;
	}
	
	/**
	 * draw map after every time an user input 
	 */
    public void drawMap() {  
    	// draw the first line : column number 1 2 3 ...
        System.out.print("   "); //three spaces  
        for (int i = 0; i<a[0].length; i++) {  
            if (i>=9) {  
                System.out.print(i+1+" ");  
            }  
            else {  
                System.out.print(i+1+"  ");  
            }             
        }  
        System.out.println();   
        
        // draw the second line  
        System.out.print("   ");  
        for (int i = 0; i<a[0].length; i++) {  
            System.out.print("---");  
        }  
        System.out.println();  
          
        // draw the left lines except the last one
        for (int i = 0; i<a.length; i++) {  
            if(i>=9) {  
                System.out.print(i+1+"|");  
            }  
            else {  
                System.out.print(i+1+" |");  
            }             
            for (int j = 0; j<a[i].length; j++) {  
                System.out.print(a[i][j]+"  ");  
            }  
            System.out.print("|");  
            System.out.println();  
        }  
          
        // draw the last line
        System.out.print("   ");  
        for (int i = 0; i<a[0].length; i++) {  
            System.out.print("---");  
        }  
        
        System.out.println("   Rest Number of mines:"+(nbMine-count));  
        System.out.println();  
    }  
    
    /**
     * draw the real map using the two-dimensional array "b"
     */
    public void drawRealMap() {
		System.out.print("   "); //three spaces
		for (int i = 0; i<b[0].length; i++) {
			if (i>=9) {
				System.out.print(i+1+" ");
			}
			else {
				System.out.print(i+1+"  ");
			}			
		}
		System.out.println(); 
		
		System.out.print("   ");
		for (int i = 0; i<b[0].length; i++) {
			System.out.print("---");
		}
		System.out.println();
		
		for (int i = 0; i<b.length; i++) {
			if(i>=9) {
				System.out.print(i+1+"|");
			}
			else {
				System.out.print(i+1+" |");
			}			
			for (int j = 0; j<b[i].length; j++) {
				System.out.print(b[i][j]+"  ");
			}
			System.out.print("|");
			System.out.println();
		}
		
		System.out.print("   ");
		for (int i = 0; i<b[0].length; i++) {
			System.out.print("---");
		}
		System.out.println();
		System.out.println();
	}
	
	public static void main(String[] args) {
		System.out.println("-----------------------------------------------");
		System.out.println("***********WELCOM TO PLAY THIS GAME************");
		System.out.println("-----------------------------------------------");
		System.out.println("Please choose a level:");
		System.out.println("Level 1 : 9*9    10 Mines");
		System.out.println("Level 2 : 16*16  40 Mines");
		System.out.println("Level 3 : 16*30  99 Mines");
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String s;
		MineMap map;
		try {
			s = reader.readLine();
			switch(s) {
			case "1" : 
				map = new MineMap(9, 9, 10);
				map.drawMap();
				map.initialMap();
				//map.drawRealMap(); <---- uncomment this line to the real map with all mines
				try {
					while (map.pickMine() == false);
					if (map.makeDecision() == true) {
						System.out.println("BRAVO! You Win!");
					}
					else {
						System.out.println("Failed!");
					}
				}
				catch (IOException ioe) {
					System.out.println("Error occurs.");
				}		
				break;
			case "2" : 
				map = new MineMap(16, 16, 40);
				map.drawMap();
				map.initialMap();
				//map.drawRealMap();
				try {
					while (map.pickMine() == false);
					if (map.makeDecision() == true) {
						System.out.println("BRAVO! You Win!");
					}
					else {
						System.out.println("Failed!");
					}
				}
				catch (IOException ioe) {
					System.out.println("Error occurs.");
				}		
				break;
			case "3" : 
				map = new MineMap(16, 30, 99);
				map.drawMap();
				map.initialMap();
				//map.drawRealMap();
				try {
					while (map.pickMine() == false);
					if (map.makeDecision() == true) {
						System.out.println("BRAVO! You Win!");
					}
					else {
						System.out.println("Failed!");
					}
				}
				catch (IOException ioe) {
					System.out.println("Error occurs.");
				}		
				break;
			default  : 
				System.out.println("Please input 1, 2 or 3 for a level");
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
