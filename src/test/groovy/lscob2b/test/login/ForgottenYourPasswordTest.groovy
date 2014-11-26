package lscob2b.test.login

import geb.spock.GebReportingSpec

import lscob2b.pages.LoginPage
import spock.lang.Stepwise

@Stepwise
class ForgottenYourPasswordTest extends GebReportingSpec {

    String levisUser = "robert.moris@monsoon.com"

    def setupSpec() {
        to LoginPage
    }

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

        // TODO not yet implemented on front end

    }

}
