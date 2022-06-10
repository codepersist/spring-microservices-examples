package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import models.User;

@Component
public class UserDaoServiceImpl implements UserDaoService {

	private static List<User> usersList = new ArrayList<>();
	private static int userCount=4;
	static {
		usersList.add(new User(1, "Adam", new Date()));
		usersList.add(new User(2, "Eve", new Date()));
		usersList.add(new User(3, "Jack", new Date()));
		usersList.add(new User(4, "Ryan", new Date()));
	}

	@Override
	public List<User> findAll()
	{
		return usersList;
	}

	@Override
	public User save(User user)
	{
		if(user.getId()==null)
		{
			user.setId(++userCount);
		}
		usersList.add(user);
		return user;
	}

	@Override
	public User findOne(int id)
	{
		for(User user :usersList)
		{
			if(user.getId()==id)
				return user;
		}
		return null;
	}

	@Override
	public User deleteUserById(int id) {
		Iterator<User> iterator = usersList.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
}
