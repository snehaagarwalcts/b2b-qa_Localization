package lscob2b.data

import geb.Page

class PropertyProviderPage extends Page{

	private static final INSTANCE = new PropertyProviderPage()
	
		Properties props	
			
		static getInstance(){
			return INSTANCE
		}
	
		private PropertyProviderPage() {
			props = new Properties()
			new File("test.properties").withInputStream { stream ->
				props.load(stream)
			}
		}
		
		String getLocalizedPropertyValue(String property){
			props[property]
		}
}
