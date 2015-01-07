package lscob2b.test.category;

import org.spockframework.compiler.model.ExpectBlock;

import spock.lang.Shared;
import spock.lang.Stepwise;
import lscob2b.pages.HomePage;
import lscob2b.pages.LoginPage;
import lscob2b.pages.productcategory.ProductCategoryPage;
import lscob2b.pages.productdetails.ProductDetailsPage;
import lscob2b.test.data.TestDataCatalog;
import lscob2b.test.data.User;
import lscob2b.test.login.LoginFailureTest;
import geb.navigator.Navigator;
import geb.spock.GebReportingSpec;
import static lscob2b.TestConstants.*

@Stepwise
public class CmsCategoriesTest extends GebReportingSpec {

	@Shared
	def currentOpenMenu

	def setupSpec() {
		to LoginPage
		login (TestDataCatalog.getAMultibrandUser())
		at HomePage
	}

	def "All cms categories should be displayed in the navigation menu"(){

		when: "Switch to correct brand if not already there"

		masterTemplate.switchBrandIfNecessary(brand)

		then: "we should be at correct brand" //could not find a good way to confirm this

		when: "Open category menu"

		if (currentOpenMenu != parentCategory) {
			masterTemplate.mouseOverParentCategory(parentCategory)
			currentOpenMenu = parentCategory
		}

		then: "Check category exists in menu"

		masterTemplate.subCategoryLinkExists(subCategory)

		where:

		[brand, parentCategory, subCategory] << new File("src/test/resources/testinput/CmsCategories.txt").readLines()*.tokenize()

	}
}
