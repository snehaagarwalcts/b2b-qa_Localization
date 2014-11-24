package lscob2b.pages

import geb.Page

class ForgottenYourPasswordPage extends Page {

	
	static url = "baseUrl"
	
	static at = {
		title == "LSCO B2B Site"
	}

	static content = {
		forgottenYourPasswordButton { $("#loginForm a") }
		emailAddress { $("#forgottenPwdForm input") }
		sendemail { $("#forgottenPwdForm button") }
	}
	
	def doforgottenYourPassword(){
		forgottenYourPasswordButton.click()
	}
	
	def sendEmail(String email){
		emailAddress = email
		sendemail.click()
	}
}