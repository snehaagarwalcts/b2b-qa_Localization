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
        masterTemplate {module MasterTemplate}
		  
		  switchToLink(required: false) { $("#switchTheme") }
		  dockersTheme { $("div.subnav a").find('img', src: contains('/medias/?context=bWFzdGVyfHJvb3R8')) }
		  levisTheme { $("div.subnav a").find('img', src: contains('/medias/?context=bWFzdGVyfHJvb3R8')) }
    }
	 
	 def checkSwitchTo(){
		 !switchToLink.empty
	 }
	
	 def clickSwitchTo(){
		 switchToLink.click()
	 }
}
