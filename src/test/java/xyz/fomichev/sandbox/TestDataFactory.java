package xyz.fomichev.sandbox;

import java.util.UUID;
import xyz.fomichev.sandbox.model.Book;
import xyz.fomichev.sandbox.model.BookInstance;
import xyz.fomichev.sandbox.model.Quality;
import xyz.fomichev.sandbox.model.Student;

public class TestDataFactory {

    public static final UUID STUDENT_ID = UUID.fromString("ef76da29-9e8a-4c49-a7e5-73c926558222");
    public static final String STUDENT_LAST_NAME = "Best last name";

    public static final UUID BOOK_ID = UUID.fromString("ef76da29-9e8a-4c49-a7e5-93c926595847");
    public static final String BOOK_TITLE = "my best book title";

    public static final UUID BOOK_INSTANCE_ID = UUID.fromString("ef76da29-9e8a-4c49-a7e5-93c926684258");

    public static Student.StudentBuilder aStudent() {
        return Student.builder()
                .id(STUDENT_ID)
                .lastName(STUDENT_LAST_NAME);
    }

    public static Book.BookBuilder aBook() {
        return Book.builder()
                .id(BOOK_ID)
                .title(BOOK_TITLE);
    }

    public static BookInstance.BookInstanceBuilder aBookInstance(Book book) {
        return BookInstance.builder()
                .id(BOOK_INSTANCE_ID)
                .book(book)
                .quality(Quality.GOOD)
                ;
    }

    public static BookInstance.BookInstanceBuilder aNewBookInstance(Book book) {
        return aBookInstance(book)
                .id(UUID.randomUUID())
                ;
    }
}
