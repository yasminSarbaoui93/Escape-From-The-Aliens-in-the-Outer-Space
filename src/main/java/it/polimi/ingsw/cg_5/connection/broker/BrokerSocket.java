package it.polimi.ingsw.cg_5.connection.broker;


import java.util.ArrayList;
public class BrokerSocket extends Thread implements Broker{

	private ArrayList<BrokerThread> subscribers = new ArrayList<BrokerThread>();

	private String topic;
	
	
	public BrokerSocket(String topic){
		this.topic = topic;
		this.start();
	}
	
	@Override
	public void setTopic(String topic){
		this.topic = topic;
	}
	
	public String getTopic() {
		return topic;
	}

	@Override
	public void publish(Boolean chat, String msg){

		if(!subscribers.isEmpty()){
			System.out.println("Publishing message");
			for (BrokerThread sub : subscribers) {
				sub.dispatchMessage(chat, msg);

			}
		}else{
			System.err.println("No subscribers!!");
		}
	}
	
	@Override
	public void publishNumberGame(Integer numberGame, Integer playerId) {
		if(!subscribers.isEmpty()){
			System.out.println("Publishing message");
			for (BrokerThread sub : subscribers) {
				try {
					sub.updateNumberGame(numberGame);
					sub.updatecurrentPlayerId(playerId);
				} catch (InterruptedException e) {
					System.err.println(e.getMessage());
				}
				
			}
		}else{
			System.err.println("No subscribers!!");
		}
	}
	

	@Override
	public void subscribe(PubSubCommunication o) {
		BrokerThread r = (BrokerThread)o;
		subscribers.add(r);
	//	r.start();
		System.out.println(subscribers.size());
	}
	

}
