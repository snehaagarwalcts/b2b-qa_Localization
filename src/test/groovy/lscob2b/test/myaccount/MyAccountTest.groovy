package lscob2b.test.myaccount

import static lscob2b.TestConstants.*
import geb.spock.GebReportingSpec
import lscob2b.data.UserHelper
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

	/**
	 * TC BB-341 Automated Test Case: Any User should see the "My Account" link in the 
	 * right-side of the header section of the Application, that should redirect the user to the My Account Home Page.
	 */
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

	/**
	 * TC BB-362 Automated Test Case: Validate the Breadcrumb Trail from the My Account - "My Account" Page.
	 */
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

	/**
	 * TC BB-367 Automated Test Case: Validate the content of the My Account - "User Profile" Page for any user.
	 */
	def "Check the Profile page content"() {
		setup:
		loginAsUserAndGoToMyAccount(user)
		profileLink.click()

		when: "At profile page"
		at ProfilePage

		then: "Correct sections/links should be visible"
		checkProfileDataExists()
		checkUpdatePersonalDetailsLinkExists()
		checkChangeYourPasswordLinkExists()

		where:
		user << [levisUser, dockersUser, multibrandUser]
	}

	/**
	 * TC BB-368 Automated Test Case: Validate the Breadcrumb Trail from the My Account - "User Profile" Page.
	 */
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

	/**
	 * TC BB-426 Automated Test Case: Validate the Breadcrumb Trail from the My Account - "Address Book" Page.
	 */
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

	/**
	 * TC BB-424 Automated Test Case: Validate the content of the My Account - "Manage Users" Page for b2badministrator user.
	 * @return
	 */
	def "Check the ManageUsers page structure"(){
		setup:
			at LoginPage
			login(user)
			
			at HomePage
			masterTemplate.clickMyAccount()
		
		when: "At MyAccount page"
			at MyAccountPage
							
		and: "Go to ManageUser page"
			manageUsers.click()
			
		then: "At ManageUser Page" 	
			at ManageUsersPage	
			
		and: "Check breadcrumb"
			waitFor { masterTemplate.breadCrumbs.size() == 3 }
			masterTemplate.breadCrumbs.size() == 3
			masterTemplate.breadCrumbs[0].text() == 'HOME'
			masterTemplate.breadCrumbs[1].text() == 'MY ACCOUNT'
			masterTemplate.breadCrumbs[2].text() == 'MANAGE USERS'
			
		and: "Check page element"
			!buttonCreateNewUser.empty
			//TODO more elements!!!
			
		where:
			user | _
			UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_ADMIN) | _
			UserHelper.getUser(UserHelper.B2BUNIT_DOCKERS, UserHelper.ROLE_ADMIN) | _
			UserHelper.getUser(UserHelper.B2BUNIT_MULTIBRAND, UserHelper.ROLE_ADMIN) | _
		
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

	/**
	 * BB-428 Automated Test Case: Validate the Breadcrumb Trail from the My Account - "Order History" Page.
	 */
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
