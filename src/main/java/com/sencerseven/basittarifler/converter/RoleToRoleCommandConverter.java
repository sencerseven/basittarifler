package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.RoleCommand;
import com.sencerseven.basittarifler.domain.Role;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RoleToRoleCommandConverter implements Converter<Role,RoleCommand> {

    @Synchronized
    @Nullable
    @Override
    public RoleCommand convert(Role role) {
        if(role == null)
            return null;
        RoleCommand roleCommand = new RoleCommand();
        roleCommand.setId(role.getId());
        roleCommand.setRole(role.getRole());
        return roleCommand;

    }
}
