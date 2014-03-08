package main;

import main.Game;
import main.LatLng;
import play.Application;
import play.GlobalSettings;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by alex on 08/03/14.
 */
public class Global extends GlobalSettings {

    public static Game currentGame;

    @Override
    public void onStart(Application app) {
        Game.locations.add(new LatLng(53.474, -2.248));
        Game.locations.add(new LatLng(53.474, -2.248));
        currentGame = new Game();
        Timer timer = new Timer();
        TimerTask endGame = new TimerTask() {
            public void run()
            {
                for(String player: currentGame.guesses.keySet()) {
                    LatLng guess = currentGame.guesses.get(player);
                    System.out.println(player + ": " + guess.getX() + ", " + guess.getY());
                }
            }
        };
        timer.schedule(endGame, currentGame.endTime.getTime() - (new Date()).getTime());
        TimerTask newGame = new TimerTask() {
            public void run()
            {
                currentGame = new Game();
            }
        };
        timer.schedule(newGame, currentGame.endTime.getTime() - (new Date()).getTime() + 10*1000);
    }
    @Override
    public void onStop(Application app) {
    }
}
