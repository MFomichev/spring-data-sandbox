package xyz.fomichev.sandbox;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan
public class ApplicationConfiguration {
}
