//created by I844489 on 12/10/2014

package lscob2b.pages.MyAccount

import geb.Page
import geb.navigator.Navigator;
import lscob2b.modules.MasterTemplate

class CreateUserPage extends Page{

	static url = "my-account/manage-users/create"

	static at = { title == "LSCO B2B Site" }

	static content = {
		masterTemplate { module MasterTemplate }

		customerForm { $("form#b2BCustomerForm") }
		titleFirstOptionText { customerForm.find("select#user\\.title option",1).text() }
		firstNameInput { customerForm.find("input#user\\.firstName") }
		lastNameInput { customerForm.find("input#user\\.lastName") }
		emailInput { customerForm.find("input#user\\.email") }
		defaultDeliveryAddrFirstOptionText { customerForm.find("select#text\\.company\\.user\\.default\\.shipping\\.address option",1).text() }
		roleCheckBoxes { customerForm.find("input", type: "checkbox", name: "roles") }
		saveButton { $("button", type: "submit") }
	}

	def String selectFirstTitleOption(){
		customerForm.titleCode = titleFirstOptionText
		return titleFirstOptionText
	}

	def String selectDefaultDeliveryAddrOption(){
		customerForm."defaultShippingAddress.publicKey" = defaultDeliveryAddrFirstOptionText
		return defaultDeliveryAddrFirstOptionText
	}

	def setFirstName(String firstName){
		firstNameInput.value(firstName)
	}

	def setLastName(String lastName){
		lastNameInput.value(lastName)
	}

	def setEmail(String email){
		emailInput.value(email)
	}

	def selectAllRoles(){
		$("div.icheckbox").not(".checked").find("ins").each {
			it.jquery.mouseover()
			it.click()
		}
	}

	def submit(){
		saveButton.click()
	}
}
