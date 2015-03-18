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
		
		titleText { $('.controls select[name="titleCode"] option', it) }
		firstName { $('.controls input', 0) }
		lastName { $('.controls input', 1) }
		emailText { $('.controls input', 2) }
		phoneText { $('.controls input', 3) }
		companyName { $('.controls input', 4) }
		customerNumber { $('.controls input', 5) }
		country { $('.dropdown-container select[name="countryCode"] option', it) }
		comments { $('#contactUsForm div.controls textarea') }
		
		firstNameAfterLogin { $('.controls', 1) }
		lastNameAfterLogin { $('.controls', 2) }
		emailTextAfterLogin { $('.controls', 3) }
		phoneTextAfterLogin { $('.controls', 4) }
		companyNameAfterLogin { $('.controls', 5) }
		customerNumberAfterLogin { $('.controls', 6) }
		
		sendButton { $('button.button') }
		//required fields left unfilled or something wrong with server
		alertMessage { $('div.alert-message') }
		//when email sends
		noteMessage { $('div.note-message') }
		
		//Localization
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
