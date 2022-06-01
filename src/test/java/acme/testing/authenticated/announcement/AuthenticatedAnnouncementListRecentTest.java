package acme.testing.authenticated.announcement;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TemporalAwareTestHarness;

public class AuthenticatedAnnouncementListRecentTest extends TemporalAwareTestHarness {
		
	//Test cases
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/announcement/list-recent.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void positiveTest(final int recordIndex, final String moment, final String title, final String body, final String status, final String link) {
		super.signIn("administrator", "administrator");
		//Testing list
		super.clickOnMenu("Authenticated", "Recent Announcements");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 1, status);
		super.checkColumnHasValue(recordIndex, 2, title);
		super.checkColumnHasValue(recordIndex, 3, body);
		super.checkColumnHasValue(recordIndex, 4, link);
		
		//Testing show
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("status", status);
		super.checkInputBoxHasValue("body", body);
		super.checkInputBoxHasValue("link", link);
		super.signOut();
	}
	@Test
	@Order(20)
	public void negativeTest() {
		super.navigate("/authenticated/announcement/show", "id=-1");
		super.checkErrorsExist();			
	}
}
