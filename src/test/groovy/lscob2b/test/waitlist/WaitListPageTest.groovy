package lscob2b.test.waitlist

import geb.spock.GebReportingSpec
import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.waitlist.WaitListPage
import spock.lang.IgnoreIf

@IgnoreIf({System.getProperty("geb.browser") == "internet explorer"})
class WaitListPageTest extends GebReportingSpec {
	
	def setup() {
		PageHelper.gotoPageLogout(browser,baseUrl)
		to LoginPage 
	}
	
	/**
	 * TC BB-510 Automated test: wait list should be accessible by user
	 */
	def "Test page structure of [WaitList]"() {
		when: "at login page"
			at LoginPage
			
		and: "do login"
			login(user)
		
		and: "at home page"
			at HomePage	
		
		and: "Click on WaitList link"	
			masterTemplate.waitListLink.click()
			
		then: "at QuickOrder page"
			at WaitListPage
			
		and: "Correct sections/links should be visible"	
//				emptyList.displayed
				continueToShoppingLink.displayed
			
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


