package lscob2b.modules

import geb.Module
import geb.navigator.Navigator

class LoggedInModule extends Module {

	static content = {
		
		logoutLink {
			$('div.global-nav ul.global-nav-list').find("a", href: contains("/logout"))
		}

		myAccountLink {
			$('div.global-nav ul.global-nav-list').find("a.global-nav-hasmenu", href: contains("/my-account"))
		}

		breadCrumbs {
			$('#breadcrumb.breadcrumb #breadcrumb').find('li').not('.separator')
		}

		logoAltTag {$('header h1 a img').attr('alt')}

		themeForm(required: false) { $('#theme-form') }

		quickOrderLink { $('header h2').find("a", href: contains("/advanced")) }
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
}
