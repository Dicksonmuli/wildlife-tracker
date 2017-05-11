import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;
import java.sql.Timestamp;
import java.util.Date;
import java.text.DateFormat;


public class AnimalTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

	private Animal testAnimal;
	private Animal animal2;

	@Before
	public void instanciate() {
		testAnimal = new Animal("Lion");
		animal2 = new Animal("Leopard");
	}

	@Test
	public void Animal_instanciatesCorrectly_true() {
		assertTrue(testAnimal instanceof Animal);
	}
	//test if method returns name
	@Test
	public void getName_returnsName_Lion() {
		assertEquals("Lion", testAnimal.getName());
	}

	//test if method returns type
	@Test
	public void getType_returnsName_true() {
		assertEquals("notEndangered", testAnimal.getType());
	}
	//throws exception if name field is null
	@Test(expected = IllegalArgumentException.class)
	public void instance_throwsError_null(){
		Animal testAnimal = new Animal("");
	}
	//saves in database
  @Test
  public void save_savesAnimalIntoDB_true() {
    testAnimal.save();
    assertTrue(Animal.all().get(0).equals(testAnimal));
  }
  //id assigned to an instance once created
  @Test
  public void save_assignsIdToAnimal() {
    testAnimal.save();
    Animal savedAnimal = Animal.all().get(0);
    assertEquals(savedAnimal.getId(), testAnimal.getId());
  }
  //all retrieves animals from DB
  @Test
  public void all_returnsAllInstancesOfAnimal_true() {
    testAnimal.save();
    animal2.save();
    assertTrue(Animal.all().get(0).equals(testAnimal));
    assertTrue(Animal.all().get(1).equals(animal2));
  }
  //overriding equals
  @Test
  public void equals_returnsTrueIfNameIsSame_true() {
    Animal animal3 = new Animal("Lion");
    assertTrue(testAnimal.equals(animal3));
  }
  //finds an animal if saved
  @Test
  public void find_returnsAnimalWithSameId_animal2() {
    testAnimal.save();
    animal2.save();
    assertEquals(Animal.find(animal2.getId()), animal2);
  }
  //deleting animal from the database
  @Test
  public void delete_deletesAnimal_true() {
    testAnimal.save();
    testAnimal.delete();
    assertEquals(null, Animal.find(testAnimal.getId()));
  }
}
