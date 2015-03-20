package lscob2b.pages.myaccount.admin

import geb.Page;

class UpdatePasswordPage extends Page {

	static url = "my-account/update-password"

	static at = { waitFor { title == "Profile | LSCO B2B Site" } }

	static content = {
		
		currentPasswordText { $('div.b2BCustomerFormList>div.control-group:nth-child(1) div.controls>input') }
		
		newPasswordText { $('#profile-newPassword') }
		
		confirmNewPasswordText { $('div.b2BCustomerFormList>div.control-group:nth-child(4) div.controls>input') }
		
		updatePasswordButton{ $('.button.save') }
		
		errorMessage { $('div.b2BCustomerFormList>div.control-group.error>:nth-of-type(2)>span') }
		
		passwordUpdateMessage { $('div.note-message h2') }		
		
		//localization
		
		updatePasswordText {$("#main-container>h1")}
		profileDetails {$(".intro-container")}	
		passwordHintText {$("div.b2BCustomerFormList>div.control-group.error>:nth-of-type(2)>span",1)} 
				
		currentPwdLabel {$("div.b2BCustomerFormList>div.control-group .label .control-label",0)}
		newPasswordLabel {$("div.b2BCustomerFormList>div.control-group .label .control-label",1)}
		confirmNewPasswordLabel {$("div.b2BCustomerFormList>div.control-group .label .control-label",2)}
		
		//localization error messages
		alertMessage {$(".alert-message>h2")}
		errorMessageText {$(".alert-message>p")}
		requiredMessageText{$(".required")}		
		currentPasswordError {$("div.b2BCustomerFormList>div.control-group.error>:nth-of-type(2)>span",0)}
		cancelButton {$(".button.btn-txt-red.cancel>p")}
		
	}
	
	def doUpdatePassword(oldPassword, newPassword)
	{
		currentPasswordText.value(oldPassword)
		newPasswordText.value(newPassword)
		confirmNewPasswordText.value(newPassword)
		updatePasswordButton.click()
	}
	
	def clickSaveButton(){
		updatePasswordButton.click()
	}	

}
