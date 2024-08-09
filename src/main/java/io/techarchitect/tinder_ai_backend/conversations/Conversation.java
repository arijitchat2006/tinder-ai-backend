package io.techarchitect.tinder_ai_backend.conversations;

import io.techarchitect.tinder_ai_backend.profiles.Profile;

import java.util.List;

public record Conversation(
        String id,
        String profileId,
        List<ChatMessage> message
) {
}
