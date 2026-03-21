package com.Samyuktha.userinfo.controller;


import com.Samyuktha.userinfo.dto.UserDTO;
import com.Samyuktha.userinfo.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @Test
    public void testAddUser(){
        UserDTO mockUserDTO = new UserDTO(1, "John Doe", "Pssword123", "Address 123", "1234567890");

        when(userService.addUser(mockUserDTO)).thenReturn(mockUserDTO);

        ResponseEntity<UserDTO> response = userController.addUser(mockUserDTO);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertEquals(response.getBody(), mockUserDTO);
    }

    @Test
    public void testFetchUserDetailsById(){
        int mockUserId = 1;
        UserDTO mockUserDTO = new UserDTO(mockUserId, "JohnDoe", "Pssword123", "Address123", "1234567890");

        when(userService.fetchUserDetailsById(mockUserId)).thenReturn(new ResponseEntity<>(mockUserDTO, HttpStatus.OK));

        ResponseEntity<UserDTO> response = userController.fetchUserDetailsById(mockUserId);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), mockUserDTO);

        verify(userService,times(1)).fetchUserDetailsById(mockUserId);


    }

}
