package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.RoleCommand;
import com.sencerseven.basittarifler.command.UsersCommand;
import com.sencerseven.basittarifler.domain.Role;
import com.sencerseven.basittarifler.domain.Users;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UsersCommandToUsersConverter implements Converter<UsersCommand,Users> {

    RoleCommandToRoleConverter roleCommandToRoleConverter;
    UsersDetailCommandToUserDetailsConverter usersDetailCommandToUserDetailsConverter;

    public UsersCommandToUsersConverter(RoleCommandToRoleConverter roleCommandToRoleConverter, UsersDetailCommandToUserDetailsConverter usersDetailCommandToUserDetailsConverter) {
        this.roleCommandToRoleConverter = roleCommandToRoleConverter;
        this.usersDetailCommandToUserDetailsConverter = usersDetailCommandToUserDetailsConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Users convert(UsersCommand usersCommand) {
        if(usersCommand == null)
            return null;
        Users users = new Users();
        users.setId(usersCommand.getId());
        users.setActive(usersCommand.getActive());
        users.setLastName(usersCommand.getLastName());
        users.setPassword(usersCommand.getPassword());
        users.setUserName(usersCommand.getUserName());
        users.setEmail(usersCommand.getEmail());

        if(usersCommand.getRoles().size() > 0 && usersCommand.getRoles() != null)
            usersCommand.getRoles().forEach(roleCommand -> users.getRoles().add(roleCommandToRoleConverter.convert(roleCommand)));

        if(usersCommand != null)
            users.setUsersDetail(usersDetailCommandToUserDetailsConverter.convert(usersCommand.getUsersDetailCommand()));

        return users;
    }
}
