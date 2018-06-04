package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.UsersCommand;
import com.sencerseven.basittarifler.domain.Role;
import com.sencerseven.basittarifler.domain.Users;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UsersToUsersCommandConverter implements Converter<Users,UsersCommand> {

    RoleToRoleCommandConverter roleToRoleCommandConverter;
    UsersDetailToUserDetailCommandConverter usersDetailToUserDetailCommandConverter;

    public UsersToUsersCommandConverter(RoleToRoleCommandConverter roleToRoleCommandConverter, UsersDetailToUserDetailCommandConverter usersDetailToUserDetailCommandConverter) {
        this.roleToRoleCommandConverter = roleToRoleCommandConverter;
        this.usersDetailToUserDetailCommandConverter = usersDetailToUserDetailCommandConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public UsersCommand convert(Users users) {
        if(users == null)
            return null;

        UsersCommand usersCommand = new UsersCommand();

        usersCommand.setId(users.getId());
        usersCommand.setLastName(users.getLastName());
        usersCommand.setUserName(users.getUserName());
        usersCommand.setActive(users.getActive());
        usersCommand.setEmail(users.getEmail());
        usersCommand.setPassword(users.getPassword());

        if(users.getRoles() != null && users.getRoles().size() >0)
            users.getRoles().forEach((Role roles) -> usersCommand.getRoles().add(roleToRoleCommandConverter.convert(roles)));

        if(users.getUsersDetail() != null)
            usersCommand.setUsersDetailCommand(usersDetailToUserDetailCommandConverter.convert(users.getUsersDetail()));

        return usersCommand;
    }
}
