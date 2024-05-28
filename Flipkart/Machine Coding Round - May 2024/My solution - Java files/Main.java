import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        QnAPlatform platform = new QnAPlatform();

        platform.subscribe(Arrays.asList("java", "hadoop","jdk"), "userId1"); // userId1 is subscribed

        String q1 = platform.addQuestion("What are new open source jdks?", Arrays.asList("java", "jdk"), "userId1");
        //Q-1 is added by userId1

        String q2 = platform.addQuestion("Does Hadoop work on JDK 11?", Arrays.asList("hadoop", "jdk"), "userId1");
        //Q-2 is added by userId1

        System.out.println(platform.showFeed("userId1"));
        //[Question{, question='What are new open source jdks?', topic='[java, jdk]', upvotes=0},
        // Question{, question='Does Hadoop work on JDK 11?', topic='[hadoop, jdk]', upvotes=0}]

        System.out.println(platform.showFeed("java", "userId1"));
        //[Question{, question='What are new open source jdks?', topic='[java, jdk]', upvotes=0}]

        System.out.println(platform.showFeed("jdk", "userId1"));
        //[Question{, question='What are new open source jdks?', topic='[java, jdk]', upvotes=0}, Question{, question='Does Hadoop work on JDK 11?', topic='[hadoop, jdk]', upvotes=0}]

        System.out.println(platform.showFeed(true, "userId1"));
        // []

        platform.subscribe(Arrays.asList("apache", "hadoop"), "userId2");
        //userId2 is subscribed

        System.out.println(platform.showFeed("userId2"));
        // [Question{, question='Does Hadoop work on JDK 11?', topic='[hadoop, jdk]', upvotes=0}]

        String q3 = platform.addQuestion("Does Apache Spark support streaming of data from hdfs?", Arrays.asList("apache", "hadoop"), "userId2");
        // Q-3 is added by userId2

        String a1 = platform.answerQuestion(q2, "Yeah Hadoop 3 and above supports it.", "userId2");
        //A-1 is added by userId2

        System.out.println(platform.showFeed("userId2"));
        //[Question{, question='Does Apache Spark support streaming of data from hdfs?', topic='[apache, hadoop]', upvotes=0}, Question{, question='Does Hadoop work on JDK 11?', topic='[hadoop, jdk]', upvotes=0}]

        System.out.println(platform.showFeed(true, "userId1"));
        //[Question{, question='Does Hadoop work on JDK 11?', topic='[hadoop, jdk]', upvotes=0}]

        platform.subscribe(Arrays.asList("apache","java", "hadoop"), "userId3");
        //userId3 is subscribed

        System.out.println(platform.showFeed("userId3"));
        //[Question{, question='What are new open source jdks?', topic='[java, jdk]', upvotes=0},
        // Question{, question='Does Apache Spark support streaming of data from hdfs?', topic='[apache, hadoop]', upvotes=0},
        // Question{, question='Does Hadoop work on JDK 11?', topic='[hadoop, jdk]', upvotes=0}]

        System.out.println(platform.showQuestion(q2, "userId3"));
        //Question: Does Hadoop work on JDK 11?
        //User: userId1
        //Topics: [hadoop, jdk]
        //Answers for the question are:
        //Answer: Yeah Hadoop 3 and above supports it.
        //Answer Upvote: 0
        //Comments: []

        platform.upvoteQuestion(q2, "userId3");
        System.out.println(platform.showFeed("userId1"));
        //[Question{, question='What are new open source jdks?', topic='[java, jdk]', upvotes=0},
        // Question{, question='Does Apache Spark support streaming of data from hdfs?', topic='[apache, hadoop]', upvotes=0},
        // Question{, question='Does Hadoop work on JDK 11?', topic='[hadoop, jdk]', upvotes=1}]
        platform.upvoteAnswer(a1, "userId3");

        //bonus
        platform.respondToAnswer(a1, "This is my response.", "userId3");
        System.out.println(platform.showQuestion(q2, "userId3"));
        //Question: Does Hadoop work on JDK 11?
        //User: userId1
        //Topics: [hadoop, jdk]
        //Answers for the question are:
        //Answer: Yeah Hadoop 3 and above supports it.
        //Answer Upvote: 1
        //Comments: [This is my response.]


        platform.unsubscribe(Arrays.asList("java","hadoop","jdk"), "userId1");
        System.out.println("UserId1 has subcribed to below:");
        System.out.println(platform.showFeed("userId1"));
        //UserId1 has subcribed to below:
        //[]

    }
}