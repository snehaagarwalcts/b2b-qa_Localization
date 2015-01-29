package lscob2b.pages.category

import geb.Page
import lscob2b.modules.KeylookModule
import lscob2b.modules.MasterTemplate

class KeyLookPage extends Page {

	static at = {
		waitFor { browser.currentUrl.contains("keylook") }
	}

	static content = {
		
		masterTemplate { module MasterTemplate }

		//DESCRIPTION
		
		keylookHero { $("div.keylook-hero") }
		
		keylookHeroImg { keylookHero.find("div.keylook-img img#keylook-img") }
		
		keylookHeroTitle { keylookHero.find("div.keylook-overlay span.label") }
		
		keylookHeroDescription { keylookHero.find("div.keylook-overlay p") }
	
		//ITEMS
		
		keylookItems { $("div.keylooktItem").collect { module KeylookModule, it  } }
			
	}

		
}
