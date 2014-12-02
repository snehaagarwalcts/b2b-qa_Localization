package lscob2b.pages

import geb.Page
import lscob2b.modules.MasterTemplate //added this cause of an error "Log in with Swedish as your default language(lscob2b.test.login.LanguageSelectorTest): Unable to resolve masterTemplate as content for LoginPage, or as a property on its Navigator context. Is masterTemplate a class you forgot to import?"


class LoginPage extends Page {

    static url = ""

    static at = {
        title == "LSCO B2B Site"
    }

    static content = {
        // login form
        usernameInput(wait: true) { $("#j_username") }
        passwordInput { $("#j_password") }
        loginButton { $("#loginForm button") }

        // some error messages
        globalMessages { $("#globalMessages") }
        errorMessage { $("#globalMessages div.negative") }
        errorMessageText { errorMessage.text().trim() }

        // forgotten password
        forgottenPasswordDialog(required: false) { $("#cboxLoadedContent") }
        forgottenPasswordDialogVisible(required: false) { forgottenPasswordDialog.present && forgottenPasswordDialog.displayed }
        forgottenYourPasswordButton { $("#loginForm a") }
        closeForgottenPasswordButton (required: false) { $('#cboxClose') }
        sendEmailButton(required: false) { $("#forgottenPwdForm button") }
        emailAddress { $("#forgottenPwdForm input") }
		  
		  // language selector
		  langSelector { $("#lang-selector") }
    }

    def doLogin(String username, String password) {
        usernameInput = username
        passwordInput = password
        loginButton.click()
    }

    def openForgottenPasswordDialog() {
        forgottenYourPasswordButton.click()

        waitFor {
            forgottenPasswordDialogVisible
            emailAddress
        }
    }

    def closeForgottenPasswordDialog() {
        closeForgottenPasswordButton.click()

        waitFor {
            !forgottenPasswordDialogVisible
        }
    }

    def sendForgottenPasswordEmail(String email) {
        emailAddress = email
        sendEmailButton.click()
    }
}
