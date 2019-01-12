/*
Class keeps informations about player
 */
public class Player {
    public static void setName(String aName) {name = aName;}
    public static String getName() {return name;}
    public static int getActualLevel() {return actualLevel;}

    private static String name;
    private static int score = 0;
    /*
    Level which is actual playing, if game hasn`t begun value is 1, when game ends value backs to 1
     */
    private static int actualLevel = 1;
}
