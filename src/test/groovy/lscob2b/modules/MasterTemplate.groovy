package lscob2b.modules

import geb.Module
import geb.navigator.Navigator

/**
 * Created by i303936 on 11/20/14.
 */
class MasterTemplate extends Module {

    static content = {
		
		//added by I065970 on 12/2/14
		myaccountLink
		{
			$('nav.global-nav ul.global-nav-list').find("a.global-nav-hasmenu", href: contains("/my-account"))
		}

        logoutLink {
            $('nav.global-nav ul.global-nav-list').find("a", href: contains("/logout"))
        }

		breadcrumbs {
			$('#breadcrumb.breadcrumb #breadcrumb').find('li').not('.separator')
		}
    }

	def getBreadcrumbByUrl(String url) {
		breadcrumbs.find('a', href: endsWith(url))
	}

	def isBreadcrumbActive(String text) {
		breadcrumbs.filter('li.active').text().toUpperCase() == text.toUpperCase()
	}

	def clickMyAccount()
	{
		myaccountLink.click()
	}
	
    def doLogout() {
        logoutLink.click()
    }
}
