package lscob2b.test.productdetails;

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

	def "recommended retail price should be displayed"(){

		when: "Log in"
		login (multibrandUser) //goes to HomePage
		then: "We should be at home page"
		at HomePage
		when: "Go to first product category in the navigation menu"
		masterTemplate.categoryNavigation.clickFirstCategoryLink()
		then: "We should be at category page"
		at ProductCategoryPage
		when: "Click first product on the page"
		clickFirstProductLink()
		then: "We should be at product details page"
		at ProductDetailsPage
		recommendedRetailPriceExist()
		wholesalePriceExist()
	}
}
