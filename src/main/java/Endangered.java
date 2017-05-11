import org.sql2o.*;
import java.util.List;

public class Endagered extends Animal {
	public String name;
	public int id;
	public String type;
	public static final String DATABASE_TYPE = "endangered";
	public static final boolean HEALTH = true;
	public static final int AGE = 0;

// instanciating animal class
	public Animal(String name) {
		if(name.equals("")){
			throw new IllegalArgumentException("Animal Name Required");
		}
		this.name = name;
		type = DATABASE_TYPE;
		healthy = HEALTH;
		ill = !HEALTH;
		okey = HEALTH;
		newborn = AGE + 1;
		young = AGE + 5;
		adult = AGE + 10;
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
