package de.lclutz.GameOfLife;

public class GameOfLife {

	private static int GRIDSIZE = 25;

	public static boolean[][] grid = new boolean[GRIDSIZE][GRIDSIZE];
	public static int steps = 0;
	
	public static void main(String[] args) {
		grid[5][5] = true;
		grid[5][6] = true;
		grid[5][7] = true;
		Renderer.init();
	}

	public static void update() {
		boolean[][] newGrid = new boolean[GRIDSIZE][GRIDSIZE];
		for (int row = 0; row < grid.length; row++) {
			for (int column = 0; column < grid[row].length; column++) {
				int aliveNeighbours = 0;

				if (checkNeighbour(row, column - 1))
					aliveNeighbours++;
				if (checkNeighbour(row, column + 1))
					aliveNeighbours++;
				if (checkNeighbour(row - 1, column))
					aliveNeighbours++;
				if (checkNeighbour(row - 1, column - 1))
					aliveNeighbours++;
				if (checkNeighbour(row - 1, column + 1))
					aliveNeighbours++;
				if (checkNeighbour(row + 1, column))
					aliveNeighbours++;
				if (checkNeighbour(row + 1, column - 1))
					aliveNeighbours++;
				if (checkNeighbour(row + 1, column + 1))
					aliveNeighbours++;

				if (grid[row][column]) {
					if (aliveNeighbours == 2 || aliveNeighbours == 3)
						newGrid[row][column] = true;
					else
						newGrid[row][column] = false;
				} else {
					if (aliveNeighbours == 3)
						newGrid[row][column] = true;
					else
						newGrid[row][column] = false;
				}
			}
		}
		grid = newGrid;
		steps++;
		Renderer.render();
	}

	private static boolean checkNeighbour(int row, int col) {
		if ((row >= 0 && row < GRIDSIZE) && (col >= 0 && col < GRIDSIZE))
			return grid[row][col];
		else
			return false;
	}

	public static void quit() {
		System.exit(0);
	}

	public static void clear() {
		for (int row = 0; row < grid.length; row++) {
			for (int column = 0; column < grid[row].length; column++) {
				grid[row][column] = false;
			}
		}
		steps = 0;
		Renderer.render();
	}

	public static void randomize() {
		for (int row = 0; row < grid.length; row++) {
			for (int column = 0; column < grid[row].length; column++) {
				if (Math.random() < 0.8)
					grid[row][column] = false;
				else
					grid[row][column] = true;
			}
		}
		steps = 0;
		Renderer.render();
	}
	
	public static void handleMouseClick(int x, int y) {
		int[] gameCoordinates = Renderer.convertToGameCoordinates(new int[] {x, y});
		x = gameCoordinates[0];
		y = gameCoordinates[1];
		if ((x >= 0 && x < GRIDSIZE) && (y >= 0 && y < GRIDSIZE)) {
			if (grid[y][x])
				grid[y][x] = false;
			else 
				grid[y][x] = true;
			Renderer.render();
		}
	}

}
