package life5observer;

// The main program used to invoke the game of life system.
 
public class Main {

    public static void main(String[] args) {
        // Create an instance of the game with a 20x20 life grid
    	//   shown in a 500x500 window
    	GameOfLife life1 = new GameOfLife(20, 20);
    	GameOfLifeUI life2 = new GameOfLifeUI(life1, "Game of Life", 500, 500);
    }
}

