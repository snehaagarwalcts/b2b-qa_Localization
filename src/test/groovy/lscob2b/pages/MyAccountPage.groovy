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
		 */
		// account page content
		profile { $("div.profile div.headline a").text() }
		addressBook { $("div.addressBook div.headline a").text() }
		manageUsers { $("div.headline").find('a', href: endsWith('/manage-users')).text() }
		orderHistory { $("div.orderHistory div.headline a").text() }

	}

}
