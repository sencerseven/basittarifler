package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.RoleCommand;
import com.sencerseven.basittarifler.command.UsersCommand;
import com.sencerseven.basittarifler.domain.Users;
import org.hibernate.validator.constraints.br.TituloEleitoral;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UsersCommandToUsersConverterTest {

    UsersCommandToUsersConverter usersCommandToUsersConverter;

    RoleCommandToRoleConverter roleCommandToRoleConverter;

    UsersDetailCommandToUserDetailsConverter usersDetailCommandToUserDetailsConverter;

    private static final Long ID = 1L;

    @Before
    public void setUp() throws Exception {
        roleCommandToRoleConverter = new RoleCommandToRoleConverter();
        usersDetailCommandToUserDetailsConverter = new UsersDetailCommandToUserDetailsConverter();
        usersCommandToUsersConverter = new UsersCommandToUsersConverter(roleCommandToRoleConverter,usersDetailCommandToUserDetailsConverter);
    }

    @Test
    public void nullTest() throws Exception{
        assertNull(usersCommandToUsersConverter.convert(null));
    }

    @Test
    public void emptyTest() throws Exception{
        assertNotNull(usersCommandToUsersConverter.convert(new UsersCommand()));
    }

    @Test
    public void convert() throws Exception {
        UsersCommand users = new UsersCommand();
        users.setId(ID);

        RoleCommand roleCommand = new RoleCommand();
        roleCommand.setId(ID);
        users.getRoles().add(roleCommand);

        Users userResponse = usersCommandToUsersConverter.convert(users);

        assertEquals(ID,userResponse.getId());
        assertEquals(userResponse.getRoles().size(),1);

    }
}