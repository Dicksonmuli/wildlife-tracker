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
	
}
