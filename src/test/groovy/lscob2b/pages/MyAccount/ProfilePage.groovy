//created by I844489 on 12/9/2014

package lscob2b.pages.MyAccount

import geb.Page
import lscob2b.modules.MasterTemplate

class ProfilePage extends Page{

	static url = "my-account/profile"

	static at = { title == "Profile | LSCO B2B Site" }

	static content = {
		masterTemplate { module MasterTemplate }

		//Profile page content
		profileData { $("div.b2BCustomerFormList div.label")*.text()*.toUpperCase() }
		updatePersonalDetails { $("a.button.editUser", href: endsWith('update-profile')).text() }
		changeYourPassword { $("a.button.editUser", href: endsWith('update-password')).text() }
		updatePersonalDetailsLink { $("a.button.editUser", href: endsWith('update-profile')) }
		changeYourPasswordLink { $("a.button.editUser", href: endsWith('update-password')) }
	}
}
