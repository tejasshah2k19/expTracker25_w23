package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bean.UserBean;
import com.dao.UserDao;

@Controller
public class SessionController {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserDao userDao;

	@GetMapping({ "/", "register" })
	public String signup() {
		return "Register";// Jsp Name
	}

	@GetMapping("login")
	public String signIN() {
		return "Login";// jsp name
	}

	@PostMapping("saveuser")
	public String saveUser(UserBean user) {
		//
		System.out.println(user.getPassword());

		String encPwd = passwordEncoder.encode(user.getPassword());
		user.setPassword(encPwd);

//		Hash hash = Password.hash(user.getPassword()).addRandomSalt(10).withArgon2();
//		user.setPassword(hash.getResult());

		System.out.println(user.getPassword());
		// db save

		return "Login";
	}

	@PostMapping("authenticate")
	public String authenticate(String email, String password, Model model) {

		UserBean dbUser = userDao.findByEmail(email);
		if (dbUser == null) {
			model.addAttribute("error", "Invalild Credentials");
			return "Login";
		} else {

			boolean ans = passwordEncoder.matches(password, dbUser.getPassword());
			if (ans == true) {
				return "Home";
			}
			model.addAttribute("error", "Invalild Credentials");
			return "Login";
		}
	}

}
