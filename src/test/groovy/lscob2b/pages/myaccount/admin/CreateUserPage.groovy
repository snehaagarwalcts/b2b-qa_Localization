//created by I844489 on 12/10/2014

package lscob2b.pages.myaccount.admin

import geb.Page
import geb.navigator.Navigator
import lscob2b.modules.EditUserDetailsModule
import lscob2b.modules.MasterTemplate

class CreateUserPage extends Page {

	static url = "my-account/manage-users/create"

	static at = { waitFor { title == "LSCO B2B Site" } }

	static content = {

		masterTemplate { module MasterTemplate }

		userDetails { module EditUserDetailsModule }

		//Localization
		AddUserLabels { $('.control-label', it) }

		roleUserManagement { $("div.controls fieldset").find("label", 0)}
		rolePurchasing {$("div.controls fieldset").find("label", 1)}
		roleFinance {$("div.controls fieldset").find("label", 2)}

		cancelButton {$(".button.btn-txt-red.cancel>p")}
		saveButton {$(".button.save")}

	}
}
