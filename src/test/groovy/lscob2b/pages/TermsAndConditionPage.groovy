package lscob2b.pages

import geb.Page

class TermsAndConditionPage extends Page{

	static url = "termsAndConditions"
	
	static at = { waitFor { title == "Terms & Conditions | LSCO B2B Site" } }
	
	static content = {
		
		agree { $("div.dialogueButtons").find('a', href: endsWith('/agree')) }
		disagree { $("div.dialogueButtons").find('a', href: endsWith('/disagree')) }
	}
	
	def agreeLinkExists(){
		!agree.empty
	}
	
	def disagreeLinkExists(){
		!disagree.empty
	}
	
	def agreeLinkClick(){
		agree.click()
	}
	
	def disagreeLinkClick(){
		disagree.click()
	}
}