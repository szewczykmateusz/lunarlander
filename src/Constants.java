public class Constants {
    private static Config cfg = new Config(0);
    private static float DEFAULT_WIDTH = Float.parseFloat(cfg.getProperty("defaultWidth"));
    private static float DEFAULT_HEIGHT = Float.parseFloat(cfg.getProperty("defaultHeight"));
    public final static float GAME_SCENE_DEFAULT_WIDTH = DEFAULT_WIDTH;
    public final static float GAME_SCENE_DEFAULT_HEIGHT = DEFAULT_HEIGHT;
    public final static int GAME_TIME = 30;
    public final static float START_SCORE = 1000;
    public final static int LEVEL_NUMBERS = Utils.intFromConfig(cfg, "levelNumbers");
    public final static int LIFES_NUMBERS = Utils.intFromConfig(cfg, "lifesNumber");
    public final static int STARTING_LEVEL = Utils.intFromConfig(cfg, "startingLevel");

    public static void setDefaultWidth(float defaultWidth) {DEFAULT_WIDTH = defaultWidth;}
    public static void setDefaultHeight(float defaultHeight) {DEFAULT_HEIGHT = defaultHeight;}
    public static float getDefaultWidth() {return DEFAULT_WIDTH;}
    public static float getDefaultHeight() {return DEFAULT_HEIGHT;}
}
