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
		  dockersTheme { $("a").find('img', src: endsWith('/medias/?context=bWFzdGVyfHJvb3R8NzMyNHxpbWFnZS9wbmd8aDdlL2g3Ny84Nzk2NDM4OTIxMjQ2LnBuZ3wzYWI3N2RkYjRlMGRmNjYyZmU2MTE0YmU5Y2NmNjA2YjkxNWZmM2NmNTMyY2RlNmI2MTdjNmE4ODEzZGUxZDE4')) }
		  levisTheme { $("a").find('img', src: endsWith('/medias/?context=bWFzdGVyfHJvb3R8ODI0MnxpbWFnZS9wbmd8aDdmL2gwZS84Nzk2NDM4ODg4NDc4LnBuZ3w3NDA1YWYzZTc0N2IzYWIwYzU0YTFmZmRlMzI4YzQ2NTU3MjEzNjE0MWMyYmQ5ZWU5NjFlZjI0ZjA1ZjQwMWVh')) }
    }
	 
	 def checkSwitchTo(){
		 !switchToLink.empty
	 }
	
	 def clickSwitchTo(){
		 switchToLink.click()
	 }
}
