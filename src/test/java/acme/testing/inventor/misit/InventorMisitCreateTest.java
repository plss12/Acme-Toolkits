package acme.testing.inventor.misit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorMisitCreateTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/misit/misit-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String code, final String creationMoment, final String subject,
		final String explanation, final String startDate, final String finishDate, final String quantity, 
		final String additionalInfo, final String artifactCode) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "Inventor Artifacts");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(1);
		super.checkFormExists();
		super.clickOnButton("View Misits");
		super.checkListingExists();
		
		super.clickOnButton("Create");
		
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("creationMoment", creationMoment);
		super.fillInputBoxIn("subject", subject);
		super.fillInputBoxIn("explanation", explanation);
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("finishDate", finishDate);
		super.fillInputBoxIn("quantity", quantity);
		super.fillInputBoxIn("additionalInfo", additionalInfo);
		
		super.clickOnSubmit("Create");
		
		super.clickOnMenu("Inventor", "Inventor Artifacts");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(1);
		super.checkFormExists();
		super.clickOnButton("View Misits");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 1, creationMoment);
		super.checkColumnHasValue(recordIndex, 2, subject);
		super.checkColumnHasValue(recordIndex, 3, quantity);
		super.checkColumnHasValue(recordIndex, 4, artifactCode);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("creationMoment", creationMoment);
		super.checkInputBoxHasValue("title", subject);
		super.checkInputBoxHasValue("description", explanation);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("finishDate", finishDate);
		super.checkInputBoxHasValue("budget", quantity);
		super.checkInputBoxHasValue("link", additionalInfo);
		super.checkInputBoxHasValue("artefact.code", artifactCode);
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/misit/misit-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String code, final String creationMoment, final String subject,
		final String explanation, final String startDate, final String finishDate, final String quantity, 
		final String additionalInfo, final String artifactCode) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "Inventor Artifacts");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(1);
		super.checkFormExists();
		super.clickOnButton("View Misits");
		super.checkListingExists();
		
		super.clickOnButton("Create");
		
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("creationMoment", creationMoment);
		super.fillInputBoxIn("title", subject);
		super.fillInputBoxIn("description", explanation);
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("finishDate", finishDate);
		super.fillInputBoxIn("budget", quantity);
		super.fillInputBoxIn("link", additionalInfo);
		
		super.clickOnSubmit("Create");
		
		super.checkErrorsExist();
		
		super.signOut();
	}

}
