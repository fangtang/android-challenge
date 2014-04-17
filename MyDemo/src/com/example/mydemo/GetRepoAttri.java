package com.example.mydemo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.CommitService;
import org.eclipse.egit.github.core.service.RepositoryService;

public class GetRepoAttri{

	private String userName, repoName;
	private GitHubClient client;
	private List<MsgShow> msgList;

	public GetRepoAttri() {
		this.client = new GitHubClient();
		this.msgList = new ArrayList<MsgShow>();
	}

	private void getRepoAttri() {
		// TODO Auto-generated method stub
		client.setCredentials("buaaNY", "woaini52344");
		Repository repo = null;
		try {
			RepositoryService repoService = new RepositoryService(client);
			repo = repoService.getRepository(userName, repoName);
			CommitService commitService = new CommitService(client);
			for (Collection<RepositoryCommit> commitList : commitService
					.pageCommits(repo, 50)) {

				for (RepositoryCommit commit : commitList) {
					MsgShow msg = new MsgShow(commit.getCommit().getAuthor()
							.getName(), commit.getCommit().getAuthor()
							.getDate(), commit.getCommit().getMessage());
					msgList.add(msg);
				}
				break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(repoName + "," + userName + " error!\t"
					+ new Date().toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(repoName + "," + userName + " error!\t"
					+ new Date().toString());
		}
	}
	private List<HashMap<String, String>> MsgConvert(){
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		for(MsgShow msg : msgList){
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("name", msg.getAuthor());
			map.put("date",msg.getDate().toString());
			map.put("content", msg.getCommitMsg());		
			list.add(map);
		}
		return list;
	}
	
	private void setUserName(String userName) {
		this.userName = userName;
	}

	private void setRepoName(String repoName) {
		this.repoName = repoName;
	}

	public List<HashMap<String, String>> getMsgList(String userName,String repoName) {
		setUserName(userName);
		setRepoName(repoName);
		getRepoAttri();
		return MsgConvert();
	}

}
