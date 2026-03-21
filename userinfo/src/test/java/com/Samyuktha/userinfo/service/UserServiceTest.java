package com.Samyuktha.userinfo.service;

import com.Samyuktha.userinfo.dto.UserDTO;
import com.Samyuktha.userinfo.entity.User;
import com.Samyuktha.userinfo.mapper.UserMapper;
import com.Samyuktha.userinfo.repo.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    UserService userService;

    @Mock
    UserRepo userRepo;

    @Test
    public void testAddUser(){
        UserDTO mockUserDTO = new UserDTO(1, "John Doe", "Pssword123", "Address 123", "1234567890");
        User mockUser = UserMapper.INSTANCE.mapUserDTOToUser(mockUserDTO);

        when(userRepo.save(mockUser)).thenReturn(mockUser);
        UserDTO result = userService.addUser(mockUserDTO);

        assertEquals(mockUserDTO, result);
        verify(userRepo,times(1)).save(mockUser);

    }

    @Test
    public void testFetchUserDetailsById_ifpresent(){
        int mockUserId = 1;
        User mockUser = new User(mockUserId, "John Doe", "Pssword123", "Address 123", "1234567890");

        when(userRepo.findById(mockUserId)).thenReturn(Optional.of(mockUser));

        ResponseEntity<UserDTO> result = userService.fetchUserDetailsById(mockUserId);

        assertEquals(mockUserId,result.getBody().getId());
        assertEquals(result.getStatusCode(), HttpStatus.OK);

        verify(userRepo,times(1)).findById(mockUserId);
    }

    @Test
    public void testFetchUserDetailsById_ifNotPresent(){
        int mockUserId = 1;

        when(userRepo.findById(mockUserId)).thenReturn(Optional.empty());

        ResponseEntity<UserDTO> result = userService.fetchUserDetailsById(mockUserId);

        assertEquals(result.getStatusCode(), HttpStatus.NOT_FOUND);
        assertEquals(result.getBody(), null);

        verify(userRepo,times(1)).findById(mockUserId);
    }
}
