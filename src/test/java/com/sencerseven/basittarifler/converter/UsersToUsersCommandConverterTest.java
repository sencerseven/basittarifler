package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.UsersCommand;
import com.sencerseven.basittarifler.domain.Role;
import com.sencerseven.basittarifler.domain.Users;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UsersToUsersCommandConverterTest {

    UsersToUsersCommandConverter usersToUsersCommandConverter;
    RoleToRoleCommandConverter roleToRoleCommandConverter;
    UsersDetailToUserDetailCommandConverter usersDetailToUserDetailCommandConverter;

    private static final Long ID = 1L;
    @Before
    public void setUp() throws Exception {
        roleToRoleCommandConverter = new RoleToRoleCommandConverter();
        usersDetailToUserDetailCommandConverter = new UsersDetailToUserDetailCommandConverter();
        usersToUsersCommandConverter = new UsersToUsersCommandConverter(roleToRoleCommandConverter,usersDetailToUserDetailCommandConverter);
    }

    @Test
    public void nullTest(){
        assertNull(usersToUsersCommandConverter.convert(null));
    }

    @Test
    public void emptyTest(){
        assertNotNull(usersToUsersCommandConverter.convert(new Users()));
    }

    @Test
    public void convert() {
        Users users = new Users();
        users.setId(1L);

        Role role = new Role();
        role.setId(ID);

        users.getRoles().add(role);

        UsersCommand usersCommand = usersToUsersCommandConverter.convert(users);

        assertEquals(ID,usersCommand.getId());
        assertEquals(usersCommand.getRoles().size(),1);


    }
}