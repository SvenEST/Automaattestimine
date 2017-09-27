package weather;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ConnectionTest {

	@Test
	public void testInternetConnectionExists() {
		boolean result = Connection.internetConnectionExists();
		assertTrue(result);
	}
}
