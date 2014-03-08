package controllers;

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

    public static Result guessLocation(String playerId) {
        return TODO;
    }

}
