package pl.coderslab;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MockBookService implements BookService {

    private static long nextId = 4L;

    private List<Book> list;
    public MockBookService() {
        list = new ArrayList<>();
        list.add(new Book(1L, "9788324631766", "Thinking	in	Java", "Bruce	Eckel", "Helion", "programming"));
        list.add(new Book(2L, "9788324627738", "Rusz	głowa	Java.", "Sierra	Kathy,	Bates	Bert", "Helion",
                "programming"));
        list.add(new Book(3L, "9780130819338", "Java	2.	Podstawy", "Cay	Horstmann,	Gary	Cornell", "Helion",
                "programming"));
    }

    public List<Book> getList() {
        return list;
    }

    public void setList(List<Book> list) {
        this.list = list;
    }

    @Override
    public List<Book> getBooks() {
        return list;
    }

    @Override
    public void add(Book book) {
        book.setId(nextId++);
        list.add(book);
    }

    @Override
    public void delete(Long id) {
        if (getId(id).isPresent()){
            list.remove(getId(id).get());
        }
    }

    @Override
    public void update(Book book) {
        if (this.getId(book.getId()).isPresent()) {
            int bookIndex = list.indexOf(this.getId(book.getId()).get());
            list.set(bookIndex,book);
        }
    }

    @Override
    public Optional<Book> getId(Long id) {
        return list.stream() .filter(list -> id.equals(list.getId())) .findAny();
    }
}
