package acme.testing.inventor.misit;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorMisitUpdateTest extends TestHarness{

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
		super.clickOnButton("View Chimpums");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		
		final LocalDate dateObj = LocalDate.now();
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd");
        final String date = dateObj.format(formatter);
		
		super.fillInputBoxIn("code", artifactCode+"-"+date+"-"+code);
		super.fillInputBoxIn("creationMoment", creationMoment);
		super.fillInputBoxIn("subject", subject);
		super.fillInputBoxIn("explanation", explanation);
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("finishDate", finishDate);
		super.fillInputBoxIn("quantity", quantity);
		super.fillInputBoxIn("additionalInfo", additionalInfo);
		
		super.clickOnSubmit("Update");
		
		super.clickOnMenu("Inventor", "Inventor Artifacts");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(1);
		super.checkFormExists();
		super.clickOnButton("View Chimpums");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 1, creationMoment);
		super.checkColumnHasValue(recordIndex, 2, subject);
		super.checkColumnHasValue(recordIndex, 3, quantity);
		super.checkColumnHasValue(recordIndex, 4, artifactCode);
		
		super.clickOnListingRecord(recordIndex);
		
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
		super.clickOnButton("View Chimpums");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("creationMoment", creationMoment);
		super.fillInputBoxIn("subject", subject);
		super.fillInputBoxIn("explanation", explanation);
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("finishDate", finishDate);
		super.fillInputBoxIn("quantity", quantity);
		super.fillInputBoxIn("additionalInfo", additionalInfo);
		
		super.clickOnSubmit("Update");
		
		super.checkErrorsExist();
		
		super.signOut();
	}

}
