package main;

import java.util.*;

/**
 * Created by alex on 08/03/14.
 */
public class Game {

    private static Random randomGenerator = new Random();

    public Date endTime;
    public LatLng center;
    public static ArrayList<LatLng> locations = new ArrayList<LatLng>();
    private HashSet<Player> players = new HashSet<Player>();

    public Game(LatLng center) {
        this.center = center;
        this.endTime = new Date((new Date()).getTime() + 60*1000);
    }

    public Game() {
        this(locations.get(randomGenerator.nextInt(locations.size())));
    }

    public LatLng getCenter() {
        return center;
    }

}
