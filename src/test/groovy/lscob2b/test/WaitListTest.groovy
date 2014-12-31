package lscob2b.test

import org.spockframework.compiler.model.ExpectBlock;

import spock.lang.Stepwise;
import lscob2b.pages.HomePage;
import lscob2b.pages.LoginPage;
import lscob2b.pages.QuickOrder.QuickOrderPage;
import lscob2b.pages.productcategory.ProductCategoryPage;
import lscob2b.pages.productdetails.ProductDetailsPage;
import lscob2b.test.login.LoginFailureTest;
import geb.navigator.Navigator;
import geb.spock.GebReportingSpec;
import static lscob2b.TestConstants.*

/**
 * Test waitlist functionality in both PDP and quick order page.
 * 
 * @author i310850
 *
 */
@Stepwise
public class WaitListTest extends GebReportingSpec {

	def setupSpec() {
		to LoginPage
		login(levisUser)
		at HomePage
	}


	def "Test adding to waitlist"(){

		when: "Open waitlist grid"

		sizingGrid.clickNotifyMeWhenItemsBecomeAvailable()

		then:
		$("div.popup_box div.product-grid-container>div.product-grid-header>h2").text() == "NOTIFY ME"

		when:"input count and add to waitlist"

		$("div.overlay form#AddToWaitListForm input.sku-quantity", 0).value("5")
		sizingGrid.clickAddToWaitList()

		then:
		$("div.popup_box div.product-grid-container>div.product-grid-header>h2").text() == "NOTIFY ME"

		where:
		theseMethodsWillBeRunningAutomaticallyBeforeEachIterationToOpenTheSizingGrid << [
			openSizingGridAtQuickOrderPage('05527-0458'),
			openSizingGridAtProductDetailsPage('05527-0458')
		]
	}

	def openSizingGridAtQuickOrderPage(String productCode){
		masterTemplate.clickQuickOrder()
		at QuickOrderPage
		doSearch(productCode)
	}

	def openSizingGridAtProductDetailsPage(String productCode){
		browser.go(baseUrl + "p/" + productCode)
		at ProductDetailsPage
	}
}
