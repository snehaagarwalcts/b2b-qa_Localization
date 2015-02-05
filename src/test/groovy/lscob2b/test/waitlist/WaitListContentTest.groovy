package lscob2b.test.waitlist

import static lscob2b.TestConstants.*
import geb.spock.GebReportingSpec
import lscob2b.pages.LoginPage
import lscob2b.pages.productdetails.ProductDetailsPage
import lscob2b.pages.waitlist.WaitListPage
import lscob2b.test.data.TestHelper
import spock.lang.Ignore
import de.hybris.geb.page.hac.HomePage
import de.hybris.geb.page.hac.console.ImpexImportPage

class WaitListContentTest extends GebReportingSpec{

	def setupSpec() {
		browser.go(baseUrl + TestHelper.PAGE_LOGOUT)
	}

	def setup() {
		to lscob2b.pages.LoginPage
	}

	def loginAndGoToPage(user) {
		login(user)

		at lscob2b.pages.HomePage
		masterTemplate.waitListLink.click()
	}

	/*def cleanup() {
	 masterTemplate.doLogout()
	 }*/

	@Ignore //FIXME IE Proble
	def "Load Out Of Stock impex"(){
		when: "go to HAC login"
		browser.go(baseUrl +"../")

		then: "At HAC login"
		at de.hybris.geb.page.hac.LoginPage

		when: "at do login"
		doLogin("admin", "nimda")
		at de.hybris.geb.page.hac.HomePage
		browser.go(baseUrl +"../"+"console/impex/import")

		then:"At impex import page and import the impex"
		at ImpexImportPage
		importScript(this.getClass().getResource('/impex/OutOfStock.impex').toString())
		checkNotification()
	}

	@Ignore //FIXME IE Proble
	def "Adding to waitlist from Product Details page"() {
		setup:
			loginAndGoToPage(levisUser)
			
			when: "At WaitList page"
			at WaitListPage
		
		then: "Check current quantity of product"
			int currentQuantity = getProductQuantityRequested(productCode)
		
		and: "Open waitlist grid at ProductDetail"
			openSizingGridAtProductDetailsPage(productCode)
			sizingGrid.clickNotifyMe()
			
		and: "Add item to waitlist"
			sizingGrid.addQuantityToFirstPossibleItemInWaitListGrid(1)
			sizingGrid.clickAddToWaitList()
		
		and: "Go to waitlist page"
			masterTemplate.waitListLink.click()
			
		when: "At WaitList page"
			at WaitListPage
			
		then: "Check updated quantity of product"
			getProductQuantityRequested(productCode) == (currentQuantity+1)

		where:
		productCode <<["05527-0458"]
	}
	
	@Ignore //FIXME IE Proble
	def "Load Update In Stock impex"(){
		when: "go to HAC login"
		browser.go(baseUrl +"../")

		then: "At HAC login"
		at de.hybris.geb.page.hac.LoginPage

		when: "at do login"
		doLogin("admin", "nimda")
		browser.go(baseUrl +"../"+"console/impex/import")

		then:"At impex import page and import the impex"
		at ImpexImportPage
		importScript(this.getClass().getResource('/impex/UpdateInStock.impex').toString())
		checkNotification()
	}
	
	@Ignore //FIXME IE Proble
	def "Go to Waitlist and check for requested and available quantity"(){
		setup:
			loginAndGoToPage(levisUser)
			
		when: "At WaitList page"
			at WaitListPage
		
		then: "Check current quantity of product"
			int currentQuantity = getProductQuantityRequested(productCode)
			
			
		and: "Check requested quantity = available"
			getProductQuantityAvailable(productCode) == (currentQuantity)
	}
	
	//FIXME create a page helper
		def openSizingGridAtProductDetailsPage(String productCode){
		browser.go(baseUrl + "p/" + productCode)
		at ProductDetailsPage
	}
}
