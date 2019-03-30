package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.converter.RoleToRoleCommandConverter;
import com.sencerseven.basittarifler.converter.UsersDetailToUserDetailCommandConverter;
import com.sencerseven.basittarifler.converter.UsersToUsersCommandConverter;
import com.sencerseven.basittarifler.domain.Users;
import com.sencerseven.basittarifler.model.CustomUserDetails;
import com.sencerseven.basittarifler.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class CustomUserDetailsServiceTest {

    @Mock
    UserRepository userRepository;

    CustomUserDetailsService customUserDetailsService;


    UsersToUsersCommandConverter usersToUsersCommandConverter;

    @Mock
    RoleToRoleCommandConverter roleToRoleCommandConverter;
    @Mock
    UsersDetailToUserDetailCommandConverter usersDetailToUserDetailCommandConverter;

    private static final Long ID = 1L;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        usersToUsersCommandConverter = new UsersToUsersCommandConverter(roleToRoleCommandConverter,usersDetailToUserDetailCommandConverter);
        customUserDetailsService = new CustomUserDetailsService(userRepository,usersToUsersCommandConverter);
    }

    @Test
    public void loadUserByUsername() {
        Users user = new Users();
        user.setId(1L);

        Optional<Users> optionalUsers = Optional.of(user);

        CustomUserDetails userDetails = new CustomUserDetails(usersToUsersCommandConverter.convert(user));

        when(userRepository.findByUserName(anyString())).thenReturn(optionalUsers);

        CustomUserDetails customUserDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(anyString());

        verify(userRepository,times(1)).findByUserName(anyString());
        assertEquals(ID,customUserDetails.getId());

    }
}