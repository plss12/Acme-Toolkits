package acme.testing.inventor.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageListTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage/patronage.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String status, final String code, final String legalStuff, 
		final String budget, final String startDate, final String finishDate, final String link) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor","Inventor Patronages");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, startDate);
		super.checkColumnHasValue(recordIndex, 2, finishDate);
		super.checkColumnHasValue(recordIndex, 3, legalStuff);
		super.checkColumnHasValue(recordIndex, 4, status);
		
		super.signOut();		
		
	}

}
