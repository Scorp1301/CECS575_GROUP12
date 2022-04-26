public  class EventFactory
{
public Event getEvent(String eventType)
{
if(eventType == null)
{
return null;
}

if(eventType.equalsIgnoreCase("BookingTheEvent"))
{
return new BookingTheEvent();
}
else if (eventType.equalsIgnoreCase("HostingTheEvent"))
{
return new HostingTheEvent();
}
return null;
}
}