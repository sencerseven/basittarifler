package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.command.UsersCommand;
import com.sencerseven.basittarifler.domain.Users;

public interface UsersService {

    Users saveUsersCommand(UsersCommand usersCommand);
}
