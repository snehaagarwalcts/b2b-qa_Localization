//created by I844489 on 12/10/2014

package lscob2b.pages.myaccount.admin

import geb.Page
import geb.navigator.Navigator;
import lscob2b.modules.EditUserDetailsModule;
import lscob2b.modules.MasterTemplate

class EditUserDetailsPage extends Page{

	static at = { waitFor { title == "LSCO B2B Site" } }

	static content = {
		
		masterTemplate { module MasterTemplate }
		
		userDetails { module EditUserDetailsModule}
		
		//Localization
		titleDropdownLabels { $('select[id="user.title"] option',it) }
		
		EditUserDetailsLabel { $('.control-label',it) }
		
		roleUserManagement { $('.controls fieldset label',0) }
		
		rolePurchasing { $('.controls fieldset label',1) }
		
		roleFinance { $('.controls fieldset label',2) }
		
		cancelButton { $('.button.btn-txt-red.cancel>p') }
	}
	
}
