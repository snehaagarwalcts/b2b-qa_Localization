package lscob2b.pages.MyAccount.admin

import geb.Page
import lscob2b.modules.MasterTemplate

class UserDetailPage extends Page {
	
	static url = "my-account/manage-users/details"
	
	static at = { waitFor { title == "LSCO B2B Site" } }

	static content = {
		
		masterTemplate {module MasterTemplate}
		
		editButton(to: EditUserDetailsPage) { $("a.edituser") }
		
		mainContent { $("div.b2BCustomerFormList") }
		
		userData { mainContent.find("div.control-group") }
		
	}

	def getDefaultDeliveryAddress() {
		userData[4].find("div.controls").text()
	}
	
}
