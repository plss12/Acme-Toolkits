package acme.testing.any.artifact;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyArtifactTest extends TestHarness{
	//Test cases
		@ParameterizedTest
		@CsvFileSource(resources = "/any/artifact/artifact.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)	
		public void positiveTest(final int recordIndex, final String artifactType, final String name, final String code, final String technology, final String description, final String price, final String link) {
			
			//Testing list
			super.clickOnMenu("Anonymous", "Tools & Components");
			super.checkListingExists();
			super.sortListing(0, "asc");
	
			super.checkColumnHasValue(recordIndex, 0, name);
			super.checkColumnHasValue(recordIndex, 1, price);
			super.checkColumnHasValue(recordIndex, 2, artifactType);
			
			//Testing show
			super.clickOnListingRecord(recordIndex);
			super.checkFormExists();
			super.checkInputBoxHasValue("name", name);
			super.checkInputBoxHasValue("code", code);
			super.checkInputBoxHasValue("technology", technology);
			super.checkInputBoxHasValue("description", description);
			super.checkInputBoxHasValue("retailPrice", price);
			super.checkInputBoxHasValue("artifactType", artifactType);
			super.checkInputBoxHasValue("link", link);
			
		}
		
		
		@Test
		@Order(20)
		public void negativeTest() {
			super.navigate("/any/artifact/show", "id=-1");
			super.checkErrorsExist();			
		}	
	}

