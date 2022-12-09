package dat.com.QuizzDT;

import java.io.Serializable;

public class Question implements Serializable {
    private final String questionText, answer1,answer2;
    private final String questionAnswer;

    public Question(String questionText, String answer1, String answer2, String questionAnswer) {
        this.questionText = questionText;
        this.questionAnswer = questionAnswer;
        this.answer1 = answer1;
        this.answer2 = answer2;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

}
