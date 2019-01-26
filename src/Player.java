import java.util.ArrayList;

/*
Class keeps informations about player
 */
public class Player {

    private static String name;
    private static double score = 0;
    private static int actualLevel = 1;
    private static Enum chosenDifficulty;
    private static ArrayList<Integer> completedLevels = new ArrayList<Integer>();
    private static int lifesNumber = 3;
    private static boolean isLose = false; // when player lost all lifes, isLose = true, and game ends
    private static BestScoresConfig bestScores = new BestScoresConfig();

    public static void setName(String Name) {name = Name;}
    public static String getName() {return name;}
    public static double getScore() {return score;}
    public static int getActualLevel() {return actualLevel;}
    public static double getPlayerScore() {return score;}
    public static Enum getChosenDifficulty() {return chosenDifficulty;}
    public static int getLifesNumber() {return lifesNumber;}
    public static void resetPlayerScore() {score = 0;}
    public static void resetPlayerLevel() {actualLevel = 1;}
    public static boolean getGameStatus() {return isLose;}
    public static BestScoresConfig getBestScores() {return bestScores;}

    /*
        Method incrementing player's score by adding the passed value
     */
    public static void incrementScore(double value) {
        score += value;
    }

    /*
        Method checks that level was completed successfuly and increments the level number
     */
    public static void levelCompleted() {
        completedLevels.add(1);
        actualLevel++;
    }

    /*
    Method checks that level was failed
     */
    public static void levelFailed() {
        completedLevels.add(0);
        lifesNumber--;
        if(lifesNumber == 0) {
            isLose = true;
        }
    }
    /*
    Method resets player properties after lose
     */
    public static void reset() {
        actualLevel = 1;
        isLose = false;
        lifesNumber = 3;
        score = 0;
        completedLevels = new ArrayList<>();
    }


    /*
        Method checking if completedLevels ArrayList is not empty.
        Shows if Player has already played.
     */
    public static boolean hasPlayed() {
        if(completedLevels.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    /*
        Method returns completion status of last level
     */
    public static Integer getLastLevelStatus() {
        if(completedLevels.size() > 0) {
            return completedLevels.get(completedLevels.size() - 1);
        } else {
            return 0;
        }
    }

    /*
        Method setting difficulty level chosen by player
     */
    public static void setDifficulty(Enum difficulty) {
        chosenDifficulty = difficulty;
    }
}
