package stiver;

public class CherryPickup {

    public static int cherryPickupRecursion(int[][] grid){
        return Math.max(0, cherryPickupRecursionDown(grid, 0, 0));
    }

    public static int cherryPickupRecursionDown(int[][] grid, int r, int c){
        if(r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == -1) return Integer.MIN_VALUE;

        if(r == grid.length - 1 && c == grid.length - 1){
            return cherryPickupRecursionUp(grid, grid.length - 1, grid[0].length - 1);
        }

        int cherryCount = grid[r][c];
        grid[r][c] = 0;
        int right = cherryCount + cherryPickupRecursionDown(grid, r, c + 1);
        int down = cherryCount + cherryPickupRecursionDown(grid, r + 1, c);

        grid[r][c] = cherryCount;

        return Math.max(right, down);
    }

    public static int cherryPickupRecursionUp(int[][] grid, int r, int c){
        if(r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == -1) return Integer.MIN_VALUE;

        if(r == 0 && c == 0){
            return grid[0][0];
        }

        int cherryCount = grid[r][c];
        grid[r][c] = 0;
        int left = cherryCount + cherryPickupRecursionUp(grid, r, c - 1);
        int up = cherryCount + cherryPickupRecursionUp(grid, r - 1, c);

        grid[r][c] = cherryCount;

        return Math.max(left, up);
    }

    public static void main(String[] args) {
        int[][] grid = {{1,1,-1},{1,-1,1},{-1,1,1}};
        System.out.println(cherryPickupRecursion(grid));
    }
}