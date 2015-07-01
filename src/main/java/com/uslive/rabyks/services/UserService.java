package com.uslive.rabyks.services;

import java.io.StringWriter;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uslive.rabyks.models.mysql.Role;
import com.uslive.rabyks.models.mysql.User;
import com.uslive.rabyks.repositories.mysql.RoleRepository;
import com.uslive.rabyks.repositories.mysql.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;

	public User findByEmailAndPassword(String email, String password) throws Exception{
		return userRepo.findByEmailAndPassword(email, password);
	}

	public List<Role> findByUserId(int id) throws Exception {
		return roleRepo.findByUserId(id);
	}

	public void save(User user) throws Exception {
		userRepo.save(user);
	}

	public User findByEmail(String email) throws Exception {
		return userRepo.findByEmail(email);
	}

	public String login(String email, String password) throws Exception {
		User user = userRepo.findByEmailAndPassword(email, password);
		List<Role> role = roleRepo.findByUserId(user.getId());
		int[] partners = userRepo.findPartnersByUserId(user.getId());

		JSONObject jobj = new JSONObject();
		jobj.put("id", user.getId());
		jobj.put("email", user.getEmail());
		jobj.put("password", user.getPassword());
		jobj.put("number", user.getNumber());
		jobj.put("role", role.get(0).getRole() == 1 ? "admin" : "konobar");
		
		jobj.put("partners", partners);
		
//		String json = "{\"id\": " + user.getId() + ", " + "\"email\": \"" + user.getEmail() + "\", " + "\"password\": \"" + user.getPassword() + "\", " + "\"number\": \"" + user.getNumber();
//
//		if() {
//			json += "\", " + "\"role\": \"" + "admin\", ";
//		} else if (role.get(0).getRole() == 2) {
//			json += "\", " + "\"role\": \"" + "konobar\", ";
//		}
				
		StringWriter sw = new StringWriter();
		jobj.write(sw);
		return sw.toString();
	}	
}
