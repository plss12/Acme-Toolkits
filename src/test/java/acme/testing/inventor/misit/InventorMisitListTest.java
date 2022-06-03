package acme.testing.inventor.misit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorMisitListTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/misit/misit.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String code, final String creationMoment, final String subject,
		final String explanation, final String startDate, final String finishDate, final String quantity, 
		final String additionalInfo, final String artifactCode) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "Inventor Artifacts");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.clickOnButton("View Misits");
		super.checkListingExists();
		
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, creationMoment);
		super.checkColumnHasValue(recordIndex, 2, subject);
		super.checkColumnHasValue(recordIndex, 3, quantity);
		super.checkColumnHasValue(recordIndex, 4, artifactCode);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("creationMoment", creationMoment);
		super.checkInputBoxHasValue("subject", subject);
		super.checkInputBoxHasValue("explanation", explanation);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("finishDate", finishDate);
		super.checkInputBoxHasValue("quantity", quantity);
		super.checkInputBoxHasValue("additionalInfo", additionalInfo);
		super.checkInputBoxHasValue("artefact.code", artifactCode);
		
		super.signOut();
	}
	
	@Test
	@Order(20)
	public void negativeTest() {
		super.signIn("inventor1", "inventor1");
		super.navigate("/inventor/misit/list","masterId=-1");
		super.checkErrorsExist();
	}

}
