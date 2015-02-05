package lscob2b.modules

import geb.Module
import lscob2b.test.data.User

class ViewUserDetailsModule extends Module {

	static content = {
		
		container { $("div.b2BCustomerFormList") }
		
		title { container.find("div.control-group",0).find("div.controls") }
		
		firstName { container.find("div.control-group",1).find("div.controls") }
		
		lastName { container.find("div.control-group",2).find("div.controls") }
		
		email { container.find("div.control-group",3).find("div.controls") }
		
		address { container.find("div.control-group",4).find("div.controls") }
		
		editUserButton { $("a.button.edituser")}

		disableUserButton { $("a#disableUser") }
	}

	def clickEditUser(){
		editUserButton.click()
	}
	
	def User getUser() {
		User user = new User()
		user.title = title.text()
		user.name = firstName.text()
		user.surname = lastName.text()
		user.email = email.text()
		user.address = address.text()
		user
	}
	
}
