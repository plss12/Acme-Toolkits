package acme.testing.authenticated.inventor;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedBecomeInventorTest extends TestHarness {
	
	@Test
	@Order(20)
	public void negativeTest() {
		super.signIn("inventor1", "inventor1");
		super.navigate("/authenticated/inventor/create");
		super.checkErrorsExist();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/inventor/createInventor.csv", encoding="utf-8",numLinesToSkip=1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String username, final String password, final String name, final String surname, final String email, 
		final String company, final String statement, final String url){
		
		super.signUp(username, password, name, surname, email);
		super.signIn(username, password);
		
		super.clickOnMenu("Account", "Become an inventor");
		super.checkFormExists();
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("statement", statement);
		super.fillInputBoxIn("link", url);
		super.clickOnSubmit("Create");
		
		super.navigate("/inventor/artifact/list");
		super.checkListingExists();
		super.checkListingEmpty();
		
		super.signOut();
	}

}
