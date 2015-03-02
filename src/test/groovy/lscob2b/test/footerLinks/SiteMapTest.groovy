package lscob2b.test.footerLinks

import spock.lang.Stepwise;
import lscob2b.data.PageHelper;
import lscob2b.data.UserHelper;
import lscob2b.pages.HomePage;
import lscob2b.pages.LoginPage;
import lscob2b.pages.SiteMapPage;
import geb.spock.GebReportingSpec;

class SiteMapTest extends GebReportingSpec{
	
	def setup(){
		PageHelper.gotoPageLogout(browser, baseUrl)
		to LoginPage
		at LoginPage
	}	
	
	/**
	 * US BB-594 Site Map
	 * TC BB-839 Automated Test Case: Verify Site Map Link in Footer section
	 */
	def "Verify Site Map Link in Footer section"(){
		setup:
		login(user)
		
		when:"at Home Page"
		at HomePage
		
		then:"check SiteMap Link is present or not"
		waitFor{ masterTemplate.siteMapLink.displayed }
				
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
	
	/**
	 * US BB-594 Site Map
	 * TC BB-840 Automated Test Case: Check the content of SiteMap Page
	 */
	def "Check the content of SiteMap Page"(){
		setup:
		login(user)
		
		when:"at Home Page"
		at HomePage		
		
		then: "click on SiteMap Link"
		waitFor{ masterTemplate.siteMapLink.displayed }
		masterTemplate.siteMapLink.click()
		
		when: "at SiteMap page"
		at SiteMapPage
		
		then: "Check Sub Category Links"		
		!menLink.empty
		!womenLink.empty
		!myAccountLink.empty		
		
		where:
		user | _
		UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER) | _
		UserHelper.getUser(UserHelper.B2BUNIT_DOCKERS, UserHelper.ROLE_ADMIN) | _
		UserHelper.getUser(UserHelper.B2BUNIT_MULTIBRAND, UserHelper.ROLE_CUSTOMER) | _
		
	}

}
