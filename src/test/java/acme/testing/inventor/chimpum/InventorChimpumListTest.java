package acme.testing.inventor.chimpum;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorChimpumListTest extends TestHarness {
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/chimpum/chimpumList.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int artifactIndex, final int chimpumIndex, final String title, 
		final String creationMoment, final String budget) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor","Inventor Artifacts");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(artifactIndex);
		
		super.clickOnButton("Chimpums");
		super.sortListing(0, "asc");

		super.checkColumnHasValue(chimpumIndex, 0, title);
		super.checkColumnHasValue(chimpumIndex, 1, creationMoment);
		super.checkColumnHasValue(chimpumIndex, 2, budget);
		
		super.signOut();			
	}
	
}
