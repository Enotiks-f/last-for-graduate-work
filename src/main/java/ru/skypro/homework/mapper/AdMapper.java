package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Ads.AdShortDto;
import ru.skypro.homework.dto.Ads.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.Ads.ExtendedAdDto;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class AdMapper {

    public AdShortDto toShortDto(Ad ad) {
        if (ad == null) {
            return null;
        }

        AdShortDto dto = new AdShortDto();
        dto.setPk(ad.getId());
        dto.setTitle(ad.getTitle());
        dto.setPrice(ad.getPrice());

        if (ad.getAuthor() != null) {
            dto.setAuthor(ad.getAuthor().getId());
        }

        return dto;
    }

    public ExtendedAdDto toExtendedDto(Ad ad) {
        if (ad == null) {
            return null;
        }

        ExtendedAdDto dto = new ExtendedAdDto();
        dto.setPk(ad.getId());
        dto.setTitle(ad.getTitle());
        dto.setDescription(ad.getDescription());
        dto.setPrice(ad.getPrice());
        dto.setImage(ad.getImage());

        User author = new User();
        if (ad.getAuthor() != null) {
            dto.setAuthorFirstName(author.getFirstName());
            dto.setAuthorLastName(author.getLastName());
            dto.setEmail(author.getEmail());
            dto.setPhone(author.getPhone());
        }

        return dto;
    }

    public Ad toEntity(CreateOrUpdateAdDto adDto) {
        if (adDto == null) {
            return null;
        }

        Ad ad = new Ad();
        ad.setTitle(adDto.getTitle());
        ad.setDescription(adDto.getDescription());
        ad.setDescription(adDto.getDescription());

        return ad;
    }

//    for put/putch
    public void toEntity(CreateOrUpdateAdDto adDto, User user) {
        if (adDto == null) {
            return;
        }

        Ad ad = new Ad();
        ad.setTitle(adDto.getTitle());
        ad.setDescription(adDto.getDescription());
        ad.setDescription(adDto.getDescription());

    }

    public List<AdShortDto> toShortDtoList(List<Ad> ads) {
        if (ads == null) {
            return null;
        }
        return ads.stream()
                .map(this::toShortDto)
                .collect(Collectors.toList());
    }

}
