public class Sighting {
	private String location;
	private String animalId;
	private String rangerName;

	public Sighting(String animalId, String location, String rangerName) {
		this.animalId = animalId;
		this.location = location;
		this.rangerName = rangerName;
	}
}
