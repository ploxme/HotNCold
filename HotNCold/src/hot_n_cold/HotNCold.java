package hot_n_cold;

import java.util.Random;

/**
 * This class is a random number game called Hot/Cold game
 * @author Patrick Luong
 * @version 1.0
 * * The package HotNCold contains two classes a HotNCold class to run the game and a console class for user inputs
 *  * file name HotNCold
 *  * This game was finished on 10/06/2020
 */
public class HotNCold {

    private static final int EXTREMELY_HOT = 1;
    private static final int VERY_HOT = 2;
    private static final int HOT = 5;
    private static final int WARM = 10;
    private static final int COLD = 30;

    private static Random random = new Random();

    private static int countTotalMatch = 0;
    private static int leastAttemptedGuess = 0;
    private static int mostAttemptedGuess = 0;
    private static int countTotalGuess;

    /**
     * welcomes user to the program and calls the main menu
     * @param args standard
     */
    public static void main(String[] args) {

        System.out.println(
                "Welcome to the Hot/Cold game!\n" +
                "*****************************\n" +
                "Game Rules: a match starts by picking a random number\n" +
                "between a low and high value. the player than tries to\n" +
                "guess the number. The game will respond with adjectives\n" +
                "such as: cold, warm, hot, very hot! the hotter the\n" +
                "adjective the closer the guess was to the right answer,\n" +
                "according to the following table:\n" +
                "\n" +
                "*****************************\n" +
                "extremely hot - 1 away from the target number\n" +
                "very hot - 2 away from the target number\n" +
                "hot - 3-5 away from the target number\n" +
                "warm - 6-10 away from the target number\n" +
                "cold - 11-30 away from the target number\n" +
                "ice cold - more than 60 away from the target number\n" +
                "*******************************\n" +
                "\n" +
                "Use the menu below to start the game.");

        gameMenu();

    }

    /**
     * This class calls for the game menu
     * @param choiceMenu choices for the menu
     */
    private static void gameMenu() {

        //user input for menu choice
        int choiceMenu;

        do {
            System.out.println(

                            "1. Start a new match of Hot/Cold game\n" +
                            "2. Print match statistics\n" +
                            "3. Exit");

            choiceMenu = Console.readInt("Make a choice: ");

            if(choiceMenu == 2 && countTotalMatch == 0){

                System.out.println("You must play before statistics are shown!\n");
                gameMenu();

            }
            else if(choiceMenu == 1){


                playGame(getLow(), getHigh());
                countTotalMatch++;

            }
            else if(choiceMenu == 2){

                getStats();

            }
            else if(choiceMenu == 3){

                System.out.println("Goodbye, thank you for playing! \n");

            }
            else{

                System.out.println("Not a valid menu option. Please Choose again.\n");
                gameMenu();
            }

        }while (countTotalMatch > 0 && choiceMenu != 3) ;

    }

    /**
     * retrieves low value
     * @return low value
     */
    private static int getLow(){

       return Console.readInt("\nEnter a low value for the match: ");

    }

    /**
     * retrieves high value
     * @return high value
     */
    private static int getHigh(){

        return Console.readInt("Enter a high value for the match: ");

    }

    /**
     * sets up and executes the game for user to play
     * @param low   user enters their own low value
     * @param high user enters their own high value
     */
    public static void playGame(int low, int high ) {

        //swap high low if low > high
        if(low > high){

            int swapLow = low;
            int swapHigh = high;
            high = swapLow;
            low = swapHigh;

        }

        int randomNumber = randomBetween(low, high);
        int numGuessAttempted = 0;
        int guess;

        System.out.println("Generating a number between: " + low + "-" + high);

        do {

            guess = Console.readInt("Guess? ");

            if (guess < 0) {
                guess = Math.abs(guess);
            }

            if (guess == (randomNumber - EXTREMELY_HOT) || guess == (randomNumber + EXTREMELY_HOT)) {

                System.out.println("extremely hot!");

            } else if (guess == (randomNumber - VERY_HOT) || guess == (randomNumber + VERY_HOT)) {

                System.out.println("very hot!");

            } else if (guess >= (randomNumber - HOT) && guess <= (randomNumber + HOT) && guess != randomNumber) {

                System.out.println("hot!");

            } else if (guess >= (randomNumber - WARM) && guess <= (randomNumber + WARM) && guess != randomNumber) {

                System.out.println("warm!");

            } else if (guess >= (randomNumber - COLD) && guess <= (randomNumber + COLD) && guess != randomNumber) {

                System.out.println("cold!");

            } else if (guess > (randomNumber - COLD) && guess != randomNumber ||
                    guess < (randomNumber + COLD) && guess != randomNumber) {

                System.out.println("ice cold!");

            } else {

                System.out.println("Congratulations, you found the number " + randomNumber + "!\n");
            }

            numGuessAttempted++;
            countTotalGuess++;

        } while (randomNumber != guess);

        calculateEndMatchStats(numGuessAttempted);
    }

    /**
     * assigns appropriate values for mostAttemptedGuess and leastAttemptedGuess in comparison
     * to current game numGuessAttempted
     * @param numGuessAttempted used to compare and if applicable, update current game with current statistics
     */
    private static void calculateEndMatchStats(int numGuessAttempted)
    {
        //This if - if - else statement compares and assigns/reassigns number of guesses attempted
        if (numGuessAttempted > mostAttemptedGuess) {

            mostAttemptedGuess = numGuessAttempted;

        } else if (numGuessAttempted < mostAttemptedGuess && leastAttemptedGuess >= numGuessAttempted) {

            leastAttemptedGuess = numGuessAttempted;

        }

        //This if statement is strictly for the first attempted guess where the number of guess is both high and low
        if (leastAttemptedGuess == 0) {

            leastAttemptedGuess = mostAttemptedGuess;
        }
    }

    /**
     * This method returns the random number
     * @param low used to calculate random number within low value
     * @param high used to calculate random number within high value
     * @return random number
     */
    private static int randomBetween(int low, int high){

            return random.nextInt((high - low) + 1) + low;
    }

    /**
     * This method displays match statistics
     */
    private static void getStats(){

        System.out.println(

                "\n**************************************\n"+
                "Total matches: " + countTotalMatch +
                "\nTotal guesses across matches: " + countTotalGuess +
                "\nLowest guesses in a match: " + leastAttemptedGuess +
                "\nHighest guesses in a match:  " + mostAttemptedGuess +
                "\n**************************************\n");

    }

}
