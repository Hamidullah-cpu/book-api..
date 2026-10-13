cpackage edu.ku.bookapi.controller;

import edu.ku.bookapi.model.BookInput;
import edu.ku.bookapi.model.Books;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final List<Books> books = new ArrayList<>(List.of(

            new Books(
                    1L,
                    "Clean Code",
                    "Robert C. Martin",
                    "9780132350884",
                    2008,
                    "Software Engineering"
            ),

            new Books(
                    2L,
                    "Effective Java",
                    "Joshua Bloch",
                    "9780134685991",
                    2018,
                    "Java"
            ),

            new Books(
                    3L,
                    "Designing Data-Intensive Applications",
                    "Martin Kleppmann",
                    "9781449373320",
                    2017,
                    "Distributed Systems"
            ),

            new Books(
                    4L,
                    "Spring in Action",
                    "Craig Walls",
                    "9781617297571",
                    2022,
                    "Spring"
            ),

            new Books(
                    5L,
                    "Computer Networks",
                    "Andrew S. Tanenbaum",
                    "9780132126953",
                    2010,
                    "Networking"
            )
    ));


    // ==========================================
    // GET - Get all books
    // ==========================================

    @GetMapping
    public List<Books> getAllBooks() {

        return books;
    }


    // ==========================================
    // PUT - Update a book
    // ==========================================

    @PutMapping("/{bookId}")
    public ResponseEntity<?> updateBook(
            @PathVariable Long bookId,
            @RequestBody BookInput input
    ) {

        for (int i = 0; i < books.size(); i++) {

            Books book = books.get(i);

            if (book.getId().equals(bookId)) {

                Books updatedBook = new Books(
                        book.getId(),
                        input.title(),
                        input.author(),
                        input.isbn(),
                        input.publishedYear(),
                        input.category()
                );

                books.set(i, updatedBook);

                return ResponseEntity.ok(updatedBook);
            }
        }

        return ResponseEntity.notFound().build();
    }


    // ==========================================
    // DELETE - Delete a book
    // ==========================================

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(
            @PathVariable Long bookId
    ) {

        boolean removed = books.removeIf(
                book -> book.getId().equals(bookId)
        );

        if (removed) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
