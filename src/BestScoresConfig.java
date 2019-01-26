import java.util.*;
import java.util.Properties;
import javafx.util.Pair;

//CLASS reading and creating files with best scores and keeps actual best scores in list

public class BestScoresConfig {
    private Properties configFile;
    private List<Pair<Float, String>> bestResults = new ArrayList<Pair<Float, String>>();   //list of pairs of users name and his score
    private int bestResultNumber = 10;
  /*  public BestScoresConfig() {
        configFile = new java.util.Properties();
        try {
        } catch(Exception e) {
            e.printStackTrace();
        }
    } */
    public void addResult(float result, String name) {
        Pair newPair = new Pair(result, name);
        bestResults.add(newPair);
        bestResults.sort(new Comparator<Pair<Float, String>>() {
            @Override
            public int compare(Pair<Float, String> o1, Pair<Float, String> o2) {
                if(o1.getKey() > o2.getKey()) return -1;
                else if(o1.getKey().equals(o2.getKey())) return 0;
                else return 1;
            }
        });
        for(int i = 0; i < bestResults.size(); i++) {
            System.out.println(bestResults.get(i).getKey() + " " + bestResults.get(i).getValue());
        }
    }
    public Pair<Float, String> getResult(int number) {
        return bestResults.get(number);
    }
    public int getResultsNumber() {
        if (bestResults.size() < bestResultNumber)
            return bestResults.size();
        else
            return bestResultNumber;
    }

    public String getProperty(String key) {
        String value = this.configFile.getProperty(key);
        return value;
    }
}
