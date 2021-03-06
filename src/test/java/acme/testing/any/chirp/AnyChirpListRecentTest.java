package acme.testing.any.chirp;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyChirpListRecentTest extends TestHarness {
	
	//Test cases
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/chirp/chirp.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void positiveTest(final int recordIndex, final String creationMoment, final String title, final String author, final String body, final String email) {
		super.signIn("administrator", "administrator");
		//Testing list
		super.clickOnMenu("Authenticated", "Chirps of this month");
		super.checkListingExists();
		super.sortListing(0, "desc");
		
		super.checkColumnHasValue(recordIndex, 0, creationMoment);
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, author);
		super.checkColumnHasValue(recordIndex, 3, body);
		super.checkColumnHasValue(recordIndex, 4, email);
		super.signOut();
	}	
}
