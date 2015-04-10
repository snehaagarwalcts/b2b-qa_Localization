package lscob2b.pages

import geb.Page
import lscob2b.modules.MasterTemplate

/**
 * Created by i303936 on 11/20/14.
 */
class HomePage extends Page{

    static url = ""

    static at = {
		waitFor { title == "LSCO B2B Site | Homepage" ||  title == "LSCO B2B Site | DE_Homepage"}
    }

    static content = {
        masterTemplate {module MasterTemplate}
		
		newsAndTipsLabel { $('.content>h1') }
		
		quickOrderButton { $('.button-large>p',0) }
		
		uploadOrderButton { $('.button-large>p',1) }
    }
	 
	 def checkSwitchTo(){
		 !switchToLink.empty
	 }
	
	 def clickSwitchTo(){
		 switchToLink.click()
	 }
}
