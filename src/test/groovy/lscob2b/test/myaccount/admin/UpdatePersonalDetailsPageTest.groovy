package lscob2b.test.myaccount.admin

import lscob2b.data.PageHelper
import lscob2b.data.UserHelper
import lscob2b.pages.HomePage
import lscob2b.pages.LoginPage
import lscob2b.pages.myaccount.MyAccountPage
import lscob2b.pages.myaccount.ProfilePage
import lscob2b.pages.myaccount.admin.UpdatePersonalDetailsPage
import lscob2b.test.data.PropertProviderTest

class UpdatePersonalDetailsPageTest extends PropertProviderTest{
	
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}
	
	def "Verify translations in UpdatePersonalDetails Page"(){
		
			setup:
			to LoginPage
			at LoginPage
			login(user)
			at HomePage
			masterTemplate.clickMyAccount()
			
			when: "at MyAccountPage"
			at MyAccountPage
			
			then: "click on Profile Link"
			clickOnProfile()
			
			when: "at ProfilePage"
			at ProfilePage
			
			then: "click on Update Personal Details Link"
			clickOnUpdatePersonalDetailsLink()
			
			when: "at UpdatePersonalDetailsPage"
			at UpdatePersonalDetailsPage
			
			then: "verify translations in UpdatePersonalDetailsPage"
			assert masterTemplate.breadCrumbActive.text()==expectedValue("text.account.profile").toUpperCase()
			assert masterTemplate.mainContainerLabel.text()==expectedValue("text.account.profile").toUpperCase()
			assert masterTemplate.introContainerLabel.text() == expectedValue("text.account.profile.update.subtitle")
			assert masterTemplate.requiredMessageText.text()== expectedValue("address.required")
			assert updateProfileLabel(0).text() == expectedValue("profile.title").toUpperCase()
			assert updateProfileLabel(1).text() == expectedValue("profile.firstName").toUpperCase()
			assert updateProfileLabel(2).text() == expectedValue("profile.lastName").toUpperCase()
			assert cancelButton.text()- ~/&/ == expectedValue("cancelButton.displayName").toUpperCase()
			assert saveButton.text() == expectedValue("text.account.profile.saveUpdates").toUpperCase()
			
			when: "click on Save button"
			clickSaveButton()
			
			then: "verify translation of PROFILE UPDATE message"
			assert masterTemplate.noteMessageHeader.text() == expectedValue("text.please.note").toUpperCase()
			assert masterTemplate.noteMessage.text().replaceAll(masterTemplate.noteMessageHeader.text()+"\n","")+"." == expectedValue("account.confirmation.profile.updated")     
			
			where:
			user=UserHelper.getUser(UserHelper.B2BUNIT_LEVIS, UserHelper.ROLE_SUPER)
	}
	
}
