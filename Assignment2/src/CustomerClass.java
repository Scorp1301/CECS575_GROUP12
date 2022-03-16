package sample;
import java.util.*;


public class Customer
{
    private String name;
    private String cnic;
    private int age;
    private String contact_info;
    private String email_address;
    private String account_number;
    private int priority_status;



    private Customer(CustomerBuilder builder)
    {
        this.name = builder.name;
        this.cnic = builder.cnic;
        this.age = builder.age;
        this.contact_info = builder.contact_info;
        this.email_address = builder.email;
        this.account_number = builder.account_number;
        this.priority_status = builder.priority_status;
    }


public String getName()
    {
        return name;
    }
   
    public String getCnic()
    {
        return cnic;
    }
    
    public int getAge()
    {
        return age;
    }
    
    public String getContact_info()
    {
        return contact_info;
    }
    
    public String getEmail_address()
    {
        return email_address;
    }
   
    public String getAccount_number()
    {
        return account_number;
    }
   
    public int getPriority_status()
    {
        return priority_status;
    }



public static class CustomerBuilder
{
    private String name;
    private String cnic;
    private int age;
    private String contact_info;
    private String email_address;
    private String account_number;
    private int priority_status;

}

public CustomerBuilder(String name,String cnic)
{
this.name=name;
this.cnic=cnic;
}

public CustomerBuilder(int age ,String contact_info)
{
this.age=age;
this.contact_info=contact_info;
}

public CustomerBuilder(String account_number)
{
this.account_number=account_number;
}

public CustomerBuilder(int priority_status)
{
this.priority_status=priority_status;
}


  
    
   
    public void display() {
        System.out.print("Name: ");
        System.out.println(name);

        System.out.print("Age: ");
        System.out.println(age);

        System.out.print("Phone number ");
        System.out.println(contact_info);

        System.out.print("Email: ");
        System.out.println(email_address);

        System.out.print("CNIC: ");
        System.out.println(cnic);

        System.out.print("Account Number: ");
        System.out.println(account_number);

        System.out.print("Priority Status: ");
        System.out.println(priority_status);
    }

//Return the finally constructed Customer  object
		public User build() {
			Customer cust =  new Customer(this);
			Boolean validated=validateCustomerObject(cust);
			if(validated)
			return user;
		}

public boolean validateCustomerObject(Customer cust)
    {
if(cust!=null)
{
return true ;
}
else
{
return false;
}
}