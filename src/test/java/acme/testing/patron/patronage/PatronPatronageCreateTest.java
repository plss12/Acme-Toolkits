package acme.testing.patron.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronPatronageCreateTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String code, final String legalStuff, final String budget,
		final String startDate, final String finishDate, final String link, final String inventorSelector) {
		super.signIn("patron1", "patron1");
		
		super.clickOnMenu("Patron", "List my Patronages");
		super.checkListingExists();
		
		super.clickOnButton("Create");
		super.checkFormExists();
		
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("legalStuff", legalStuff);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("finishDate", finishDate);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("inventor.userAccount.username", inventorSelector);
		
		super.clickOnSubmit("Create");
		
		super.clickOnMenu("Patron", "List my Patronages");
		super.checkListingExists();
		super.sortListing(0, "desc");
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, startDate);
		super.checkColumnHasValue(recordIndex, 2, finishDate);
		super.checkColumnHasValue(recordIndex, 3, legalStuff);
		super.checkColumnHasValue(recordIndex, 4, "PROPOSED");
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("legalStuff", legalStuff);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("finishDate", finishDate);
		super.checkInputBoxHasValue("link", link);
		
		super.clickOnButton("Patronage Reports");
		
		super.checkListingExists();
		super.checkListingEmpty();
		
		super.clickOnMenu("Patron", "List my Patronages");
		super.checkListingExists();
		super.sortListing(0, "desc");
		super.clickOnListingRecord(recordIndex);
		
		super.signOut();		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String code, final String legalStuff, final String budget,
		final String startDate, final String finishDate, final String link, final String inventorSelector) {
		
		super.signIn("patron1", "patron1");
		
		super.clickOnMenu("Patron", "List my Patronages");
		super.clickOnButton("Create");
		super.checkFormExists();
	
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("legalStuff", legalStuff);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("finishDate", finishDate);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("inventor.userAccount.username", inventorSelector);
		
		super.clickOnSubmit("Create");
		
		super.checkErrorsExist();
		
		super.signOut();
	}
}
