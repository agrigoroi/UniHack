package main;

import main.Game;
import main.LatLng;
import play.Application;
import play.GlobalSettings;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import play.libs.*;
import play.libs.F.*;

/**
 * Created by alex on 08/03/14.
 */
public class Global extends GlobalSettings {

    public static Game currentGame;
    // public static Firebase fb =  new Firebase("https://incandescent-fire-6038.firebaseio.com/");
    @Override
    public void onStart(Application app) {
        Game.locations.add(new LatLng(53.474, -2.248));
        Game.locations.add(new LatLng(53.474, -1.248));
        currentGame = new Game();
    }
    @Override
    public void onStop(Application app) {
    }
}
