package com.macbitsgoa.icef.Lists;

/**
 * Created by aayush on 10/2/18.
 */

@SuppressWarnings("ALL")
public class FaqList {
    private String question;
    private String answer;


    public FaqList() {

    }

    public FaqList(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


}
