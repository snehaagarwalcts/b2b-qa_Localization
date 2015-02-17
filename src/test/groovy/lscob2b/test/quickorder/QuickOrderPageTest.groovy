package lscob2b.test.quickorder

import geb.spock.GebReportingSpec
import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.quickorder.QuickOrderPage

class QuickOrderPageTest extends GebReportingSpec {
	
	def setup() {
		PageHelper.gotoPageLogout(browser,baseUrl)
		to LoginPage 
	}
	
	/**
	 * US BB-88 Be able to place a quick order 
	 * TC BB-436 Automated Test Case: Any User should see the "Quick Order" link in the header section of the Application,
	 * that should redirect the user to the Quick Order Page.
	 * TC BB-437 Automated Test Case: validate the content of the "Quick Order" Page for any user
	 */
	def "Test page structure of [QuickOrderPage]"() {
		when: "at login page"
			at LoginPage
			
		and: "do login"
			login(user)
		
		and: "at home page"
			at HomePage	
		
		then: "QuickOrder link is available"
			masterTemplate.quickOrderLink.displayed				//TC BB-436
			
		when: "Click on QuickOrder link"	
			masterTemplate.clickQuickOrder()
			
		then: "at QuickOrder page"
			at QuickOrderPage
			
		and: "Correct sections/links should be visible"			//TC BB-437
			searchInput.displayed
			searchLink.displayed
			divCheckboxProductIDs.displayed
			buttonAdd.displayed
			spanQuantity.displayed
			spanPrice.displayed
			checkOutLink.displayed
			
		where:
			user | _
			UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER) | _
			UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_ADMIN) | _
			UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_CUSTOMER) | _
			UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_FINANCE) | _
			UserHelper.getUser(UserHelper.B2BUNIT_DOCKERS, UserHelper.ROLE_SUPER) | _
			UserHelper.getUser(UserHelper.B2BUNIT_DOCKERS, UserHelper.ROLE_ADMIN) | _
			UserHelper.getUser(UserHelper.B2BUNIT_DOCKERS, UserHelper.ROLE_CUSTOMER) | _
			UserHelper.getUser(UserHelper.B2BUNIT_DOCKERS, UserHelper.ROLE_FINANCE) | _
			UserHelper.getUser(UserHelper.B2BUNIT_MULTIBRAND, UserHelper.ROLE_SUPER) | _
			UserHelper.getUser(UserHelper.B2BUNIT_MULTIBRAND, UserHelper.ROLE_ADMIN) | _
			UserHelper.getUser(UserHelper.B2BUNIT_MULTIBRAND, UserHelper.ROLE_CUSTOMER) | _
			UserHelper.getUser(UserHelper.B2BUNIT_MULTIBRAND, UserHelper.ROLE_FINANCE) | _
	}

}


