//  Michael Nichol
//  SID: 0624004
//  Date: Feb 12 2021

public class Main {
    public static void main(String[] args) {
        int[] box = new int[]{1,2,11,1,13,1,12,1,52,1,4,1,1,43,1,1,55,1,1,3,1}; //initializes an array of integers (cards)
        box = new int[]{1,1,3,4,1,1,3,3,3,1,1,1};

        //Count occurrences of dominant card
        int ans  = Matches(box, 0, box.length, FindMaximum(box, 0, box.length - 1)); //Initializes the recursive method to find answer

        if (ans > box.length/2) //If more than half of the cards are the same
            System.out.println("More than n/2 cards are the same.");
        else //If highest occurrence is less than or equal to half the array length
            System.out.println("No card number belongs to more than half of the accounts");
    }

    //Recursively find the occurrence of the integer that appears the most
    public static int FindMaximum(int[] A, int low, int high) {
        if (low == high) //BASE CASE: If array is size 1, return the value
            return (A[low]);

        int mid = (low + high) / 2; //Find the mid section of the currently observed section of the array

        int left = FindMaximum(A, low, mid); //Recurse over the left side of the array
        int right = FindMaximum(A, mid + 1, high); //Recurse over the right side of the array

        if (Equivalator(left, right)) //If the maximum on the left is the same as the maximum on the right, return either
            return left;
        else { //Otherwise iterate over the currently observed array and find which one occurs more often
            if (Matches(A, low, high, left) > Matches(A, low, high, right))
                return left; //Return card number that has a greater number of occurrences
            else
                return right;
        }
    }

    //Given an array, scan from lower to upper and count the occurrences of the target
    public static int Matches(int[] A, int low, int high, int target) {
        int count = 0;

        for (int i = low; i < high; ++i)
            if (A[i] == target)
                count++;

        return count;
    }

    //Equivalator function to determine if two cards are the same
    public static boolean Equivalator(int i, int j) {
        return i == j;
    }
}
