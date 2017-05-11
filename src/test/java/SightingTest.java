import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

public class SightingTest {
	private Sighting testSighting;
	private Sighting secondSighting;

	@Rule
	public DatabaseRule database = new DatabaseRule();
	//creating an instance of Sighting
	@Before
	public void instance() {
		testSighting = new Sighting(1, "Park A", "Juma");
		secondSighting = new Sighting(2, "Park B", "Melvin");
	}
	//person instantiates corectly
	@Test
	public void Sighting_instantiatesCorrectly_true() {
		assertTrue(testSighting instanceof Sighting);
	}
	//getName returns animal name
	@Test
	public void getAnimalId_sightingInstantiatesWithId_1() {
		assertEquals(1, testSighting.getAnimalId());
	}
	//getName returns location
	public void getLocation_sightingInstantiatesWithlocation_Park_A() {
		assertEquals("Park A", testSighting.getLocation());
	}
	//getName returns rangerName
	@Test
	public void getRangerName_sightingInstantiatesWithName_Hippo() {
		assertEquals("Juma", testSighting.getRangerName());
	}
	//overriding equals
	@Test
	public void equals_returnsTrueIfLocationAnimalIdAreSame_true() {
		Sighting someSighting = new Sighting(1, "Park A", "Juma");
		Sighting otherSighting = new Sighting(1, "Park A", "Juma");
		assertTrue(someSighting.equals(otherSighting));
	}
	//saving to the db
	@Test
	public void save_insertsObjectIntoDatabase_Sighting() {
		//saves automatically when an instance is created
		assertTrue(Sighting.all().get(0).equals(testSighting));
	}
	//returning all database entries
	@Test
	public void all_returnsAllInstancesOfPerson_true() {
		assertTrue(Sighting.all().get(0).equals(testSighting));
		assertTrue(Sighting.all().get(1).equals(secondSighting));
	}
	@Test
	public void save_assignsIdToObject() {
		Sighting savedSight = Sighting.all().get(0);
		assertEquals(testSighting.getId(), savedSight.getId());
	}
	//returning a sighting by id
	@Test
 public void find_returnsSightingWithSameId_secondSighting() {
	 assertEquals(Sighting.find(secondSighting.getId()), secondSighting);
 }
}
