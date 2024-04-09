package teamchallenge.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/personal")
public class PersonalController {

    @PostMapping
    public String test() {
        return "Hello User!";
    }
}
