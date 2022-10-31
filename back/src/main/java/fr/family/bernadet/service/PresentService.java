package fr.family.bernadet.service;

import java.util.List;

import fr.family.bernadet.dto.PresentDto;
import fr.family.bernadet.dto.UserDto;

public interface PresentService {
    
    UserDto drawPresent();

    boolean hasDrawn();

    List<PresentDto> getPresentList();
}
