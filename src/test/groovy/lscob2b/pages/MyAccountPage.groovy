//Created by I065970 on 12/2/14
package lscob2b.pages

import geb.Page
import lscob2b.modules.MasterTemplate

class MyAccountPage extends Page {

	static url = "my-account"

	static at = { title == "Your Account | LSCO B2B Site" }

	static content = {
		logoAltTag { $('header.subnav div.simple_disp-img img').attr('alt') }
		masterTemplate(required: false) { module MasterTemplate }
		themeForm(required: false) { $('#theme-form') }
		
		/**
		 * Added by i844489 on 12/4/14.
		 * Updated by i844489 on 12/9/2014
		 */
		//account page profile section
		profileLink { $("div.profile div.headline a") }
		profile { $("div.profile div.headline a").text() }
		updatePersonalDetails { $("div.profile ul").find('a', href: endsWith('/profile')).text()}
		changeYourPassword { $("div.profile ul").find('a', href: endsWith('/update-password')).text()}
		
		//account page address book section
		addressBookLink { $("div.addressBook div.headline a") }
		addressBook { $("div.addressBook div.headline a").text() }
		viewYourDeliveryAddress { $("div.addressBook ul a").text()}

		//account page manage section
		manageUsersLink { $("div.headline").find('a', href: endsWith('/manage-users')) }
		manageUsers { $("div.headline").find('a', href: endsWith('/manage-users')).text() }
		addNewUsers { $("div ul").find('a', href: endsWith('/manage-users/create')).text() }
		editUsers { $("div ul").find('a', href: endsWith('/manage-users')).text() }
		
		//account page order section
		orderHistoryLink { $("div.orderHistory div.headline a") }
		orderHistory { $("div.orderHistory div.headline a").text() }
		viewOrderHistory { $("div.orderHistory ul  a").text() }
	}

}
