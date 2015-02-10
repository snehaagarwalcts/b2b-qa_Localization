package lscob2b.test.login

import static lscob2b.TestConstants.*
import geb.spock.GebReportingSpec
import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.test.data.User
 

class LoginTest extends GebReportingSpec { 

	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}

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
	 * US BB-17 User is identified as Levis, Dockers or Multibrand
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
//			new User(email:'simone.romei@levi.com', password:'12341234') | _
	}
	
}
