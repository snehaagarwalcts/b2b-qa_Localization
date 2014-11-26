package lscob2b.modules

import geb.Module

/**
 * Created by i303936 on 11/20/14.
 */
class MasterTemplate extends Module {

    static content = {
        logoutLink {
            $('nav.global-nav ul.global-nav-list').find("a", href: contains("/logout"))
        }
    }

    def doLogout() {
        logoutLink.click()
    }
}
