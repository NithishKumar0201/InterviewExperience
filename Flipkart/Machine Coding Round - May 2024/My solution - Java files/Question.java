import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class Question {
    String id;
    String question;

    String userId;
    List<String> topics;
    List<Answer> answers;
    int upvotes;

    Question(String id, String question, List<String> topics, String userId) {
        this.id = id;
        this.question = question;
        this.userId = userId;
        this.topics = topics;
        this.answers = new ArrayList<>();
        this.upvotes = 0;
    }

    void addAnswer(Answer answer) {
        answers.add(answer);
    }
    boolean isAnswered() {
        return !answers.isEmpty();
    }
    void upvote() {
        upvotes++;
    }
    String getQuestion() {
        return this.question;
    }

    String getUserId() {
        return this.userId;
    }

    List<String> getTopics() {
        return this.topics;
    }

    List<Answer> getAnswers() {
        return this.answers;
    }
    @Override
    public String toString() {
        return "Question{" +
                ", question='" + question + '\'' +
                ", topic='" + topics + '\'' +
                ", upvotes=" + upvotes +
                '}';
    }
}