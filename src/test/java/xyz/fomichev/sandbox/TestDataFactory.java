package xyz.fomichev.sandbox;

import java.util.UUID;
import xyz.fomichev.sandbox.model.Student;

public class TestDataFactory {

    public static final UUID STUDENT_ID = UUID.fromString("ef76da29-9e8a-4c49-a7e5-73c926558222");
    public static final String STUDENT_LAST_NAME = "Best last name";

    public static Student.StudentBuilder aStudent() {
        return Student.builder()
                .id(STUDENT_ID)
                .lastName(STUDENT_LAST_NAME);
    }
}
