package lscob2b.test.myaccount

import geb.spock.GebReportingSpec
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.myaccount.MyAccountPage
import lscob2b.pages.myaccount.admin.EditUserDetailsPage
import lscob2b.pages.myaccount.admin.ManageUsersPage
import lscob2b.pages.myaccount.admin.UserDetailPage
import lscob2b.test.data.TestDataCatalog
import lscob2b.test.data.TestHelper
import spock.lang.IgnoreIf

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
	
	def loginAndGoToTargetPage(user) {
		login(user)
		
		at HomePage
		masterTemplate.clickMyAccount()
				
		at MyAccountPage
		manageUsers.click()
	}

	def "Check access to ManageUserPage for [b2badmingroup]"() {
		setup:
			login(user)
			
		when: "At HomePage"
			at HomePage
			
		then: "Go to my-account/manage-users"
			browser.go(baseUrl + "my-account/manage-users")
			at ManageUsersPage
			
		where:
			user = TestDataCatalog.getAnAdminUser()
	}
	
	def "Check denied access to ManageUserPage for not [b2badmingroup]"() {
		setup:
			login(user)
			
		when: "At HomePage"
			at HomePage
			
		then: "Go to my-account/manage-users"
			browser.go(baseUrl + "my-account/manage-users")
			at HomePage
			
		where:
			user | _
			TestDataCatalog.getUserNotInGroups([TestDataCatalog.ADMIN_GROUP, TestDataCatalog.FINANCE_GROUP]) | _
			TestDataCatalog.getUserNotInGroups([TestDataCatalog.ADMIN_GROUP, TestDataCatalog.CUSTOMER_GROUP]) | _
	}
	
	@IgnoreIf({ System.getProperty("geb.browser").contains("safari") })
	def "Change default delivery address"() {
		setup:
			loginAndGoToTargetPage(loginUser)
			
		when: "at manage users page"
			at ManageUsersPage
//			println "current user ${loginUser.email}"
		
		then: "search for target user"
//			println "searching for user ${targetUser.email}"
			def detailLink = searchUserDetailLinkInAllPages(targetUser.email)
			assert !detailLink.empty
			detailLink.click()
			
		when: "at user detail page"
			at UserDetailPage
			
		then: "check current delivery address"
			def initialDeliveryAddress = getDefaultDeliveryAddress()
//			println "current delivery address is ${initialDeliveryAddress}"
			assert !editButton.empty
			editButton.click()
			
		when: "at edit user detail page"
			at EditUserDetailsPage
			
		then: "change delivery address"
			def oldDA = getDefaultDeliveryAddressSelected().text()
			changeDefaultDeliveryAddress()
			def newDA = getDefaultDeliveryAddressSelected().text()
					
		and: "check change of delivery address on edit page"
			assert oldDA != newDA
			userDetails.saveButton.click()
			
		when: "at user detail page"
			at UserDetailPage	
			
		then: "check change of delivery address on detail page"
			def updateDeliveryAddress = getDefaultDeliveryAddress()
//			println "new delivery addres is ${updateDeliveryAddress}"
			assert updateDeliveryAddress != initialDeliveryAddress
			
		where:
			loginUser | targetUser
			TestDataCatalog.getAnAdminUser() | TestDataCatalog.getAnotherUserOfSameBU(TestDataCatalog.getAnAdminUser(), TestDataCatalog.ADMIN_GROUP)
			TestDataCatalog.getAnAdminUser() | TestDataCatalog.getAnotherUserOfSameBU(TestDataCatalog.getAnAdminUser(), TestDataCatalog.CUSTOMER_GROUP)
			TestDataCatalog.getAnAdminUser() | TestDataCatalog.getAnotherUserOfSameBU(TestDataCatalog.getAnAdminUser(), TestDataCatalog.FINANCE_GROUP)
	}
		
}
