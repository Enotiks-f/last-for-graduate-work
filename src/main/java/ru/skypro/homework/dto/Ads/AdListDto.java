package ru.skypro.homework.dto.Ads;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AdListDto {
    private Long count;
    private List<AdShortDto> results = new ArrayList<>();

    // Конструктор по умолчанию (нужен для скелета)
    public AdListDto() {
        this.count = 0L;
        this.results = new ArrayList<>();
    }


}
