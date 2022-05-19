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
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/system-configuration/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void negativeTest(final int recordIndex, final String defaultCurrency, final String acceptedCurrencies, 
		final String weakSpamTrheshold, final String strongSpamTrheshold, final String strongSpam, 
		final String weakSpam) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "System Configuration");
		super.checkFormExists();
		
		super.clickOnButton("Edit");
		super.checkFormExists();
		
		final String correctAcceptedCurrencies = "EUR,USD";
		final String correctDefaultCurrency = "US";
		final String correctWeakSpamTrheshold = "20";
		
		//Test 1 (Accepted Currencies)
		super.fillInputBoxIn("acceptedCurrencies", acceptedCurrencies);
		super.clickOnSubmit("Update");
		super.checkErrorsExist();
		
		//Test 2 (Default Currency)
		super.fillInputBoxIn("acceptedCurrencies", correctAcceptedCurrencies);
		super.fillInputBoxIn("defaultCurrency", defaultCurrency);
		super.clickOnSubmit("Update");
		super.checkErrorsExist();
		
		//Test 3 (Default Currency)
		super.fillInputBoxIn("acceptedCurrencies", correctAcceptedCurrencies);
		super.fillInputBoxIn("defaultCurrency", "");
		super.clickOnSubmit("Update");
		super.checkErrorsExist();
		
		//Test 4 (Weak Spam Trheshold)
		super.fillInputBoxIn("acceptedCurrencies", correctAcceptedCurrencies);
		super.fillInputBoxIn("defaultCurrency", correctDefaultCurrency);
		super.fillInputBoxIn("weakSpamTrheshold", weakSpamTrheshold);
		super.clickOnSubmit("Update");
		super.checkErrorsExist();
		
		//Test 5 (Weak Spam Trheshold)
		super.fillInputBoxIn("acceptedCurrencies", correctAcceptedCurrencies);
		super.fillInputBoxIn("defaultCurrency", correctDefaultCurrency);
		super.fillInputBoxIn("weakSpamTrheshold", "");
		super.clickOnSubmit("Update");
		super.checkErrorsExist();
		
		//Test 6 (Weak Spam Trheshold)
		super.fillInputBoxIn("acceptedCurrencies", correctAcceptedCurrencies);
		super.fillInputBoxIn("defaultCurrency", correctDefaultCurrency);
		super.fillInputBoxIn("weakSpamTrheshold", "0");
		super.clickOnSubmit("Update");
		super.checkErrorsExist();
		
		//Test 7 (Strong Spam Trheshold)
		super.fillInputBoxIn("acceptedCurrencies", correctAcceptedCurrencies);
		super.fillInputBoxIn("defaultCurrency", correctDefaultCurrency);
		super.fillInputBoxIn("weakSpamTrheshold", correctWeakSpamTrheshold);
		super.fillInputBoxIn("strongSpamTrheshold", strongSpamTrheshold);
		super.clickOnSubmit("Update");
		super.checkErrorsExist();
		
		//Test 8 (Strong Spam Trheshold)
		super.fillInputBoxIn("acceptedCurrencies", correctAcceptedCurrencies);
		super.fillInputBoxIn("defaultCurrency", correctDefaultCurrency);
		super.fillInputBoxIn("weakSpamTrheshold", correctWeakSpamTrheshold);
		super.fillInputBoxIn("strongSpamTrheshold", "");
		super.clickOnSubmit("Update");
		super.checkErrorsExist();
		
		//Test 9 (Strong Spam Trheshold)
		super.fillInputBoxIn("acceptedCurrencies", correctAcceptedCurrencies);
		super.fillInputBoxIn("defaultCurrency", correctDefaultCurrency);
		super.fillInputBoxIn("weakSpamTrheshold", correctWeakSpamTrheshold);
		super.fillInputBoxIn("strongSpamTrheshold", "0");
		super.clickOnSubmit("Update");
		super.checkErrorsExist();
		
		super.signOut();
		
	}
	
		

	

}
