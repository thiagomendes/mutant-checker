package br.com.mutant.checker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MutantCheckerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MutantCheckerApplication.class, args);
	}

}
