package it.polimi.ingsw.cg_5.view;

import it.polimi.ingsw.cg_5.connection.SubscriberInterface;

public class User {
	private SubscriberInterface userSubscriber;
	private Integer PlayerId;

	public User(SubscriberInterface userSubscriber, Integer playerId) {
		super();
		this.userSubscriber = userSubscriber;
		PlayerId = playerId;
	}

	public SubscriberInterface getUserSubscriber() {
		return userSubscriber;
	}

	public void setUserSubscriber(SubscriberInterface userSubscriber) {
		this.userSubscriber = userSubscriber;
	}

	public Integer getPlayerId() {
		return PlayerId;
	}

	public void setPlayerId(Integer playerId) {
		PlayerId = playerId;
	}
}
