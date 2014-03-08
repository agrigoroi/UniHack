package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;
import play.mvc.*;

import views.html.*;
import main.*;

import java.util.Date;

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
        location.put("lon", Global.currentGame.getCenter().getY());
        node.put("location", location);
        node.put("timeleft", (Global.currentGame.endTime.getTime() - (new Date()).getTime())/1000);
        return ok(node);
//        return TODO;
    }

    public static Result guessLocation() {
        JsonNode node = request().body().asJson();
        if(node == null)
            return badRequest("Expecting Json data");
        String playerId = node.findPath("player").asText();
        JsonNode location = node.findPath("location");
        Global.currentGame.guesses.put(playerId, new LatLng(location.get("lat").asDouble(), location.get("lng").asDouble()));
        return ok();
    }

}
