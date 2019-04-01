package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.command.UsersCommand;
import com.sencerseven.basittarifler.converter.RoleCommandToRoleConverter;
import com.sencerseven.basittarifler.converter.UsersCommandToUsersConverter;
import com.sencerseven.basittarifler.converter.UsersDetailCommandToUserDetailsConverter;
import com.sencerseven.basittarifler.domain.Users;
import com.sencerseven.basittarifler.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UsersServiceImplTest {

    @Mock
    UserRepository userRepository;

    UsersServiceImpl usersService;

    RoleCommandToRoleConverter roleCommandToRoleConverter;

    UsersCommandToUsersConverter usersCommandToUsersConverter;

    UsersDetailCommandToUserDetailsConverter usersDetailCommandToUserDetailsConverter;

    private static final Long ID = 1L;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        roleCommandToRoleConverter = new RoleCommandToRoleConverter();
        usersDetailCommandToUserDetailsConverter = new UsersDetailCommandToUserDetailsConverter();
        usersCommandToUsersConverter = new UsersCommandToUsersConverter(roleCommandToRoleConverter,usersDetailCommandToUserDetailsConverter);
        usersService = new UsersServiceImpl(userRepository,usersCommandToUsersConverter);
    }

    @Test
    public void saveUsersCommand() {
        Users users = new Users();
        users.setId(ID);

        UsersCommand usersCommand = new UsersCommand();
        usersCommand.setId(ID);

        when(userRepository.save(any())).thenReturn(users);
        Users resultUser = usersService.saveUsersCommand(usersCommand);

        assertEquals(ID,resultUser.getId());

    }
}