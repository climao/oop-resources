import java.util.*;

public class GameHelper {
    private static final String ALPHABET = "abcdefg";
    private static final int GRID_LENGTH = 7;
    private static final int GRID_SIZE = 49;
    private static final int MAX_ATTEMPTS = 200;
    static final int HORIZONTAL_INCREMENT = 1;
    static final int VERTICAL_INCREMENT = GRID_LENGTH;
    private final int[] grid = new int[GRID_SIZE];
    private final Random random = new Random();
    private int startupCount = 0;

    public String getUserInput(String prompt) {
        System.out.print(prompt + ": ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().toLowerCase();
    } //end getUserInput

    public ArrayList<String> placeStartup(int stSize) {
        int[] startupCoords = new int[stSize];
        int attempts = 0;
        boolean success = false;
        startupCount++;
        int increment = getIncrement();
        while (!success & attempts++ < MAX_ATTEMPTS) {
            int location = random.nextInt(GRID_SIZE);
            for (int i = 0; i < startupCoords.length; i++) {
                startupCoords[i] = location;
                location += increment;
            }
            if (startupFits(startupCoords, increment)) {
                success = coordsAvailable(startupCoords);
            } // end if
        } // end while
        savePositionToGrid(startupCoords);
        ArrayList<String> alphaCells =
                coordsToAlphaFormat(startupCoords);
        return alphaCells;
    } //end placeStartup

    private boolean startupFits(int[] coords, int inc) {
        int finalLoc = coords[coords.length - 1];
        if (inc == HORIZONTAL_INCREMENT) {
            return calcRowFromIndex(coords[0]) ==
                    calcRowFromIndex(finalLoc);
        } else {
            return finalLoc < GRID_SIZE;
        }
    } //end startupFits

    private boolean coordsAvailable(int[] coords) {
        for (int coord : coords) {
            if (grid[coord] != 0) {
                return false; // NO success
            }
        }
        return true; // there were no clashes, yay!
    } //end coordsAvailable

    private void savePositionToGrid(int[] coords) {
        for (int index : coords) {
            grid[index] = 1;
        }
    } //end savePositionToGrid

    private ArrayList<String> coordsToAlphaFormat(int[] coords) {
        ArrayList<String> alphaCells = new ArrayList<String>();
        for (int index : coords) {
            String alphaCoords = getAlphaCoordsFromIndex(index);
            alphaCells.add(alphaCoords); // add to a list
        }
        return alphaCells; // return the "a0"-style coords
    } // end convertCoordsToAlphaFormat

    private String getAlphaCoordsFromIndex(int index) {
        int row = calcRowFromIndex(index); // get row value
        int column = index % GRID_LENGTH;
        String letter = ALPHABET.substring(column, column + 1);
        return letter + row;
    } // end getAlphaCoordsFromIndex

    private int calcRowFromIndex(int index) {
        return index / GRID_LENGTH;
    } // end calcRowFromIndex

    private int getIncrement() {
        if (startupCount % 2 == 0) { // if EVEN Startup
            return HORIZONTAL_INCREMENT; // place horizontally
        } else { // else ODD
            return VERTICAL_INCREMENT; // place vertically
        }
    } //end getIncrement
} //end class
