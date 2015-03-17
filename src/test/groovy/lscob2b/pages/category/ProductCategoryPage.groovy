package lscob2b.pages.category

import lscob2b.modules.MasterTemplate
import geb.Page
import groovy.lang.MetaClass;

class ProductCategoryPage extends Page {

	static at = {
		waitFor { browser.currentUrl.contains("Categories") }
	}

	static content = {
		
		masterTemplate {module MasterTemplate}
		
		categoryLink { $('.yCmsContentSlot.shop-by-category>h2') }
		
		seasonalInitiativeLink { $('.yCmsContentSlot.shop-by-seasonal>h2') }
		
		shopByStyleLink { $('.yCmsContentSlot.shop-by-style>h2') }
		
		shopByFitLink { $('.yCmsContentSlot.shop-by-fit>h2') }
		
		firstProductLink {$("a.productMainLink", 0)}
		
		/**/
		
		keylookItems { $("div#keylook_slider ul li.org") }
		
		keylookLink { index -> keylookItems[index].find("a") }
		
	}

	def clickFirstProductLink() {
		firstProductLink.click()
	}
	
	/**/
	
}
