package lscob2b.modules

import geb.Module

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
    }

	//added by I065970 on 12/2/14
	def doMyaccount()
	{
		myaccountLink.click()
	}

	
    def doLogout() {
        logoutLink.click()
    }
}
