package lscob2b.test.myaccount

import geb.spock.GebReportingSpec
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.MyAccountPage
import static lscob2b.TestConstants.*

class MyAccountTest extends GebReportingSpec {

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

    def "My account is accessible"() {
        when: "logged in as any user"

        doLogin(user)

        then: "My account link is available"

        myaccountLink

        masterTemplate.clickMyAccount()

        at MyAccountPage

        where:

        user    // TODO change for user roles
        levisUser
        dockersUser
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

        masterTemplate.breadcrumbs.size() == 2

        def homeBC = masterTemplate.getBreadcrumbByUrl("/")

        homeBC
        homeBC.text().toUpperCase() == 'HOME'

        masterTemplate.isBreadcrumbActive("My Account")

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


}
