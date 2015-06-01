package com.uslive.rabyks.controllers.mvc;

import java.security.MessageDigest;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.uslive.rabyks.common.RoleEnum;
import com.uslive.rabyks.models.mysql.User;
import com.uslive.rabyks.repositories.mysql.RoleRepository;
import com.uslive.rabyks.repositories.mysql.UserRepository;

@Controller
public class LoginController {

	private Logger log = LoggerFactory.getLogger("LoginController.class");
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private RoleRepository roleRepository;
	
	@Autowired
	private Environment env;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void register(@RequestBody String user) {
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			JSONObject jsonUser = new JSONObject(user);
			
			String username = jsonUser.getString("username");
			String email = jsonUser.getString("email");
			String number = jsonUser.getString("number");
			String password = jsonUser.getString("password");
			String hash = jsonUser.getString("hash");
			String userToHash = username + " " + email + " " + password + " " + number;
			
			String hashed = hashPost(env.getProperty("register_url"), userToHash);
			if (hashed.equals(hash)) {
				User userDB = new User();
				userDB.setPassword(password);
				userDB.setNumber(number);
				userDB.setEmail(email);
				userDB.setRole(roleRepository.findByRole(RoleEnum.USER.ordinal()));
				userRepository.save(userDB);
			} else {
				log.error("Hash not same!!!");
			}
		} catch(Exception e) {
			log.error("Registration failed", e.getMessage());
		}
	}
	
	private String hashPost(String url, String user) {
        try {
            String salt = "registration salt";
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(salt.getBytes());
            byte[] hash = digest.digest((url + user).getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            log.error("Hash failed!", e.getMessage());
            return "";
        }
    }
}
