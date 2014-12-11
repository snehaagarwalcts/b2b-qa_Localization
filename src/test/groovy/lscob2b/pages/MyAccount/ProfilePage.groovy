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
		profileData { $("div tbody tr td")*.text() }
		updatePersonalDetails { $("div.clearfix").find('a', href: endsWith('/update-profile')).text() }
		changeYourPassword { $("div.clearfix").find('a', href: endsWith('/update-password')).text() }
		updatePersonalDetailsLink { $("div.clearfix").find('a', href: endsWith('/update-profile')) }
		changeYourPasswordLink { $("div.clearfix").find('a', href: endsWith('/update-password')) }
	}
}
