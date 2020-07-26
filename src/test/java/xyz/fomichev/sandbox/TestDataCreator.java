package xyz.fomichev.sandbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.fomichev.sandbox.model.Book;
import xyz.fomichev.sandbox.model.BookInstance;
import xyz.fomichev.sandbox.model.Student;
import xyz.fomichev.sandbox.repository.BookInstanceRepository;
import xyz.fomichev.sandbox.repository.BookRepository;
import xyz.fomichev.sandbox.repository.StudentRepository;

import static xyz.fomichev.sandbox.TestDataFactory.aBook;
import static xyz.fomichev.sandbox.TestDataFactory.aBookInstance;
import static xyz.fomichev.sandbox.TestDataFactory.aStudent;

@Component
public class TestDataCreator {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookInstanceRepository bookInstanceRepository;

    public Student student() {
        return student(aStudent().build());
    }

    public Student student(Student student) {
        return studentRepository.saveAndFlush(student);
    }

    public Book book() {
        return book(aBook().build());
    }

    public Book book(Book book) {
        return bookRepository.saveAndFlush(book);
    }

    public BookInstance bookInstance(Book book) {
        return bookInstance(aBookInstance(book).build());
    }

    public BookInstance bookInstance(BookInstance bookInstance) {
        return bookInstanceRepository.saveAndFlush(bookInstance);
    }
}
