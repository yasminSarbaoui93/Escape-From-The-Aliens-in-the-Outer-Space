package it.polimi.ingsw.cg_5.view;

import it.polimi.ingsw.cg_5.view.subscriber.SubscriberInterfaceRmi;

public class User {
	private SubscriberInterfaceRmi userSubscriber;
	private Integer PlayerId;

	public User(SubscriberInterfaceRmi userSubscriber, Integer playerId) {
		super();
		this.userSubscriber = userSubscriber;
		PlayerId = playerId;
	}

	public SubscriberInterfaceRmi getUserSubscriber() {
		return userSubscriber;
	}

	public void setUserSubscriber(SubscriberInterfaceRmi userSubscriber) {
		this.userSubscriber = userSubscriber;
	}

	public Integer getPlayerId() {
		return PlayerId;
	}

	public void setPlayerId(Integer playerId) {
		PlayerId = playerId;
	}
}
