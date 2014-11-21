package lscob2b.pages

import geb.Page
import lscob2b.modules.Logout

/**
 * Created by i303936 on 11/20/14.
 */
class HomePage extends Page{

    static url = "/"

    static at = {
        title == "LSCO B2B Site | Homepage"
    }

    static content = {
        logoAltTag {$('#header div.simple_disp-img img').attr('alt')}
        logout (required:false) {module Logout}
        themeForm (required: false) {$('#theme-form')}
    }

}
