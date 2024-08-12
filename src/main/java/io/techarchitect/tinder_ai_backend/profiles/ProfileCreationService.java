package io.techarchitect.tinder_ai_backend.profiles;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@Description("Save the Tinder Profile information")
public class ProfileCreationService {

    private static final String PROFILES_FILE_PATH = "profiles.json";
    private ProfileRepository profileRepository;

    @Value("#{${tinderai.character.user}}")
    Map<String, String> userProfileProperties;

    public ProfileCreationService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public void saveProfilesToDB() {
        Gson gson = new Gson();
        try {
            List<Profile> existingProfiles = gson.fromJson(
                    new FileReader(PROFILES_FILE_PATH),
                    new TypeToken<ArrayList<Profile>>() {}.getType()
            );
            profileRepository.deleteAll();
            profileRepository.saveAll(existingProfiles);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Profile profile = new Profile(
                userProfileProperties.get("id"),
                userProfileProperties.get("firstName"),
                userProfileProperties.get("lastName"),
                Integer.parseInt(userProfileProperties.get("age")),
                userProfileProperties.get("ethnicity"),
                Gender.valueOf(userProfileProperties.get("gender")),
                userProfileProperties.get("bio"),
                userProfileProperties.get("imageUrl"),
                userProfileProperties.get("myersBriggsPersonalityType")
        );
        System.out.println(userProfileProperties);
        profileRepository.save(profile);
    }
}
