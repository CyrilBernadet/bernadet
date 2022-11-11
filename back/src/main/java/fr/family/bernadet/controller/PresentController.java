package fr.family.bernadet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.family.bernadet.dto.PresentDto;
import fr.family.bernadet.dto.UserDto;
import fr.family.bernadet.service.PresentService;

@RestController
@RequestMapping("/api/present")
public class PresentController {

    @Autowired
    private PresentService presentService;

    @GetMapping("/draw")
    public ResponseEntity<UserDto> draw() {
        UserDto drawnUser = this.presentService.drawPresent();

        if (drawnUser != null) {
            return new ResponseEntity<>(drawnUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/draw/done")
    public ResponseEntity<Boolean> hasDrawn() {
        boolean result = this.presentService.hasDrawn();

        return new ResponseEntity<>(Boolean.valueOf(result), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<PresentDto>> getPresentList() {
        List<PresentDto> result = this.presentService.getPresentList();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
