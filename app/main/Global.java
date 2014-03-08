package main;

import main.Game;
import main.LatLng;
import play.Application;
import play.GlobalSettings;

/**
 * Created by alex on 08/03/14.
 */
public class Global extends GlobalSettings {

    public static Game currentGame;

    @Override
    public void onStart(Application app) {
        System.out.println("Here");
        Game.locations.add(new LatLng(0, 0));
        Game.locations.add(new LatLng(53.474, -2.248));
        System.out.println("Here");
        currentGame = new Game();
    }
    @Override
    public void onStop(Application app) {
        System.out.println("Here");
        Game.locations.add(new LatLng(53.474, -2.248));
        Game.locations.add(new LatLng(53.474, -2.248));
        System.out.println("Here");
        currentGame = new Game();
    }
}
