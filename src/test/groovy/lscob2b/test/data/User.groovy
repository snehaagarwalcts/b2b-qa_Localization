package lscob2b.test.data

import groovy.transform.ToString;

@ToString
class User {
	String email;
	String password;
	String title;
	String name;
	String surname;
	String address;
	B2BUnit defaultB2BUnit;
	List<String> groups;
	
	def boolean compareTo(User user) {
		return user.email == email && user.title == title && user.name == name && user.surname == surname
	}
	
}
