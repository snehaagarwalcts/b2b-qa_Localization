package lscob2b.test.waitList

import lscob2b.data.PageHelper
import lscob2b.data.ProductHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.productdetails.ProductDetailsPage
import lscob2b.pages.waitlist.WaitListPage
import lscob2b.test.data.PropertProvider
import lscob2b.test.data.User


class WaitListProductTest extends PropertProvider{
	
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
		verifyTrue(itemLabels(0).text(), expectedValue("product.variants.style").toUpperCase())
		verifyTrue(itemLabels(1).text(), expectedValue("product.variants.color").toUpperCase())
		verifyTrue(itemLabels(2).text(), expectedValue("product.wholesale.price").toUpperCase())
		verifyTrue(itemLabels(3).text(), expectedValue("waitlist.quantity.requested").toUpperCase())
		verifyTrue(itemLabels(4).text(), expectedValue("waitlist.quantity.available").toUpperCase())
		verifyTrue(items[0].buttonRemove.text()- ~/&/, expectedValue("text.remove").toUpperCase())
		verifyTrue(items[0].buttonEditQuantities.text()- ~/&/, expectedValue("product.edit.quantities").toUpperCase())

		when: "at WaitListPage"
		at WaitListPage
		
		then: "click on Edit Quantities"
		items.size() > 0
		items[0].buttonEditQuantities.click()
		
		and: "verify translations of Sizing Grid"
		verifyTrue(items[0].buttonHideQuantities.text()- ~/&/, expectedValue("product.hide.quantities").toUpperCase())
		verifyTrue(inStockLabel.text(), expectedValue("product.variants.in.stock").toUpperCase())
		verifyTrue(limitedStockLabel.text(), expectedValue("product.variants.limited.stock").toUpperCase())
		verifyTrue(outOfStockLabel.text(), expectedValue("product.variants.out.of.stock").toUpperCase())	
		verifyTrue(items[0].buttonCancel.text(), expectedValue("cancelButton.displayName").toUpperCase())
		verifyTrue(items[0].buttonUpdate.text(), expectedValue("product.variants.update").toUpperCase())
		
		when: "At WaitList page"
		at WaitListPage
		
		then: "remove product from WaitList"
		items.size() == 1
		items[0].buttonRemove.click()
		at WaitListPage
		
		and: "check empty list message"		
		waitFor { emptyList.displayed }
		verifyTestFailedOrPassed()
		
		where:
		productCode = ProductHelper.getProduct(ProductHelper.BRAND_LEVIS)
	}

}
