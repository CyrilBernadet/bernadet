package fr.family.bernadet.service.impl;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.StreamSupport;

import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import fr.family.bernadet.dto.PresentDto;
import fr.family.bernadet.dto.UserDto;
import fr.family.bernadet.entity.PresentEntity;
import fr.family.bernadet.repository.PresentRepository;
import fr.family.bernadet.service.PresentService;
import fr.family.bernadet.service.UserService;

@Service
public class PresentServiceImpl implements PresentService {

    @Autowired
    private KeycloakRestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private PresentRepository repository;

    @Value("${keycloak.auth-server-url}")
    private String keycloakServerUrl;

    @Override
    public UserDto drawPresent() {

        // Get all users from KeyCloak
        UserDto[] users = restTemplate
                .getForEntity(URI.create(keycloakServerUrl + "/admin/realms/Bernadet/users"), UserDto[].class)
                .getBody();

        Optional<String> currentUserOptional = this.userService.getCurrentUserLogin();

        if (users != null && currentUserOptional.isPresent()) {
            // Get all already drawn people (database)
            Iterable<PresentEntity> presents = this.repository.findAll();
            List<String> alreadyDrawnId = StreamSupport.stream(presents.spliterator(), false)
                    .map(present -> present.getToUser()).toList();

            // Remove already drawn people and current user from all users list
            String currentUserId = currentUserOptional.get();

            List<UserDto> filteredUsersList = List.of(users).stream()
                    .filter(user -> !currentUserId.equals(user.getId()) && !alreadyDrawnId.contains(user.getId()))
                    .toList();

            // Drawn a random name
            UserDto drawnUser = filteredUsersList.get(ThreadLocalRandom.current().nextInt(filteredUsersList.size()));

            // Save the name in database
            PresentEntity entity = new PresentEntity();
            entity.setFromUser(currentUserId);
            entity.setToUser(drawnUser.getId());

            this.repository.save(entity);

            return drawnUser;
        } else {
            return null;
        }
    }

    @Override
    public boolean hasDrawn() {
        Optional<String> currentUserOptional = this.userService.getCurrentUserLogin();

        if (currentUserOptional.isPresent()) {
            String currentUserId = currentUserOptional.get();
            return this.repository.existsFrom(currentUserId);
        } else {
            return false;
        }
    }

    @Override
    public List<PresentDto> getPresentList() {
        Iterable<PresentEntity> presents = this.repository.findAll();
        
        List<PresentDto> result = StreamSupport.stream(presents.spliterator(), false).map(present -> {
            PresentDto dto = new PresentDto();

            UserDto fromUser = restTemplate
                .getForEntity(URI.create(keycloakServerUrl + "/admin/realms/Bernadet/users/" + present.getFromUser()), UserDto.class)
                .getBody();

            dto.setFromUser(fromUser);

            UserDto toUser = restTemplate
                .getForEntity(URI.create(keycloakServerUrl + "/admin/realms/Bernadet/users/" + present.getToUser()), UserDto.class)
                .getBody();

            dto.setToUser(toUser);

            return dto;
        }).toList();

        return result;
    }

}
