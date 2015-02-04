package lscob2b.test.myaccount

import static lscob2b.TestConstants.*
import geb.spock.GebReportingSpec
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.myaccount.AddressBookPage
import lscob2b.pages.myaccount.MyAccountPage
import lscob2b.pages.myaccount.OrderHistoryPage
import lscob2b.pages.myaccount.ProfilePage
import lscob2b.pages.myaccount.admin.ManageUsersPage

class MyAccountTest extends GebReportingSpec {

	def setup() {
		to LoginPage
	}

	def cleanup() {
		masterTemplate.doLogout()
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

	def "My account is accessible"() {
		when: "logged in as any user"

		login(user)

		then: "My account link is available"
		at HomePage

		masterTemplate.clickMyAccount()

		at MyAccountPage

		where:

		user << [levisUser, dockersUser]    // TODO change for user roles
	}

	def "Check breadcrumbs on My account page"() {
		// tests the login itself without worrying about rights
		when: "At My Account page"

		login(user)
		at HomePage
		to MyAccountPage
		at MyAccountPage

		then: "There should be 2 breadcrumbs"
		and: "1 should be home, the other should be 'my account'"
		and: "The text should be correct"

		masterTemplate.breadCrumbs.size() == 2

		def homeBC = masterTemplate.getBreadCrumbByUrl("/")

		homeBC
		homeBC.text().toUpperCase() == 'HOME'

		masterTemplate.isBreadCrumbActive("My Account")

		where:
		user << [levisUser, dockersUser, multibrandUser]

	}

	//Fix this test to check for what content is there
	/*	def "Check the My Account page"(){
	 when: "Going to My Account page"
	 loginAsUserAndGoToMyAccount(user)
	 then: "Correct sections/links should be visible depending on user"
	 // check main section
	 if (isVisible) {
	 assert page.getContent(section) == headerValue
	 // check links for each section
	 sublinks.each { k,v ->
	 assert page.getContent(k).toUpperCase() == v.toUpperCase()
	 }
	 } else {
	 page.getContent(section) == null
	 }
	 where:
	 isVisible	| user			| section			| headerValue		| sublinks
	 true 		| administrator	| 'profile'			| 'PROFILE'			| [updatePersonalDetails	: 'Update personal details'		, changeYourPassword: 'Change your password'	]
	 true 		| administrator	| 'addressBook'		| 'ADDRESS BOOK'	| [viewYourDeliveryAddress	: 'View My Address Book'												]
	 true 		| administrator	| 'orderHistory'	| 'ORDER HISTORY'	| [viewOrderHistory			: 'View order history'															]
	 true 		| administrator	| 'manageUsers'		| 'MANAGE USERS'	| [addNewUsers				: 'Add new users'				, editUsers			: 'Edit or disable users'	]
	 true 		| nonAdmin		| 'profile'			| 'PROFILE'			| [updatePersonalDetails: 'Update personal details', changeYourPassword: 'Change your password']
	 true 		| nonAdmin		| 'addressBook'		| 'ADDRESS BOOK'	| [viewYourDeliveryAddress: 'View My Address Book']
	 true 		| nonAdmin		| 'orderHistory'	| 'ORDER HISTORY'	| [viewOrderHistory: 'View order history']
	 false 		| nonAdmin		| 'manageUsers'		| 'MANAGE USERS'	| [addNewUsers: 'Add new users', editUsers: 'Edit or disable users']
	 }*/

	//Partially refractored test
	def "Check the My Account page using admin user"(){
		when: "Going to My Account page"

		loginAsUserAndGoToMyAccount(administrator)

		then: "Correct sections/links should be visible depending on user"
		checkProfileLinkExists()
		checkUpdatePersonalDetailsLinkExists()
		checkChangeYourPasswordLinkExists()

		checkAddressBookLinkExists()
		checkViewYourDeliveryAddressLinkExists()

		checkManageUsersLinkExists()
		checkAddNewUserLinkExists()
		checkEditUsersLinkExists()

		checkOrderHistoryLinkExists()
		checkViewOrderHistoryLinkExists()

	}

	def "Check the My Account page using non admin user"(){
		when: "Going to My Account page"
		
		loginAsUserAndGoToMyAccount(nonAdmin)

		then: "Correct sections/links should be visible depending on user"
		checkProfileLinkExists()
		checkUpdatePersonalDetailsLinkExists()
		checkChangeYourPasswordLinkExists()

		checkAddressBookLinkExists()
		checkViewYourDeliveryAddressLinkExists()

		!manageUsers.displayed
		!addNewUsers.displayed
		!editUsers.displayed

		checkOrderHistoryLinkExists()
		checkViewOrderHistoryLinkExists()
		
	}

	def "Check the Profile page content"() {
		setup:
		loginAsUserAndGoToMyAccount(user)
		profileLink.click()

		when: "At profile page"
		at ProfilePage

		then: "Correct sections/links should be visible"
		/*profileData.contains("TITLE:")
		 profileData.contains("FIRST NAME:")
		 profileData.contains("LAST NAME:")
		 profileData.contains("EMAIL:")*/
		checkProfileDataExists()
		checkUpdatePersonalDetailsLinkExists()
		checkChangeYourPasswordLinkExists()

		where:
		user << [levisUser, dockersUser, multibrandUser]
	}

	def "Check Breadcrumb on Profile Page"(){
		setup:
		loginAsUserAndGoToMyAccount(user)
		profileLink.click()

		when: "At profile page"
		at ProfilePage

		then: "There should be 3 breadcrumbs"
		and: "1 should be home, 2 should be 'my account', and other should be 'profile'"
		and: "The text should be correct"

		masterTemplate.breadCrumbs.size() == 3

		def homeBC = masterTemplate.getBreadCrumbByUrl("/")
		def myAccountBC = masterTemplate.getBreadCrumbByUrl("/my-account")

		homeBC
		homeBC.text().toUpperCase() == 'HOME'

		myAccountBC
		myAccountBC.text().toUpperCase()  == 'MY ACCOUNT'

		masterTemplate.isBreadCrumbActive("Profile")

		where:
		user<<[levisUser, dockersUser, multibrandUser]
	}

	/*	//Address book page content //Don't need this since Simone created address book test  //TODO add more content as page gets developed
	 def "Check the Address book page content"(){
	 setup:
	 loginAsUserAndGoToMyAccount(user)
	 addressBookLink.click()
	 when: "At address book page"
	 at AddressBookPage
	 then: "Correct sections/links should be visible"
	 addressBookData.contains("VIEW MY ADDRESS BOOK")
	 addressBookData.contains("VIEW YOUR BILLING ADDRESSES")
	 addressItem.contains("Cassilis Road")
	 addressItem.contains("12, Turner House, Canary Central")
	 addressItem.contains("Dublin")
	 addressItem.contains("E149LJ")
	 addressItem.contains("Ireland")
	 where:
	 user<<[levisUser]
	 }*/

	def "Check Breadcrumb on Address Book Page"(){
		setup:
		loginAsUserAndGoToMyAccount(user)
		addressBookLink.click()

		when: "At Address Book page"
		at AddressBookPage

		then: "There should be 3 breadcrumbs"
		and: "1 should be home, 2 should be 'my account', and other should be 'address book'"
		and: "The text should be correct"

		masterTemplate.breadCrumbs.size() == 3

		def homeBC = masterTemplate.getBreadCrumbByUrl("/")
		def myAccountBC = masterTemplate.getBreadCrumbByUrl("/my-account")

		homeBC
		homeBC.text().toUpperCase() == 'HOME'

		myAccountBC
		myAccountBC.text().toUpperCase()  == 'MY ACCOUNT'

		masterTemplate.isBreadCrumbActive("Address Book")

		where:
		user<<[levisUser, dockersUser, multibrandUser]
	}

	//Manage Users page content
	def "Check the Manage Users page content"(){
		setup:
		loginAsUserAndGoToMyAccount(user)
		manageUsers.click()

		when: "At manage users page"
		at ManageUsersPage

		then: "Correct sections/links should be visible"
		/*manageUsersData.contains("NAME")
		 manageUsersData.contains("ROLES")
		 manageUsersData.contains("STATUS")
		 createNewUser.contains("CREATE NEW USER")*/
		checkManageUsersDataExists()
		checkCreateNewUsersLinkExists()

		where:
		user<<[levisUser, dockersUser, multibrandUser]
	}

	def "Check Breadcrumb on Manage Users Page"(){
		setup:
		loginAsUserAndGoToMyAccount(user)
		manageUsers.click()

		when: "At Manage Users page"
		at ManageUsersPage

		then: "There should be 3 breadcrumbs"
		and: "1 should be home, 2 should be 'my account', and other should be 'manage users'"
		and: "The text should be correct"

		masterTemplate.breadCrumbs.size() == 3

		def homeBC = masterTemplate.getBreadCrumbByUrl("/")
		def myAccountBC = masterTemplate.getBreadCrumbByUrl("/my-account")

		homeBC
		homeBC.text().toUpperCase() == 'HOME'

		myAccountBC
		myAccountBC.text().toUpperCase()  == 'MY ACCOUNT'

		manageUsers.contains("MANAGE USERS")
		//checkManageUsersBreadCrumbExists() //checks HTML elements instead of text

		where:
		user<<[levisUser]
	}

	//Uncomment once we are able to place orders
	/*def "Check the Order History page content"(){
	 setup:
	 login(user)
	 at HomePage
	 masterTemplate.clickQuickOrder()
	 at QuickOrderPage
	 doSearch('05527-0458')
	 sizingGrid.addOrderQuantity('1')
	 sizingGrid.addToCart()
	 doCheckOut()
	 at CheckOutPage
	 doPlaceOrder()
	 at OrderConfirmationPage
	 masterTemplate.clickMyAccount()
	 at MyAccountPage
	 orderHistoryLink.click()
	 when: "At order history page"
	 at OrderHistoryPage
	 //TODO remove below text and look for HTML Elements instead
	 then: "Correct sections/links should be visible"
	 checkOrderHistoryData()
	 ["ORDERS FOUND","SORT BY:"].each {
	 orderHistoryBar.contains(it)
	 }
	 ["DATE PLACED",
	 "ORDER NUMBER",
	 "ORDER STATUS",
	 "ORDER TYPE",
	 "TOTAL",
	 "ORDER SOURCE",
	 "ACTIONS"].each {
	 orderHistoryListTable.contains(it)
	 }
	 checkOrderHistoryDescription()
	 checkOrderHistoryBar()
	 checkOrderHistoryListTable()
	 where:
	 user << [levisUser]
	 }*/

	def "Check Breadcrumb on Order History Page"(){
		setup:
		loginAsUserAndGoToMyAccount(user)
		orderHistoryLink.click()

		when: "At Order History page"
		at OrderHistoryPage

		then: "There should be 3 breadcrumbs"
		and: "1 should be home, 2 should be 'my account', and other should be 'order history'"
		and: "The text should be correct"

		masterTemplate.breadCrumbs.size() == 3

		def homeBC = masterTemplate.getBreadCrumbByUrl("/")
		def myAccountBC = masterTemplate.getBreadCrumbByUrl("/my-account")

		homeBC
		homeBC.text().toUpperCase() == 'HOME'

		myAccountBC
		myAccountBC.text().toUpperCase()  == 'MY ACCOUNT'

		masterTemplate.isBreadCrumbActive("Order History")

		where:
		user<<[levisUser]
	}
}

//in setup login with levis user and create the test user.
