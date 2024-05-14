package teamchallenge.server.controllers.personal;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teamchallenge.server.entities.Book;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/personal")
public class PersonalController {

    @PostMapping
    public String test() {
        return "Hello User!";
    }


}
