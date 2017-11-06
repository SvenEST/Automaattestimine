package connection;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import connection.Connection;

public class ConnectionTest {

	@Test
	public void testInternetConnectionExists() {
		Connection con = new Connection();
		boolean result = con.internetConnectionExists();
		assertTrue(result);
	}
}
