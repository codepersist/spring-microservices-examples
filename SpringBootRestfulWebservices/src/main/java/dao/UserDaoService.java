package dao;

import java.util.List;

import models.User;

public interface UserDaoService {

	List<User> findAll();

	User save(User user);

	User findOne(int id);
	
	User deleteUserById(int id);
}
