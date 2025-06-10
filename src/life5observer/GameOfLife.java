package life5observer;

import java.util.List;
import java.util.ArrayList;

public class GameOfLife {

    private List<LifeObserver> observers;
    private int rows;
    private int cols;
    private Cell[][] grid;

   
    public GameOfLife(int rows, int cols) {
        observers = new ArrayList<>();
        this.rows = rows;
        this.cols = cols;
        initializeGrid();
    }

    ///setup cells & their neighbors 
    private void initializeGrid() {
        grid = new Cell[rows][cols];
        setupCells();
        linkNeighbors();
    }

  //dead cells ?
    private void setupCells() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Cell();
            }
        }
    }

 
    private void linkNeighbors() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                addNeighborsForCell(i, j);
            }
        }
    }

    //same logic as I built in the previous 3 packages, just split up accordinhly
    private void addNeighborsForCell(int i, int j) {
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x != 0 || y != 0) {
                	//horiz
                    int ni = (i + x + rows) % rows;
                    
                    //vert (nested) 
                    int nj = (j + y + cols) % cols;
                    grid[i][j].addNeighbor(grid[ni][nj]);
                }
            }
        }
    }

    // one step at a time , split up the methods 
    public void advance() {
        List<LifeCommand> commands = determineCommands();
        executeCommands(commands);
        notifyObservers();
    }

    // either live or die --- 
    //**NOTE!!!** this method was built with the help of chatgpt
    //it recommened that I break up some larger methods into smaller 
    //as first it is determined what needs to be done, then the changes are later executed 
    
    private List<LifeCommand> determineCommands() {
        List<LifeCommand> commandList = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int n = grid[i][j].nbrAliveNeighbors();
                if (grid[i][j].isAlive()) {
                    if (n != 2 && n != 3) {
                        commandList.add(new DieCommand(grid[i][j]));
                    } else {
                        commandList.add(null);
                    }
                } else {
                    if (n == 3) {
                        commandList.add(new LiveCommand(grid[i][j]));
                    } else {
                        commandList.add(null);
                    }
                }
            }
        }
        return commandList;
    }

    // execute commadn based on cell 
    private void executeCommands(List<LifeCommand> commands) {
        for (LifeCommand command : commands) {
            if (command != null) {
                command.execute();
            }
        }
    }

    //add obs erver 
    public void attach(LifeObserver o) {
        observers.add(o);
    }

    //rm observer
    public void detach(LifeObserver o) {
        observers.remove(o);
    }

    // notify what just happened 
    public void notifyObservers() {
        for (LifeObserver o : observers) {
            o.updateObserver();
        }
    }

    // inherent getters 
    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Cell getCell(int row, int col) {
        return grid[row][col];
    }
}
