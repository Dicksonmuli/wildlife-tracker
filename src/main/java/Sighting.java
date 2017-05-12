import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class Sighting {
	private String location;
	private String name;
	private String rangerName;
	private int id;

	public Sighting(String name, String location, String rangerName) {
		this.name = name;
		this.location = location;
		this.rangerName = rangerName;

		//saving each time an instance is created
		try(Connection con = DB.sql2o.open()) {
  		 String sql = "INSERT INTO sightings (name, location, rangerName) VALUES (:name, :location, :rangerName)";
  		 this.id = (int) con.createQuery(sql, true)
			 .addParameter("name", this.name)
  		 .addParameter("location", this.location)
 		 	 .addParameter("rangerName", this.rangerName)
  		 .executeUpdate()
  		 .getKey();
  	 }
	}
	public String getName() {
		return name;
	}
	//gets the location
	public String getLocation(){
		return location;
	}
	//gets the rangerName
	public String getRangerName() {
		return rangerName;
	}
	//returns an id
	public int getId() {
		return id;
	}
	//overriding equals
	@Override
 public boolean equals(Object otherSighting){
	 if (!(otherSighting instanceof Sighting)) {
		 return false;
	 } else {
		 Sighting newSighting = (Sighting) otherSighting;
		 return this.getLocation().equals(newSighting.getLocation()) && this.getRangerName().equals(newSighting.getRangerName())
		 && this.getName().equals(newSighting.getName());
	 }
}
	//saving Sighting
  public void save() {
		try(Connection con = DB.sql2o.open()) {
 			String sql = "INSERT INTO sightings (name, location, rangerName) VALUES (:name, :location, :rangerName)";
 			this.id = (int) con.createQuery(sql, true)
 			.addParameter("name", this.name)
 			.addParameter("location", this.location)
 			.addParameter("rangerName", this.rangerName)
 			.executeUpdate()
 			.getKey();
 		}
  }
	//retrieves all database entries
	public static List<Sighting> all() {
		String sql = "SELECT id, name, location, rangerName FROM sightings";
		try(Connection con = DB.sql2o.open()) {
			return con.createQuery(sql).executeAndFetch(Sighting.class);
		}
	}
	// finds a Sighting with a specified id
	public static Sighting find(int id) {
		 try(Connection con = DB.sql2o.open()) {
			 String sql = "SELECT * FROM sightings where id=:id";
			 Sighting sighting = con.createQuery(sql)
				 .addParameter("id", id)
				 .executeAndFetchFirst(Sighting.class);
			 return sighting;

		 }
	 }

	 //retrieves all animals associated with this sightings

	//  public List<Object> getAnimals() {
  //    List<Object> allAnimals = new ArrayList<Object>();
	 //
  //    try(Connection con = DB.sql2o.open()) {
  //      String sqlAnimal = "SELECT * FROM animals WHERE sightingId=:id AND type='notEndangered';";
  //      List<Animal> animal = con.createQuery(sqlAnimal)
  //        .addParameter("id", this.id)
 // 				.throwOnMappingFailure(false)
  //        .executeAndFetch(Animal.class);
  //        allAnimals.addAll(animal);
	 //
  //      String sqlEndangeredAnimal = "SELECT * FROM animals WHERE sightingId=:id AND type='endangered';";
  //      List<Endangered> endangeredAnimal = con.createQuery(sqlEndangered)
  //        .addParameter("id", this.id)
 // 				.throwOnMappingFailure(false)
  //        .executeAndFetch(Endangered.class);
  //        allAnimals.addAll(endangeredAnimal);
  //      }
	 //
  //      return allAnimals;
  //  }

}
