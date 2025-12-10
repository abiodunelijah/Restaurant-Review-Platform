package com.coder2client.repositories;


import com.coder2client.entities.Restaurant;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends ElasticsearchRepository<Restaurant, String> {
}
