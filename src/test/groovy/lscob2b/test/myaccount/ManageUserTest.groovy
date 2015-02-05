package lscob2b.test.myaccount

import geb.spock.GebReportingSpec
import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.MyAccount.admin.EditUserDetailsPage
import lscob2b.pages.MyAccount.admin.ManageUsersPage
import lscob2b.pages.MyAccount.admin.ViewUserDetailsPage
import lscob2b.test.data.TestHelper
import spock.lang.Ignore

class ManageUserTest extends GebReportingSpec {

	def setupSpec() {
		browser.go(baseUrl + TestHelper.PAGE_LOGOUT)
	}
	
	def setup() {
		to LoginPage
	}
	
	def cleanup() {
		masterTemplate.doLogout()
	}
	
	def "Check access to ManageUserPage for [b2badmingroup]"() {
		setup:
			login(user)
			
		when: "At HomePage"
			at HomePage
			
		and: "Go to my-account/manage-users"
			masterTemplate.clickMyAccount()
		
		then: "At ManageUsers page"
			at ManageUsersPage
			
		where:
			user | _
			UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_ADMIN) | _
			UserHelper.getUser(UserHelper.B2BUNIT_DOCKERS, UserHelper.ROLE_ADMIN) | _
			UserHelper.getUser(UserHelper.B2BUNIT_MULTIBRAND, UserHelper.ROLE_ADMIN) | _
	}
	
	def "Check denied access to ManageUserPage for not [b2badmingroup]"() {
		setup:
			login(user)
			
		when: "At HomePage"
			at HomePage
			
		and: "Go to my-account/manage-users"
			masterTemplate.clickMyAccount()
			
		then: "Redirect to home page"
			at HomePage
			
		where:
			user | _
			UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_CUSTOMER) | _
			UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_FINANCE) | _
			UserHelper.getUser(UserHelper.B2BUNIT_DOCKERS, UserHelper.ROLE_CUSTOMER) | _
			UserHelper.getUser(UserHelper.B2BUNIT_DOCKERS, UserHelper.ROLE_FINANCE) | _
			UserHelper.getUser(UserHelper.B2BUNIT_MULTIBRAND, UserHelper.ROLE_CUSTOMER) | _
			UserHelper.getUser(UserHelper.B2BUNIT_MULTIBRAND, UserHelper.ROLE_FINANCE) | _
	}
	
	@Ignore
	def "Change default delivery address"() {
		setup:
			login(loginUser)
			at HomePage
			PageHelper.gotoPageViewUserDetail(browser, baseUrl, targetUser.email)
		
		when: "At UserDetail page"
			at ViewUserDetailsPage
			
		and: "Edit current user"		
			def currentUser = userDetails.getUser()
			userDetails.editUserButton.click()
			
		then: "At EditUserDetail page"
			at EditUserDetailsPage
		
		when: "At EditUserDetail page"
			at EditUserDetailsPage
			
		and: "Change delivery address"
			userDetails.changeDefaultDeliveryAddress()
					
		and: "Save User"
			userDetails.saveButton.click()
			
		then: "At UserDetail page"
			at ViewUserDetailsPage	
			
		and: "Check updated delivery address"
			currentUser.address != userDetails.getUser().address
						
		where:
			loginUser | targetUser
			UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_ADMIN) | UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_CUSTOMER)
			
	}
		
}
