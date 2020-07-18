package xyz.fomichev.sandbox.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.fomichev.sandbox.repository.AuthorRepository;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

}
