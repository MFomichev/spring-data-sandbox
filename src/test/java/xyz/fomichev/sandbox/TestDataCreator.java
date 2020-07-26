package xyz.fomichev.sandbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.fomichev.sandbox.model.Student;
import xyz.fomichev.sandbox.repository.StudentRepository;

import static xyz.fomichev.sandbox.TestDataFactory.aStudent;

@Component
public class TestDataCreator {

    @Autowired
    private StudentRepository studentRepository;

    public Student student() {
        return student(aStudent().build());
    }

    public Student student(Student student) {
        return studentRepository.saveAndFlush(student);
    }

}
