
//Scenario : All the subscribers are observers. They are observing the subject. 
//In our case, the subject is the channel. Everytime we upload an event in our system/application, the subscribers
//will get notification.
//Similar to pushing from subject to observer.


package com.demo;
// we want to create multiple events

public class Event
{
	public static void main(String[] args)
	{
		Channel Event = new Channel();    //create a channel
		//Create new subscribers for the events
		
		Subscriber s1 = new Subscriber("Tom");
		Subscriber s2 = new Subscriber("Jerry");
		Subscriber s3 = new Subscriber("Lilly");
		Subscriber s4 = new Subscriber("Harry");
		Subscriber s5 = new Subscriber("Alex");
		
		//Add subscribers to the channel
		demo.subscribe(s1);
		demo.subscribe(s2);
		demo.subscribe(s3);
		demo.subscribe(s4);
		demo.subscribe(s5);
		
		//If the subscribers wants to unsubscribe to the notifications of the events uploaded
		demo.unSubscribe(s4);
		
		//subscribers should know which event channel they have subscribed
		s1.subscribeChannel(demo);
		s2.subscribeChannel(demo);
		s3.subscribeChannel(demo);
		s4.subscribeChannel(demo);
		s5.subscribeChannel(demo);
		
		
		//upload an event
		demo.upload("Sports Event");
	}
}