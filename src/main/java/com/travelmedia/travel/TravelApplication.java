package com.travelmedia.travel;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@SpringBootApplication()
public class TravelApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelApplication.class, args);
	}

	@Bean
    public Dotenv dotenv() {
        return Dotenv.configure().ignoreIfMissing().load();
    }

    @Bean
    public ConfigurableEnvironment environment(Dotenv dotenv) {
        ConfigurableEnvironment environment = new StandardEnvironment();
        dotenv.entries().forEach(entry -> environment.getSystemProperties().put(entry.getKey(), entry.getValue()));
        return environment;
    }

}
