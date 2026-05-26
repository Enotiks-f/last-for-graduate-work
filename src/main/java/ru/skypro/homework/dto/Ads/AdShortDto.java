package ru.skypro.homework.dto.Ads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdShortDto {
    private Long author;
    private String image;
    private Long pk;
    private Integer price;
    private String title;
}
