import java.util.HashSet;
import java.util.Set;

class Topic {
    String name;
    Set<String> subscribers;

    Topic(String name) {
        this.name = name;
        this.subscribers = new HashSet<>();
    }

    void subscribe(String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        subscribers.add(userId);
    }

    void unsubscribe(String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        subscribers.remove(userId);
    }

    boolean isSubscribed(String userId) {
        return subscribers.contains(userId);
    }
}