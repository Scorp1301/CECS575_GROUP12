public class Mediator {
    private Venue ven;
    private CateringService cs;
    private Event eve;

    // constructor, getters and setters
   
	public Mediator(Venue ven,CateringService cs,Event eve) {
    this.ven=ven;
    this.CateringService = cs;
    this.eve=eve;
	}

	 public String getName() {
        return name;
    }



 public void getVenue()
{
return ven;
}


    public void check() {
        if (ven.isOn()) {
            ven.checkAvailability();
        } else {
            ven.ReturnAvailability();
        }
    }

    public void start() {
       eve.proceedEventBooking();
    }

    public void stop() {
        eve.stopEventBooking();
    }
}