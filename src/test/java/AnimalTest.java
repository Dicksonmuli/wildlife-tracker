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
}
