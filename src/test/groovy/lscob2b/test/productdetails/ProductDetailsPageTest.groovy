package lscob2b.test.productdetails;

import org.spockframework.compiler.model.ExpectBlock;

import spock.lang.Stepwise;
import lscob2b.pages.HomePage;
import lscob2b.pages.LoginPage;
import lscob2b.pages.productcategory.ProductCategoryPage;
import lscob2b.pages.productdetails.ProductDetailsPage;
import lscob2b.test.data.TestDataCatalog;
import lscob2b.test.login.LoginFailureTest;
import geb.navigator.Navigator;
import geb.spock.GebReportingSpec;

@Stepwise
public class ProductDetailsPageTest extends GebReportingSpec {

	def "wholesale and recommended retail prices should be displayed"(){

		setup: "Log in"
		to LoginPage
		login (TestDataCatalog.getALevisUser())
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
		'05527-0458'		|	80.5			| 70.75
	}

}
