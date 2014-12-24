package lscob2b.test.QuickOrder

import geb.spock.GebReportingSpec
import lscob2b.TestConstants
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.CheckOut.CheckOutPage;
import lscob2b.pages.OrderConfirmation.OrderConfirmationPage;
import lscob2b.pages.QuickOrder.QuickOrderPage
import lscob2b.pages.cart.CartPage
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
	
	def doRemoveProduct(){
		doRemove()
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
		prdouctIdsOnly == "Product IDs Only"
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
		doSearch('00501-1615')
		addOrderQuantity('10')
		doAddToCart()
		
		and: "go to shopping cart page"
		masterTemplate.doGoToCart()
		at CartPage
		
		then: "Look at cart content"
		
		cartTemplate.itemName == "501 LEVIS ORIGINAL FIT MOODY MONDAY"
		cartTemplate.itemStyle == "STYLE"
		cartTemplate.itemColor == "COLOR"
		cartTemplate.itemPrice == "WHOLESALE PRICE"
		cartTemplate.itemQuantity == "QUANTITY"
		cartTemplate.itemTotal == "TOTAL"
		
		where:
		user << [levisUser]
	}
	
	def "Remove product from checkout Page"(){
		setup:
		loginAsUserAndGoToQuickOrder(user)
		doSearch('00501-1615')
		addOrderQuantity('10')
		doAddToCart()
		doCheckOut()
		
		Thread.sleep(1000);
		
		when: "At Check out page"
		at CheckOutPage
		Thread.sleep(1000);
		
		then: "Remove the product from the page and you should get a message cart is empty"
		doRemoveProduct()
		cartIsEmpty == "Your shopping cart is empty"
		/*waitFor(5){
			$('div.global-nav ul.global-nav-list').find("a", href: contains("/logout"))
		}*/
		
		
		where:
		user << [levisUser]
	}
	
	def "Place an order from Quick Order Page"(){
		setup: 
		loginAsUserAndGoToQuickOrder(user)
		doSearch('00501-1615')
		addOrderQuantity('10')
		doAddToCart()
		
		when: "Checking out from quick order page"
		doCheckOut()
		
		then: "Place an order"
		at CheckOutPage
		doPlaceOrder()
		at OrderConfirmationPage
		
		where:
		user << [levisUser]
	}
}
