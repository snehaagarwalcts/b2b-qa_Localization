package lscob2b.modules

import geb.Module
import geb.navigator.Navigator

class CategoryNavigationModule extends Module {

	static content = {
		firstMenuItem {$("li.yCmsContentSlot > h2 > a", 0)} //ie "MEN"
		firstCategoryLink {$(".subnav-column > ul > .yCmsComponent >a", 0)}
	}

	def mouseOverFirstMenuItem(){
		firstMenuItem.jquery.mouseover()
	}

	def clickFirstCategoryLink() {
		mouseOverFirstMenuItem(); //put mouse over to the menu in order to make it visible. (Geb cannot access invisible elements)
		firstCategoryLink.click()
	}
}
