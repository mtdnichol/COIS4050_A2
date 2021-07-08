//  Michael Nichol
//  SID: 0624004
//  Date: Feb 12 2021

import java.util.Scanner;

public class Main {
    static int count;
    static int[][] board;

    public static void main(String[] args) {
        int k, x, y, numTiles; //K represents the value of k, x and y are the coordinates of the single square tile
        Scanner reader = new Scanner(System.in);

        //Ensures the user enters a positive k value
        do {
            System.out.print("\nEnter k Value (k>=1): ");
            k = reader.nextInt();
        } while (k < 1);

        //Calculates and initializes the size of the board
        int kE = (int)Math.pow(2, k);
        board = new int[kE][kE];

        //Ensures the coordinates entered by the user for the 1x1 is in a valid location
        boolean inRange = false;
        do {
            System.out.println("Enter x coordinate of single square tile: ");
            x = reader.nextInt();
            System.out.println("Enter y coordinate of single square tile: ");
            y = reader.nextInt();

            if (x < kE && y < kE)
                inRange = true;

            else
                System.out.println("\nNot in range...\n\n");
        } while (!inRange);

        System.out.println("Both in range");
        board[x][y] = -1;

        /*
            Algorithm
                1. Find the location of the origin
                2. Check each quadrant (greater than size 2x2) if there exists a filled square in it
                3. Fill the square closest to the origin in every other quadrant except the one with the filled square
                4. Split each quadrant into 4 quadrants of its own
                5. Perform all actions again
         */

        count = 0;
        fillBoard(0, 0, k);
        print(board); //Outputs the result
    }

    public static void fillBoard(int x, int y, int n) {
        //Board size has been decreased to a 2x2
        count++;

        if (n == 1) { //Base case: 2x2 board size has been reached.  Fill out each square that isn't filled
            for (int i = 0; i <= n; ++i) {
                for (int j = 0; j <= n; ++j) {
                    if (board[x + i][y + j] == 0)
                        board[x + i][y + j] = count;
                }
            }

            return; //Exits recursive loop once base condition is satisfied
        }

        //Below code checks each quadrant to see if it has a filled square.  If yes, fills squares in other quadrants
        //closest to the middle

        //Acquires the middle coordinate relative to the currently observed square
        int middle = (int)Math.pow(2, n)/2;
        int xMiddle = x + middle; //Calculates middle relative to x coordinate
        int yMiddle = y + middle; //Calculates middle relative to y coordinate
        if (hasFilled(x, y, middle)) { //Checks the NW quadrant
            board[xMiddle][yMiddle - 1] = count; //Top Right
            board[xMiddle][yMiddle] = count; //Bottom Right
            board[xMiddle - 1][yMiddle] = count; //Bottom Left
        } else if (hasFilled(xMiddle, y, middle * 2)) { //Checks the NE quadrant
            board[xMiddle - 1][yMiddle - 1] = count; //Top Left
            board[xMiddle][yMiddle] = count; //Bottom Right
            board[xMiddle - 1][yMiddle] = count; //Bottom Left
        } else if (hasFilled(xMiddle, yMiddle, middle * 2)) { //Checks the SE quadrant
            board[xMiddle - 1][yMiddle - 1] = count; //Top Left
            board[xMiddle][yMiddle - 1] = count; //Top Right
            board[xMiddle - 1][yMiddle] = count; //Bottom Left
        } else { //Checks the SW quadrant
            board[xMiddle - 1][yMiddle - 1] = count; //Top Left
            board[xMiddle][yMiddle - 1] = count; //Top Right
            board[xMiddle][yMiddle] = count; //Bottom Right
        }

        //Board, x, y, n
        //Recursively calls fillBoard on each quadrant until the base case is reached
        fillBoard(x, y, n - 1); //NW
        fillBoard(xMiddle, y, n - 1); //NE
        fillBoard(xMiddle, yMiddle, n - 1); //SE
        fillBoard(x, yMiddle, n - 1); //SW
    }


    //Iteratively checks over a given quadrant for "filled" squares
    public static boolean hasFilled(int startX, int startY, int endpoint) {
        for (int i = startX; i < endpoint; ++i)
            for (int j = startY; j < endpoint; ++j)
                if (board[i][j] != 0)
                    return true;

        return false;
    }


    //Prints out the board
    public static void print(int arr[][]) {
        for (int i = 0; i < arr.length; ++i) { //Iterate over the entire board
            for (int j = 0; j < arr[i].length; ++j) {
                System.out.print(arr[i][j] + " "); //Output value of current tile
            }
            System.out.println(); //Separates each line
        }

    }
}
