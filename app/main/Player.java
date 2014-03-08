package main;

import com.fasterxml.jackson.databind.JsonNode;
import play.mvc.WebSocket;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by alex on 08/03/14.
 */
public class Player {

    private static HashMap<String, Player> players = new HashMap<String, Player>();

    public String id;
    public int score;
//
//    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//    static final int ID_LENGTH = 15;
//    static Random rnd = new Random();
//    private Game game = main.Global.defaultGame;
//
//    private static String genRandomId() {
//        StringBuilder sb = new StringBuilder(ID_LENGTH);
//        for( int i = 0; i < ID_LENGTH; i++ )
//            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
//        return sb.toString();
//    }
//
//    public Player() {
//        this(genRandomId());
//    }
//
//    private Player(String id) {
//        this.id = id;
//        score = 0;
//        players.put(id, this);
//    }
//
//
//    public static Player getPlayer(String id) {
//        if(players.containsKey(id))
//            return players.get(id);
//        return new Player(id);
//    }
//
//    public void setSocket(WebSocket.Out<JsonNode> socket) {
//        this.socket = socket;
//    }
//
//    public void makeGuess(LatLng guessLocation) {
//        game.newGuess(this, guessLocation);
//    }
}
