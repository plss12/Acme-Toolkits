package acme.testing.administrator.systemConfiguration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorSystemConfigurationUpdateTest extends TestHarness{
	
	//Test cases
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/system-configuration/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	
	@Order(10)	
	public void positiveTest(final int recordIndex, final String defaultCurrency, final String acceptedCurrencies, 
		final String weakSpamTrheshold, final String strongSpamTrheshold, final String strongSpam, 
		final String weakSpam) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "System Configuration");
		super.checkFormExists();
		
		super.clickOnButton("Edit");
		super.checkFormExists();
		
		//Test on inputs
		super.fillInputBoxIn("acceptedCurrencies", acceptedCurrencies);
		super.fillInputBoxIn("defaultCurrency", defaultCurrency);
		super.fillInputBoxIn("weakSpamTrheshold", weakSpamTrheshold);
		super.fillInputBoxIn("weakSpam", weakSpam);
		super.fillInputBoxIn("strongSpamTrheshold", strongSpamTrheshold);
		super.fillInputBoxIn("strongSpam", strongSpam);
		
		super.clickOnSubmit("Update");
		super.checkNotErrorsExist();
		
		super.checkFormExists();
		super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);
		super.checkInputBoxHasValue("defaultCurrency", defaultCurrency);
		super.checkInputBoxHasValue("weakSpamTrheshold", weakSpamTrheshold);
		super.checkInputBoxHasValue("weakSpam", weakSpam);
		super.checkInputBoxHasValue("strongSpamTrheshold", strongSpamTrheshold);
		super.checkInputBoxHasValue("strongSpam", strongSpam);
		
		super.signOut();
		
	}
		

	

}
