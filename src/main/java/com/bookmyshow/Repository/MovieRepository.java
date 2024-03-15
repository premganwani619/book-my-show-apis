package com.bookmyshow.Repository;

import com.bookmyshow.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Integer> {

    Movie findByMovieName(String name);

}
