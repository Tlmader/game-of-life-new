import java.util.Scanner;

/**
 * Contains the program logic for Conway's Game of Life.
 *
 * @author tlmader.dev@gmail.com
 * @since 2017-01-17
 */
public class GameOfLife {

  private static Scanner input;

  /**
   * Reads the number of test cases and runs each.
   */
  private static void run() {
    input = new Scanner(System.in);
    int n = Integer.parseInt(input.nextLine());
    for (int i = 0; i < n; i++) {
      testCase();
    }
  }

  /**
   * Reads the information for a test case and evaluates.
   */
  private static void testCase() {
    int x = Integer.parseInt(input.nextLine());
    int y = Integer.parseInt(input.nextLine());
    if (x < 3 || y < 3) {
      System.out.println("Error: Size must be at least 3 x 3");
    }
    int iters = Integer.parseInt(input.nextLine());
    int[][] grid = new int[x][y];
    for (int i = 0; i < x; i++) {
      String[] str = input.nextLine().split(" ");
      for (int j = 0; j < y; j++) {
        grid[i][j] = Integer.parseInt(str[j]);
      }
    }
    for (int i = 1; i <= iters; i++) {
      System.out.println(i);
      grid = step(grid);
    }
  }

  /**
   * Evaluates and returns the next step for a grid while printing each cell.
   *
   * @param grid a 2D array for a grid
   * @return the next step
   */
  private static int[][] step(int[][] grid) {
    int[][] temp = new int[grid.length][grid[0].length];
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        temp[i][j] = getNext(grid[i][j], getNeighbors(grid, i, j));
        if (temp[i][j] == 1) {
          System.out.print("X ");
        } else {
          System.out.print(". ");
        }
      }
      System.out.println();
    }
    return temp;
  }

  /**
   * Gets the neighbor count of a cell at a specific location on a grid, such
   * that cells on outside edges check edge cells on the opposite side.
   *
   * @param grid a 2D array for a grid
   * @param x a coordinate for the row
   * @param x a coordinate for the column
   * @return the neighbor count
   */
  private static int getNeighbors(int[][] grid, int x, int y) {
    int n = 0;
    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        if (i == 0 && j == 0) {
          continue;
        }
        int adjX = x + i;
        int adjY = y + j;
        int lastX = grid.length - 1;
        int lastY = grid[0].length - 1;
        if (adjX < 0) {
          adjX = lastX;
        } else if (adjX > lastX) {
          adjX = 0;
        }
        if (adjY < 0) {
          adjY = lastY;
        } else if (adjY > lastY) {
          adjY = 0;
        }
        if (grid[adjX][adjY] == 1) {
          n++;
        }
      }
    }
    return n;
  }

  /**
   * Gets the next state based on state and neighbors.
   *
   * @param cell a cell's state
   * @param n a cell's neighbor count
   * @return the next state
   */
  private static int getNext(int cell, int n) {
    if (n == 3 || (cell == 1 && n == 2)) {
      return 1;
    }
    return 0;
  }

  /**
   * Called at the start of the program.
   *
   * @param args an array of command line arguments
   */
  public static void main(String[] args) {
    run();
  }
}
