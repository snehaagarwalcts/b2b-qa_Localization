package lscob2b.modules

import geb.Module
import geb.navigator.Navigator

/**
 * Created by i303936 on 11/20/14.
 */
class MasterTemplate extends Module {

	static content = {

		myAccountLink {
			$('div.global-nav ul.global-nav-list').find("a.global-nav-hasmenu", href: contains("/my-account"))
		}

		logoutLink {
			$('div.global-nav ul.global-nav-list').find("a", href: contains("/logout"))
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
