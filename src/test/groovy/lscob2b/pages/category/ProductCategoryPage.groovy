package lscob2b.pages.category

import lscob2b.modules.MasterTemplate
import lscob2b.modules.ProductCategoryModule
import geb.Page
import groovy.lang.MetaClass

class ProductCategoryPage extends Page {

	static at = {
		waitFor { browser.currentUrl.contains("Categories") || browser.currentUrl.contains("search") }
	}

	static content = {
		
		masterTemplate {module MasterTemplate}
		
		pdpItems { $("div.productListItem").collect { module ProductCategoryModule, it } }
		
		firstProductLink {$("a.productMainLink", 0)}
		
		keylookItems { $("div#keylook_slider ul li.org") }
		
		keylookLink { index -> keylookItems[index].find("a") }
		
		/* Localization */
		
		categoryLink { $('.yCmsContentSlot.shop-by-category>h2') }
		
		seasonalInitiativeLink { $('.yCmsContentSlot.shop-by-seasonal>h2') }
		
		shopByStyleLink { $('.yCmsContentSlot.shop-by-style>h2') }
		
		shopByFitLink { $('.yCmsContentSlot.shop-by-fit>h2') }
		
		breadCrumbLink { $('div#breadcrumb li:nth-child(3)') }
		
		productsFoundLabel { $('.paginationBar .totalResults') }
		
		sortByLabel { $('.paginationBar .sortForm label') }
		
		pageOfLabel { $('.paginationBar .pagination span') }
		
		refinementsLabel { $('.headline') }
		
		facetHeadLabels {$('.refinementToggle')}
		
	}

	def clickFirstProductLink() {
		firstProductLink.click()
	}
	
}
