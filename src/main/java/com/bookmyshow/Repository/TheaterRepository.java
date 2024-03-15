package com.bookmyshow.Repository;

import com.bookmyshow.Models.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<Theater,Integer> {

    Theater findByLocation(String location);
}
