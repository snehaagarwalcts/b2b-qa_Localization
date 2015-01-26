//created by I844489 on 12/10/2014

package lscob2b.pages.MyAccount.admin

import geb.Page
import geb.navigator.Navigator;
import lscob2b.modules.EditUserDetailsModule;
import lscob2b.modules.MasterTemplate

class EditUserDetailsPage extends Page{

	static at = { waitFor { title == "LSCO B2B Site" } }

	static content = {
		masterTemplate { module MasterTemplate }
		userDetails { module EditUserDetailsModule}
		
		form { $("form#b2BCustomerForm") }
	
		defaultDeliveryAddressSelect { form.find("select#text\\.company\\.user\\.default\\.shipping\\.address") }
		
		defaultDeliveryAddressOptions { defaultDeliveryAddressSelect.find("option") }

	}

	def getDefaultDeliveryAddressSelected() {
		defaultDeliveryAddressSelect.find('option', value:defaultDeliveryAddressSelect.value())
	}
	
	def changeDefaultDeliveryAddress() {
		def item = defaultDeliveryAddressSelect.find('option', value: notContainsWord(defaultDeliveryAddressSelect.value()),1)
		assert !item.empty
		defaultDeliveryAddressSelect.value(item.value())
	}
	
	
}
