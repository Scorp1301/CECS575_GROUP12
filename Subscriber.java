public class Subscriber implements Observer
{
  private String name;
  private Channel channel = new Channel();
  
  //create a constructor for giving subscriber's name
  
  public Subscriber(String name)
  {
	  super();
	  this.name = name;
  }

  @Override
  public void update()
  {
    System.out.println("Hey " + name + " Event Uploaded: " + channel.title);
  }
  
  @Override
  //subscriber should know which event he is subscribing
  public void subscribeChannel(Channel ch)
  {
	channel = ch;
	
  }
}