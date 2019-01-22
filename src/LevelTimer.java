import java.awt.event.*;
import java.time.LocalDate;
import java.util.*;
import javax.swing.Timer;

public class LevelTimer {
	public LevelTimer() {
		counter = 0;
		date = new Date(1,1,10,0,0);
		TimeCounter timeCounter = new TimeCounter();
		timer = new Timer(1000, timeCounter);
		timer.start();
	}
	
	
	
	
	
	/*
	 * Klasa wewnetrzna zwiekszajaca wartosc licznika co sekunde o 1
	 */


	class TimeCounter implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (!ifPaused) {
				counter++;
				if (counter == 60) {
					date.setMinutes(date.getMinutes() + 1);
					counter = 0;
					date.setSeconds(counter);
				} else
					date.setSeconds(counter);
//			System.out.println(date.getMinutes() + ":" + date.getSeconds());
			}
		}
		
	}
	public Date getDate() {return date;}
	public int getSeconds() {return counter;}
	/*
	These 2 methods stops timer when user pause the game and runs when pause ends
	 */
	public void pauseTimer() {ifPaused = true;}
	public void runTimer() {ifPaused = false;}
	public int getEdgeDistance() {return edgeDistance;}
	
	private Timer timer;
	private int counter;
	private Date date;
	private boolean ifPaused = false; //if the game is paused timer is stopped
	private Config cfg = new Config(Player.getActualLevel());
	private int edgeDistance = (int) Utils.floatFromConfig(cfg, "timerEdgeDistance");
}