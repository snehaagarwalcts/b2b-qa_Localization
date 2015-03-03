package lscob2b.test.myaccount

import geb.spock.GebReportingSpec
import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.myaccount.MyAccountPage
import lscob2b.pages.myaccount.UpdatePasswordPage
import spock.lang.Ignore

class UpdatePasswordTest extends GebReportingSpec {

	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
		to LoginPage
		at LoginPage	
	}
	
	/**
	 * US BB-746 Password rules
	 * TC BB-860 Verify Password Update functionality with non-compliant password
	 */
	def "Check Password Update functionality with non-compliant password"(){
		setup:
		login(user)
		at HomePage
		masterTemplate.clickMyAccount()
		
		when: "at My account page"
		at MyAccountPage
		
		then: "click on change password link"
		changeYourPassword.click()
		
		when: "at update Password"
		at UpdatePasswordPage
		
		and: "Try to update with a password that does not comply with password requirements"
		doUpdatePassword(user.password,uncompliantPassword)
		
		then: "check error message"
		errorMessage.displayed
		
		when: "Login with the same non-compliant password"
		PageHelper.gotoPageLogout(browser, baseUrl)
		at LoginPage
		doLogin(user.email,uncompliantPassword)
		
		then: "We should still be at Login page"
		at LoginPage
		
		and: "We should see an error message"
		errorMessage.displayed
		
		where:
		user | uncompliantPassword
		UserHelper.getUpdatePasswordUser() | UserHelper.UNCOMPLIANT_PASSWORD
	}
	
	/**
	 * US BB-746 Password rules
	 * TC BB-861 Verify Password Update functionality with a compliant password
	 */
	
	@Ignore
	def "Check Password Update functionality with compliant password"(){
		setup:
		login(user)
		at HomePage
		masterTemplate.clickMyAccount()
		
		when: "at My account page"
		at MyAccountPage
		
		then: "click on change password link"
		changeYourPassword.click()
		
		when: "at update Password"
		at UpdatePasswordPage
		
		and: "Try to update password "
		doUpdatePassword(user.password,compliantPassword)
		
		then: "Password Successfully Updated message appears "
		passwordUpdateMessage.displayed
		
		when: "Login with new password"
		PageHelper.gotoPageLogout(browser, baseUrl)
		at LoginPage
		doLogin(user.email,compliantPassword)
		
		then: "at home page"
		at HomePage
		
		where:
		user | compliantPassword
		UserHelper.getUpdatePasswordUser() | UserHelper.COMPLIANT_PASSWORD
	}	
}
