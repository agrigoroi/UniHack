package main;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import main.Game;
import main.LatLng;
import play.Application;
import play.GlobalSettings;

import java.io.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import play.libs.*;
import play.libs.F.*;
import scala.reflect.io.VirtualFile;

/**
 * Created by alex on 08/03/14.
 */
public class Global extends GlobalSettings {

    public static Game currentGame;
    // public static Firebase fb =  new Firebase("https://incandescent-fire-6038.firebaseio.com/");
    @Override
    public void onStart(Application app) {
        try {
            String content = Files.toString(new File("locations"), Charsets.UTF_8);
            String[] locations = content.split("\n");
            for(String location: locations) {
                String coordinates[] = location.split(",");
                Game.locations.add(new LatLng(Double.parseDouble(coordinates[0]),
                                              Double.parseDouble(coordinates[1])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        currentGame = new Game();
    }
    @Override
    public void onStop(Application app) {
    }
}
