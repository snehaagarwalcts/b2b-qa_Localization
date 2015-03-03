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

	/**
	 * TC BB-552 Automated test: User should be able to add products to waitlist from QuickOrder
	 */
	@IgnoreIf({System.getProperty("geb.browser") == "chrome" || System.getProperty("geb.browser") == "internet explorer"})
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
			def int updateWL = masterTemplate.waitListItemCount.text().toInteger()
		
		then: "check waitlist count"
			updateWL == (currentWL+1)

		where:
			productCode 	| _
			ProductHelper.getProduct(ProductHelper.BRAND_LEVIS)	| _
	}

	/**
	 * TC BB-552 Automated test: User should be able to add products to waitlist from QuickOrder page and ProductDetail page.
	 */
	@IgnoreIf({System.getProperty("geb.browser") == "chrome" || System.getProperty("geb.browser") == "internet explorer"})
	def "Adding to waitlist from ProductDetail page"() {
		setup:
			PageHelper.gotoPageProductDetail(browser,baseUrl,productCode)
			
		when: "at product detail page"
			at ProductDetailsPage
		
		and: "get current waitlist item count"
			def int currentWL = masterTemplate.waitListItemCount.text().toInteger()
		
		and: "add item to waitlist"
			addOutOfStockQuantityToWaitList(1)//Bug raised BB-876
			
		and: "get updated waitlist item count"
			def int updateWL = masterTemplate.waitListItemCount.text().toInteger()
		
		then: "check waitlist count"
			updateWL == (currentWL+1)

		where:
			productCode 	| _
			ProductHelper.getProduct(ProductHelper.BRAND_LEVIS)	| _
			
	}

	/**
	 * TC BB-556 Automated test: User should be able to edit product quantity from wait list
	 */
	@IgnoreIf({System.getProperty("geb.browser") == "chrome" || System.getProperty("geb.browser") == "internet explorer"})
	def "Edit quantities of product in WaitList page"() {

		setup: "Go to waitlist page"
			masterTemplate.waitListLink.click()
		
		when: "At WaitList page"
			at WaitListPage
			
		then: "Check product in WaitList"
			items.size() > 0
			
		when: "Get current product quantiy"
			def int currentQuantity = quantityRequested.text().toInteger() 
		
		and: "Update OutOfStock Quantity" 
			items[0].editOutOfStockQuantity(1)
			
		and: "Get updated product quantity"
			def int updatedQuantity = quantityRequested.text().toInteger()
			
		then:
			updatedQuantity == (currentQuantity+1)  

	}

	/**
	 * BB-511 Automated test: User should be able to remove product from wait list
	 */
	@IgnoreIf({System.getProperty("geb.browser") == "chrome" || System.getProperty("geb.browser") == "internet explorer"})
	def "Remove product from WaitList page"() {
		when: "At WaitList page"
			at WaitListPage
			
		then: "get current product count"
			items.size() == 1	
			
		when: "Remove product from waitlist"
			items[0].buttonRemove.click()
		
		and: "at WaitList page"
			at WaitListPage
				
		then: "check product count"
			waitFor { emptyList.displayed }
				
	}

}
