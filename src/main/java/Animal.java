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


}
