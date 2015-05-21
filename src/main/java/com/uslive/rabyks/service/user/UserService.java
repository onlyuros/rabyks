package com.uslive.rabyks.service.user;


import java.util.List;

import com.uslive.rabyks.models.UserCreateForm;
import com.uslive.rabyks.models.mysql.User;

public interface UserService {

    User getUserById(long id);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    User create(UserCreateForm form);

}
