//created by I844489 on 12/10/2014

package lscob2b.pages.MyAccount.admin

import geb.Page
import geb.navigator.Navigator;
import lscob2b.modules.EditUserDetailsModule;
import lscob2b.modules.MasterTemplate

class CreateUserPage extends Page{

	static url = "my-account/manage-users/create"

	static at = { waitFor { title == "LSCO B2B Site" } }

	static content = {
		masterTemplate { module MasterTemplate }
		userDetails { module EditUserDetailsModule}
	}
}
