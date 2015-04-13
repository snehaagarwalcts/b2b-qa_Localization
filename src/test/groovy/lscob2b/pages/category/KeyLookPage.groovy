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
		
		//LOCALIZATION
		
		breadCrumbLink { $('div#breadcrumb li:nth-child(3)') }
		
		labelQuantity { $('div.quantity span') }
		
		labelTotal { $('div.total span') }
		
		continueShopping { $('.button-large.btn-txt-red>p') }
		
		buttonAddToCart { $('.button-large.add_keylook_to_cart.checkout>p') }
		
		
			
	}

		
}
