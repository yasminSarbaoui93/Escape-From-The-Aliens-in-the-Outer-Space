package it.polimi.ingsw.cg_5.view;

import it.polimi.ingsw.cg_5.connection.broker.PubSubCommunication;

public class User {
	private PubSubCommunication userSubscriber;
	private Integer PlayerId;

	public User(PubSubCommunication userSubscriber, Integer playerId) {
		super();
		this.userSubscriber = userSubscriber;
		PlayerId = playerId;
	}

	public PubSubCommunication getUserSubscriber() {
		return userSubscriber;
	}

	public void setUserSubscriber(PubSubCommunication userSubscriber) {
		this.userSubscriber = userSubscriber;
	}

	public Integer getPlayerId() {
		return PlayerId;
	}

	public void setPlayerId(Integer playerId) {
		PlayerId = playerId;
	}
}
