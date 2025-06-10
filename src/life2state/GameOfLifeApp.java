package life2state;

import java.awt.Color;
import edu.du.dudraw.Draw;
import edu.du.dudraw.DrawListener;

// Game of life main app using DuDraw
// Use mouse clicks to toggle cells
// Use spacebar to advance game

public class GameOfLifeApp implements DrawListener {
	// width and height in pixels
	private int width;
    private int height;
    private Draw window;
 
    // rows and cols for the game
    private int rows;
    private int cols;
    private Cell[][] grid;

   
    
    public GameOfLifeApp(String title, int rows, int cols, int width, int height) {
        
        // Save the instance variables
        this.rows = rows;
        this.cols = cols;
        this.width = width;
        this.height = height;
        
        // Setup the grid wCell objects
        this.grid = new Cell[rows][cols];
        setupGrid(grid);
         
        // Setup the DuDraw board
        window = new Draw(title);
        window.setCanvasSize(width, height);
        window.setXscale(0, width);
        window.setYscale(0, height);
       
        // Add the mouse/key listeners
        window.addListener(this);
        
        // Draw the initial board
        update();
    }
    
    private void setupGrid(Cell[][] g) {
        // Initialize each cell and set its state to Dead
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                g[i][j] = new Cell();
                g[i][j].die(); //initially set to dead but this can also be done in the constructor itself
            }
        }

        // Set neighbors for each cell
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // The neighbors can be found in the 8 surrounding cells
                for (int nx = -1; nx <= 1; nx++) {
                    for (int ny = -1; ny <= 1; ny++) {
                        // Skip the cell itself
                        if (nx == 0 && ny == 0) continue;

                        // Calculate neighbor coordinates
                        int ni = i + nx;
                        int nj = j + ny;
//**please note that I had issues with this logic, and chat-gpt was a very 
//useful tool in helping me figure out how to reach surroundings and add to neighbors                 
                        // Check bounds and add neighbor
                        if (ni >= 0 && ni < rows && nj >= 0 && nj < cols) {
                            g[i][j].addNeighbor(g[ni][nj]);
                        }
                    }
                }
            }
        }
    }

    
    private void drawGrid() {
        
    	window.setPenColor(Color.black);
 		
        int cellWidth = width / cols;
        int cellHeight = height / rows;
     
        for (int i = 0; i <= rows; i++) {
        	window.line(0, i * cellHeight, this.width, i * cellHeight);
        }
        
        for (int i = 0; i <= cols; i++) {
        	window.line(i * cellWidth, 0, i * cellWidth, this.height);
        }
    }
     
    private void drawLives() {
        int cellWidth = width / cols;
        int cellHeight = height / rows;
        
        window.setPenColor(Color.red);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j].isAlive()) { // changed this line to fit cellType
                    // This is the center and half width/height
                    window.filledRectangle((j * cellWidth)+(cellWidth/2), (i * cellHeight)+(cellHeight/2), cellWidth/2, cellHeight/2);
                }
            }
        }
    }

   
	
	// Returns the number of alive neighbors for the cell at position r,c. The
    //   grid is finite but unbound, meaning that the neighbor relation wraps
    //   around the grid boundaries.  This is a helper method
//   	private int nbrOfNeighbors(int r, int c) {
//        int n = 0;
//        int x = 0;
//        int y = r - 1;
//        if (y < 0) {
//            y = rows - 1;
//        }
//        for (int rCt = 1; rCt <= 3; rCt++) {
//            x = c - 1;
//            if (x < 0) {
//                x = cols - 1;
//            }
//            for (int cCt = 1; cCt <= 3; cCt++) {
//                if (x != c || y != r) {
//                    if (grid[y][x]) {
//                        n++;
//                    }
//                }
//                x = (x + 1) % cols;
//            }
//            y = (y + 1) % rows;
//        }
//        return n;
//    }

    // This method implements the rules of the Game of Life. For each cell,
    //   we simple find the number of neighbors and then bring the cell to life
    //   if appropriate.
    public void advance() {
        Cell[][] newGrid = new Cell[rows][cols];
        setupGrid(newGrid); // using setupGrid to init new grid with dead cells
        
        int n = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                n = grid[i][j].nbrAliveNeighbors(); // no more nbrOfNeighbors
                
                if (grid[i][j].isAlive()) { // Check if the cell is alive
                    if (n == 2 || n == 3) {
                        newGrid[i][j].live();
                    } else {
                        newGrid[i][j].die();
                    }
                } else {
                    if (n == 3) {
                        newGrid[i][j].live();
                    } else {
                        newGrid[i][j].die();
                    }
                }
            }
        }
        grid = newGrid;
        update();
    }


    // Below are the mouse/key listeners
    
	@Override
	public void keyPressed(int key) {
		// This is the advance button
		// Only advance for spacebar (ascii 32)
		if (key==32) {
			advance();
		}
	}

	@Override
	public void keyReleased(int key) {
		// Do nothing
	}

	@Override
	public void keyTyped(char key) {
		// Do nothing
	}

	@Override
	public void mouseClicked(double arg0, double arg1) {
		// Do nothing
	}

	@Override
	public void mouseDragged(double x, double y) {
		// Do nothing
	}

	@Override
	public void mousePressed(double x, double y) {
		// This is the toggle of grid locations
		int cellWidth = width / cols;
        int cellHeight = height / rows;
        
        int cellLocRow = (int)(y / cellHeight);
        int cellLocCol = (int)(x / cellWidth);

        if (grid[cellLocRow][cellLocCol].isAlive()) {
        	grid[cellLocRow][cellLocCol].die();
        } else {
        	grid[cellLocRow][cellLocCol].live();
        }
		update();
	}
	

	@Override
	public void mouseReleased(double x, double y) {
		// Do nothing
	}

	@Override
	public void update() {
		// Redraw the entire board
		window.clear(Color.white);  // Clear in white
	 	drawGrid();
	 	drawLives();
	}
        
}