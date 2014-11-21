package lscob2b.pages

import geb.Page

class LoginPage extends Page {

	static url = "https://lscob2b.local:9002/lscob2bstorefront/lscob2b/en/login"
	
	static at = {
		title == "LSCO B2B Site"
	}

	static content = {
		usernameInput (wait: true) { $("#j_username") }
		passwordInput { $("#j_password") }
		loginButton { $("#loginForm button") }
		globalMessages { $("#globalMessages") }
		errorMessage { $("#globalMessages div.negative") }
		errorMessageText { errorMessage.text().trim() }
	}

	def doLogin(String username, String password) {
		usernameInput = username
		passwordInput = password
		loginButton.click()
	}
}
