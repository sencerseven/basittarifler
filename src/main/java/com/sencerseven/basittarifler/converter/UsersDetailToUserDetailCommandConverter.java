package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.UsersDetailCommand;
import com.sencerseven.basittarifler.domain.UsersDetail;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UsersDetailToUserDetailCommandConverter implements Converter<UsersDetail,UsersDetailCommand> {

    @Value("${s3.bucket}")
    private String bucketName;

    @Value("${s3.region}")
    private String region;

    @Value("${s3.domain_name}")
    private String domainName;

    @Synchronized
    @Nullable
    @Override
    public UsersDetailCommand convert(UsersDetail usersDetail) {
        if(usersDetail == null)
            return null;

        UsersDetailCommand usersDetailCommand = new UsersDetailCommand();
        usersDetailCommand.setId(usersDetail.getId());
        usersDetailCommand.setDescription(usersDetailCommand.getDescription());
        usersDetailCommand.setProfileImg("http://"+region + "." + domainName + "/" + bucketName + "/profile/" + usersDetail.getProfileImg());
        return usersDetailCommand;
    }
}
