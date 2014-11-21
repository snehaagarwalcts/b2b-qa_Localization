package lscob2b.modules

import geb.Module

class Login extends Module {
	
	static content = {
		usernameInput (wait: true) { $("#j_username") }
		passwordInput { $("#j_password") }
		loginButton { $("#loginForm button") }
	}

	def doLogin() {
		loginButton.click()
	}
}
