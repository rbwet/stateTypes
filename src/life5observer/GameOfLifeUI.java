package life5observer;

import java.awt.Color;
import edu.du.dudraw.Draw;

public class GameOfLifeUI extends LifeObserver {
    // Constants for reused colors
    private static final Color GRID_COLOR = Color.black;
    private static final Color CELL_COLOR = Color.red;

    private int width;
    private int height;
    private int cellWidth;
    private int cellHeight;
    private Draw window;

    public GameOfLifeUI(GameOfLife s, String title, int width, int height) {
        super(s);
        this.width = width;
        this.height = height;
        this.cellWidth = width / subj.getCols();
        this.cellHeight = height / subj.getRows();

        window = new Draw(title);
        window.setCanvasSize(width, height);
        window.setXscale(0, width);
        window.setYscale(0, height);
        window.addListener(this);

        update();
    }

    private void drawGrid() {
        window.setPenColor(GRID_COLOR);
//horizontal lines 
        for (int i = 0; i <= subj.getRows(); i++) {
            window.line(0, i * cellHeight, this.width, i * cellHeight);
        }
//vert lines 
        for (int i = 0; i <= subj.getCols(); i++) {
            window.line(i * cellWidth, 0, i * cellWidth, this.height);
        }
    }

    private void drawLives() {
        window.setPenColor(CELL_COLOR);
        //draw if alive after iterating thru each 
        for (int i = 0; i < subj.getRows(); i++) {
            for (int j = 0; j < subj.getCols(); j++) {
                if (subj.getCell(i, j).isAlive()) {
                    window.filledRectangle((j * cellWidth) + (cellWidth / 2), (i * cellHeight) + (cellHeight / 2), cellWidth / 2, cellHeight / 2);
                }
            }
        }
    }

    @Override
    public void updateObserver() {
        update();
    }

    @Override
    public void keyPressed(int key) {
        switch (key) {
            case 32: // ASCII for spacebar
                subj.advance();
                break;
        }
    }

    @Override
    public void keyReleased(int key) {
    }

    @Override
    public void keyTyped(char key) {
    }

    @Override
    public void mouseClicked(double arg0, double arg1) {
    }

    @Override
    public void mouseDragged(double x, double y) {
    }

    @Override
    public void mousePressed(double x, double y) {
        int cellLocRow = (int) (y / cellHeight);
        int cellLocCol = (int) (x / cellWidth);

        Cell cell = subj.getCell(cellLocRow, cellLocCol);
        if (cell.isAlive()) {
            cell.die();
        } else {
            cell.live();
        }

        update();
    }

    @Override
    public void mouseReleased(double x, double y) {
        // Do nothing
    }

    @Override
    public void update() {
        window.clear(Color.white);
        drawGrid();
        drawLives();
    }
}
