package com.bookmyshow.Services;

import com.bookmyshow.Dtos.RequestDto.AddShowDto;
import com.bookmyshow.Dtos.RequestDto.ShowSeatsDto;
import com.bookmyshow.Enums.SeatType;
import com.bookmyshow.Exception.MovieNotFound;
import com.bookmyshow.Exception.ShowNotFound;
import com.bookmyshow.Exception.TheaterNotFound;
import com.bookmyshow.Models.*;
import com.bookmyshow.Repository.MovieRepository;
import com.bookmyshow.Repository.ShowRepository;
import com.bookmyshow.Repository.TheaterRepository;
import com.bookmyshow.Transformers.ShowTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private ShowRepository showRepository;

    public String addShow(AddShowDto showDto)throws TheaterNotFound, MovieNotFound {

        Show show = ShowTransformer.convertDtoToEntity(showDto);

        //Set the movie and theater entity
        Optional<Movie> movieOptional = movieRepository.findById(showDto.getMovieId());

        if(!movieOptional.isPresent()){
            throw new MovieNotFound("Movie is not found");
        }

        Optional<Theater> theaterOptional = theaterRepository.findById(showDto.getTheaterId());

        if(!theaterOptional.isPresent()){
            throw new TheaterNotFound("Theater is not found");
        }

        Movie movie = movieOptional.get();
        Theater theater = theaterOptional.get();

        show.setMovie(movie);
        show.setTheater(theater);

        //we save show here so that we can avoid saving showing two times
        show = showRepository.save(show);

        movie.getShowList().add(show);
        theater.getShowList().add(show);

        movieRepository.save(movie);

        theaterRepository.save(theater);

        return "Show has been added and showId is "+show.getId();
    }


    public String associateShowSeats(ShowSeatsDto showSeatsDto)throws ShowNotFound {

        Optional<Show> optionalShow = showRepository.findById(showSeatsDto.getShowId());

        //Validation check
        if(!optionalShow.isPresent()){
            throw new ShowNotFound("Show Id incorrect!!");
        }

        //Valid Show Now
        Show show = optionalShow.get();


        //We need to theaterSeats
        Theater theater = show.getTheater();

        List<TheaterSeat> theaterSeatList = theater.getTheaterSeatList();

        //Each seat needs to be added in the ?????? -->

        List<ShowSeat> showSeatList = show.getShowSeatList();

        for(TheaterSeat theaterSeat : theaterSeatList){

            ShowSeat showSeat = new ShowSeat();
            showSeat.setSeatNo(theaterSeat.getSeatNo());
            showSeat.setSeatType(theaterSeat.getSeatType());

            if(showSeat.getSeatType().equals(SeatType.CLASSIC))
                showSeat.setPrice(showSeatsDto.getPriceForClassicSeats());
            else
                showSeat.setPrice(showSeatsDto.getPriceForPremiumSeats());
            //Foreign key mapping
            showSeat.setShow(show);
            showSeat.setAvailable(true);
            showSeat.setFoodAttached(false);

            showSeatList.add(showSeat);
        }


        showRepository.save(show);
        //Child will automatically get saved.....
        return "Show seats has been successfully added";
    }

    public String getMovieName(AddShowDto showDto) {

        Integer movieId = showRepository.getMostShowedMovie(showDto.getShowDate());
        Movie movie = movieRepository.findById(movieId).get();
        return movie.getMovieName();

    }
}
