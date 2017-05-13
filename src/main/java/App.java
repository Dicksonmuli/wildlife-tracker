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
		// if a port is get for the app, use it else continue with 4567
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
			model.put("template", "templates/new-sighting-modal.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());
		get("/sightings/new/add", (request, response) -> {
			Map<String, Object> model = new HashMap<String, Object>();
			try{
				Animal animal = Animal.find(Integer.parseInt(request.queryParams("id")));
				model.put("animal", animal);
			}catch (IllegalArgumentException exception) {
				System.out.println("Animal Name Required");
			}

			model.put("template", "templates/sighting-form.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());
		//sightings
		post("/sightings", (request, response) -> {
			Map<String, Object> model = new HashMap<String, Object>();
			String rangerName = request.queryParams("rangerName");
			String location = request.queryParams("location");
			String name =request.queryParams("animalName");
			try{
				Sighting sighting = new Sighting(name,location, rangerName);
				sighting.save();
			}catch (IllegalArgumentException exception) {
				System.out.println("Animal Name, Location and Ranger Name Required");
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
				try{
					Endangered newEndangered = new Endangered(name, health, age);
					newEndangered.save();
				}catch (IllegalArgumentException exception) {
					System.out.println("Animal Name, health and age Required");
				}
				model.put("template", "templates/sighting-form.vtl");
	 			return new ModelAndView(model, layout);
				}, new VelocityTemplateEngine());
				//add new animal
			get("/animals/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/animal-form.vtl");
      return new ModelAndView(model, layout);
     }, new VelocityTemplateEngine());

    post("/animals/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      try {
        Animal animal = new Animal(name);
        animal.save();
      } catch (IllegalArgumentException exception) {
        System.out.println("Please enter an animal name.");
      }
      response.redirect("/sightings/new/add");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
			//non endangered animal
			get("/animals/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("animal", Animal.find(Integer.parseInt(request.params(":id"))));
      model.put("endangered", Endangered.find(Integer.parseInt(request.params(":id"))));
      model.put("Sighting", Sighting.class);
      model.put("template", "templates/animal.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
<<<<<<< HEAD

=======
		//displaying all the animas
		get("/animals", (request, response) -> {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("animls", Animal.all());
			model.put("endangered", Endangered.allEndangered());
			model.put("template", "templates/animalas.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());
>>>>>>> 9f72f771c7c5c6a0d5e8542b6904a1e07e82b39b
    get("/animals/:id/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Animal.find(Integer.parseInt(request.params(":id"))).delete();
      response.redirect("/animals");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/sightings/:id/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Sighting.find(Integer.parseInt(request.params(":id"))).delete();
      response.redirect("/sightings");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/sightings/:id/edit", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("animal", Animal.find(Integer.parseInt(request.params(":id"))));
      model.put("endangered", Endangered.find(Integer.parseInt(request.params(":id"))));
      model.put("template", "templates/animal-edit.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/sightings/:id/edit", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("sighting", Sighting.find(Integer.parseInt(request.params(":id"))));
      model.put("Animal", Animal.class);
      model.put("template", "templates/sighting-edit.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/sightings/:id/edit", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params(":id"));
			String name = request.queryParams("name");
      String location = request.queryParams("location");
      String rangerName = request.queryParams("rangerName");
      Sighting sighting = Sighting.find(id);
      sighting.update(name, location, rangerName);
      response.redirect("/sightings");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

	}
}
