package ru.myproject.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.myproject.models.Book;
import ru.myproject.models.Person;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM books", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM books WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO books(name, author, year) VALUES(?,?,?)", book.getName(), book.getAuthor(), book.getYear());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE books SET name=?, author=?, year=? WHERE id=?", updatedBook.getName(), updatedBook.getAuthor(), updatedBook.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM books WHERE id=?", id);
    }

    public List<Book> showOwn(int id) {
        return jdbcTemplate.query("SELECT b.* FROM books b JOIN person p ON p.id = b.person_id WHERE b.person_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }

    public Person showBelongBoook(int id) {
        return jdbcTemplate.query("SELECT p.* FROM person p JOIN books b ON p.id = b.person_id WHERE b.id =?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void freeBook(int id) {
        jdbcTemplate.update("UPDATE books SET person_id = null WHERE id=?", id);
    }

    public void assign(int id, int id_person) {
        jdbcTemplate.update("UPDATE books SET person_id =? WHERE id=?", id_person, id);
    }
}
