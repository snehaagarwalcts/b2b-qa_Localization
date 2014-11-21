package lscob2b.modules

import geb.Module

/**
 * Created by i303936 on 11/20/14.
 */
class Logout extends Module {

    static content = {
        logoutLink {
            $('#header ul.nav').find("a", text: "Sign Out")
        }
    }

    def doLogout() {
        logoutLink.click()
    }
}
