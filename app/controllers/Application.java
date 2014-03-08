package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(map.render());
    }

    public static Result rules() {
    	return ok(rules.render());       
    }

}
