package lscob2b.test.productdetails

import lscob2b.data.PageHelper
import lscob2b.data.ProductHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.productdetails.ProductDetailsPage
import lscob2b.test.data.PropertProvider

class ProductDetailsPageTest extends PropertProvider{
	
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}
	
	def "Verify translations of Product Details Page"() {
		setup:
		to LoginPage
		at LoginPage
		login(user)
		
		when:"at HomePage"
		at HomePage
		
		then: "go to Product Details Page"
		PageHelper.gotoPageProductDetail(browser,browser.getCurrentUrl(),targetProductCode)
		
		when: "at Product Details Page"
		at ProductDetailsPage
		
		then: "verify translations of Product Details Page"
		verifyTrue(masterTemplate.breadCrumbs.text(), expectedValue("breadcrumb.home").toUpperCase())
		verifyTrue(masterTemplate.breadCrumbHref("/categories").text(), expectedValue("categorylandingpage.categories").toUpperCase())
		verifyTrueContains(buyStack.colorStyle.text(), expectedValue("product.variants.style").toUpperCase(), "use contains()")
		verifyTrueContains(buyStack.colorName.text(), expectedValue("product.variants.color").toUpperCase(), "use contains()")
		verifyTrueContains(buyStack.labelWholesalePrice.text(), expectedValue("product.wholesale.price").toUpperCase(), "use contains()")
		verifyTrueContains(buyStack.labelRecommendedPrice.text(), expectedValue("product.retail.price").toUpperCase(), "use contains()")
		verifyTrueContains(buyStack.buyStackAttributes(0).text(), expectedValue("product.variants.fit"), "use contains()")
//		verifyTrueContains(buyStack.buyStackAttributes(1).text(), expectedValue(""), "use contains()")
//		verifyTrueContains(buyStack.buyStackAttributes(2).text(), expectedValue(""), "use contains()")
//		verifyTrueContains(buyStack.buyStackAttributes(3).text(), expectedValue(""), "use contains()")
		verifyTrue(addToCart.enterSizeLabel.text(), expectedValue("product.variants.select.size").toUpperCase())
		verifyTrue(addToCart.sizeGuideLabel.text(), expectedValue("product.variants.size.guide"))
		verifyTrue(addToCart.inStockLabel.text(), expectedValue("product.variants.in.stock").toUpperCase())
		verifyTrue(addToCart.limitedStockLabel.text(), expectedValue("product.variants.limited.stock").toUpperCase())
		verifyTrue(addToCart.outOfStockLabel.text(), expectedValue("product.variants.out.of.stock").toUpperCase())
		verifyTrue(addToCart.buttonNotifyMe.text()- ~/&/, expectedValue("basket.add.to.waitlist").toUpperCase())
		verifyTrue(addToCart.buttonAddToCart.text()- ~/&/, expectedValue("basket.add.to.cart").toUpperCase())
		verifyTestFailedOrPassed()
		
		where:
		user | targetProductCode 
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER) | ProductHelper.getProduct(ProductHelper.BRAND_LEVIS)
	}
}
