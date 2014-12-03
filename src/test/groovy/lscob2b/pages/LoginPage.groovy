package lscob2b.pages

import geb.Page
import lscob2b.modules.MasterTemplate

class LoginPage extends Page {

	static url = "login"

	static at = { title == "LSCO B2B Site" }

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
		langSelectorValues { $("#lang-selector option") }
		pageheading { $("div.title_holder h2").text() }
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

		waitFor { !forgottenPasswordDialogVisible }
	}

	def sendForgottenPasswordEmail(String email) {
		emailAddress = email
		sendEmailButton.click()
	}
}
