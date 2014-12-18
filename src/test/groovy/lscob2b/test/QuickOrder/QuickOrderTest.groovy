package lscob2b.test.QuickOrder

import geb.spock.GebReportingSpec
import lscob2b.TestConstants
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.QuickOrder.QuickOrderPage
import spock.lang.Stepwise
import static lscob2b.TestConstants.*

//@Stepwise
class QuickOrderTest extends GebReportingSpec {

	def setup() {
		to LoginPage
	}

	def cleanup() {
		masterTemplate.doLogout()
	}
	
	def login(String username) {
		doLogin(username, defaultPassword)
	}

	def loginAsUserAndGoToQuickOrder(String user) {
		login(user)
		at HomePage
		masterTemplate.clickQuickOrder()
		at QuickOrderPage
	}
	
	def "Quick Order link is accessible"() {

		when: "logged in as any user"

		login(user)

		then: "Quick Order link is available"
		at HomePage
		masterTemplate.clickQuickOrder()
		at QuickOrderPage

		where:

		user << [levisUser]    // TODO change for user roles
	}
	
	def "Check the Quick Order page content"(){
		
		when: "Logging in and going to Quick Order page"
		loginAsUserAndGoToQuickOrder(user)
		
		then: "Correct sections/links should be visible"
		keywordSearch == "KEYWORD SEARCH"
		searchButton == "SEARCH"
		prdouctIdsOnly == "PRODUCT IDS ONLY"
		quantity == "Quantity"
		total == "Total "
		cartButtons == "CONTINUE SHOPPING&"
		checkOut == "CHECKOUT&"
		
		where:
		user << [levisUser]
	}
	
	def "Add to cart from Quick Order Page"(){
		when: "Logging in and going to Quick Order page"
		loginAsUserAndGoToQuickOrder(user)
		
		then: "Add to Cart"
		doSearch('005011615')
		//Need to add more here
		
		addOrderQuantity('10')
		
		doAddToCart
		
		doCheckOut()
		
		where:
		user << [levisUser]
	}
	
	/*def "Place an order from Quick Order Page"(){
		when: "Logging in and going to Quick Order page"
		loginAsUserAndGoToQuickOrder(user)
		
		then: "Place an order"
		doSearch('005011615')
		//Need to add more here
		
		orderQuantity('10')
		
		doAddToCart
		
		doCheckOut()
		
		where:
		user << [levisUser]
	}*/
}
