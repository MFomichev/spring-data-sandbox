package xyz.fomichev.sandbox;

import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Rollback
@Transactional
public class TransactionalBaseTest extends BaseTest {

}
