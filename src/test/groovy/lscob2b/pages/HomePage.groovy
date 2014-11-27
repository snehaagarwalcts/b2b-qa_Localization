package lscob2b.pages

import geb.Page
import lscob2b.modules.MasterTemplate

/**
 * Created by i303936 on 11/20/14.
 */
class HomePage extends Page{

    static url = "/"

    static at = {
        title == "LSCO B2B Site | Homepage"
    }

    static content = {
        logoAltTag {$('header.subnav div.simple_disp-img img').attr('alt')}
        masterTemplate (required:false) {module MasterTemplate}
        themeForm (required: false) {$('#theme-form')}
    }

}
