package main;

import java.util.*;
import play.libs.*;
import play.libs.F.*;

/**
 * Created by alex on 08/03/14.
 */
public class Game {

    private static Random randomGenerator = new Random();

    public Date endTime;
    public LatLng center;
    public static ArrayList<LatLng> locations = new ArrayList<LatLng>();
    public HashMap<String, LatLng> guesses = new HashMap<String, LatLng>();

    public Game(LatLng center) {
        this.center = center;
        this.endTime = new Date((new Date()).getTime() + 60*1000);
        Promise<WS.Response> result = WS.url("https://vivid-fire-7318.firebaseio.com/test.json").put("\"Start\"");


        Timer timer = new Timer();
        TimerTask endGame = new TimerTask() {
            public void run()
            {
                for(String player: Global.currentGame.guesses.keySet()) {
                    LatLng guess = Global.currentGame.guesses.get(player);
                    System.out.println(player + ": " + guess.getX() + ", " + guess.getY());
                }
            }
        };
        timer.schedule(endGame, endTime.getTime() - (new Date()).getTime());
        TimerTask newGame = new TimerTask() {
            public void run()
            {
                Global.currentGame = new Game();
                //Global.fb.setValue(true);
            }
        };
        timer.schedule(newGame, endTime.getTime() - (new Date()).getTime() + 10*1000);
    }

    public Game() {
        this(locations.get(randomGenerator.nextInt(locations.size())));
    }

    public LatLng getCenter() {
        return center;
    }

}
