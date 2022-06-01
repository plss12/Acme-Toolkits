package acme.testing.inventor.chimpum;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorChimpumShowTest extends TestHarness {
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/chimpum/chimpumShow.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int artifactIndex, final int chimpumIndex, final String title, final String code, 
		final String creationMoment, final String description, final String budget, 
		final String link, final String startDate, final String finishDate) {
		
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
		
		super.clickOnListingRecord(chimpumIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("finishDate", finishDate);
		super.checkInputBoxHasValue("budget", budget);
						
		super.signOut();			
	}
	
}
