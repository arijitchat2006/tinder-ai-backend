package io.techarchitect.tinder_ai_backend;

import io.techarchitect.tinder_ai_backend.conversations.ChatMessage;
import io.techarchitect.tinder_ai_backend.conversations.Conversation;
import io.techarchitect.tinder_ai_backend.conversations.ConversationRepository;
import io.techarchitect.tinder_ai_backend.profiles.Gender;
import io.techarchitect.tinder_ai_backend.profiles.Profile;
import io.techarchitect.tinder_ai_backend.profiles.ProfileRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class TinderAiBackendApplication implements CommandLineRunner {

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private ConversationRepository conversationRepository;

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

		profile = new Profile(
				"2",
				"Foo",
				"Bar",
				"38",
				"Indian",
				Gender.MALE,
				"Software Programmer",
				"Foo.jpg",
				"INTP"
		);
		profileRepository.save(profile);

		profileRepository.findAll().forEach(System.out :: println);

		Conversation conversation = new Conversation(
				"1",
				profile.id(),
				List.of(new ChatMessage("Hello", profile.id(), LocalDateTime.now()))
		);
		conversationRepository.save(conversation);
		conversationRepository.findAll().forEach(System.out :: println);
	}
}
