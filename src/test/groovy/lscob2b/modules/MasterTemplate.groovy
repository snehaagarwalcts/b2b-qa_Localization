package lscob2b.modules

import geb.Module
import geb.navigator.Navigator

import org.openqa.selenium.Keys

/**
 * Created by i303936 on 11/20/14.
 */
class MasterTemplate extends Module {

	static content = {
		
		homeLink { $("#breadcrumb li a", href: endsWith('/')) }

		myAccountLink {
			$('div.global-nav ul.global-nav-list').find("a.global-nav-hasmenu", href: contains("/my-account"),0)
		}
		
		manageUsersLink {
			$('div.global-nav a', href: endsWith('manage-users/'))
		}

		logoutLink {
			$('div.global-nav ul.global-nav-list').find("a", href: contains("/logout"))
		}

		/* BreadCrumb */
		
		breadCrumbs {
			$('div#breadcrumb').find('li').not('li.separator')
		}
		
		breadCrumbHref { String href -> $('div#breadcrumb').find("a", href:endsWith(href),0) }
		
		breadCrumbActive { $('div#breadcrumb').find("li.active",0) }

		logoAltTag {$('header h1 a img').attr('alt')}

		themeForm(required: false) { $('#theme-form') }

		quickOrderLink { $('header h2').find("a", href: contains("/advanced")) }

		goToCartLink { $("div.mini-cart h3") }

		brandSelectionInput { $("form#theme-form").find("input", type: "hidden", name: "code") }
		
		switchBrandLink {$("a#switchTheme")}
		
		
		searchInput { $("#input-search") }
		
		searchLink { $('a.search-icon') } //TODO Enable once working
		
		/*Contact us after login*/
		
		contactUs { $('.yCmsComponent').find('a', href:endsWith('/contactus')) }
		

		/* WaitList */
		
		waitListItem { $("li#waitlist-container") }
		
		waitListLink { waitListItem.find("a",class:"miniWaitlist miniWaitlistLink",0) }
		
		waitListItemCount { waitListItem.find("span.count") }
		
		/* Cart */
		
		cartItem { $("li#cart-container") }
		
		cartItemLink { cartItem.find("a",0) }
		
		cartItemCount { cartItem.find("span.count") }
		
		/* SwitchTo Link */
		
		dockersLogo(required:false) { $("a.logo-dockers") }
		
		levisLogo(required: false) { $("a.logo-levis") }
		
		/* SubNav Menu */
		
		subNav { $("div.subnav") }
		
		quickOrderLink { subNav.find("ul.subnav-list li.subnav-quickorder").find("a") }
		
		categoryItems { subNav.find("ul.subnav-list li").not(".subnav-quickorder") }

		categoryLink { categoryName ->  subNav.find("a", href: endsWith(categoryName)) }
				
		subCategoryLink { categoryName,subCategoryName -> subNav.find("a", href: endsWith(categoryName)).parent().parent().find("a",href: endsWith(subCategoryName)) }
		 		
	}
	
	def doContactUs(){
		waitFor {
			contactUs.displayed
		}
		contactUs.click()
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

	def void switchBrandIfNecessary(brand){
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
	
	def getCategoryLink(parentCategory) {
		$("a", href: endsWith(parentCategory))
	}
	
	def getSubcategoryLink(parentCategory, subCategory) {
		def item = getCategoryLink(parentCategory)
		def menu = item.parent().parent().find("div.menu")
		item.jquery.mouseover()
		waitFor {
			menu.displayed
			return menu.find("a", href: endsWith(subCategory))
		}
	}
	
	def selectManageUsers(){
		mouseOverMyAccountMenuItem()
		waitFor {
			manageUsersLink.displayed
			manageUsersLink.click()
		}
	}
	
	/* MY ACCOUNT */
	
	def getMyAccountSubLink(String link) {
		myAccountLink.jquery.mouseover()
		waitFor {
			myAccountLink.parent().find("ul").displayed
		}
		myAccountLink.parent().find("a", href: endsWith(link))
	}
	
	def getMyAccountSubLinks() {
		myAccountLink.jquery.mouseover()
		waitFor {
			myAccountLink.parent().find("ul").displayed
		}
		myAccountLink.parent().find("ul").find("a")
	}
	
	
	
}
