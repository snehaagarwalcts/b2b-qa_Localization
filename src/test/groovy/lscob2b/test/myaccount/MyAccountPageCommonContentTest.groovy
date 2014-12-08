package lscob2b.test.myaccount

/**
 * Created by i844489 on 12/4/2014.
 * Updated by i844489 on 12/8/2014.
 */

import geb.spock.GebReportingSpec
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.MyAccountPage

class MyAccountPageCommonContentTest extends GebReportingSpec {
	static String defaultPassword = "12341234"
	static String administrator = "robert.moris@monsoon.com"
	//String customer = ""  //TODO add customer ID

	def setup() {
		to LoginPage
	}

	def login(String username) {
		doLogin(username, defaultPassword)
	}

	def loginAsUserAndGoToMyAccount(String user) {
		login(user)
		at HomePage
		masterTemplate.clickMyAccount()
		at MyAccountPage
	}


	def "Check the My Account page"(){
		when: "Going to My Account page"

		loginAsUserAndGoToMyAccount(user)

		then: "Correct sections/links should be visible depending on user"

		// check main section
		page.getContent(section) == headerValue

		// check links for each section
		sublinks.each { k,v ->
			assert page.getContent(k) == v
		}

		where:
		user			| section			| headerValue		| sublinks
		administrator	| 'profile'			| 'PROFILE'			| [updatePersonalDetails	: 'Update personal details'		, changeYourPassword: 'Change your password'	]
		administrator	| 'addressBook'		| 'ADDRESS BOOK'	| [viewYourDeliveryAddress	: 'View your delivery addresses'												]
		administrator	| 'orderHistory'	| 'ORDER HISTORY'	| [viewOrderHistory			: 'View order history'															]
		administrator	| 'manageUsers'		| 'MANAGE USERS'	| [addNewUsers				: 'Add new users'				, editUsers			: 'Edit or disable users'	]
/*		nonAdmin		| 'profile'			| 'PROFILE'			| [updatePersonalDetails: 'Update personal details', changeYourPassword: 'Change your password']
		nonAdmin		| 'addressBook'		| 'ADDRESS BOOK'	| [viewYourDeliveryAddress: 'View your delivery addresses']
		nonAdmin		| 'orderHistory'	| 'ORDER HISTORY'	| [viewOrderHistory: 'View order history']
		nonAdmin		| 'manageUsers'		| 'MANAGE USERS'	| [addNewUsers: 'Add new users', editUsers: 'Edit or disable users']
*/

	}
}
