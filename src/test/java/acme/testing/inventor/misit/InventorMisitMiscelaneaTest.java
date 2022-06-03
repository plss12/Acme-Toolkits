package acme.testing.inventor.misit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.TestHarness;

public class InventorMisitMiscelaneaTest extends TestHarness{
	
	@Test
	@Order(10)
	public void deleteTest() {
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "Inventor Artifacts");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.clickOnButton("View Misits");
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
		super.navigate("/inventor/misit/create");
		super.checkPanicExists();
		
		super.signIn("administrator", "administrator");
		super.navigate("/inventor/misit/create");
		super.checkPanicExists();
		super.signOut();
		
		super.signIn("patron1", "patron1");
		super.navigate("/inventor/misit/create");
		super.checkPanicExists();
		super.signOut();
	}
}
