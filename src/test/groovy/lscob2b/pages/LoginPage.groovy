package lscob2b.pages

import geb.Page
import lscob2b.test.data.User

class LoginPage extends Page {

	static url = "login"

	static at = { waitFor { title == "Login | LSCO B2B Site" ||  title == "DE_Login | LSCO B2B Site" } }

	static content = {
		// login form
		usernameInput { $("#j_username") }
		passwordInput { $("#j_password") }
		loginButton { $("#loginForm button") }

		// some error messages
		errorMessage { $("div.alert-message p") }
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
		langSelectorFor { value -> $("#lang-selector option[value='${value}']") }
		langSelectorValueCount { $("#lang-selector option").size() }
		pageheading { $("#main-container h1").text() }
		
		// contact us
		contactUS { $('div.contact a') }
		contactUsMessage { $('.contact') }
		
		//Password sent
		noteMessage { $("div.note-message") }
		
		//Translation
		loginTitle { $('#page>p') }
		username { $('.form_field-label>label',0) }
		password { $('.form_field-label>label',1) }
		submitButton { $('.form.right') }
		selectlanguageLink { $('.control-label') }
		
		forgottenPwdTitle { $('.headline') }
		forgottenPwdRequired { $('.required.right') }
		forgottenPwdDescription { $('.description') }
		forgottenPwdEmail { $('#forgottenPwdForm .control-label') }
		forgottenPwdSubmit { $('.positive') }
		
		myAccount {$('a.global-nav-hasmenu span')}
	}

	def login(User user){
		doLogin(user.email, user.password)
	}

	def login(String username) {
		doLogin(username, defaultPassword)
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
		waitFor { closeForgottenPasswordButton.displayed }
		
		closeForgottenPasswordButton.click()

		waitFor { !forgottenPasswordDialogVisible }
	}

	def sendForgottenPasswordEmail(String email) {
		emailAddress = email
		sendEmailButton.click()
	}
	
	def clickContactUS(){
		contactUS.click()
	}
	
	def clickMyAccount(){
		myAccount.click()
	}
}
