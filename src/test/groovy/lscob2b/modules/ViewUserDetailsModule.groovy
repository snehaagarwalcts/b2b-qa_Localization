package lscob2b.modules

import geb.Module
import lscob2b.test.data.User

class ViewUserDetailsModule extends Module {

	static content = {
		
		container(wait: true) { $("div.b2BCustomerFormList") }
		
		title(wait: true) { container.find("div.control-group",0).find("div.controls") }
		
		firstName(wait: true) { container.find("div.control-group",1).find("div.controls") }
		
		lastName(wait: true) { container.find("div.control-group",2).find("div.controls") }
		
		email(wait: true) { container.find("div.control-group",3).find("div.controls") }
		
		address(wait: true) { container.find("div.control-group",4).find("div.controls") }
		
		editUserButton { $("a.button.edituser")}

		disableUserButton { $("a#disableUser") }
		
		enableUserButton { $("a#enableUser") }
				
	}

	def clickEditUser(){
		editUserButton.click()
	}
	
	def User getUser() {
		waitFor { 
			container.displayed 
		}
		User user = new User()
		user.title = title.text()
		user.name = firstName.text()
		user.surname = lastName.text()
		user.email = email.text()
		user.address = address.text()
		user
	}
	
	def clickDisableUser(){
		disableUserButton.click()
	}
	
	def clickEnableUser(){
		enableUserButton.click()
	}
	
}
