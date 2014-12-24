package lscob2b.modules

import geb.Module
import geb.navigator.Navigator

class UtilModule extends Module {

	def mouseOverHrefEndsWith(endsWithStr){
		$("a", href: endsWith(endsWithStr)).jquery.mouseover()
	}
}
