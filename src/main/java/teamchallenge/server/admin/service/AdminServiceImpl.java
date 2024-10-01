package teamchallenge.server.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teamchallenge.server.catalog.author.entity.AuthorRepository;
import teamchallenge.server.catalog.author.service.AuthorService;
import teamchallenge.server.catalog.book.dto.ResponseListsDto;
import teamchallenge.server.catalog.book.service.BookService;
import teamchallenge.server.catalog.category.service.CategoryService;
import teamchallenge.server.catalog.language.service.LanguageService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final LanguageService languageService;
    private final BookService bookService;
    public ResponseListsDto getAllLists(){
        ResponseListsDto responseListsDto = new ResponseListsDto();
        responseListsDto.setAuthors(authorService.getAllAuthors());
        responseListsDto.setCategories(categoryService.getAllCategories());
        responseListsDto.setLanguages(languageService.getAllLanguages());
        responseListsDto.setYears(bookService.getAllYears());
        return responseListsDto;
    }
}
