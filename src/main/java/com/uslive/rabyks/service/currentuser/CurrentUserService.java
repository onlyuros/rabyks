package com.uslive.rabyks.service.currentuser;

import com.uslive.rabyks.models.CurrentUser;

public interface CurrentUserService {

    boolean canAccessUser(CurrentUser currentUser, Long userId);

}
