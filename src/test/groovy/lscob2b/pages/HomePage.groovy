package lscob2b.pages

import geb.Page
import lscob2b.modules.MasterTemplate

/**
 * Created by i303936 on 11/20/14.
 */
class HomePage extends Page{

    static url = "/"

    static at = {
//        title == "LSCO B2B Site | Homepage"
		waitFor { title == "LSCO B2B Site | Homepage" }
    }

    static content = {
        masterTemplate {module MasterTemplate}
    }
	 
	 def checkSwitchTo(){
		 !switchToLink.empty
	 }
	
	 def clickSwitchTo(){
		 switchToLink.click()
	 }
}
