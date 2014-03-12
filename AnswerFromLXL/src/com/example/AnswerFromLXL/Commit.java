package com.example.AnswerFromLXL;


import java.io.Serializable;

public class Commit implements Serializable{
    private String person = null;
    private String commit = null;
    private String message = null;

    public Commit(String person, String commit, String message) {
        this.person = person;
        this.commit = commit;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Commit{" +
                "person='" + person + '\'' +
                ", commit='" + commit + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getCommit() {
        return commit;
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
