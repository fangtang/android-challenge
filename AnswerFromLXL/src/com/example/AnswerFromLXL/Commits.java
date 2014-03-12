package com.example.AnswerFromLXL;

import java.io.Serializable;
import java.util.List;

/**
 * Created by takall on 14-3-12.
 */
public class Commits implements Serializable {
    private List<Commit> commits = null;

    public Commits(List<Commit> commits) {
        this.commits = commits;
    }

    public List<Commit> getCommits() {
        return commits;
    }

    public void setCommits(List<Commit> commits) {
        this.commits = commits;
    }
}
