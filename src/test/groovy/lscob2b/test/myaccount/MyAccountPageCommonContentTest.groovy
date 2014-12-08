package lscob2b.test.myaccount

/**
 * Created by i844489 on 12/4/2014.
 * Updated by i844489 on 12/8/2014.
 */

import geb.spock.GebReportingSpec
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.MyAccountPage
import spock.lang.Stepwise

class MyAccountPageCommonContentTest extends GebReportingSpec {
	static String defaultPassword = "12341234"
	static String administrator = "robert.moris@monsoon.com"
	//String customer = ""  //TODO add customer ID

	def setup() {
		to LoginPage
	}

	def cleanup() {
		masterTemplate.doLogout()
	}


	def login(String username) {
		doLogin(username, defaultPassword)
	}

	def "Check the My Account page content using administrator user"(){
		setup: "Login as an administrator"

		login(administrator)
		at HomePage
		masterTemplate.doMyaccount()


		when: "at my account page"
		
		at MyAccountPage

		then: "you should see Profile, Address Book, Manage Users, and Order History "
		
		profile == 'PROFILE'
		addressBook == 'ADDRESS BOOK'
		manageUsers == 'MANAGE USERS'
		orderHistory == 'ORDER HISTORY'
	}

	//TODO once added regular customer remove the comments from below
	/*def "Check the My Account page content using customer user"(){
	 setup: "Login as a customer"
	 
	 login(customer)
	 at HomePage
	 masterTemplate.doMyaccount()
	 
	 when: "at my account page"
	 
	 at MyAccountPage
	 
	 then: "you should see Profile, Address Book, Manage Users, and Order History "
	 
	 profile == 'PROFILE'
	 addressBook == 'ADDRESS BOOK'
	 orderHistory == 'ORDER HISTORY'
	 }*/
	
	def "Check the My Account page profile content using administrator user"(){
		setup: "Login as an administrator"

		login(administrator)
		at HomePage
		masterTemplate.doMyaccount()
		at MyAccountPage

		when: "looking at my profile block"
		
		profile == 'PROFILE'

		then: "you should see Update prsonal details and Change your password "
		
		updatePersonalDetails == 'Update personal details'
		changeYourPassword == 'Change your password'
		
	}
	
	def "Check the My Account page address book content using administrator user"(){
		setup: "Login as an administrator"

		login(administrator)
		at HomePage
		masterTemplate.doMyaccount()
		at MyAccountPage

		when: "looking at address book block"
		
		addressBook == 'ADDRESS BOOK'

		then: "you should see view your delivery addresses"
		
		viewYourDeliveryAddress == 'View your delivery addresses'
		
	}
	
	def "Check the My Account page manage users content using administrator user"(){
		setup: "Login as an administrator"

		login(administrator)
		at HomePage
		masterTemplate.doMyaccount()
		at MyAccountPage

		when: "looking at manage users block"
		
		manageUsers == 'MANAGE USERS'

		then: "you should see add new users and edit or disable users"
		
		addNewUsers == 'Add new users'
		editUsers == 'Edit or disable users'
		
	}
	
	def "Check the My Account page order history content using administrator user"(){
		setup: "Login as an administrator"

		login(administrator)
		at HomePage
		masterTemplate.doMyaccount()
		at MyAccountPage

		when: "looking at order history block"
		
		orderHistory == 'ORDER HISTORY'

		then: "you should see view order history"
		
		viewOrderHistory == 'View order history'
				
	}
	/*def "Check the My Account page profile content using administrator user"(){
		setup: "Login as an administrator"

		login (user)
		at HomePage
		masterTemplate.doMyaccount()
		at MyAccountPage

		when: "looking at my profile"
		
		section == sectionValue


		then: "you should see Update prsonal details and Change your password "

		for (Map.Entry entry : sublinks.entrySet()) {entry.key == entry.value}
		//stringMap.each() { key, value -> println "${key} == ${value}" };
		//sublinks.each {key == value}

		where:
		user				|	section		|	headerValue		| sublinks
		administrator	|	profile		|	'PROFILE'		| [updatePersonalDetails: 'Update personal details', changeYourPassword: 'Change your password']
		//notadmin	|	profile		|	'PROFILE'		| [updatePersonalDetails: 'Update personal details', changeYourPassword: 'Change your password']

	}*/
}
