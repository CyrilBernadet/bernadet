package fr.family.bernadet.service;

import java.util.Optional;

public interface UserService {
    
    Optional<String> getCurrentUserLogin();
}
