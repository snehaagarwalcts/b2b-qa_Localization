package lscob2b.test.productdetails;

import org.spockframework.compiler.model.ExpectBlock;

import lscob2b.pages.HomePage;
import lscob2b.pages.LoginPage;
import lscob2b.pages.productcategory.ProductCategoryPage;
import lscob2b.pages.productdetails.ProductDetailsPage;
import lscob2b.test.login.LoginFailureTest;
import geb.spock.GebReportingSpec;
import static lscob2b.TestConstants.*

public class ProductDetailsPageTest extends GebReportingSpec {

	def setup() {
		to LoginPage
	}

	def "wholesale and recommended retail prices should be displayed"(){

		setup: "Log in"

		login (levisUser)
		at HomePage

		when: "At product details page"

		browser.go(baseUrl + "p/" + productCode)
		at ProductDetailsPage

		then: "We should be at product details page"

		recommendedRetailPriceExist()
		wholesalePriceExist()

		recommendedRetailPriceValue == retailPrice
		wholesalePriceValue == wholesalePrice

		where:

		productCode		|	retailPrice		| wholesalePrice
		'005010089'		|	100.75			| 90.75
	}
}
