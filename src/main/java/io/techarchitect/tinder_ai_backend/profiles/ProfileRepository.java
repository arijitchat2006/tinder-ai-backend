package io.techarchitect.tinder_ai_backend.profiles;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends MongoRepository<Profile, String> {

    // This is an aggregation query in MongoDB used for summarization, reporting, etc. Can be used for analytics, etc.
    @Aggregation(pipeline = { "{ $sample: {size: 1 } }" })
    Profile getRandomProfile();
}
