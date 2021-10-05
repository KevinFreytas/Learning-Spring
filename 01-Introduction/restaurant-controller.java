/* For later usage */

package com.codecademy.springcap.controller;

import com.codecademy.springcap.model.Restaurant;
import com.codecademy.springcap.repository.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.regex.Pattern;

@RequestMapping("/restaurants")
@RestController
public class RestaurantController {
  private final RestaurantRepository restaurantRepository;
  private final Pattern zipCodePattern = Pattern.compile("\\d{5}");

  public RestaurantController(RestaurantRepository restaurantRepository) {
    this.restaurantRepository = restaurantRepository;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
    validateNewRestaurant(restaurant);

    return restaurantRepository.save(restaurant);
  }

  /*
  POST request using Curl
  curl -X POST -d 
  "{\"name\":\"Pizza Hut\", 
  \"line1\":\"123 Main St.\", 
  \"city\":\"San Diego\", 
  \"state\":\"CA\", 
  \"zipCode\":\"90029\"}" 
  -H "Content-Type: application/json" http://localhost:4001/restaurants/
  */

  @GetMapping("/{id}")
  public Restaurant getRestaurant(@PathVariable Long id) {
    Optional<Restaurant> restaurant = restaurantRepository.findById(id);
    if (restaurant.isPresent()) {
        return restaurant.get();
    }

    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
  }

  @GetMapping
  public Iterable<Restaurant> getAllRestaurants() {
    return restaurantRepository.findAll();
  }
    
/*
  Get Request using Curl
  curl localhost:4001/restaurants
*/

  private void validateNewRestaurant(Restaurant restaurant) {
    if (ObjectUtils.isEmpty(restaurant.getName())) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    validateZipCode(restaurant.getZipCode());

    Optional<Restaurant> existingRestaurant = restaurantRepository.findRestaurantsByNameAndZipCode(restaurant.getName(), restaurant.getZipCode());
    if (existingRestaurant.isPresent()) {
        throw new ResponseStatusException(HttpStatus.CONFLICT);
    }
  }

  private void validateZipCode(String zipcode) {
    if (!zipCodePattern.matcher(zipcode).matches()) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }
}
