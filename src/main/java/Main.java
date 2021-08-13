import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class Main {
    private static long count = 81;
//    Extremely Hard
//    public static  int[][] grid = {
//            {0, 0, 7, 0, 0, 0, 0, 3, 5},
//            {0, 1, 0, 0, 4, 0, 0, 8, 0},
//            {0, 0, 0, 3, 0, 0, 1, 0, 0},
//            {5, 0, 0, 0, 0, 1, 8, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0, 2},
//            {0, 2, 6, 0, 0, 0, 4, 0, 0},
//            {0, 0, 0, 0, 8, 0, 0, 0, 0},
//            {8, 0, 0, 7, 6, 5, 0, 9, 0},
//            {0, 7, 0, 1, 3, 9, 0, 0, 0}};
//
//    New
//    public static  int[][] grid = {
//            {0, 0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0, 0}};
//
//    Easy
//    public static  int[][] grid = {
//            {0, 0, 3, 5, 0, 0, 4, 9, 0},
//            {7, 6, 0, 0, 0, 0, 5, 0, 1},
//            {0, 5, 4, 0, 7, 3, 6, 0, 8},
//            {0, 1, 0, 0, 0, 0, 3, 0, 0},
//            {0, 0, 7, 2, 6, 1, 0, 0, 0},
//            {2, 0, 6, 0, 9, 0, 0, 1, 4},
//            {6, 3, 2, 8, 5, 0, 0, 0, 0},
//            {4, 0, 0, 0, 0, 2, 8, 0, 6},
//            {8, 0, 5, 0, 0, 7, 2, 0, 0}};
//
//    Hard
    public static  int[][] grid = {
            {0, 0, 0, 1, 3, 0, 0, 0, 0},
            {0, 0, 0, 0, 9, 0, 3, 0, 0},
            {4, 0, 0, 6, 2, 5, 1, 7, 0},
            {0, 0, 0, 8, 7, 0, 0, 0, 0},
            {5, 0, 9, 0, 0, 0, 0, 4, 0},
            {0, 0, 7, 0, 0, 3, 0, 0, 9},
            {0, 7, 0, 0, 0, 6, 0, 0, 3},
            {0, 5, 0, 0, 4, 0, 6, 0, 0},
            {0, 0, 4, 5, 0, 0, 7, 0, 0}};


    public static void main(String[] args) throws InterruptedException {
        int[][] solvedGrid = solve(grid);
        String title = "Solved Sudoku";

        if (countEmptySpace(solvedGrid) != 0)   title = "Unsolvable Sudoku";

        System.out.println(title);
        printGrid(solvedGrid);
    }

    private static boolean possible(int x, int y, int n) {
        for (int row=0; row < 9; row++){
            if (grid[row][y] == n) return false;
        }
        for (int col=0; col < 9; col++){
            if (grid[x][col] == n) return false;
        }

        int x0 = x - (x % 3);
        int y0 = y - (y % 3);
        for (int row = x0; row < (x0 + 3); row++){
            for (int col = y0; col < (y0 + 3); col++){
                if (grid[row][col] == n) return false;
            }
        }
        return true;
    }

    private static int[][] solve(int[][] grid) throws InterruptedException {
        for (int row = 0; row < 9; row++){
            for (int col = 0; col < 9; col++){
                if (grid[row][col] == 0){
                    for(int number = 1; number < 10; number++){
                        if (possible(row, col, number)) {
                            grid[row][col] = number;
                            if (isFull(solve(grid))){
                                return grid;
                            }else {
                                grid[row][col] = 0;
                            }
                        }
                    }
                    return grid;
                }
            }
        }
        return grid;
    }

    private static boolean isFull(int[][] grid){
        return countEmptySpace(grid) == 0;
    }

    private static long countEmptySpace(int[][] grid){
        return Arrays.stream(grid).flatMapToInt(arr -> Arrays.stream(arr)).filter(n -> n == 0).count();
    }

    public static void printGrid(int [][] grid){
        for (int row = 0; row < 9; row++){
            for (int col = 0; col < 9; col++){
                System.out.print(grid[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
