package teamchallenge.server.catalog.book.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teamchallenge.server.catalog.book.BookSearchCriteria;
import teamchallenge.server.catalog.book.service.BookService;
import teamchallenge.server.catalog.book.dto.ListResponseBookDto;
import teamchallenge.server.catalog.book.dto.ResponseBookDto;

import java.util.List;

@RestController
@RequestMapping("api/open/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBookDto> getBooks(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById((id)));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<ListResponseBookDto>> getBooks(@RequestParam(required = false) String category,
                                                              @RequestParam(required = false) String price,
                                                              @RequestParam(required = false) String year,
                                                              @RequestParam(required = false) String language,
                                                              @RequestParam(required = false) String author,
                                                              @RequestParam(required = false) String expected,
                                                              @RequestParam(required = false) String title,
                                                              @RequestParam(defaultValue = "1") int page,
                                                              @RequestParam(defaultValue = "9") int size,
                                                              @RequestParam(defaultValue = "title,asc") String sort) {
        Pageable pageable = PageRequest.of(page-1, size, Sort.by(parseSortParams(sort)));
        return ResponseEntity.ok(bookService.getBooks(pageable, category, price, year, language, author, expected, title));
    }


    @GetMapping("/search")
    public ResponseEntity<List<ResponseBookDto>> searchBook(@ModelAttribute BookSearchCriteria bookSearchCriteria) {
        return ResponseEntity.ok(bookService.searchBooks(bookSearchCriteria));
    }


    private Sort.Order[] parseSortParams(String sort) {
        // Разделяем строку на части, используя "," в качестве разделителя
        String[] sortParams = sort.split(",");

        // Проверяем, что количество параметров корректное
        if (sortParams.length % 2 != 0) {
            throw new IllegalArgumentException("Sort parameters must be in pairs of field and direction.");
        }

        Sort.Order[] orders = new Sort.Order[sortParams.length / 2];
        for (int i = 0; i < sortParams.length; i += 2) {
            String field = sortParams[i];
            String direction = sortParams[i + 1].toUpperCase();
            orders[i / 2] = new Sort.Order(Sort.Direction.fromString(direction), field);
        }

        return orders;
    }
}
