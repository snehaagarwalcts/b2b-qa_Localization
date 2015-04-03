package lscob2b.pages.myaccount.admin

import geb.Page
import geb.navigator.Navigator
import lscob2b.modules.MasterTemplate
import lscob2b.modules.ViewUserDetailsModule

class ViewUserDetailsPage extends Page{
	
	static at = { waitFor { title == "LSCO B2B Site" } }

	static content = { 
		
		masterTemplate {module MasterTemplate}
		
		userDetails { module ViewUserDetailsModule }
		
		//Localization
		ViewUserDetailsLabel { $('.label',it) }
		
	}
	
}