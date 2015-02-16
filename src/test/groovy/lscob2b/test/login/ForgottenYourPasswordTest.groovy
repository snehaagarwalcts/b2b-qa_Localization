package lscob2b.test.login

import static lscob2b.TestConstants.*
import geb.spock.GebReportingSpec
import lscob2b.data.UserHelper
import lscob2b.pages.LoginPage
import lscob2b.test.data.TestHelper
import spock.lang.Stepwise

class ForgottenYourPasswordTest extends GebReportingSpec {

    def setup() {
		browser.go(baseUrl + TestHelper.PAGE_LOGOUT)
        to LoginPage
    }
	
	 /**
	  * TC BB-750 Forgotten your password
	  */
    def "Open forgotten password dialog"() {
		when: "At login page"
			at LoginPage
		
        and: "Click forgot password link"
			openForgottenPasswordDialog()

        then: "Dialog should be present"
			forgottenPasswordDialog.displayed
			
		when: "Clicking close button, dialog should close"
			closeForgottenPasswordDialog()
		
		then: "Dialog should not be visible"
			!forgottenPasswordDialog.displayed
    }

    def "Send forgotten password to an existing user"() {
		when: "At login page"
			at LoginPage

		and: "Open Dialog" 
			openForgottenPasswordDialog()
		
        and: "Enter email and click send"
        	sendForgottenPasswordEmail(user.email)

        then: "At login page"
		  at LoginPage
		  
		and: "Confirmation message should be display" 
		  waitFor { noteMessage.displayed }
		  
		where: 
			user = UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_CUSTOMER)  
    }
	
	def "Send forgotten password to a not existing user"() {
		when: "At login page"
			at LoginPage

		and: "Open Dialog"
			openForgottenPasswordDialog()
		
		and: "Enter email and click send"
			sendForgottenPasswordEmail(user.email)

		then: "At login page"
		  at LoginPage
		  
		and: "Confirmation message should be display"
		  waitFor { errorMessage.displayed }
		  
		where:
			user = UserHelper.getInvalidUser()
	}
	

}
