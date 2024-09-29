package com.jonathanfoucher.tzatzikiexample.steps;

import com.jonathanfoucher.tzatzikiexample.TzatzikiExampleApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.apache.groovy.util.Maps;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.HostPortWaitStrategy;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = TzatzikiExampleApplication.class)
@ContextConfiguration(initializers = TzatzikiExampleApplicationSteps.Initializer.class)
public class TzatzikiExampleApplicationSteps {
    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17-alpine")
                .withTmpFs(Maps.of("/var/lib/postgresql/data", "rw"));

        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            postgres.waitingFor(new HostPortWaitStrategy());
            postgres.start();
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgres.getJdbcUrl() + "&currentSchema=my_schema",
                    "spring.datasource.username=" + postgres.getUsername(),
                    "spring.datasource.password=" + postgres.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}
