//created by I844489 on 12/10/2014

package lscob2b.pages.myaccount.admin

import geb.Page
import geb.navigator.Navigator;
import lscob2b.modules.MasterTemplate

class ManageUsersPage extends Page{

	static url = "my-account/manage-users"

	static at = { waitFor { title == "Your Account | LSCO B2B Site" || title == "DE_Your Account | LSCO B2B Site"} }

	static content = {
		
		masterTemplate { module MasterTemplate }

		tableUsers(required:false) { $("table#manage_user") }
		
		buttonCreateNewUser { $("a.addnewuser") }

		//Localization
		breadcrumbLink { $('#breadcrumb>ul>li:nth-child(5)') }
		
		usersFoundLabel{ $(".totalResults") }
		
		pageLabel{ $(".pagination>span") }
		
		nameLabel{ $("#header1") }
		
		rolesLabel{ $("#header2") }
		
		statusLabel{ $("#header5") }
		
		selectFirstUserLink {$('.odd>td>a',0)}
								
	}
	
	def clickOnSelectFirstUserLink()
	{
		waitFor { selectFirstUserLink.displayed }
		selectFirstUserLink.click()
	}
	
}
