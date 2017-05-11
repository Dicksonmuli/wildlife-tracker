import org.sql2o.*;
import java.util.List;

public class Endangered extends Animal {
	private int age;
	private String type;
	private int newborn;
	private int young;
	private int adult;

	String health;
	public static final String DATABASE_TYPE = "endangered";
	public static final int MIN_AGE = 0;

// instanciating animal class
	public Endangered(String name, String health, int age) {
		super(name);
		if(name.equals("") || health.equals("") || age == 0){
			throw new IllegalArgumentException("Animal Name, health and age Required");
		}
		this.name = name;
		type = DATABASE_TYPE;
		newborn = MIN_AGE + 1;
		young = MIN_AGE + 5;
		adult = MIN_AGE + 10;
		this.age = age;
		this.health = health;

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
	//returns health
	public String getHealth() {
		return health;
	}
	public int getAge() {
		return age;
	}

	//saves animal in DB
	@Override
	public void save(){
		try(Connection con = DB.sql2o.open()) {
			String sql = "INSERT INTO animals (name, type, health, age) VALUES(:name, :type, :health, :age)";
			this.id =(int) con.createQuery(sql, true)
			.addParameter("name", this.name)
			.addParameter("type", this.type)
			.addParameter("health", this.health)
			.addParameter("age", this.age)
			.executeUpdate()
			.getKey();
		}
	}
	//all Endangered animals
	public static List<Endangered> allEndangered() {
    String sql = "SELECT * FROM animals WHERE type='endangered';";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
			.throwOnMappingFailure(false)
			.executeAndFetch(Endangered.class);
    }
  }
	//overriding equals
	@Override
 public boolean equals(Object someEndangeredAnimal){
	 if (!(someEndangeredAnimal instanceof Endangered)) {
		 return false;
	 } else {
		 Endangered newAnimal = (Endangered) someEndangeredAnimal;
		 return this.getName().equals(newAnimal.getName());
	 }
 }
 //finds the animal with given animal id
 public static Endangered find(int id) {
	 try(Connection con = DB.sql2o.open()) {
		 String sql = "SELECT * FROM animals where id=:id";
		 Endangered enAnimal = con.createQuery(sql)
		 .addParameter("id", id)
		 .throwOnMappingFailure(false)
		 .executeAndFetchFirst(Endangered.class);
		return enAnimal;
	 }
 }
 @Override
 public void delete() {
	 try(Connection con = DB.sql2o.open()) {
		 String sql = "DELETE FROM animals WHERE id = :id;";
		 con.createQuery(sql)
		 .addParameter("id", this.id)
		 .executeUpdate();
	 }
 }
 //updates endangered animal
 @Override
 public void update() {
	 try(Connection con = DB.sql2o.open()) {
		 String sql = "UPDATE animals SET (name, health, age) = (:name, :health, :age) WHERE id=:id;";
		 con.createQuery(sql)
		 .addParameter("id", this.id)
		 .throwOnMappingFailure(false)
		 .executeUpdate();
	 }
 }
}
