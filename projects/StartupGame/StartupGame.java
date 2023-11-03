import java.util.ArrayList;

public class StartupGame {
    private GameHelper helper = new GameHelper();
    private ArrayList<Startup> startups = new ArrayList<Startup>();
    private int numOfGuesses = 0;

    private void setUpGame() {
        Startup one = new Startup();
        one.setName("Opacle");

        Startup two = new Startup();
        two.setName("Balela");

        Startup three = new Startup();
        three.setName("Microhard");

        startups.add(one);
        startups.add(two);
        startups.add(three);

        System.out.println("Your goal is to sink 3 Startups.");
        System.out.println("Opacle, Balela, Microhard");
        System.out.println("Try to sink them all");

        for (Startup startup : startups) {
            ArrayList<String> newLocation = helper.placeStartup(3);
            startup.setLocationCells(newLocation);
        } // close for loop
    } // close setUpGame method


    private void startPlaying() {
        while (!startups.isEmpty()) {
            String userGuess = helper.getUserInput("Enter a guess");
            checkUserGuess(userGuess);
            //graphics.drawBoard();
        } // close while
        finishGame();
    } // close startPlaying method

    private void checkUserGuess(String userGuess) {
        numOfGuesses++;
        String result = "miss";
        for (Startup startupToTest : startups) {
            result = startupToTest.checkAttempt(userGuess);
            if (result.equals("hit")) {
                break;
            }
            if (result.equals("kill")) {
                startups.remove(startupToTest);
                break;
            }
        } // close for
        System.out.println(result);
    } // close method

    private void finishGame() {
        System.out.println("All Startups are dead! Your stock is now worthless");
        if (numOfGuesses <= 18) {
            System.out.println("It only took you " + numOfGuesses
                    + " guesses.");
            System.out.println("You got out before your options sank.");
        } else {
            System.out.println("Took you long enough. " + numOfGuesses
                    + " guesses.");
            System.out.println("Fish are dancing with your options");
        }
    } // close method

    public static void main(String[] args) {
        StartupGame game = new StartupGame();
        game.setUpGame();
        game.startPlaying();
    } // close method
}
