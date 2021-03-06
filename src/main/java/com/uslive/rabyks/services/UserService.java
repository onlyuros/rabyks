package com.uslive.rabyks.services;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uslive.rabyks.models.mysql.Partner;
import com.uslive.rabyks.models.mysql.Role;
import com.uslive.rabyks.models.mysql.RolePK;
import com.uslive.rabyks.models.mysql.User;
import com.uslive.rabyks.repositories.mysql.PartnerRepository;
import com.uslive.rabyks.repositories.mysql.RoleRepository;
import com.uslive.rabyks.repositories.mysql.UserRepository;

@Service
public class UserService {
	
	Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private PartnerRepository partnerRepo;
	
	public User findByEmailAndPassword(String email, String password) throws Exception{
		return userRepo.findByEmailAndPassword(email, password);
	}

	public List<Role> findByUserId(int id) throws Exception {
		return roleRepo.findByIdUserId(id);
	}

	public void save(User user) throws Exception {
		userRepo.save(user);
	}

	public Integer deleteByEmail(String email) throws Exception {
		User u = userRepo.findByEmail(email);
		userRepo.delete(u);
		return u.getId();
	}
	
	public User findByEmail(String email) throws Exception {
		return userRepo.findByEmail(email);
	}

	public String login(String email, String password) throws Exception {
		User user = userRepo.findByEmailAndPassword(email, password);
		List<Role> role = roleRepo.findByIdUserId(user.getId());
		List<Partner> partners = userRepo.findPartnersByUserId(user.getId());
		ArrayList<Integer> partnerIds = new ArrayList<Integer>();
		for (Partner p : partners) {
			partnerIds.add(p.getId());
		}
		
		JSONObject jobj = new JSONObject();
		jobj.put("id", user.getId());
		jobj.put("email", user.getEmail());
		jobj.put("password", user.getPassword());
		jobj.put("number", user.getNumber() == null ? "null" : user.getNumber());
		jobj.put("role", role.get(0).getId().getRole() == 1 ? "admin" : "konobar");
		jobj.put("partners", partnerIds);
		StringWriter sw = new StringWriter();
		jobj.write(sw);
		return sw.toString();
	}	

	public void addWaiter(String email, String password, int partnerId) throws Exception {
		User u = new User();
		u.setEmail(email);
		u.setPassword(password);
		
		Partner p = partnerRepo.findById(partnerId);
		List<Partner> pl = new ArrayList<Partner>();
		pl.add(p);
		u.setPartners2(pl);
		
		User uNew = userRepo.save(u);
		
		Role role = new Role();
		RolePK pk = new RolePK();
		pk.setRole(2);
		pk.setUserId(uNew.getId());
		role.setId(pk);
		role.setUser(uNew);
		roleRepo.save(role);
		
		ArrayList<Role> roles = new ArrayList<Role>();
		roles.add(role);
		
		uNew.setRoles(roles);
		userRepo.save(uNew);
	}

	public List<User> findByRole(int role) throws Exception {
		List<Role> rl = roleRepo.findByIdRole(role);
		List<User> ul = new ArrayList<User>();
		
		for (Role r : rl) {
			ul.add(userRepo.findById(r.getId().getUserId()));
		}
		return ul;
	}
}
