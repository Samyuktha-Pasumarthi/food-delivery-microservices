package com.Samyuktha.restaurantlisting.service;

import com.Samyuktha.restaurantlisting.dto.RestaurantDTO;
import com.Samyuktha.restaurantlisting.entity.Restaurant;
import com.Samyuktha.restaurantlisting.mapper.RestaurantMapper;
import com.Samyuktha.restaurantlisting.repo.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    @Autowired
    RestaurantRepo restaurantRepo;

    @Autowired
    private RestaurantMapper restaurantMapper;


    public List<RestaurantDTO> findAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepo.findAll();
        List<RestaurantDTO> restaurantDTOList = restaurants.stream().map(restaurant -> RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant)).collect(Collectors.toList());
        return restaurantDTOList;
    }

    public RestaurantDTO addRestaurantInDB(RestaurantDTO restaurantDTO) {

        //  Map DTO → Entity
        Restaurant restaurant =
                RestaurantMapper.INSTANCE.mapRestaurantDTOToRestaurant(restaurantDTO);

        // Force CREATE (important)
        restaurant.setId(null);

        // Save
        Restaurant savedRestaurant = restaurantRepo.save(restaurant);

        // Map back to DTO
        return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(savedRestaurant);
    }


    public ResponseEntity<RestaurantDTO> fetchRestaurantById(Integer id) {
        Optional<Restaurant> restaurant =  restaurantRepo.findById(id);
        if(restaurant.isPresent()){
            return new ResponseEntity<>(RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant.get()), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }
}
