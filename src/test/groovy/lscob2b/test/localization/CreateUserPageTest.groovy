package lscob2b.test.localization
import lscob2b.data.PageHelper
import lscob2b.pages.myaccount.MyAccountPage
import lscob2b.data.UserHelper
import lscob2b.pages.ContactUsPage
import lscob2b.pages.HomePage;
import lscob2b.pages.LoginPage
import lscob2b.test.data.PropertProviderTest
import lscob2b.pages.myaccount.ProfilePage
class CreateUserPageTest extends PropertProviderTest {
	
	
	def setup() {
		PageHelper.gotoPageLogout(browser, baseUrl)
	}
	
	def "Verify Profile Page Fields"(){
		setup:
		to LoginPage
		at LoginPage
		login(user)
		at HomePage
		masterTemplate.clickMyAccount()
		
		when: "at MyAccountPage"
		at MyAccountPage
		
		and: "click on Add New Users Link "
		clickAddNewUsers()
		
		then: "at Add Users Details Page" 
		assert errorMessage.text()==expectedValue("loginPage.error.accountNotFound")
		
where:
user=UserHelper.getInvalidUser()

}
}