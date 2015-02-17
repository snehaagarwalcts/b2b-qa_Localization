package lscob2b.test.productdetails

import geb.spock.GebReportingSpec
import lscob2b.data.PageHelper
import lscob2b.data.ProductHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.productdetails.ProductDetailsPage
import spock.lang.IgnoreIf

class ProductDetailPageTest extends GebReportingSpec {
	
	def setup() {
		PageHelper.gotoPageLogout(browser,baseUrl)
		to LoginPage 
	}
	
	/**
	 * TC BB-450 Automated Test Case: Wholesale and recommended price should be displayed on PDP
	 */
	def "Test page structure of [ProductDetailPage]"() {
		when: "at login page"
			at LoginPage
			
		and: "do login"
			login(user)
		
		and: "at home page"
			at HomePage	

		and: "go to product detail page"
			PageHelper.gotoPageProductDetail(browser,baseUrl,productCode)	
			
		then: "at product detail page"
			at ProductDetailsPage
			
		and: "check Wholesale/Recommended price element"
			recommendedRetailPriceExist()
			wholesalePriceExist()

		where:
			user | productCode
			UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER) | ProductHelper.getProduct(ProductHelper.BRAND_LEVIS)
			UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_ADMIN) | ProductHelper.getProduct(ProductHelper.BRAND_LEVIS)
			UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_CUSTOMER) | ProductHelper.getProduct(ProductHelper.BRAND_LEVIS)
			UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_FINANCE) | ProductHelper.getProduct(ProductHelper.BRAND_LEVIS)
//			UserHelper.getUser(UserHelper.B2BUNIT_DOCKERS, UserHelper.ROLE_SUPER) | _
//			UserHelper.getUser(UserHelper.B2BUNIT_DOCKERS, UserHelper.ROLE_ADMIN) | _
//			UserHelper.getUser(UserHelper.B2BUNIT_DOCKERS, UserHelper.ROLE_CUSTOMER) | _
//			UserHelper.getUser(UserHelper.B2BUNIT_DOCKERS, UserHelper.ROLE_FINANCE) | _
//			UserHelper.getUser(UserHelper.B2BUNIT_MULTIBRAND, UserHelper.ROLE_SUPER) | _
//			UserHelper.getUser(UserHelper.B2BUNIT_MULTIBRAND, UserHelper.ROLE_ADMIN) | _
//			UserHelper.getUser(UserHelper.B2BUNIT_MULTIBRAND, UserHelper.ROLE_CUSTOMER) | _
//			UserHelper.getUser(UserHelper.B2BUNIT_MULTIBRAND, UserHelper.ROLE_FINANCE) | _
	}

	/**
	 * TC BB-645 Automated test case: Cross-Selling & Up-Selling
	 */
	@IgnoreIf({ System.getProperty("geb.browser") == "safari" || System.getProperty("geb.browser") == "ie8" || System.getProperty("geb.browser") == "chrome"})
	def "Check Up-Selling and Cross-Selling"() {
		setup:
			to LoginPage
			login(user)
			PageHelper.gotoPageProductDetail(browser,baseUrl,productCode)
						
		when: "At ProductDetail page"
			at ProductDetailsPage
			
		then: "Check Up-Selling product"
			for(pc in upSellingPCs) { 
				assert !upSelling.itemLink(pc).empty
			}
		
		//TODO remove comment when product are fixed
//		and: "Check Cross-Selling product"
//			for(pc in crossSellingPCs) {
//				assert !crossSelling.itemLink(pc).empty
//			}
			
		where:
			user | productCode | upSellingPCs | crossSellingPCs
			UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_CUSTOMER) | ProductHelper.getUpCrossSelling(ProductHelper.BRAND_LEVIS)["code"] | ProductHelper.getUpCrossSelling(ProductHelper.BRAND_LEVIS)["upSelling"] | ProductHelper.getUpCrossSelling(ProductHelper.BRAND_LEVIS)["crossSelling"]
			//UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_CUSTOMER) | "00501-0039" | ["00501-0114"] | ["00501-0101"]
	}
	
	@IgnoreIf({ System.getProperty("geb.browser") == "safari" || System.getProperty("geb.browser") == "ie8" || System.getProperty("geb.browser") == "chrome"})
	def "Check Color Switch"() {
		setup:
			to LoginPage
			login(user)
			PageHelper.gotoPageProductDetail(browser,baseUrl,productCode)
	
		when: "At ProductDetail page"
			at ProductDetailsPage
		
		then: "Check color links"
			for(rel in relatedCodes) {
				assert !findLinkColor(rel).empty
			}
			
		when: "click on first color item"
			buyStack.colorItem(0).click()
		
		then: "at product detail page"
			at ProductDetailsPage

		where:
			user | productCode | relatedCodes
			UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_CUSTOMER) | ProductHelper.getProductColor(ProductHelper.BRAND_LEVIS)["code"] | ProductHelper.getProductColor(ProductHelper.BRAND_LEVIS)["related"]
	}
	
}


