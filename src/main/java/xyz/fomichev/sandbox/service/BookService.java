package xyz.fomichev.sandbox.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.fomichev.sandbox.repository.BookRepository;

@Service
@RequiredArgsConstructor
public class BookService {

    private BookRepository bookRepository;

}
