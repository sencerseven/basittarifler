package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.RoleCommand;
import com.sencerseven.basittarifler.domain.Role;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoleCommandToRoleConverterTest {

    RoleCommandToRoleConverter roleCommandToRoleConverter;

    private static final Long ID = 1L;

    @Before
    public void setUp() throws Exception {
        roleCommandToRoleConverter = new RoleCommandToRoleConverter();
    }

    @Test
    public void nullTest(){
        assertNull(roleCommandToRoleConverter.convert(null));
    }

    @Test
    public void emptyTest(){
        assertNotNull(roleCommandToRoleConverter.convert(new RoleCommand()));
    }


    @Test
    public void convert(){
        RoleCommand roleCommand = new RoleCommand();
        roleCommand.setId(ID);

        Role role = roleCommandToRoleConverter.convert(roleCommand);

        assertEquals(role.getId(),ID);
    }


}