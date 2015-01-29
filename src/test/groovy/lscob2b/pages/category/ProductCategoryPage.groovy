package lscob2b.pages.category

import geb.Page
import groovy.lang.MetaClass;

class ProductCategoryPage extends Page {

	static at = {
		waitFor { browser.currentUrl.contains("Categories") }
	}

	static content = {
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
