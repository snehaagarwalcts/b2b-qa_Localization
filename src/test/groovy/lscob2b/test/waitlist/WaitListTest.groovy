package lscob2b.test.waitlist

import geb.spock.GebReportingSpec
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.QuickOrder.QuickOrderPage
import lscob2b.pages.productdetails.ProductDetailsPage
import lscob2b.pages.waitlist.WaitListPage
import lscob2b.test.data.TestDataCatalog
import spock.lang.Ignore
import spock.lang.IgnoreRest;
import spock.lang.Stepwise


public class WaitListTest extends GebReportingSpec {
	
	def setup() {
		to LoginPage
	}

	def cleanup() {
		masterTemplate.doLogout()
	}
	
	def loginAndGoToPage(user) {
		login(user)
		
		at HomePage
		masterTemplate.waitListLink.click()
	}

	def "Test WaitList link"() {
		setup:
			login(user)
		
		when: "At homepage"
			at HomePage
			
		then: "Check waitlist link"
			!masterTemplate.waitListLink.empty
				
		and: "Click on link"
			masterTemplate.waitListLink.click()
			at WaitListPage
			
		where:
		user << [TestDataCatalog.getALevisUser(), TestDataCatalog.getADockersUser()]
		
	}
	
	def "Adding to waitlist from QuickOrder page"() {
		setup:
			loginAndGoToPage(user)
			
		when: "At WaitList page"
			at WaitListPage	
		
		then: "Check current quantity of product"
			int currentQuantity = getProductQuantityRequested(productCode)
		
		and: "Open waitlist grid at QuickOrderPage"
			openSizingGridAtQuickOrderPage(productCode)	
			sizingGrid.clickNotifyMeWhenItemsBecomeAvailable()
			
		and: "Add item to waitlist"
			sizingGrid.checkIfWaitListGridPoppedUp()
			sizingGrid.addQuantityToFirstPossibleItemInWaitListGrid(1)
			sizingGrid.clickAddToWaitList()
		
		and: "Go to waitlist page"
			masterTemplate.waitListLink.click()
			
		when: "At WaitList page"
			at WaitListPage
			
		then: "Check updated quantity of product"
			getProductQuantityRequested(productCode) == (currentQuantity+1)
		
		where:
			productCode 	| user
			"05527-0458"	| TestDataCatalog.getALevisUser()
//			"05527-0458"	| TestDataCatalog.getADockersUser()
	}
	
	def "Adding to waitlist from ProductDetail page"() {
		setup:
			loginAndGoToPage(user)
			
		when: "At WaitList page"
			at WaitListPage
		
		then: "Check current quantity of product"
			int currentQuantity = getProductQuantityRequested(productCode)
		
		and: "Open waitlist grid at ProductDetail"
			openSizingGridAtProductDetailsPage(productCode)
			sizingGrid.clickNotifyMeWhenItemsBecomeAvailable()
			
		and: "Add item to waitlist"
			sizingGrid.checkIfWaitListGridPoppedUp()
			sizingGrid.addQuantityToFirstPossibleItemInWaitListGrid(1)
			sizingGrid.clickAddToWaitList()
		
		and: "Go to waitlist page"
			masterTemplate.waitListLink.click()
			
		when: "At WaitList page"
			at WaitListPage
			
		then: "Check updated quantity of product"
			getProductQuantityRequested(productCode) == (currentQuantity+1)
		
		where:
			productCode 	| user
			"05527-0458"	| TestDataCatalog.getALevisUser()
//			"05527-0458"	| TestDataCatalog.getADockersUser()
	}
	
	def "Edit quantities of product in WaitList page"() {
		setup:
			login(user)
			
		when: "At HomePage"
			at HomePage
			
		then: "Add product to waitlist"
			openSizingGridAtProductDetailsPage(productCode)
			sizingGrid.clickNotifyMeWhenItemsBecomeAvailable()
			sizingGrid.checkIfWaitListGridPoppedUp()
			sizingGrid.addQuantityToFirstPossibleItemInWaitListGrid(1)
			sizingGrid.clickAddToWaitList()
			
		and: "Go to waitlist page"
			masterTemplate.waitListLink.click()
			
		when: "At WaitList page"
			at WaitListPage
		
		then: "Check product quantity"
			int currentQuantity = getProductQuantityRequested(productCode)
			currentQuantity > 0
			
		and: "Edit product quantity"
			editProductQuantityRequested(productCode,currentQuantity+1)
			
		and: "Check edited product quantity"
			getProductQuantityRequested(productCode) == (currentQuantity+1)
			
		where:
			productCode 	| user
			"05527-0458"	| TestDataCatalog.getALevisUser()
//			"05527-0458"	| TestDataCatalog.getADockersUser()
	}
	
	def "Remove product from WaitList page"() {
		setup:
			login(user)
			
		when: "At HomePage"
			at HomePage
			
		then: "Add product to waitlist"
			openSizingGridAtProductDetailsPage(productCode)
			sizingGrid.clickNotifyMeWhenItemsBecomeAvailable()
			sizingGrid.checkIfWaitListGridPoppedUp()
			sizingGrid.addQuantityToFirstPossibleItemInWaitListGrid(1)
			sizingGrid.clickAddToWaitList()
			
		and: "Go to waitlist page"
			masterTemplate.waitListLink.click()
			
		when: "At WaitList page"
			at WaitListPage
		
		then: "Check product quantity"
			getProductQuantityRequested(productCode) > 0
			
		and: "Remove product from waitlist"
			removeProduct(productCode)
			
		when: "At WaitList page"
			at WaitListPage
			
		then: "Check absence of product"
			getProductQuantityRequested(productCode) == 0
						
		where:
			productCode 	| user
			"05527-0458"	| TestDataCatalog.getALevisUser()
//			"05527-0458"	| TestDataCatalog.getADockersUser()
	}
	
	//FIXME create a page helper
	def openSizingGridAtQuickOrderPage(String productCode){
		browser.go(baseUrl + "search/advanced")
		at QuickOrderPage
		doSearch(productCode)
	}
	
	//FIXME create a page helper
	def openSizingGridAtProductDetailsPage(String productCode){
		browser.go(baseUrl + "p/" + productCode)
		at ProductDetailsPage
	}
}
