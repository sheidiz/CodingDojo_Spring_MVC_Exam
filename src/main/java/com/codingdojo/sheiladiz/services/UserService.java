package com.codingdojo.sheiladiz.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.sheiladiz.models.User;
import com.codingdojo.sheiladiz.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository ur;

	public User register(User newUser, BindingResult result) {

		String password = newUser.getPassword();
		String confirm = newUser.getConfirm();
		if (!password.equals(confirm)) {
			result.rejectValue("confirm", "Matches", "Password and confirmation don't match");
		}

		String email = newUser.getEmail();
		User userExist = ur.findByEmail(email);
		if (userExist != null) {
			result.rejectValue("email", "Unique", "E-mail already exists");
		}

		if (result.hasErrors()) {
			return null;
		} else {
			String passHash = BCrypt.hashpw(password, BCrypt.gensalt());
			newUser.setPassword(passHash);
			return ur.save(newUser);
		}

	}

	public User login(String email, String password) {
		User userTryingLogin = ur.findByEmail(email);

		if (userTryingLogin == null) {
			return null;
		}

		if (BCrypt.checkpw(password, userTryingLogin.getPassword())) {
			return userTryingLogin;
		} else {
			return null;
		}

	}

	public List<User> allUsers() {
		return ur.findAll();
	}
	
	public User findUser(Long id) {
		return ur.findById(id).orElse(null);
	}

	public User saveUser(User user) {
		return ur.save(user);
	}
}
