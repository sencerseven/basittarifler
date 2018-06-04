package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.UsersDetailCommand;
import com.sencerseven.basittarifler.domain.UsersDetail;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UsersDetailCommandToUserDetailsConverter implements Converter<UsersDetailCommand,UsersDetail> {

    @Synchronized
    @Nullable
    @Override
    public UsersDetail convert(UsersDetailCommand usersDetailCommand) {
        if(usersDetailCommand == null)
            return null;

        UsersDetail usersDetail = new UsersDetail();
        usersDetail.setDescription(usersDetailCommand.getDescription());
        usersDetail.setId(usersDetailCommand.getId());

        return usersDetail;
    }
}
