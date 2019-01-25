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

    public static void setName(String Name) {name = Name;}
    public static String getName() {return name;}
    public static int getActualLevel() {return actualLevel;}
    public static double getPlayerScore() {return score;}
    public static Enum getChosenDifficulty() {return chosenDifficulty;}
    public static int getLifesNumber() {return lifesNumber;}

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
            actualLevel = 1;
            isLose = true;
            lifesNumber = 3;
        }
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
