package lscob2b.pages

import geb.Page;

class PrivacyPolicyPage extends Page{

	static url = "privacyPolicy"
	
	static at = { waitFor { title == "LSCO B2B Site" } }
	
	static content = {
		
		//masterTemplate {module MasterTemplate}
	}
}
