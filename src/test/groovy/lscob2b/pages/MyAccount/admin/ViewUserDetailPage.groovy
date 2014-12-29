package lscob2b.pages.MyAccount.admin

import lscob2b.modules.ViewUserDetailsModule;
import geb.Page;
import geb.navigator.Navigator;

class ViewUserDetailsPage extends Page{
	static at = {
		$(".alert").text()=="Customer successfully created"
	}

	static content = { userDetails { module ViewUserDetailsModule } }
}