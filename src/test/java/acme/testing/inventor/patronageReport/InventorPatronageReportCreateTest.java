package acme.testing.inventor.patronageReport;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageReportCreateTest extends TestHarness{


	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage-report/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int patronageRecordIndex, final int patronageReportRecordIndex, final String link, final String memorandum) {
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "Inventor Patronages");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(patronageRecordIndex);
		super.clickOnButton("Patronage Reports");

		super.clickOnButton("Create");
		super.fillInputBoxIn("memorandum", memorandum);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("confirm", "true");
		super.clickOnSubmit("Create");

		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(patronageReportRecordIndex, 1, memorandum);

		super.clickOnListingRecord(patronageReportRecordIndex);
		super.checkInputBoxHasValue("memorandum", memorandum);
		super.checkInputBoxHasValue("link", link);

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage-report/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int patronageRecordIndex, final int patronageReportRecordIndex, final String link, final String memorandum, final String confirm) {
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "Inventor Patronages");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(patronageRecordIndex);
		super.clickOnButton("Patronage Reports");

		super.clickOnButton("Create");
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("memorandum", memorandum);
		super.fillInputBoxIn("confirm", confirm);
		super.clickOnSubmit("Create");
		super.checkErrorsExist();

		super.signOut();
	}
}
