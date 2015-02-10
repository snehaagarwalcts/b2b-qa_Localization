package lscob2b.test.login

import static lscob2b.TestConstants.*
import geb.spock.GebReportingSpec
import lscob2b.data.UserHelper
import lscob2b.pages.LoginPage
import lscob2b.test.data.TestHelper
import spock.lang.Stepwise

@Stepwise
class ForgottenYourPasswordTest extends GebReportingSpec {

    def setupSpec() {
		browser.go(baseUrl + TestHelper.PAGE_LOGOUT)
        to LoginPage
    }
	
	 /**
	  * TC BB-750 Forgotten your password
	  */
    def "Open forgotten password dialog"() {

        when: "Click forgot password link"

        openForgottenPasswordDialog()

        then: "Dialog should be present"

        forgottenPasswordDialog

    }

    def "Close forgotten password dialog"() {

        when: "Clicking close button, dialog should close"

        closeForgottenPasswordDialog()

        then: "Dialog should not be visible"

        !forgottenPasswordDialog.displayed

    }

    def "Send forgotten password email"() {

        setup: "Open dialog"

        openForgottenPasswordDialog()

        when: "Enter email and click send"

        sendForgottenPasswordEmail(levisUser)

        then: "Confirmation message should be display"
		  at LoginPage
		  noteMessage.displayed
    }

}
