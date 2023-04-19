package com.portfolio.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
//Jpa/Hiberante >Database	

	private static int usersCount= 0;
	private static List<User> users=new ArrayList<>();
	static {
		users.add(new User(++usersCount,"adam",LocalDate.now().minusYears(24)));
		users.add(new User(++usersCount,"hariharan",LocalDate.now().minusYears(23)));
		users.add(new User(++usersCount,"eve",LocalDate.now().minusYears(22)));
	}
	public List<User>findAllUsers(){
		return users;
	}
	public User findOne(int id) {
		Predicate<? super User> predicate=user->user.getId().equals(id);
		return users.stream().filter(predicate).findFirst().orElse(null);
	}
	public void deleteById(int id) {
		Predicate<? super User> predicate=user->user.getId().equals(id);
		users.removeIf(predicate);
		
	}
	public User save(User user) {
		user.setId(++usersCount);
		users.add(user);
		return user;
	}
}
