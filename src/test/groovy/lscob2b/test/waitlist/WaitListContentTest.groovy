package lscob2b.test.waitlist

import geb.spock.GebReportingSpec
import lscob2b.data.PageHelper
import lscob2b.data.ProductHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.quickorder.QuickOrderPage
import lscob2b.pages.waitlist.WaitListPage
import lscob2b.test.data.User
import spock.lang.IgnoreIf
import spock.lang.Stepwise
import de.hybris.geb.page.hac.console.ImpexImportPage

/**
 * TC BB-629 Automated test case: BB-497 Order from wait list
 */
@Stepwise
class WaitListContentTest extends GebReportingSpec{

	def static User user = UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_CUSTOMER)
	
	def static String productCode = ProductHelper.getWaitlistProduct(ProductHelper.BRAND_LEVIS)
	
	def setupSpec() {
		PageHelper.gotoPageLogout(browser,baseUrl)
		
		to LoginPage
		login(user)
		
		at HomePage
	}
	
	@IgnoreIf({System.getProperty("geb.browser") == "ie8"})
	def "Load out of Stock by impex [OutOfStock.impex]"() {
		setup:
			browser.go(browser.config.rawConfig.hacUrl)
			at de.hybris.geb.page.hac.LoginPage
		
			doLogin(browser.config.rawConfig.hacUsername, browser.config.rawConfig.hacPassword)
			at de.hybris.geb.page.hac.HomePage
			
		when: "at HAC home page"
			at de.hybris.geb.page.hac.HomePage
			
		and: "go to Console>ImpexImport page"
			browser.go(browser.config.rawConfig.hacUrl + "console/impex/import")
		
		and: "at ImpexImport page"
			at ImpexImportPage
		
		and: "load impex in HAC"
			importTextScript(getClass().getResource('/impex/OutOfStock.impex').text)
			
		then: "check import result"
			checkNotification()
			
		cleanup: "logout"
			browser.go(browser.config.rawConfig.hacUrl)
			at de.hybris.geb.page.hac.HomePage
			menu.logout.click()
	}

	@IgnoreIf({System.getProperty("geb.browser") == "ie8"})
	def "Add to WaitList a product from quickorder page"() {
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
			def int currentWL = masterTemplate.waitListItemCount.text().toInteger()
	
		and: "add item to waitlist"
			addOutOfStockQuantityToWaitList(0,1)
		
		and: "get updated waitlist item count"
			def int updateWL = masterTemplate.waitListItemCount.text().toInteger()
		
		then: "check waitlist count"
			updateWL == (currentWL+1)
	}
	
	@IgnoreIf({System.getProperty("geb.browser") == "ie8"})
	def "Check [OutOfStock] Requested Quantity / Available Quantity"() {
		setup:
			to WaitListPage
		
		when: "at wait list page"
			at WaitListPage
			
		then: "check requested quantity"
			quantityRequested.text().toInteger() == 1
			
		and: "check available quantity"
			quantityAvailable.text().toInteger() == 0
	}
	
	@IgnoreIf({System.getProperty("geb.browser") == "ie8"})
	def "Update Stock status by impex [UpdateInStock.impex]"() {
		setup:
			browser.go(browser.config.rawConfig.hacUrl)
			at de.hybris.geb.page.hac.LoginPage
		
			doLogin(browser.config.rawConfig.hacUsername, browser.config.rawConfig.hacPassword)
			at de.hybris.geb.page.hac.HomePage
			
		when: "at HAC home page"
			at de.hybris.geb.page.hac.HomePage
			
		and: "go to Console>ImpexImport page"
			browser.go(browser.config.rawConfig.hacUrl + "console/impex/import")
		
		and: "at ImpexImport page"
			at ImpexImportPage
		
		and: "load impex in HAC"
			importTextScript(getClass().getResource('/impex/UpdateInStock.impex').text)
			
		then: "check import result"
			checkNotification()
			
		cleanup: "logout"
			browser.go(browser.config.rawConfig.hacUrl)
			at de.hybris.geb.page.hac.HomePage
			menu.logout.click()
	}
	
	@IgnoreIf({System.getProperty("geb.browser") == "ie8"})
	//TODO check the impex and update availability!!!
	def "Check [InStock] Requested Quantity / Available Quantity"() {
		setup:
			to WaitListPage
		
		when: "at wait list page"
			at WaitListPage
			
		then: "check requested quantity"
			quantityRequested.text().toInteger() == 1
			
		and: "check available quantity"
			quantityAvailable.text().toInteger() == 1
	}
	
	@IgnoreIf({System.getProperty("geb.browser") == "ie8"})
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
