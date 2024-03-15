package com.bookmyshow.Transformers;

import com.bookmyshow.Dtos.RequestDto.AddShowDto;
import com.bookmyshow.Models.Show;

public class ShowTransformer {
    public static Show convertDtoToEntity(AddShowDto addShowDto){

        Show show = Show.builder().time(addShowDto.getShowStartTime())
                .date(addShowDto.getShowDate()).build();

        return show;

    }
}
