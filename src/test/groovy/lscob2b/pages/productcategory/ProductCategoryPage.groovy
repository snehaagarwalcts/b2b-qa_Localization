package lscob2b.pages.productcategory

import geb.Page
import groovy.lang.MetaClass;

class ProductCategoryPage extends Page {

	static at = {
		!$("body.pageType-CategoryPage").empty
	}

	static content = {
		firstProductLink {$("a.productMainLink", 0)}
	}

	def clickFirstProductLink() {
		firstProductLink.click()
	}
}
