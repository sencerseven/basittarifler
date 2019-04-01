package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.command.UsersCommand;
import com.sencerseven.basittarifler.converter.UsersToUsersCommandConverter;
import com.sencerseven.basittarifler.domain.Users;
import com.sencerseven.basittarifler.model.CustomUserDetails;
import com.sencerseven.basittarifler.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    private UsersToUsersCommandConverter usersToUsersCommandConverter;

    public CustomUserDetailsService(UserRepository userRepository, UsersToUsersCommandConverter usersToUsersCommandConverter) {
        this.userRepository = userRepository;
        this.usersToUsersCommandConverter = usersToUsersCommandConverter;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> optionalUsers = userRepository.findByUserName(username);

        optionalUsers.orElseThrow(() -> new UsernameNotFoundException("User Not Found!"));

        Optional<UsersCommand> usersCommandOptional = Optional.of(usersToUsersCommandConverter.convert(optionalUsers.get()));

        return usersCommandOptional.map(CustomUserDetails::new).get();
    }
}
