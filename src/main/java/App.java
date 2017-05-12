import org.sql2o.*;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
	public static void main(String[] args) {
		staticFileLocation("/public");
		String layout = "templates/layout.vtl";
		// if a port is set for the app, use it else continue with 4567
		ProcessBuilder process = new ProcessBuilder();
		Integer port;
		if (process.environment().get("PORT") != null) {
			port = Integer.parseInt(process.environment().get("PORT"));
		}else {
			port = 4567;
		}
		setPort(port);

		// root route

		get("/", (request, response) -> {
			Map<String, Object> model = new HashMap<String, Object>();
			// model.put("animals", Animal.all());
			model.put("template", "templates/index.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());
		//all sightings
		get("/sightings", (request, response) -> {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("sightings", Sighting.all());
			model.put("template", "templates/sightings.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());
		//new sighting
		get("/sightings/new", (request, response) -> {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("template", "templates/sighting-form.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());
		//sightings
		post("/sightings", (request, response) -> {
			Map<String, Object> model = new HashMap<String, Object>();
			String rangerName = request.queryParams("rangerName");
			String location = request.queryParams("location");
			int age = Integer.parseInt(request.queryParams("age"));
			String type = request.queryParams("health");
			String name =request.queryParams("animalName");

			Animal newAnimal = new Animal(name);
			try{
				Sighting sighting = new Sighting(animalId,location, rangerName);
				newAnimal.save();
			}catch (IllegalArgumentException exception) {
				System.out.println("Animal Name, Location and Ranger Name Required")
			}
			model.put("template", "templates/sightings.vtl");
 			return new ModelAndView(model, layout);
			}, new VelocityTemplateEngine());

			//endangered
			get("/endangered/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/endangered-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

			//endangered post
			post("/endangered/new", (request, response) -> {
				Map<String, Object> model = new HashMap<String, Object>();
				int age = Integer.parseInt(request.queryParams("age"));
				String health = request.queryParams("health");
				String name =request.queryParams("animalName");
				Animal newAnimal = new Animal(name);
				try{
					Endangered newEndangered = new Endangered(name, health, age);
					newAnimal.save();
				}catch (IllegalArgumentException exception) {
					System.out.println("Animal Name, health and age Required")
				}
				model.put("template", "templates/endangered-form.vtl");
	 			return new ModelAndView(model, layout);
				}, new VelocityTemplateEngine());
			//

	}
}
