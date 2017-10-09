package weather;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ConnectionTest {

	@Test
	public void testInternetConnectionExists() {
		Connection con = new Connection();
		boolean result = con.internetConnectionExists();
		assertTrue(result);
	}
}
