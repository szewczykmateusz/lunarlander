import java.util.ArrayList;

/*
Class keeps informations about player
 */
public class Player {

    private static String name;
    private static double score = 0;
    private static int actualLevel = 1;
    private static ArrayList<Integer> completedLevels = new ArrayList<Integer>();

    public static void setName(String Name) {name = Name;}
    public static String getName() {return name;}
    public static int getActualLevel() {return actualLevel;}
    public static double getPlayerScore() {return score;}

    /*
        Method incrementing player's score by adding the passed value
     */
    public static void incrementScore(double value) {
        score += value;
    }

    /*
        Method checks that level was completed successfuly
     */
    public static void levelCompleted() {
        completedLevels.add(1);
    }

    /*
    Method checks that level was failed
     */
    public static void levelFailed() {
        completedLevels.add(0);
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
}
