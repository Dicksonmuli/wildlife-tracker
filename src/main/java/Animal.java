import org.sql2o.*;
import java.util.List;

public class Animal {
	public String name;
	public int id;
	public String type;
	public static final String DATABASE_TYPE = "notEndangered";

// instanciating animal class
	public Animal(String name) {
		if(name.equals("")){
			throw new IllegalArgumentException("Animal Name Required");
		}
		this.name = name;
		type = DATABASE_TYPE;
	}
	//returns id
	public int getId() {
		return id;
	}
	//returns name
	public String getName() {
		return name;
	}
	//returns type
	public String getType() {
		return type;
	}

	//saves animal in DB
	public void save(){
		try(Connection con = DB.sql2o.open()) {
			String sql = "INSERT INTO animals (name, type) VALUES(:name, :type)";
			this.id =(int) con.createQuery(sql, true)
			.addParameter("name", this.name)
			.addParameter("type", this.type)
			.throwOnMappingFailure(false)
			.executeUpdate()
			.getKey();
		}
	}
	//all method retrieves all the animals saved in DB
	public static List<Animal> all() {
		String sql = "SELECT * FROM animals WHERE type='notEndangered';";
		try(Connection con = DB.sql2o.open()) {
			return con.createQuery(sql)
			.throwOnMappingFailure(false)
			.executeAndFetch(Animal.class);
		}
	}

	//overriding equals
	@Override
 public boolean equals(Object someAnimal){
	 if (!(someAnimal instanceof Animal)) {
		 return false;
	 } else {
		 Animal newAnimal = (Animal) someAnimal;
		 return this.getName().equals(newAnimal.getName()) &&
						this.getType().equals(newAnimal.getType());
	 }
 }
 //finds the animal with given animal id
 public static Animal find(int id) {
	 try(Connection con = DB.sql2o.open()) {
		 String sql = "SELECT * FROM animals where id=:id";
		 Animal animal = con.createQuery(sql)
		 .addParameter("id", id)
		 .throwOnMappingFailure(false)
		 .executeAndFetchFirst(Animal.class);
		return animal;
	 }
 }
}
