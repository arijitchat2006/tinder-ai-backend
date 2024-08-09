package io.techarchitect.tinder_ai_backend;

import io.techarchitect.tinder_ai_backend.profiles.Gender;
import io.techarchitect.tinder_ai_backend.profiles.Profile;
import io.techarchitect.tinder_ai_backend.profiles.ProfileRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TinderAiBackendApplication implements CommandLineRunner {

	@Autowired
	private ProfileRepository profileRepository;

	public static void main(String[] args) {
		SpringApplication.run(TinderAiBackendApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Profile profile = new Profile(
				"1",
				"Arijit",
				"Chatterjee",
				"38",
				"Indian",
				Gender.MALE,
				"Software Programmer",
				"Foo.jpg",
				"INTP"
		);
		profileRepository.save(profile);
		profileRepository.findAll().forEach(System.out :: println);
	}
}
