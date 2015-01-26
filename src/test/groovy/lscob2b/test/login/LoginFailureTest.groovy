package lscob2b.test.login

import geb.spock.GebReportingSpec
import lscob2b.pages.LoginPage
import lscob2b.test.data.TestHelper

class LoginFailureTest extends GebReportingSpec {

	def setupSpec() {
		browser.go(baseUrl + TestHelper.PAGE_LOGOUT)
	}
	
    def "Login failure"() {
		setup:
			to LoginPage
			
        when: "At Login Page"
			at LoginPage	
		
		then: "Bad login"
        	doLogin("useridthatcannotexist", "badpassword")

        when: "We should not be logged in"
        	at LoginPage
			
		then: 
			waitFor {
				!errorMessage.empty
				errorMessageText.contains("PLEASE NOTE")
			}
    }
}
