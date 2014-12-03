//Created by I065970 on 12/2/14
package lscob2b.pages

import geb.Page
import lscob2b.modules.MasterTemplate

class MyAccountPage extends Page {

	static url = "my-account"

    static at = {
        title == "Your Account | LSCO B2B Site"
    }

    static content = {
        logoAltTag { $('header.subnav div.simple_disp-img img').attr('alt') }
        masterTemplate(required: false) { module MasterTemplate }
        themeForm(required: false) { $('#theme-form') }
    }

}
