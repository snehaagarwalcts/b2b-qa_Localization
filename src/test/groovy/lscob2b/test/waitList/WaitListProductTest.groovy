package lscob2b.test.waitList

import lscob2b.data.PageHelper
import lscob2b.data.ProductHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.productdetails.ProductDetailsPage
import lscob2b.pages.waitlist.WaitListPage
import lscob2b.test.data.PropertProviderTest
import lscob2b.test.data.User


class WaitListProductTest extends PropertProviderTest{
	
	def static User user = UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)

	def setupSpec() {
		PageHelper.gotoPageLogout(browser,baseUrl)
		to LoginPage
		at LoginPage
		login(user)
		at HomePage
	}
	
	def "Verify translations - Adding to waitlist from ProductDetail page"() {		
		setup:
		PageHelper.gotoPageProductDetail(browser,baseUrl,productCode)
			
		when: "at product detail page"
		at ProductDetailsPage
				
		then: "add item to waitlist"
		addOutOfStockQuantityToWaitList(1)
		
		and: "Go to WaitListPage"
		masterTemplate.waitListLink.click()
		
		when:"at WaitListPage"
		at WaitListPage
	
		then:"Verify translations of WaitList Page"
		items.size() > 0
		assert itemLabels(0).text()== expectedValue("product.variants.style").toUpperCase()
		assert itemLabels(1).text()== expectedValue("product.variants.color").toUpperCase()
		assert itemLabels(2).text()== expectedValue("product.wholesale.price").toUpperCase()
		assert itemLabels(3).text()== expectedValue("waitlist.quantity.requested").toUpperCase()
		assert itemLabels(4).text()== expectedValue("waitlist.quantity.available").toUpperCase()
		assert items[0].buttonRemove.text()- ~/&/== expectedValue("text.remove").toUpperCase()
		assert items[0].buttonEditQuantities.text()- ~/&/== expectedValue("product.edit.quantities").toUpperCase()

		when: "at WaitListPage"
		at WaitListPage
		
		then: "click on Edit Quantities"
		items.size() > 0
		items[0].buttonEditQuantities.click()
		
		and: "verify translations of Sizing Grid"
		assert items[0].buttonHideQuantities.text()- ~/&/== expectedValue("product.hide.quantities").toUpperCase()
		assert inStockLabel.text()== expectedValue("product.variants.in.stock").toUpperCase()
		assert limitedStockLabel.text()== expectedValue("product.variants.limited.stock").toUpperCase()
		assert outOfStockLabel.text()== expectedValue("product.variants.out.of.stock").toUpperCase()	
		assert items[0].buttonCancel.text()== expectedValue("cancelButton.displayName").toUpperCase()
		assert items[0].buttonUpdate.text()== expectedValue("product.variants.update").toUpperCase()
		
		when: "At WaitList page"
		at WaitListPage
		
		then: "remove product from WaitList"
		items.size() == 1
		items[0].buttonRemove.click()
		at WaitListPage
		
		and: "check product count"		
		waitFor { emptyList.displayed }
		
		where:
		productCode = ProductHelper.getProduct(ProductHelper.BRAND_LEVIS)
	}

}
