package lscob2b.test

import org.spockframework.compiler.model.ExpectBlock;

import spock.lang.Stepwise;
import lscob2b.pages.HomePage;
import lscob2b.pages.LoginPage;
import lscob2b.pages.QuickOrder.QuickOrderPage;
import lscob2b.pages.productcategory.ProductCategoryPage;
import lscob2b.pages.productdetails.ProductDetailsPage;
import lscob2b.pages.waitlist.WaitListPage;
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

		when: "Goto page under test which contains sizing grid"

		"${openPageMethod}"(productCode)

		then: "should be at the page containing sizing grid"

		when: "Open waitlist grid"

		sizingGrid.clickNotifyMeWhenItemsBecomeAvailable()

		then: " waitlist grid should be popped up"
		sizingGrid.checkIfWaitListGridPoppedUp()

		when:"input count and add to waitlist"

		sizingGrid.addQuantityToFirstPossibleItemInWaitListGrid(5)
		sizingGrid.clickAddToWaitList()

		then: "items should be added to waitlist. lets go check them"

		when: "Check waitlist"
		to WaitListPage

		then: "Our product should be there"
		!getWaitingProductLink("05527-0458").empty
		getQuantityRequested("05527-0458").toInteger() > 0

		where:
		openPageMethod | productCode
		"openSizingGridAtQuickOrderPage" | "05527-0458"
		"openSizingGridAtProductDetailsPage" | "05527-0458"
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
