package lscob2b.pages

import geb.Page
import lscob2b.modules.MasterTemplate

class FAQPage extends Page{	static url = "/faq"	static at = { waitFor { title == "Frequently Asked Questions | LSCO B2B Site" || title == "DE_Frequently Asked Questions | LSCO B2B Site"	} }	static content = { masterTemplate {module MasterTemplate} }
	
}
