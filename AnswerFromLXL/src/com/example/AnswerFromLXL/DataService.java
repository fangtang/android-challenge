package com.example.AnswerFromLXL;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import org.eclipse.egit.github.core.CommitComment;
import org.eclipse.egit.github.core.Contributor;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.client.NoSuchPageException;
import org.eclipse.egit.github.core.client.PageIterator;
import org.eclipse.egit.github.core.service.CommitService;
import org.eclipse.egit.github.core.service.ContentsService;
import org.eclipse.egit.github.core.service.RepositoryService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by takall on 14-3-12.
 */
public class DataService extends IntentService {
    final static String TAG = "DataService";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public DataService(String name) {
        super(name);
    }

    public DataService() {
        super("");
        Log.d(TAG, "Constructor");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "onHandleIntent");
        SomeAction();
    }

    /**
     *获取数据的逻辑块，完成后广播到UI线程
     */
    private void SomeAction(){
        try {
            //Connect to the Github API
            GitHubClient client = new GitHubClient();
            //it is my token to access Github
            client.setOAuth2Token("9943a527eb43fe50ec55acbbc3c8651bd85381c5");
            RepositoryService service = new RepositoryService(client);
            CommitService commitService = new CommitService(client);
            //Find the rails/rails repository
            Repository repo = service.getRepository("rails", "rails");
            //Find the most recent commits (choose at least 25 or more of the commits)
            //commitService.getCommits()           - - the hole
            PageIterator<RepositoryCommit> pageIterator = commitService.pageCommits(repo, 25);
            List<RepositoryCommit> commitList = new ArrayList<RepositoryCommit>();
            try {
                commitList.addAll(pageIterator.next());
            } catch (NoSuchPageException pageException) {
                throw pageException.getCause();
            }
            List<Commit> commits = new ArrayList<Commit>();
            for (int i = 0; i < commitList.size(); i++) {
                RepositoryCommit commit = commitList.get(i);
                commits.add(new Commit(commit.getCommit().getAuthor().getName(),commit.getCommit().getSha(),commit.getCommit().getMessage()));
                Log.i(TAG, commit.getCommit().getAuthor().getName() + " -- " + commit.getCommit().getSha() + " -- " + commit.getCommit().getMessage());
            }
            Intent intent = new Intent(Constant.DATA_GIT);
            intent.putExtra("data",new Commits(commits));
            sendBroadcast(intent);
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        }
    }
}
