package de.hybris.geb.page.hac

import geb.Page;

class LoginPage extends Page {

	static url = ""
	
	static at = { title == "hybris administration console | Login" }
	
	static content = {
		
		formLogin { $("form") }
		
		inputUsername { formLogin.find("input", type:"text", name:"j_username") }
		
		inputPassword { formLogin.find("input", type:"password", name:"j_password") }
		
		formSubmit { formLogin.find("button", type:"submit") }
		
	} 
	
	def doLogin(String username, String password) {
		inputUsername.value(username)
		inputPassword.value(password)
		formSubmit.click()
	}
	
}
