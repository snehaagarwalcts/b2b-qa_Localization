package lscob2b.modules

import geb.Module
import geb.navigator.Navigator
import org.openqa.selenium.Keys

/**
 * Created by i303936 on 11/20/14.
 */
class MasterTemplate extends Module {

	static content = {

		cartTemplate {module CartModule}

		myAccountLink {
			$('div.global-nav ul.global-nav-list').find("a.global-nav-hasmenu", href: contains("/my-account"))
		}
		
		manageUsersLink {
			$('div.global-nav a', href: endsWith('manage-users/'))
		}

		logoutLink {
			$('div.global-nav ul.global-nav-list').find("a", href: contains("/logout"))
		}

		breadCrumbs {
			$('#breadcrumb li').not('.separator')
		}

		logoAltTag {$('header h1 a img').attr('alt')}

		themeForm(required: false) { $('#theme-form') }

		quickOrderLink { $('header h2').find("a", href: contains("/advanced")) }

		goToCartLink { $("div.mini-cart h3") }

		brandSelectionInput {$("form#theme-form > input")}
		
		switchBrandLink {$("a#switchTheme")}
		
		waitListLink { $("a.miniWaitlistLink") }
		
		searchInput { $("#input-search") }
		
		searchLink { $('a.search-icon') } //TODO Enable once working
		
	}
	
	def doSearch(String productID){
		searchInput = productID
		searchInput << Keys.ENTER
	}

	def getBreadCrumbByUrl(String url) {
		breadCrumbs.find('a', href: endsWith(url))
	}

	def isBreadCrumbActive(String text) {
		breadCrumbs.filter('li.active').text().toUpperCase() == text.toUpperCase()
	}

	def clickMyAccount() {
		myAccountLink.click()
	}

	def clickQuickOrder(){
		quickOrderLink.click()
	}

	def doLogout() {
		logoutLink.click()
	}

	def doGoToCart(){
		goToCartLink.click()
	}

	def switchBrand(){
		switchBrandLink.click()
	}

	def switchBrandIfNecessary(brand){
		if (brandSelectionInput.value() == brand){
			switchBrand()
		}
	}

	def Navigator getSubCategoryLink(subCategory){
		$("div.menu a", href: endsWith(subCategory))
	}

	def subCategoryLinkExists(subCategory){
		!getSubCategoryLink(subCategory).empty
	}

	def Navigator getParentCategory(parentCategory){
		$("div.subnav ul.subnav-list li>h2>a", href: endsWith(parentCategory))
	}

	def mouseOverParentCategory(parentCategory){
		getParentCategory(parentCategory).jquery.mouseover()
	}

	def mouseOverMyAccountMenuItem(){
		myAccountLink.jquery.mouseover()
	}
	
	def selectManageUsers(){
		mouseOverMyAccountMenuItem()
		waitFor {
			manageUsersLink.displayed
			manageUsersLink.click()
		}
	}
}
