package lscob2b.test.waitlist

import geb.spock.GebReportingSpec
import lscob2b.data.PageHelper
import lscob2b.data.ProductHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.productdetails.ProductDetailsPage
import lscob2b.pages.quickorder.QuickOrderPage
import lscob2b.pages.waitlist.WaitListPage
import lscob2b.test.data.User
import spock.lang.IgnoreIf
import spock.lang.Stepwise

@IgnoreIf({System.getProperty("geb.env") == "integration001"})
@Stepwise
public class WaitListTest extends GebReportingSpec{

	def static User user = UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_CUSTOMER)

	def setupSpec() {
		PageHelper.gotoPageLogout(browser,baseUrl)
		to LoginPage

		at LoginPage
		login(user)

		at HomePage
	}

	def loginAndGoToPage(user) {
		login(user)

		at HomePage
		masterTemplate.waitListLink.click()
	}

	
	//@IgnoreIf({System.getProperty("geb.browser") == "chrome" || System.getProperty("geb.browser") == "internet explorer"})
	def "Adding to WaitList from QuickOrder page"() {
		setup:
			PageHelper.gotoPage(browser, baseUrl, PageHelper.PAGE_QUICKORDER)

		when: "At QuickOrder page"
			at QuickOrderPage

		and: "Search for product by id"
			doSearch(productCode,true)

		then: "at QuickOrder page"
			at QuickOrderPage

		and: "check unique result"
			checkResultSize(1)

		when: "at QuickOrder page"
			at QuickOrderPage
			
		and: "get current waitlist item count"
			waitFor { masterTemplate.waitListItemCount.displayed }
			def int currentWL = masterTemplate.waitListItemCount.text().toInteger()
		
		and: "add item to waitlist"
			addOutOfStockQuantityToWaitList(0,1)
			
		and: "get updated waitlist item count"
			waitFor { masterTemplate.waitListItemCount.displayed }						
			masterTemplate.waitForSometime()			
			def int updateWL = masterTemplate.waitListItemCount.text().toInteger()
		
		then: "check waitlist count"
			updateWL == (currentWL+1)

		where:
			productCode 	| _
			ProductHelper.getProduct(ProductHelper.BRAND_LEVIS)	| _
	}

	
}
