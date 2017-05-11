import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;


public class EndangeredTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

	private Endangered testEndangered;
	private Endangered testEndangered2;

	@Before
	public void instanciate() {
		testEndangered = new Endangered("Lion", "healthy", 2);
		testEndangered2 = new Endangered("Leopard", "healthy", 3);
	}

	@Test
	public void Endangered_instanciatesCorrectly_true() {
		assertTrue(testEndangered instanceof Endangered);
	}
	//test if method returns name
	@Test
	public void getName_returnsName_Lion() {
		assertEquals("Lion", testEndangered.getName());
	}

	//test if method returns type
	@Test
	public void getType_returnsType_true() {
		assertEquals("endangered", testEndangered.getType());
	}
	//throws exception if name field is null
	@Test(expected = IllegalArgumentException.class)
	public void instance_throwsError_null(){
		Endangered testEndangered = new Endangered("", "", 0);
	}
	//saves in database
  @Test
  public void save_savesEndangeredIntoDB_true() {
    testEndangered.save();
    assertTrue(Endangered.allEndangered().get(0).equals(testEndangered));
  }
  //id assigned to an instance once created
  @Test
  public void save_assignsIdToEndangered() {
    testEndangered.save();
    Endangered savedEndangered = Endangered.allEndangered().get(0);
    assertEquals(savedEndangered.getId(), testEndangered.getId());
  }
  //all retrieves Endangered from DB
  @Test
  public void all_returnsAllInstancesOfEndangered_true() {
    testEndangered.save();
    testEndangered2.save();
    assertTrue(Endangered.allEndangered().get(0).equals(testEndangered));
    assertTrue(Endangered.allEndangered().get(1).equals(testEndangered2));
  }
  //overriding equals
  @Test
  public void equals_returnsTrueIfNameIsSame_true() {
    Endangered testEndangered3 = new Endangered("Lion", "healthy", 6);
    assertTrue(testEndangered.equals(testEndangered3));
  }
  //finds an Endangered if saved
  @Test
  public void find_returnsEndangeredWithSameId_testEndangered2() {
    testEndangered.save();
    testEndangered2.save();
    assertEquals(Endangered.find(testEndangered2.getId()), testEndangered2);
  }
  //deleting Endangered from the database
  @Test
  public void delete_deletesEndangered_true() {
    testEndangered.save();
    testEndangered.delete();
    assertEquals(null, Endangered.find(testEndangered.getId()));
  }
}
