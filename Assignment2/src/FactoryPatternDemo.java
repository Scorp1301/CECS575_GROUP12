public class FactoryPatternDemo
{
public static void main(String[] args)
{
EventFactory eve=new EventFactory();

Event eve1=eve.getEvent("BookingTheEvent");
eve1.organise();

Event eve2=eve.getEvent("HostingTheEvent");
eve2.organise();
}
}