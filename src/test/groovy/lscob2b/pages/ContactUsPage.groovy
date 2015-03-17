package lscob2b.pages

import javax.management.modelmbean.RequiredModelMBean;

import geb.Page
import lscob2b.modules.MasterTemplate
import geb.navigator.Navigator

class ContactUsPage extends Page{

	static url = "/contactus"

	static at = { waitFor { title == "LSCO B2B Site" } }

	static content = {
		masterTemplate {module MasterTemplate}

		contact { $('#main-container h1') }
		introContainer { $('#main-container div.intro-container') }
		required { $('#main-container div.required') }
		
		contactUsForm { $('#contactUsForm div div label.control-label') }
		
		titleText { $('#contactUsForm select#user\\.title option', it) }
		firstName { $('#contactUsForm div.controls input', 0) }
		lastName { $('#contactUsForm div.controls input', 1) }
		emailText { $('#contactUsForm div.controls input', 2) }
		phoneText { $('#contactUsForm div.controls input', 3) }
		companyName { $('#contactUsForm div.controls input', 4) }
		customerNumber { $('#contactUsForm div.controls input', 5) }
		country { $('#contactUsForm select#user\\.country option', it) }
		comments { $('#contactUsForm div.controls textarea') }
		
		sendButton { $('button.button') }
		//required fields left unfilled or something wrong with server
		alertMessage { $('div.alert-message') }
		//when email sends
		noteMessage { $('div.note-message') }
		
		//translations
		titleLabel { $('.control-label', 0) }
		firstNameLabel { $('.control-label', 1) }
		lastNameLabel { $('.control-label', 2) }
		emailLabel { $('.control-label', 3) }
		phoneLabel { $('.control-label', 4) }
		companyNameLabel { $('.control-label', 5) }
		customerNumberLabel { $('.control-label', 6) }
		countryLabel { $('.control-label', 7) }
		commentsLabel { $('.label', 8) }
		
		titleError { $('.help-inline', 0) }
		firstNameError { $('.help-inline', 1) }
		lastNameError { $('.help-inline', 2) }
		emailError { $('.help-inline', 3) }
		phoneError { $('.help-inline', 4) }
		companyNameError { $('.help-inline', 5) }
		customerNumberError { $('.help-inline', 6) }
		countryError { $('.help-inline', 7) }
		commentsError { $('.help-inline', 8) }
		
		commentsErrorAfterLogin { $('.help-inline') }
		
		continueShoppingLink { $('.button.btn-txt-red>p') }
	}
	
	def checkRequiredContent(){
		!contact.empty
		!introContainer.empty
		!required.empty
		!contactUsForm.empty
	}
	
	def checkAlertMessageExists(){
		!alertMessage.empty
	}
	
	def checkNoteMessageExists(){
		!noteMessage.empty
	}
	
	def fillOutFirstName(String firstname){
		firstName.value(firstname)
	}
	
	def fillOutlastName(String lastname){
		lastName.value(lastname)
	}
	
	def fillOutEmail(String email){
		emailText.value(email)
	}
	
	def fillOutPhone(String phone){
		phoneText.value(phone)
	}
	
	def fillOutCompanyName(String companyname){
		companyName.value(companyname)
	}
	
	def fillOutCustomerNumber(String customernumber){
		customerNumber.value(customernumber)
	}
	
	def fillOutComments(String comment){
		comments.value(comment)
	}
	
	def clickSendButton(){
		sendButton.click()
	}
	
	/**
	 * @param index starts from 1
	 * @return
	 */
	def String selectTitleOption(int index){
		titleCode = titleText(index).text()
	}
	
	/**
	 * @param index starts from 1
	 * @return
	 */
	def String selectCountryOption(int index){
		countryCode = country(index).text()
	}
}
