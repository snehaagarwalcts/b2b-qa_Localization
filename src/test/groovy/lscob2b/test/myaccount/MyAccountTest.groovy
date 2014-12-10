package lscob2b.test.myaccount

import geb.spock.GebReportingSpec
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.MyAccount.MyAccountPage;
import lscob2b.pages.MyAccount.ProfilePage;
import lscob2b.pages.MyAccount.ManageUsersPage;
import lscob2b.pages.MyAccount.OrderHistoryPage;
import lscob2b.pages.MyAccount.AddressBookPage;
import static lscob2b.TestConstants.*

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

    def "Check breadcrumbs on My account page"() { // tests the login itself without worrying about rights
        when: "At My Account page"

        doLogin(levisUser, defaultPassword)
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

    }

    def "Check the My Account page"(){
        when: "Going to My Account page"

        loginAsUserAndGoToMyAccount(user)

        then: "Correct sections/links should be visible depending on user"

        // check main section
        page.getContent(section) == headerValue

        // check links for each section
        sublinks.each { k,v ->
            assert page.getContent(k).toUpperCase() == v.toUpperCase()
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

    def "Check the Profile page content"() {
        setup:
        loginAsUserAndGoToMyAccount(user)
        profileLink.click()

        when: "At profile page"
        at ProfilePage

        then: "Correct sections/links should be visible"
        profileData.contains("Title:")
        profileData.contains("First Name:")
        profileData.contains("Surname:")
        profileData.contains("Email Address:")
        updatePersonalDetails == 'UPDATE PERSONAL DETAILS'
        changeYourPassword == 'CHANGE YOUR PASSWORD'

        where:
        user << [levisUser, dockersUser, multibrandUser]
    }

    //Address book page content  //TODO add more content as page gets developed
	 def "Check the Address book page content"(){
		 setup:
		 loginAsUserAndGoToMyAccount(user)
		 addressBookLink.click()

		 when: "At address book page"
		 at AddressBookPage

		 then: "Correct sections/links should be visible"
		 addressBookData.contains("View your delivery addresses")
		 addressBookData.contains("View your billing addresses")

		 where:
		 user<<[levisUser, dockersUser, multibrandUser]
	 }

    //Manage Users page content  
	 def "Check the Manage Users page content"(){
		  setup:
		  loginAsUserAndGoToMyAccount(user)
		  manageUsersLink.click()

		  when: "At manage users page"
		  at ManageUsersPage

		  then: "Correct sections/links should be visible"
		  manageUsersData.contains("Name")
		  manageUsersData.contains("Roles")
		  manageUsersData.contains("Status")
		  createNewUser == ("CREATE NEW USER")

		  where:
		  user<<[levisUser, dockersUser, multibrandUser]
	  }

    //Order History page content  //TODO add more content as page gets developed
	 def "Check the Order History page content"(){
		  setup:
		  loginAsUserAndGoToMyAccount(user)
		  orderHistoryLink.click()

		  when: "At order history page"
		  at OrderHistoryPage

		  then: "Correct sections/links should be visible"
		  orderHistoryData == "ORDER HISTORY"

		  where:
		  user<<[levisUser, dockersUser, multibrandUser]
	  }
	 
	/* def "Check Breadcrumb on Profile Page"(){
		 setup:
		 loginAsUserAndGoToMyAccount(user)
		 profileLink.click()
		 
		 when: "At profile page"
		 at ProfilePage
		 
		 then: "There should be 2 breadcrumbs"
       and: "1 should be home, the other should be 'my account'"
       and: "The text should be correct"
	 }*/
}

//in setup login with levis user and create the test user.