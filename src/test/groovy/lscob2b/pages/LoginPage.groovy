package lscob2b.pages

import geb.Page
import lscob2b.modules.Login;

class LoginPage extends Page {

	static url = "https://lscob2b.local:9002/lscob2bstorefront/lscob2b/en/login"
	
	static at = {
		title == "LSCO B2B Site"
	}

	static content = {
		login (required:false) {module Login}
	}	

	def connect() {
		connectLink.click()
	}
}
