import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

public class SightingTest {
	private Sighting testSighting;
	private Sighting secondSighting;

	@Rule
	public DatabaseRule database = new DatabaseRule();

	@Before
	public void instance() {
		testSighting = new Sighting("Hippo", "Park A");
		secondSighting = new Sighting("Harriet", "harriet@harriet.com");
	}
