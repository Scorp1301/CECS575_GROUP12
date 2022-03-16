package sample;


import java.sql.*;
import java.util.Scanner;

public class CustomerDB
{
   
    public void displayCustomer(String id)                              {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Globals.getDb_name(), Globals.getDb_username(), Globals.getDb_pass());
        )
        {
            if (conn != null)
            {
                System.out.println("Database - display a customer");

                String query = "select * from customer where CUST_ID = " + id;  // query to be sent

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next())
                {                                            
                    String name = rs.getString("name");       
                   String phone = rs.getString("phone_no");
                    String email = rs.getString("email");
                    String acc = rs.getString("account_number");
                    String ps = rs.getString("priority_status");
                    String cnic = rs.getString("cnic");
                    String age = rs.getString("age");

                    System.out.print("ID: ");
                    System.out.println(id);

                    System.out.print("Name: ");
                    System.out.println(name);

                    System.out.print("Age: ");
                    System.out.println(age);

                    System.out.print("Phone number ");
                    System.out.println(phone);

                    System.out.print("Email: ");
                    System.out.println(email);

                    System.out.print("CNIC: ");
                    System.out.println(cnic);

                    System.out.print("Account Number: ");
                    System.out.println(acc);

                    System.out.print("Priority Status: ");
                    System.out.println(ps);
                }
            }

            else
            {
                System.out.println("Failed to make connection!");
            }
        }

        catch (SQLException e)
        {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //Method implementing CustomerBuilder 
    public void insertCustomer()
    {
        

        Scanner input = new Scanner(System.in);

        System.out.print("Enter name: ");
       String name =input.nextLine();

        System.out.print("Enter phone no: ");
        String number= input.nextLine();

        System.out.print("Enter email: ");
       String EmailAddress= input.nextLine();

        System.out.print("Enter cnic: ");
        String cnic=input.nextLine();

        System.out.print("Enter account number: ");
        String accountNUmber=input.nextLine();

        System.out.print("Enter Priority Status: ");
       String status = input.nextLine();

        System.out.print("Enter age: ");
        int age = input.nextLine;

	Customer cust = new Customer.CustomerBuilder(name,number,EmailAddress,cnic,accountNumber,status,age).build();;

        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Globals.getDb_name(), Globals.getDb_username(), Globals.getDb_pass());
        )
        {
            if (conn != null) {
                System.out.println("Database - inserting a customer");

                String query = "select * from customer";  // selects all data from database

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                String last_id = "10000";

                while (rs.next()) {                                            // reads a row
                    last_id = rs.getString("cust_id");             // reads an id
                }

                int temp = Integer.parseInt(last_id);
                temp++;
                String new_id = Integer.toString(temp);

                query = "insert into customer(CUST_ID, name, cnic, age, phone_no, email, ACCOUNT_NUMBER, PRIORITY_STATUS)" +
                        "values('" + new_id + "','" + cust.getName() + "','" + cust.getCnic() + "'," + Integer.toString(cust.getAge()) + ",'" + cust.getContact_info() +
                        "','" + cust.getEmail_address() + "','" + cust.getAccount_number() + "'," + Integer.toString(cust.getPriority_status()) + ")";

                //System.out.println(query);
                stmt.executeUpdate(query);
                stmt.executeUpdate("commit");
            }

            else {
                System.out.println("Failed to make connection!");
            }
        }

        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String insertCustomerWithPassword(Customer cust, String pass)
    {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Globals.getDb_name(), Globals.getDb_username(), Globals.getDb_pass());
        )
        {
            if (conn != null) {
                System.out.println("Database - inserting a customer + password");

                String query = "select * from customer";  // selects all data from database

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                String last_id = "10000";

                while (rs.next()) {                                            // reads a row
                    last_id = rs.getString("cust_id");             // reads an id
                }

                int temp = Integer.parseInt(last_id);
                temp++;
                String new_id = Integer.toString(temp);

                query = "insert into customer(CUST_ID, name, cnic, age, phone_no, email, ACCOUNT_NUMBER, PRIORITY_STATUS)" +
                        "values('" + new_id + "','" + cust.getName() + "','" + cust.getCnic() + "'," + Integer.toString(cust.getAge()) + ",'" + cust.getContact_info() +
                        "','" + cust.getEmail_address() + "','" + cust.getAccount_number() + "'," + Integer.toString(cust.getPriority_status()) + ")";

                //System.out.println(query);
                stmt.executeUpdate(query);
                stmt.executeUpdate("commit");

                this.addPasswordRecord(new_id, pass);

                return  new_id;
            }

            else {
                System.out.println("Failed to make connection!");
            }
        }

        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public void insertCustomer(Customer cust, String id)
    {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Globals.getDb_name(), Globals.getDb_username(), Globals.getDb_pass());
        )
        {
            if (conn != null) {
                 System.out.println("Database - inserting customer + id!");

                Statement stmt = conn.createStatement();

                String query = "insert into customer(CUST_ID, name, cnic, age, phone_no, email, ACCOUNT_NUMBER, PRIORITY_STATUS)" +
                        "values('" + id + "','" + cust.getName() + "','" + cust.getCnic() + "'," + Integer.toString(cust.getAge()) + ",'" + cust.getContact_info() +
                        "','" + cust.getEmail_address() + "','" + cust.getAccount_number() + "'," + Integer.toString(cust.getPriority_status()) + ")";

                //System.out.println(query);
                stmt.executeUpdate(query);
                stmt.executeUpdate("commit");

                this.addPasswordRecord(id, id);
            }

            else {
                System.out.println("Failed to make connection!");
            }
        }

        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

   
   
    // Returns true if password correct against that id, false otherwise
    public boolean customerLogin(String idOrEmail, String pass) {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Globals.getDb_name(), Globals.getDb_username(), Globals.getDb_pass());
        )
        {
            if (conn != null)
            {
                System.out.println("Database - logging in customer");
                String query;

                if (idOrEmail.indexOf('@') == -1) {
                    query = "SELECT password FROM customerpass WHERE cust_id = \'" + idOrEmail + "\'";
                }

                else {
                    query = "SELECT password FROM customerpass WHERE cust_id IN " +
                            "(SELECT cust_id FROM customer WHERE email = \'" + idOrEmail + "\')";
                }

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                String db_pass = "";

                while (rs.next()) {
                    db_pass = rs.getString("password");

                    if (pass.equals(db_pass))
                        return true;
                }
            }

            else
            {
                System.out.println("Failed to make connection!");
            }
        }

        catch (SQLException e)
        {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

        // Returns true if password changed successfully, false otherwise
    public boolean changeCustomerPassword(String id, String old_pass, String new_pass) {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Globals.getDb_name(), Globals.getDb_username(), Globals.getDb_pass());
        )
        {
            if (conn != null) {
                System.out.println("Database - changing customer password");

                String query = "update CUSTOMERPASS set PASSWORD = '" + new_pass + "' where CUST_ID = '" + id + "' and PASSWORD = '" + old_pass + "'";

                Statement stmt = conn.createStatement();
                stmt.executeUpdate(query);

                query = "commit";
                stmt.executeUpdate(query);

                return true;
            }

            else {
                System.out.println("Failed to make connection!");
            }
        }

        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    }