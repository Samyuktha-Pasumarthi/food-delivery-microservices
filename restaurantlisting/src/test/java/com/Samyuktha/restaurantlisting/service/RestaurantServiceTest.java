package com.Samyuktha.restaurantlisting.service;

import com.Samyuktha.restaurantlisting.dto.RestaurantDTO;
import com.Samyuktha.restaurantlisting.entity.Restaurant;
import com.Samyuktha.restaurantlisting.mapper.RestaurantMapper;
import com.Samyuktha.restaurantlisting.repo.RestaurantRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {

    @Mock
    RestaurantRepo restaurantRepo;

    @InjectMocks
    RestaurantService restaurantService;

    @Mock
    RestaurantMapper restaurantMapper;

    @Test
    public  void testFindAllRestaurants(){

        List<Restaurant> mockRestaurants = Arrays.asList(
                new Restaurant(1, "Restaurant 1", "Address 1", "city 1", "Desc 1"),
                new Restaurant(2, "Restaurant 2", "Address 2", "city 2", "Desc 2")
        );

        when(restaurantRepo.findAll()).thenReturn(mockRestaurants);

        List<RestaurantDTO> restaurantDTOList  = restaurantService.findAllRestaurants();

        assertEquals(mockRestaurants.size(),restaurantDTOList.size());
        for(int i=0;i<mockRestaurants.size();i++){
            RestaurantDTO expectedDTO = RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(mockRestaurants.get(i));
            assertEquals(expectedDTO, restaurantDTOList.get(i));
        }

            verify(restaurantRepo, times(1)).findAll();

    }

    @Test
    public void testAddRestaurantInDB(){
        RestaurantDTO mockRestaurantDTO = new RestaurantDTO(1, "Restaurant 1", "Address 1", "City 1", "Desc 1");
        Restaurant mockRestaurant = RestaurantMapper.INSTANCE.mapRestaurantDTOToRestaurant(mockRestaurantDTO);

        when(restaurantRepo.save(org.mockito.ArgumentMatchers.any(Restaurant.class)))
                .thenReturn(mockRestaurant);

        RestaurantDTO savedRestaurantDTO = restaurantService.addRestaurantInDB(mockRestaurantDTO);

        assertEquals(mockRestaurantDTO, savedRestaurantDTO);

        verify(restaurantRepo).save(org.mockito.ArgumentMatchers.any(Restaurant.class));
    }

    @Test
    public void testFetchRestaurantById(){
        Integer mockRestaurantId = 1;

        Restaurant mockRestaurant = new Restaurant(mockRestaurantId, "Restaurant 1", "Address 1", "City 1", "Desc 1");

        when(restaurantRepo.findById(mockRestaurantId)).thenReturn(Optional.of(mockRestaurant));

        ResponseEntity<RestaurantDTO> response = restaurantService.fetchRestaurantById(mockRestaurantId);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(mockRestaurantId, response.getBody().getId());

        verify(restaurantRepo,times(1)).findById(mockRestaurantId);
    }

    @Test
    public void testFetchRestaurantById_NotFound(){
        Integer mockRestaurantId = 1;

        when(restaurantRepo.findById(mockRestaurantId)).thenReturn(Optional.empty());

        ResponseEntity<RestaurantDTO> response = restaurantService.fetchRestaurantById(mockRestaurantId);

        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
        assertEquals(null, response.getBody());

        verify(restaurantRepo,times(1)).findById(mockRestaurantId);
    }

}
