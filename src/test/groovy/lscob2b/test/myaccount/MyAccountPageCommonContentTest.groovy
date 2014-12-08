package lscob2b.test.myaccount

/**
 * Created by i844489 on 12/4/2014.
 */

import geb.spock.GebReportingSpec
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.MyAccountPage
import lscob2b.modules.MasterTemplate
import spock.lang.Stepwise

class MyAccountPageCommonContentTest extends GebReportingSpec {
	String defaultPassword = "12341234"
	String administrator = "robert.moris@monsoon.com"
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

		login (user)
		at HomePage
		masterTemplate.doMyaccount()
		at MyAccountPage

		when: "looking at my profile"
		
		section == sectionValues


		then: "you should see Update prsonal details and Change your password "

		for (Map.Entry entry : sublinks.entrySet()) {
			entry.key == entry.value
		}

		where:		

		user		|	section		|	headerValue		| sublinks
		admin		|	profile		|	'PROFILE'		| [updatePersonalDetails: 'Update personal details', changeYourPassword: 'Change your password']
		notadmin	|	profile		|	'PROFILE'		| [updatePersonalDetails: 'Update personal details', changeYourPassword: 'Change your password']

	}
}
