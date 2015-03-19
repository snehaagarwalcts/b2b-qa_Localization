//created by I844489 on 12/9/2014

package lscob2b.pages.myaccount

import geb.Page
import lscob2b.modules.MasterTemplate

class ProfilePage extends Page{

	static url = "my-account/profile"

	static at = { waitFor { title == "Profile | LSCO B2B Site" } }

	static content = {
		masterTemplate { module MasterTemplate }

		//Profile page content
		//profileData { $("div.b2BCustomerFormList div.label")*.text()*.toUpperCase() }
		profileData { $("div.b2BCustomerFormList div.label") }
		//updatePersonalDetails { $("a.button.editUser", href: endsWith('update-profile')).text() }
		//changeYourPassword { $("a.button.editUser", href: endsWith('update-password')).text() }
		updatePersonalDetailsLink { $("a.button.editUser", href: endsWith('update-profile')) }
		changeYourPasswordLink { $("a.button.editUser", href: endsWith('update-password')) }
			
		//localization
		profileTxt {$("#main-container>h1")}
		profileDetails {$(".intro-container")}
		titleLabel { $(".control-group:nth-child(1) .label")}
		firstName {$(".control-group:nth-child(2) .label")}
		lastName {$(".control-group:nth-child(3) .label")}
	}
	
	def checkProfileDataExists(){
		!profileData.empty
	}
	
	def checkUpdatePersonalDetailsLinkExists(){
		!updatePersonalDetailsLink.empty
	}
	
	def checkChangeYourPasswordLinkExists(){
		!changeYourPasswordLink.empty
	}
	
	//localization
	def clickOnUpdatePersonalDetailsLink(){
		updatePersonalDetailsLink.click()
	}
	
	def clickOnChangeYourPasswordLink(){
		changeYourPasswordLink.click()
	}
}
