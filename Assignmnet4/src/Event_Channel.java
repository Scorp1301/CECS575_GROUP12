
import java.util.ArrayList;
import java.util.List;

public class Event_Channel implements Subject
{
	private List<Subscriber> subs = new ArrayList<>();
	private String title;
	
	@Override
	public void subscribe(Subscriber sub	//adding subscriber.
	{
		subs.add(sub);
	}
	
	@Override
	public void unSubscribe(Subscriber sub)  //removing subscriber.
	{
		subs.remove(sub);
	}
	
	@Override
	public void notifySubscribers(Subscriber sub)		//notify all the subscribers when a new event is released.
	{
		//iterate through all the subscribers in the list
		for(Subscriber sub : subs)
		{
			sub.update();
		}
	}
	
	@Override
	public void upload(String title)
	{
		this.title = title;
		notifySubscribers();
	}
}