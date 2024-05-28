import java.util.*;

class QnAPlatform {
    Map<String, Topic> topics;
    Map<String, Question> questions;
    Map<String, List<Question>> userQuestions;
    Map<String, Answer> answers;
    private static int questionCounter = 1;
    private static int answerCounter = 1;
    QnAPlatform() {
        this.topics = new HashMap<>();
        this.questions = new HashMap<>();
        this.userQuestions = new HashMap<>();
        this.answers = new HashMap<>();
    }

    void subscribe(List<String> topicNames, String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        for (String topicName : topicNames) {
            Topic topic = topics.getOrDefault(topicName, new Topic(topicName));
            topic.subscribe(userId);
            topics.put(topicName, topic);
        }
        System.out.println(userId +" is subscribed");
    }

    void unsubscribe(List<String> topicNames, String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        for (String topicName : topicNames) {
            Topic topic = topics.get(topicName);
            if (topic != null) {
                topic.unsubscribe(userId);
            }
        }
    }

    String addQuestion(String question, List<String> topics, String userId) {
        if (question == null || question.isEmpty()) {
            throw new IllegalArgumentException("Question cannot be null or empty");
        }
        if (topics == null || topics.isEmpty()) {
            throw new IllegalArgumentException("Topics cannot be null or empty");
        }
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        String questionId = "Q-" + questionCounter++;
        Question newQuestion = new Question(questionId, question, topics, userId);
        questions.put(questionId, newQuestion);

        for(String topic : topics) {
            Topic top = this.topics.get(topic);
            if(top != null && top.isSubscribed(userId)) {
                if(userQuestions.containsKey(userId)) {
                    userQuestions.get(userId).add(newQuestion);
                } else {
                    List<Question> questionList = new ArrayList<>();
                    questionList.add(newQuestion);
                    userQuestions.put(userId, questionList);
                }
            }
        }
        System.out.println(questionId +" is added by "+ userId);
        return questionId;
    }
    List<Question> showFeed(String userId) {
        List<Question> feed = new ArrayList<>();
        for (Question question : questions.values()) {
            for (String topic : question.topics) {
                if (topics.get(topic).isSubscribed(userId)) {
                    feed.add(question);
                    break;
                }
            }
        }
        return feed;
    }
    List<Question> showFeed( String filterTopic, String userId) {
        List<Question> feed = new ArrayList<>();
        for (Question question : questions.values()) {
            if (question.topics.contains(filterTopic) && topics.get(filterTopic).isSubscribed(userId)) {
                feed.add(question);
            }
        }
        return feed;
    }
    List<Question> showFeed(boolean answered, String userId) {
        List<Question> feed = new ArrayList<>();
        for (Question question : questions.values()) {
            if (answered && question.isAnswered() && question.getUserId().equals(userId)) {
                feed.add(question);
            } else if (!answered && !question.isAnswered() && question.getUserId().equals(userId)) {
                feed.add(question);
            }
        }
        return feed;
    }
    String answerQuestion(String questionId, String answer, String userId) {
        Question question = questions.get(questionId);
        String newAnswerId = null;
        if(question != null) {
            for(String topic : question.topics) {
                if(topics.get(topic).isSubscribed(userId)) {
                    newAnswerId = "A-"+answerCounter++;
                    Answer newAnswer = new Answer(newAnswerId, answer, userId);
                    question.addAnswer(newAnswer);
                    answers.put(newAnswerId, newAnswer);
                }
            }
        }
        System.out.println(newAnswerId +" is added by "+ userId);
        return newAnswerId;
    }
    String showQuestion(String questionId , String userId) {
        StringBuilder sb = new StringBuilder();
        Question question = questions.get(questionId);
        if (question != null) {
            sb.append("Question: " + question.getQuestion() + "\n");
            sb.append("User: " + question.getUserId() + "\n");
            sb.append("Topics: " + question.getTopics() + "\n");
            sb.append("Answers for the question are: " + "\n");
            for (Answer answer : question.getAnswers()) {
                sb.append("Answer: " + answer.getAnswer() + "\n");
                sb.append("Answer Upvote: " + answer.getUpvotes() + "\n");
                sb.append("Comments: " + answer.getResponses() + "\n");
            }
        } else {
            sb.append("No such question exists");
        }
        return sb.toString();
    }

    void upvoteQuestion(String questionId, String userId) {
        Question question = questions.get(questionId);
        if(question != null) {
            for(String topic : question.topics) {
                if(topics.get(topic).isSubscribed(userId)) {
                    question.upvote();
                }
            }
        }
    }
    void upvoteAnswer(String answerId, String userId) {
        Answer answer = answers.get(answerId);
        if(answer != null) {
            answer.upvote();
        } else {
            System.out.println("No such answer exists");
        }
    }
    void respondToAnswer(String answerId, String response, String userId) {
        for(Question question : questions.values()) {
            for(Answer answer : question.answers) {
                if(answer.id.equals(answerId)) {
                    answer.addResponse(response);
                }
            }
        }
    }
}