package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;
import play.mvc.*;

import views.html.*;
import main.*;

import java.util.Date;
import java.util.Map;

public class Application extends Controller {

    public static Result index() {
        return ok(map.render());
    }

    public static Result rules() {
    	return ok(rules.render());       
    }
    public static Result getLocation() {
        ObjectNode location = Json.newObject();
        ObjectNode node = Json.newObject();
        if(Global.currentGame == null) {
            System.out.println("Fuck");
        }
        location.put("lat", Global.currentGame.getCenter().getX());
        location.put("lng", Global.currentGame.getCenter().getY());
        node.put("location", location);
        node.put("timeleft", (Global.currentGame.endTime.getTime() - (new Date()).getTime())/1000);
        return ok(node);
//        return TODO;
    }

    public static Result guessLocation() {
//        System.out.println(request().body().asText());
        Map<String, String[]> data = request().body().asFormUrlEncoded();
        for(String dataS: data.keySet()) {
            System.out.print(dataS +": ");
            for(String s: data.get(dataS))
                System.out.print(s + ", ");
        }
        String playerId = data.get("userID")[0];
//        JsonNode location = node.findPath("location");
//        System.out.println(location);
//        Global.currentGame.guesses.put(playerId, new LatLng(location.get("lat").asDouble(), location.get("lng").asDouble()));
//        System.out.println(playerId + ": " + location.get("lat").asDouble() + ", " + location.get("lng").asDouble());
        return ok();
    }

}
