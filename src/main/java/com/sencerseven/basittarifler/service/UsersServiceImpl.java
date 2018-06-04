package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.command.RoleCommand;
import com.sencerseven.basittarifler.command.UsersCommand;
import com.sencerseven.basittarifler.converter.UsersCommandToUsersConverter;
import com.sencerseven.basittarifler.domain.Users;
import com.sencerseven.basittarifler.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    UserRepository userRepository;

    UsersCommandToUsersConverter usersCommandToUsersConverter;

    public UsersServiceImpl(UserRepository userRepository, UsersCommandToUsersConverter usersCommandToUsersConverter) {
        this.userRepository = userRepository;
        this.usersCommandToUsersConverter = usersCommandToUsersConverter;
    }

    @Override
    public Users saveUsersCommand(UsersCommand usersCommand) {
        Optional<UsersCommand> usersCommandOptional = Optional.of(usersCommand);

        if(usersCommandOptional.isPresent())
            return userRepository.save(usersCommandToUsersConverter.convert(usersCommandOptional.get()));

        return null;
    }
}
