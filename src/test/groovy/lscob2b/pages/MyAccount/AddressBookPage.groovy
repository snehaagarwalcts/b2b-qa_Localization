//created by I844489 on 12/10/2014

package lscob2b.pages.MyAccount

import geb.Page
import lscob2b.modules.MasterTemplate

class AddressBookPage extends Page{

	static url = "my-account/address-book"

	static at = { title == "Address Book | LSCO B2B Site" }

	static content = {
		masterTemplate { module MasterTemplate }

		//Address book page content //TODO add more content as page gets developed
		addressBookData { $("div strong")*.text() }
		addressItem { $("div.addressItem ul").text() }
	}
}
