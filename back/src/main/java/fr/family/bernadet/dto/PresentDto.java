package fr.family.bernadet.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PresentDto {

    private UserDto fromUser;

    private UserDto toUser;
}
