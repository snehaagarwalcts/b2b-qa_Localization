package lscob2b.test.productdetails;

import org.spockframework.compiler.model.ExpectBlock;

import spock.lang.Stepwise;
import lscob2b.pages.HomePage;
import lscob2b.pages.LoginPage;
import lscob2b.pages.productcategory.ProductCategoryPage;
import lscob2b.pages.productdetails.ProductDetailsPage;
import lscob2b.test.login.LoginFailureTest;
import geb.navigator.Navigator;
import geb.spock.GebReportingSpec;
import static lscob2b.TestConstants.*

@Stepwise
public class ProductDetailsPageTest extends GebReportingSpec {

	def "wholesale and recommended retail prices should be displayed"(){

		setup: "Log in"
		to LoginPage
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
		'05527-0458'		|	80.5			| 70.75
	}
	
	def "Wait list should be working"(){
		when: "test 'size grid'"
		
		clickNotifyMeWhenItemsBecomeAvailable()
		
		then:
		$("div.popup_box div.product-grid-container>div.product-grid-header>h2").text() == "NOTIFY ME"

		when:"input order count and add to wait list"
		//Navigator navigator = $("body")
		//TODO cannot select using an id so as a workaround solution we are selecting using the name !! waiting for BB-480
		$("table.grid_three_dimensions input", 1, name: "05527045803430.quantity").value("5")  
		clickAddToWaitList()
		
		then:
		$("div.popup_box div.product-grid-container>div.product-grid-header>h2").text() == "NOTIFY ME"
	}
	
}
