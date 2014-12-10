//created by I844489 on 12/10/2014

package lscob2b.pages.MyAccount

import geb.Page
import lscob2b.modules.MasterTemplate

class ManageUsersPage extends Page{

	static url = "my-account/manage-users"

	static at = { title == "LSCO B2B Site" }

	static content = {
		logoAltTag { $('header.subnav div.simple_disp-img img').attr('alt') }
		masterTemplate(required: false) { module MasterTemplate }
		themeForm(required: false) { $('#theme-form') }

		//Manage users page content
		manageUsersData { $("form table tr th")*.text() }
		createNewUser { $("div.right a").text() }
		createNewUserLink { $("div.right a") }
	}
}
