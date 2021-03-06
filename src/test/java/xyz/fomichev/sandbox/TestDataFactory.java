package xyz.fomichev.sandbox;

import java.time.Instant;
import java.util.UUID;

import xyz.fomichev.sandbox.model.*;

public class TestDataFactory {

    public static final UUID STUDENT_ID = UUID.fromString("ef76da29-9e8a-4c49-a7e5-73c926558222");
    public static final String STUDENT_LAST_NAME = "Best last name";

    public static final UUID BOOK_ID = UUID.fromString("ef76da29-9e8a-4c49-a7e5-93c926595847");
    public static final String BOOK_TITLE = "my best book title";

    public static final UUID BOOK_INSTANCE_ID = UUID.fromString("ef76da29-9e8a-4c49-a7e5-93c926684258");
    public static final UUID READING_SESSION_ID = UUID.fromString("ef76da29-9e8a-4c49-a7e5-93c926522921");
    public static final Instant READING_SESSION_START_TIME = Instant.ofEpochMilli(1001010);
    public static final UUID AUTHOR_ID = UUID.fromString("ef76da29-9e8a-4c49-a7e5-93c9265ff876");
    public static final String AUTHOR_LASTNAME = "Author Lastname";

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

    public static ReadingSession.ReadingSessionBuilder aReadingSession(Student student, BookInstance bookInstance) {
        return ReadingSession.builder()
                .id(READING_SESSION_ID)
                .startTime(READING_SESSION_START_TIME)
                .student(student)
                .bookInstance(bookInstance)
                ;
    }

    public static Author.AuthorBuilder anAuthor() {
        return Author.builder()
                .id(AUTHOR_ID)
                .lastName(AUTHOR_LASTNAME);
    }
}
