package acme.testing.inventor.chimpum;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.TestHarness;

public class InventorChimpumMiscelaneaTest extends TestHarness{
	
	@Test
	@Order(10)
	public void deleteTest() {
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "Inventor Artifacts");
		super.checkListingExists();
		super.sortListing(0, "desc");
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.clickOnButton("View Chimpums");
		super.checkListingExists();
		super.sortListing(0,"desc");
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.clickOnSubmit("Delete");
	}
	
	@Test
	@Order(20)
	public void hackingTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/inventor/chimpum/create");
		super.checkPanicExists();
		
		super.signIn("administrator", "administrator");
		super.navigate("/inventor/chimpum/create");
		super.checkPanicExists();
		super.signOut();
		
		super.signIn("patron1", "patron1");
		super.navigate("/inventor/chimpum/create");
		super.checkPanicExists();
		super.signOut();
	}
}
