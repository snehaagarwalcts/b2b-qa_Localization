package lscob2b.test.data

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.WebDriverWait

class TestHelper {

	def static final String PAGE_LOGOUT		= "en/logout"
	
	def static void waitForPage(driver, String title) {
		//Hack to make login working on Safari&IE
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getTitle().startsWith(title);
			}
		});
	}
	
}
