package io.techarchitect.tinder_ai_backend;

import io.techarchitect.tinder_ai_backend.conversations.ChatMessage;
import io.techarchitect.tinder_ai_backend.conversations.Conversation;
import io.techarchitect.tinder_ai_backend.conversations.ConversationRepository;
import io.techarchitect.tinder_ai_backend.matches.MatchRepository;
import io.techarchitect.tinder_ai_backend.profiles.Gender;
import io.techarchitect.tinder_ai_backend.profiles.Profile;
import io.techarchitect.tinder_ai_backend.profiles.ProfileCreationService;
import io.techarchitect.tinder_ai_backend.profiles.ProfileRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
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
	private MatchRepository matchRepository;
	@Autowired
	private ConversationRepository conversationRepository;
	@Autowired
	private ProfileCreationService profileCreationService;

	public static void main(String[] args) {
		SpringApplication.run(TinderAiBackendApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		clearAllData();
		profileCreationService.saveProfilesToDB();
	}

	private void clearAllData() {
		conversationRepository.deleteAll();
		matchRepository.deleteAll();
		profileRepository.deleteAll();
	}
}
