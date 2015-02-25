package lscob2b.pages

import geb.Page
import lscob2b.modules.MasterTemplate

class HelpPage extends Page {

	static url = "/help"

	static at = { waitFor { title == "Help | LSCO B2B Site"
		} }

	static content = {
		masterTemplate {module MasterTemplate}

		faq { $('.bannerContent').find('a', href: endsWith('/faq')) }

		training { $('.bannerContent').find('a', href: endsWith('/training')) }

		contactUs { $('.bannerContent').find('a', href: endsWith('/contactus')) }
	}

	def checkFAQLinkExists(){
		!faq.empty
	}

	def checkContactUsLinkExists(){
		!contactUs.empty
	}

	def checkTrainingLinkExists(){
		!training.empty
	}
	
	def clickFAQLink(){
		faq.click()
	}

	def clickContactUsLink(){
		contactUs.click()
	}

	def clickTrainingLink(){
		training.click()
	}
}
