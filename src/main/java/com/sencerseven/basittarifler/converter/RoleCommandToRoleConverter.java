package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.RoleCommand;
import com.sencerseven.basittarifler.domain.Role;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RoleCommandToRoleConverter implements Converter<RoleCommand,Role> {

    @Synchronized
    @Nullable
    @Override
    public Role convert(RoleCommand roleCommand) {
        if(roleCommand == null)
            return null;
        Role role = new Role();
        role.setId(roleCommand.getId());
        role.setRole(roleCommand.getRole());
        return role;
    }
}
