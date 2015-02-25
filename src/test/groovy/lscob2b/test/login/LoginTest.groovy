package lscob2b.test.login

import geb.spock.GebReportingSpec
import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.TermsAndConditionPage
import spock.lang.IgnoreIf
import spock.lang.IgnoreRest


class LoginTest extends GebReportingSpec {

	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}

	/**
	 * TC BB-751 Login Failure
	 */
	def "Test invalid login"() {
		setup:
		to LoginPage

		when: "at login page"
		at LoginPage

		and: "do an invalid login"
		login(user)

		then: "at login page"
		at LoginPage

		and: "a message is displayed"
		waitFor { errorMessage.displayed }

		where:
		user = UserHelper.getInvalidUser()
	}

	/**
	 * US BB-20 Mandatory login before you can access the site
	 * TC BB-755 Automated Test Case: Test not authorized access 
	 */
	def "Test not authorized access"() {
		when: "try to access to HomePage"
		PageHelper.gotoPage(browser, baseUrl, "/")

		then: "at LoginPage"
		at LoginPage

		when: "try to access to ManageUser"
		PageHelper.gotoPage(browser, baseUrl, PageHelper.PAGE_MANAGE_USERS)

		then: "at LoginPage"
			at LoginPage
			
		when: "try to access to ProfilePage"
			PageHelper.gotoPage(browser, baseUrl, PageHelper.PAGE_PROFILE)
		
		then: "at LoginPage"
			at LoginPage
			
		when: "try to access to OrderHistoryPage"
			PageHelper.gotoPage(browser, baseUrl, PageHelper.PAGE_ORDER_HISTORY)
		
		then: "at LoginPage"
			at LoginPage
			
	}


	/**
	 * US BB-17 User is identified as Levis, Dockers or Multibrand
	 * TC BB-754 Automated Test Case: Test Valid Login
	 */
	def "Test valid login"() {
		setup:
		to LoginPage

		when: "at login page"
		at LoginPage

		and: "do login"
		login(user)

		then: "at home page"
		at HomePage

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
	 * US BB-586 Message for blocked customers when trying to log on 
	 * TC BB-832 Message for blocked customers when trying to log on 
	 */
	def "Test blocked B2B unit login"() {
		setup:
		to LoginPage

		when: "at login page"
		at LoginPage

		and: "do login"
		login(user)

		and: "We should still be at Login page"
		at LoginPage

		then: "We should see a error message"
		errorMessage.displayed
		
		where:
			user | _
			UserHelper.getBlcokedUserWithCode01() | _
			UserHelper.getBlcokedUserWithCode02() | _
			UserHelper.getBlcokedUserWithCode03() | _
			UserHelper.getBlcokedUserWithCode99() | _
			UserHelper.getBlcokedUserWithCodeZ1() | _
			UserHelper.getBlcokedUserWithCodeZ2() | _
			UserHelper.getBlcokedUserWithCodeZ3() | _
			UserHelper.getBlcokedUserWithCodeZ4() | _
			UserHelper.getBlcokedUserWithCodeZ5() | _
			UserHelper.getBlcokedUserWithCodeZ6() | _
	}
	
	/**
	 * US BB-586 Message for blocked customers when trying to log on
	 * TC BB-845 Message for blocked customer when trying to log on
	 */
	def "Test blocked B2B customer login"() {
		setup:
		to LoginPage

		when: "at login page"
		at LoginPage

		and: "do login"
		login(user)

		and: "We should still be at Login page"
		at LoginPage

		then: "We should see a error message"
		errorMessage.displayed
		
		where:
			user | _
			UserHelper.getDefaultCreditCardAndBlockedUser() | _
	}
	
	/**
	 * US BB-591 Confirm terms and conditions at first login 
	 * TC BB-774 Test first time login and links exists
	 */
	@IgnoreIf({System.getProperty("geb.browser") == "internet explorer"})
	def "Test first time login and links exit"(){
		setup:
		to LoginPage

		when: "at login page"
		at LoginPage

		and: "do login"
		login(user)

		then: "at terms and coditions page check agree/disagree displayed"
		at TermsAndConditionPage
			agreeLinkExists()
			disagreeLinkExists()
		
		where:
		user = UserHelper.getTermsAndConditionUser()
	}
	
	/**
	 * US BB-591 Confirm terms and conditions at first login
	 * TC BB-775 Test first time login and disagree to terms and condition
	 */
	@IgnoreIf({System.getProperty("geb.browser") == "internet explorer"})
	def "Test first time login and disagree to terms and condition and you should stay on terms and condition page"(){
		setup:
		to LoginPage

		when: "at login page"
		at LoginPage

		and: "do login"
		login(user)

		and: "at terms and conditions page"
		at TermsAndConditionPage

		and: "Disgree to terms and conditions"
		disagreeLinkClick()

		then:"at home page"
		at TermsAndConditionPage
		
		where:
		user = UserHelper.getTermsAndConditionUser()
	}

	/**
	 * US BB-591 Confirm terms and conditions at first login
	 * TC BB-776 Test first time login and agree to terms and condition
	 */
	@IgnoreIf({System.getProperty("geb.browser") == "internet explorer"})
	def "Test first time login and agree to terms and condition"(){
		setup:
		to LoginPage

		when: "at login page"
		at LoginPage

		and: "do login"
		login(user)

		and: "at terms and conditions page"
		at TermsAndConditionPage

		and: "Agree to terms and conditions"
		agreeLinkClick()

		then:"at home page"
		at HomePage
		
		where:
		user = UserHelper.getTermsAndConditionUser()
	}
}
