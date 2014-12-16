package lscob2b.test.login

import geb.spock.GebReportingSpec
import lscob2b.pages.LoginPage

class LoginFailureTest extends GebReportingSpec {

    def setup() {
        to LoginPage
    }

    def "Login failure"() {
        when: "Logging in with bad credentials"

        doLogin("useridthatcannotexist", "badpassword")

        then: "We should not be logged in"

        at LoginPage

        
        errorMessage
        errorMessageText.contains("PLEASE NOTE")
		  //TODO add the final error message
		  //errorMessageText.contains("Your username or password was incorrect.")
    }
}
