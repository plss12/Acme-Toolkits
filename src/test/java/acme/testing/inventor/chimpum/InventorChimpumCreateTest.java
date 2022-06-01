package acme.testing.inventor.chimpum;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorChimpumCreateTest extends TestHarness {
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/chimpum/chimpumCreatePositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int artifactIndex, final int chimpumIndex, final String title, final String code, 
		final String description, final String budget, final String link,
		final String startDate, final String finishDate) {
		
		String validCode;
		Date date;
		SimpleDateFormat formatter;
		String format;
		
		date = new Date();
		formatter = new SimpleDateFormat("yyyy/MM/dd");
		format = formatter.format(date);
		
		validCode=code+"-"+format;
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor","Inventor Artifacts");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(artifactIndex);
		
		super.clickOnButton("Chimpums");	
		super.clickOnButton("Create");

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("code", validCode);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("finishDate", finishDate);
		super.clickOnSubmit("Create");
		
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(chimpumIndex, 0, title);
		super.checkColumnHasValue(chimpumIndex, 2, budget);
				
		super.clickOnListingRecord(chimpumIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("code", validCode);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("finishDate", finishDate);
		super.checkInputBoxHasValue("budget", budget);

		super.signOut();			
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/chimpum/chimpumCreateNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int artifactIndex, final String title, final String code, 
		final String description, final String budget, final String link, 
		final String startDate, final String finishDate) {
		
		String validCode;
		Date date;
		SimpleDateFormat formatter;
		String format;
		
		date = new Date();
		formatter = new SimpleDateFormat("yyyy/MM/dd");
		format = formatter.format(date);
		
		validCode=code+"-"+format;
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor","Inventor Artifacts");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(artifactIndex);
		
		super.clickOnButton("Chimpums");		
		super.clickOnButton("Create");

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("code", validCode);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("finishDate", finishDate);
		super.clickOnSubmit("Create");
		super.checkErrorsExist();

		super.signOut();
	}
	
}
