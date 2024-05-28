import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

class Answer {
    String id;
    String answer;
    String userId;
    List<String> responses;
    private int upvotes = 0;

    Answer(String id, String answer, String userId) {
        this.id = id;
        this.answer = answer;
        this.userId = userId;
        this.responses = new ArrayList<>();
    }

    String getAnswer() {
        return this.answer;
    }

    String getUserId() {
        return this.userId;
    }

    List<String> getResponses() {
        return this.responses;
    }

    void addResponse(String response) {
        this.responses.add(response);
    }

    public void upvote() {
        this.upvotes++;
    }

    public int getUpvotes() {
        return this.upvotes;
    }

}