import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class Sighting {
	private String location;
	private int animalId;
	private String rangerName;
	private int id;

	public Sighting(int animalId, String location, String rangerName) {
		this.animalId = animalId;
		this.location = location;
		this.rangerName = rangerName;
	}
	//gets the animalId
	public int getAnimalId() {
		return animalId;
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
		 return this.getLocation().equals(newSighting.getLocation()) && this.getRangerName().equals(newSighting.getRangerName()) &&
		 this.getAnimalId() == newSighting.getAnimalId();
	 }
}
	//saving Sighting
  public void save() {
 	 try(Connection con = DB.sql2o.open()) {
 		 String sql = "INSERT INTO sightings (animalId, location, rangerName) VALUES (:animalId, :location, :rangerName)";
 		 this.id = (int) con.createQuery(sql, true)
 		 .addParameter("animalId", this.animalId)
 		 .addParameter("location", this.location)
		 .addParameter("rangerName", this.rangerName)
 		 .executeUpdate()
 		 .getKey();
 	 }
  }
	//retrieves all database entries
	public static List<Sighting> all() {
		String sql = "SELECT id, animalId, location, rangerName FROM sightings";
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
	//  //returns commutities associated with the sighting
 // 	public List<Ranger> getRangers() {
 // 	 try(Connection con = DB.sql2o.open()){
 // 		 String joinQuery = "SELECT ranger_id FROM rangers_sightings WHERE sighting_id = :sighting_id";
 // 		 List<Integer> rangerIds = con.createQuery(joinQuery)
 // 			 .addParameter("sighting_id", this.getId())
 // 			 .executeAndFetch(Integer.class);
	//
 // 		 List<Ranger> communities = new ArrayList<Ranger>();
	//
 // 		 for (Integer rangerId : rangerIds) {
 // 			 String rangerQuery = "SELECT * FROM rangers WHERE id = :rangerId";
 // 			 Ranger ranger = con.createQuery(rangerQuery)
 // 				 .addParameter("rangerId", rangerId)
 // 				 .executeAndFetchFirst(Ranger.class);
 // 			 communities.add(ranger);
 // 		 }
 // 		 return ranger;
 // 	 }
  // }
  // //delete the sighting and ranger associates
  // @Override //overriding abstract method
  // public void delete() {
  //    try(Connection con = DB.sql2o.open()) {
  //    String sql = "DELETE FROM sightings WHERE id = :id;";
  //    con.createQuery(sql)
  //      .addParameter("id", this.id)
  //      .executeUpdate();
  //    String joinDeleteQuery = "DELETE FROM rangers_sightings WHERE sighting_id = :sightingId";
  //    con.createQuery(joinDeleteQuery)
  //      .addParameter("sightingId", this.getId())
  //      .executeUpdate();
  //    }
  //  }
 // 	public void leaveRanger(Ranger ranger) {
 // 		try(Connection con = DB.sql2o.open()){
 // 			String joinRemovalQuery = "DELETE FROM rangers_sightings WHERE ranger_id = :rangerId AND sighting_id = :sightingId;";
 // 			con.createQuery(joinRemovalQuery)
 // 			.addParameter("rangerId", ranger.getId())
 // 			.addParameter("sightingId", this.getId())
 // 			.executeUpdate();
 // 		}
 // 	}
}
