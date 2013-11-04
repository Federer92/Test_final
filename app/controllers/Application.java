package controllers;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import play.api.templates.Html;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import models.todo;

import views.html.*;

public class Application extends Controller {
	
static Form<todo> todoForm = Form.form(todo.class);

public static Result index() {
        return ok(index.render());
    }
private static Result ok(Html render) {  
	// TODO Auto-generated method stub
	 return null;
}
public static Result listtodos(String username){	
	
	List<todo> todos ;
    if(username.equals("visiteur"))
    todos = todo.all();
    else
    todos = todo.findByUsername(username);
    if(request().accepts("text/html"))
    return ok(views.html.wall.render(todos, "bonjour " + username, todoForm));
    else if(request().accepts("application/json"))
    return ok(Json.toJson(todos));
    else if (request().accepts("application/rdf+xml"))
    return ok("this will be RDF XML");
    return badRequest();
}


public static Result submittodo()
	     {
	      todo todo = todoForm.bindFromRequest().get();
	      todo.create(todo);
	      return redirect(routes.Application.listtodos("visiteur"));
	     }     
public static Result listtodosFromTo()
{
 if(request().accepts("application/json"))
 {
 JsonNode body = request().body().asJson();
 System.out.println(body);
 int from = body.get("from").asInt();
     int to = body.get("to").asInt();
     List<todo> todos = todo.findNext(from, to);
     System.out.println(Json.toJson(todos));
     return ok(Json.toJson(todos));
 }
 return badRequest();
}
}



