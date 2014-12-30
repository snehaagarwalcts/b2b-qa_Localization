//created by I844489 on 12/10/2014

package lscob2b.pages.MyAccount.admin

import geb.Page
import geb.navigator.Navigator;
import lscob2b.modules.MasterTemplate

class ManageUsersPage extends Page{

	static url = "my-account/manage-users"

	static at = { title == "LSCO B2B Site" }

	static content = {
		masterTemplate { module MasterTemplate }

		//Manage users page content
		manageUsersData { $("form table tr th")*.text() }
		createNewUser { $("div.right a").text() }
		createNewUserLink { $("a.addnewuser") }
		manageUsers { $("#breadcrumb.breadcrumb #breadcrumb li").not('separator')*.text()}
	}

	def clickCreateNewUsersLink(){
		createNewUserLink.click()
	}

	def clickUserLink(String email){
		$("#manage_user a", href : endsWith(email)).click()
	}
}
