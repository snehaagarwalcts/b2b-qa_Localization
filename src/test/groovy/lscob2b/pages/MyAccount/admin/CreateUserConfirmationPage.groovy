package lscob2b.pages.MyAccount.admin

import lscob2b.modules.MasterTemplate;
import lscob2b.modules.ViewUserDetailsModule;
import geb.Page;
import geb.navigator.Navigator;

class CreateUserConfirmationPage extends Page{
	static at = {
		$(".alert").text()=="Customer successfully created"
	}

	static content = {
		masterTemplate { module MasterTemplate }
		userDetails { module ViewUserDetailsModule }
	}
}