package edu.ku.bookapi.model;

public record BookInput(
        String title,
        String author,
        String isbn,
        int publishedYear,
        String category,
        int availableCopies
) {
}
