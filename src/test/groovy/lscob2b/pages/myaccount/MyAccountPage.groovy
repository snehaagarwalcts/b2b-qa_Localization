//Created by I065970 on 12/2/14
package lscob2b.pages.myaccount

import geb.Page
import lscob2b.modules.MasterTemplate

class MyAccountPage extends Page {

	static url = "my-account"

	static at = { waitFor { title == "Your Account | LSCO B2B Site" } }

	static content = {
		masterTemplate { module MasterTemplate }

		/**
		 * Added by i844489 on 12/4/14.
		 * Updated by i844489 on 12/9/2014
		 */
		//account page profile section
		profileLink { $("div.profile div.headline a") }
		//profile { $("div.profile div.headline a") }
		updatePersonalDetails { $("div.profile ul").find('a', href: endsWith('/profile'))}
		changeYourPassword { $("div.profile ul").find('a', href: endsWith('/update-password'))}
		
		//account page address book section
		addressBookLink { $("div.addressBook div.headline a") }
		//addressBook { $("div.addressBook div.headline a") }
		viewYourDeliveryAddress { $("div.addressBook ul a")}

		//account page manage section
		//manageUsersLink { $("div.headline").find('a', href: endsWith('/manage-users')) }
		manageUsers(required:false) { $("div.headline").find('a', href: endsWith('/manage-users')) }
		addNewUsers(required:false) { $("div ul").find('a', href: endsWith('/manage-users/create')) }
		editUsers(required:false) { $("div ul").find('a', href: endsWith('/manage-users')) }
		
		//account page order section
		orderHistoryLink { $("div.orderHistory div.headline a") }
		//orderHistory { $("div.orderHistory div.headline a") }
		viewOrderHistory { $("div.orderHistory ul  a")}
		
		//Accont Balance
		accountBalanceLink(required: false) { $("div.headline").find('a', href: endsWith('/balance')) }
	}
	
	def boolean hasPageLink(String link) {
		!($("div.customAccount").find("a", href:endsWith(link),0)).empty
	}
	
	def checkAccountBalanceLinkExists(){
		!accountBalanceLink.empty
	}
	def checkProfileLinkExists(){
		!profileLink.empty
	}
	
	def checkUpdatePersonalDetailsLinkExists(){
		!updatePersonalDetails.empty
	}
	
	def checkChangeYourPasswordLinkExists(){
		!changeYourPassword.empty
	}

	def checkAddressBookLinkExists(){
		!addressBookLink.empty
	}
	
	def checkViewYourDeliveryAddressLinkExists(){
		!viewYourDeliveryAddress.empty
	}

	def checkManageUsersLinkExists(){
		!manageUsers.empty
	}
	
	def checkAddNewUserLinkExists(){
		!addNewUsers.empty
	}
	
	def checkEditUsersLinkExists(){
		!editUsers.empty
	}
	
	def checkOrderHistoryLinkExists(){
		!orderHistoryLink.empty
	}
	
	def checkViewOrderHistoryLinkExists(){
		!viewOrderHistory.empty
	}
}
