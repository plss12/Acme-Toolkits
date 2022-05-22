package acme.testing.administrator.announcement;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorAnnouncementCreateTest extends TestHarness{
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/announcement/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int announcementRecordIndex, final String title, final String body, final String status, final String link, final String confirm) {
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "Create New Announcement");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("body", body);
		super.fillInputBoxIn("status", status);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("confirm", confirm);
		super.clickOnSubmit("Create announcement");
		
		super.clickOnMenu("Authenticated", "Recent Announcements");
		super.sortListing(0, "desc");
		super.checkColumnHasValue(announcementRecordIndex, 1, status);
		
		super.clickOnListingRecord(announcementRecordIndex);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("body", body);
		super.checkInputBoxHasValue("status", status);
		super.checkInputBoxHasValue("link", link);
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/announcement/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(15)
	public void negativeEmptyTest(final int announcementRecordIndex, final String title, final String body, final String status, final String link, final String confirm) {
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "Create New Announcement");
		
		super.clickOnSubmit("Create announcement");
	
		super.checkErrorsExist();
		super.signOut();
	}


	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/announcement/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int announcementRecordIndex, final String title, final String body, final String status, final String link, final String confirm) {
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "Create New Announcement");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("body", body);
		super.fillInputBoxIn("status", status);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("confirm", confirm);
		super.clickOnSubmit("Create announcement");
		super.checkErrorsExist();

		super.signOut();
	}
}
