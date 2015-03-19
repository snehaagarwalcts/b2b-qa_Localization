package lscob2b.pages.myaccount

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
		
		updatePasswordTxt {$("#main-container>h1")}
		profileDetails {$(".intro-container")}	
		passwordHintText {$(".help-inline>span")} 
				
		currentPwdTxt {$("div.b2BCustomerFormList>div.control-group .label .control-label",0)}
		newPasswordTxt {$("div.b2BCustomerFormList>div.control-group .label .control-label",1)}
		confirmNewPasswordTxt {$("div.b2BCustomerFormList>div.control-group .label .control-label",2)}
	}
	
	def doUpdatePassword(oldPassword, newPassword)
	{
		currentPasswordText.value(oldPassword)
		newPasswordText.value(newPassword)
		confirmNewPasswordText.value(newPassword)
		updatePasswordButton.click()
	}

}
