package com.bookmyshow.Transformers;

import com.bookmyshow.Dtos.RequestDto.TheaterEntryDto;
import com.bookmyshow.Models.Theater;

public class TheaterTransformers {


    public static Theater convertDtoToEntity(TheaterEntryDto theaterEntryDto){

        Theater theater = Theater.builder().location(theaterEntryDto.getLocation())
                .name(theaterEntryDto.getName()).build();

        return theater;
    }


}
