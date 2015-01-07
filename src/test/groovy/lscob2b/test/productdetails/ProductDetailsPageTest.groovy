package lscob2b.test.productdetails;

import org.spockframework.compiler.model.ExpectBlock;

import spock.lang.Stepwise;
import lscob2b.pages.HomePage;
import lscob2b.pages.LoginPage;
import lscob2b.pages.productcategory.ProductCategoryPage;
import lscob2b.pages.productdetails.ProductDetailsPage;
import lscob2b.test.data.Product;
import lscob2b.test.data.TestDataCatalog;
import lscob2b.test.data.User;
import lscob2b.test.login.LoginFailureTest;
import geb.navigator.Navigator;
import geb.spock.GebReportingSpec;

@Stepwise
public class ProductDetailsPageTest extends GebReportingSpec {

	def "wholesale and recommended retail prices should be displayed"(){

		User user = TestDataCatalog.getALevisUser()
		Product product = TestDataCatalog.getAProductAvailableForUser(user)

		setup: "Log in"

		to LoginPage
		login (user)
		at HomePage

		when: "Goto product details page"

		browser.go(baseUrl + "p/" + product.getCode())
		at ProductDetailsPage

		then: "We should be at product details page"

		recommendedRetailPriceExist()
		wholesalePriceExist()
		recommendedRetailPriceValue == product.getPriceForUser(user).retailPrice
		wholesalePriceValue == product.getPriceForUser(user).wholesalePrice
	}
}
