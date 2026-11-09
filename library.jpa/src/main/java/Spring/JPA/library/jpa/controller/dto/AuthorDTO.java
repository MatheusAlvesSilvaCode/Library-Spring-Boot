package Spring.JPA.library.jpa.controller.dto;

import Spring.JPA.library.jpa.model.Author;

import java.time.LocalDate;

public record AuthorDTO(
        String name,
        LocalDate dob,
        String nationality
) {
    public Author mapingToAuthor() {
        Author author = new Author();
        author.setName(this.name);
        author.setDateOfBitrh(this.dob);
        author.setNationality(this.nationality);
        return author;
    }
}
