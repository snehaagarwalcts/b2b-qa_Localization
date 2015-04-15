package lscob2b.pages

import lscob2b.modules.MasterTemplate
import geb.Page

class TermsAndConditionPage extends Page{

	static url = "termsAndConditions"
	
	static at = { waitFor { title == "Terms & Conditions | LSCO B2B Site" || title == "DE_Terms & Conditions | LSCO B2B Site" } }
	
	static content = {
		
		masterTemplate {module MasterTemplate}
		
		agree { $("div.dialogueButtons").find('a', href: endsWith('/agree')) }
		
		disagree { $("div.dialogueButtons").find('a', href: endsWith('/disagree')) }
		
		headerMessage { $('#dialogue>h2') }
		
		message { $('.terms>p') }
	}
	
	def agreeLinkExists(){
		waitFor { agree.displayed }
		!agree.empty
	}
	
	def disagreeLinkExists(){
		waitFor { disagree.displayed }
		!disagree.empty
	}
	
	def agreeLinkClick(){
		agree.click()
	}
	
	def disagreeLinkClick(){
		disagree.click()
	}
}