package lscob2b.modules

import geb.Module
import geb.navigator.Navigator

class EditUserDetailsModule extends Module{

	static content = {
		customerForm { $("form#b2BCustomerForm") }
		titleOption { customerForm.find("select#user\\.title option", it) }
		firstNameInput { customerForm.find("input#user\\.firstName") }
		lastNameInput { customerForm.find("input#user\\.lastName") }
		emailInput { customerForm.find("input#user\\.email") }
		defaultDeliveryAddrFirstOption { customerForm.find("select#text\\.company\\.user\\.default\\.shipping\\.address option",it) }

		saveButton { $("button", type: "submit") }
	}

	/**
	 * @param index starts from 1
	 * @return
	 */
	def String selectTitleOption(int index){
		customerForm.titleCode = titleOption(index).text()
	}

	/**
	 * @param index starts from 1
	 * @return
	 */
	def String selectDefaultDeliveryAddrOption(int index){
		customerForm."defaultShippingAddress.publicKey" = defaultDeliveryAddrFirstOption(index).text()
	}

	def setFirstNameField(String firstName){
		firstNameInput.value(firstName)
	}

	def setLastNameField(String lastName){
		lastNameInput.value(lastName)
	}

	def setEmailField(String email){
		emailInput.value(email)
	}

	def selectAllRoles(){
		$("div.icheckbox").not(".checked").find("ins").each {
			it.jquery.mouseover()
			it.click()
		}
	}

	def unSelectFirstRole(){
		def ins = $("div.icheckbox.checked",0).find("ins")
		ins.jquery.mouseover()
		ins.click()
	}

	def submit(){
		saveButton.click()
	}
}
