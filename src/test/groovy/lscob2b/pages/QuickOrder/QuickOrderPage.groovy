package lscob2b.pages.QuickOrder

import geb.Page
import lscob2b.modules.MasterTemplate

class QuickOrderPage extends Page{
	static url = "/advanced"

	static at = { title == "Advanced Search | LSCO B2B Site" }

	static content = { 
		
		masterTemplate { module MasterTemplate } 
		
	}
}