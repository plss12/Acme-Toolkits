package acme.testing.authenticated.systemConfiguration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedSystemConfigurationShowTest extends TestHarness{
	
	//Test cases
	
		@ParameterizedTest
		@CsvFileSource(resources = "/authenticated/system-configuration/show.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)	
		public void positiveTest(final int recordIndex, final String defaultCurrency, final String acceptedCurrencies) {
			
			super.signIn("administrator", "administrator");
			
			super.clickOnMenu("Authenticated", "System Configuration");
			super.checkFormExists();
			super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);
			super.checkInputBoxHasValue("defaultCurrency", defaultCurrency);
			super.checkButtonExists("Money Exchange");
			super.clickOnButton("Money Exchange");
			super.checkFormExists();
			
			super.signOut();
		}

}
