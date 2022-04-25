
//Scenario : All the subscribers are observers. They are observing the subject. 
//In our case, the subject is the channel. Everytime we upload an event in our system/application, the subscribers
//will get notification.
//Similar to pushing from subject to observer.


package com.demo;
// we want to create multiple events

public class Event
{
    private String name;            //Name of the event
    private String type;            //Type of the event
    private String date;            //Date of the event
    private int total_guests;       //Number of total guests in the event
    private String starting_time;           //Shift starts
    private String ending_time;             //Shift ends
    private String event_id;
    private int price;
    Payment payment;
    Bill bill;
    String approved;

    public Event() {
        name = type = date = starting_time = ending_time = "";
        total_guests = 0;
        payment = new Payment();
        bill = new Bill();
    }

    public Event(String name, String type, String date, int total_guests, String starting_time, String ending_time, Bill bill, int price) {
        this.name = name;
        this.type = type;
        this.date = date;
        this.total_guests = total_guests;
        this.starting_time = starting_time;
        this.ending_time = ending_time;
        this.bill = bill;

        this.price = price;
    }

    public Event(String id, String name, String type, String date, int total_guests, String starting_time, String ending_time, Bill bill) {
        this.event_id = id;
        this.name = name;
        this.type = type;
        this.date = date;
        this.total_guests = total_guests;
        this.starting_time = starting_time;
        this.ending_time = ending_time;
        this.bill = bill;

        price = this.getTotalBill();
    }

    public String getEvent_id() {
        return event_id;
    }
    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public int getTotal_guests() {
        return total_guests;
    }
    public void setTotal_guests(int total_guests) {
        this.total_guests = total_guests;
    }

    public String getStarting_time() {
        return starting_time;
    }
    public void setStarting_time(String starting_time) {
        this.starting_time = starting_time;
    }

    public String getEnding_time() {
        return ending_time;
    }
    public void setEnding_time(String ending_time) {
        this.ending_time = ending_time;
    }

    public int getTotalBill() {
        return bill.totalCost(total_guests);
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public String getApproved() {
        return approved;
    }
    public void setApproved(int app) {
       this.approved = (app == 1) ? "Yes" : "No";
    }

    /////////////////// Utility Functions ////////////////////


  public void proceedEventBooking()
{
bookEventWithInput(String cust_id);
}


public void stopEventBooking()
{
System.out.println("Cant Book Events");
}


    public void inputInitialEventDetails() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter event name: ");
        this.name = input.nextLine();

        System.out.print("Enter event type: ");
        this.type = input.nextLine();

        System.out.print("Enter event date: ");
        this.date = input.nextLine();

        System.out.print("Enter starting time: ");
        this.starting_time = input.nextLine();

        System.out.print("Enter ending time: ");
        this.ending_time = input.nextLine();

        System.out.print("Enter number of guests expected: ");
        this.total_guests = input.nextInt();
    }

    public void bookEventWithInput(String cust_id) {
        this.inputInitialEventDetails();

        EventDB eventDB = new EventDB();

        String venue_id = eventDB.selectVenue();
        String catering_id = eventDB.selectCateringService();
        String menu_id = eventDB.selectMenu();
        String studio_id = eventDB.selectStudio();
        String media_id = eventDB.enterMediaRequirements();

        bill.setMenu_cost(eventDB.getChosenMenuCost(menu_id));
        bill.setVenue_cost(eventDB.getChosenVenueCost(venue_id));
        bill.setCatering_cost(eventDB.getChosenCateringServiceCost(catering_id));
        bill.setStudio_cost(eventDB.getChosenStudioCost(studio_id));

        price = this.getTotalBill();
        payment.inputDetails();

        eventDB.addEvent(name, type, date, total_guests, price, starting_time, ending_time, cust_id, venue_id, studio_id, menu_id, catering_id, media_id, 0);
    }

    public void bookEvent(String cust_id, String venue_id, String studio_id, String menu_id, String catering_id, String media_id) {
        EventDB eventDB = new EventDB();

        bill.setMenu_cost(eventDB.getChosenMenuCost(menu_id));
        bill.setVenue_cost(eventDB.getChosenVenueCost(venue_id));
        bill.setCatering_cost(eventDB.getChosenCateringServiceCost(catering_id));
        bill.setStudio_cost(eventDB.getChosenStudioCost(studio_id));

        price = this.getTotalBill();

        eventDB.addEvent(name, type, date, total_guests, price, starting_time, ending_time, cust_id, venue_id, studio_id, menu_id, catering_id, media_id, 0);
    }

    public Event getEvent(String id, int IDtype) {
        EventDB obj = new EventDB();
        return obj.getEvent(id, IDtype);
    }

    // all IDS associated with an event - customer id, venue id etc
    public HashMap<String, String> getEventIDs(String id, int IDtype) {
        EventDB obj = new EventDB();
        return obj.getEventIDs(id, IDtype);
    }

    public ArrayList<Event> getListOfEvents() {
        EventDB obj = new EventDB();
        return obj.getListOfEvents();
    }

    public void deleteEvent() {
        EventDB obj = new EventDB();
        obj.removeEvent(this.event_id);

        HashMap<String, String> eventIDs = this.getEventIDs(this.event_id,0);

        String media_id = eventIDs.get("media_id");
        String menu_id = eventIDs.get("menu_id");

        Media_RequirementsDB mediaObj = new Media_RequirementsDB();
        mediaObj.removeMediaRequirement(media_id);

        MenuDB menuObj = new MenuDB();
        menuObj.removeMenu(menu_id);
    }

    public boolean isDateBooked() {
        EventDB obj = new EventDB();
        return obj.isDateBooked(this.getDate());
    }

    public String getCustomerEmailByEventID(String id) {
        EventDB obj = new EventDB();
        return obj.getCustomerEmailByEventID(id);
    }

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
