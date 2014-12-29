package lscob2b.pages.MyAccount.admin

import geb.Page;
import geb.navigator.Navigator;

class CustomerCreationConfirmationPage extends Page{
	static at = {
		$(".alert").text()=="Customer successfully created"
	}

	static content = {
		titleText { $("div.control-group:nth-child(1) > div:nth-child(2)").text() }
		firstNameText { $("div.control-group:nth-child(2) > div:nth-child(2)").text() }
		lastNameText { $("div.control-group:nth-child(3) > div:nth-child(2)").text() }
		emailText { $("div.control-group:nth-child(4) > div:nth-child(2)").text() }
		defaultDeliveryAddrText { $("div.control-group:nth-child(5) > div:nth-child(2)").text() }
		resetPasswordLinkUrl { $("div.control-group:nth-child(6) > div:nth-child(2) > a:nth-child(1)").@href }
	}
}