package teamchallenge.server.services;

import teamchallenge.server.dto.CreateBookDto;

public interface BookService {
    void createBook(CreateBookDto createBookDto);
}
