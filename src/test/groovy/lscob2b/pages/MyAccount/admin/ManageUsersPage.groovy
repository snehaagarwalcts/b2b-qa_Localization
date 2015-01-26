//created by I844489 on 12/10/2014

package lscob2b.pages.MyAccount.admin

import geb.Page
import geb.navigator.Navigator;
import lscob2b.modules.MasterTemplate

class ManageUsersPage extends Page{

	static url = "my-account/manage-users"

	static at = { waitFor { title == "LSCO B2B Site" } }

	static content = {
		masterTemplate { module MasterTemplate }

		manageUsersData { $("form table tr th") }
		
		createNewUserLink { $("a.addnewuser") }
		
		manageUsers { $("#breadcrumb li").not('separator')*.text()}
		
		userTable { $("table#manage_user") }
		
		userRows(required: false) { userTable.find("tbody>tr") }
		
		pagination(required: false) { $("div.pagination") }
		
		nextPage(required: false, to: ManageUsersPage) { pagination.find("a.nextPage") }

	}

	def clickCreateNewUsersLink(){
		createNewUserLink.click()
	}

	def clickUserLink(String email){
		$("#manage_user a", href : endsWith(email)).click()
	}
	
	def checkManageUsersDataExists(){
		!manageUsersData.empty
	}
	
	def checkCreateNewUsersLinkExists(){
		!createNewUserLink.empty
	}
	
	//Getter
		
	def getUsername(index) {
		userRows[index].find("td",0).find("a").text()
	}

	def searchUserDetailLink(userEmail) {
		userRows.find("a", href: endsWith(userEmail))
	}
	
	def searchUserDetailLinkInAllPages(userEmail) {
		def userLink = searchUserDetailLink(userEmail)
		while(userLink.empty && !nextPage.empty) {
			nextPage.click()
			userLink = searchUserDetailLink(userEmail)
		}
		userLink
	}
		
	
}
