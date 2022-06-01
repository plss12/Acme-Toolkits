package acme.testing.inventor.chimpum;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorChimpumDeleteTest extends TestHarness {
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/chimpum/chimpumDelete.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int artifactIndex, final int chimpumIndex) {
					
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor","Inventor Artifacts");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(artifactIndex);
		
		super.clickOnButton("Chimpums");	
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(chimpumIndex);
		super.clickOnSubmit("Delete");	


	}
	
}
