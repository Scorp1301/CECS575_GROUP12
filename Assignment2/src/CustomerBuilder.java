package sample;


import java.sql.*;
import java.util.Scanner;

public class CustomerBuilder
{
    
    public void displayCustomer(String id)                             {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Globals.getDb_name(), Globals.getDb_username(), Globals.getDb_pass());
        )
        {
            if (conn != null)
            {
                System.out.println("Database - display a customer");

                String query = "select * from customer where CUST_ID = " + id; 

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


	
    public Customer getCustomer(String id)
    {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Globals.getDb_name(), Globals.getDb_username(), Globals.getDb_pass());
        )
        {
            if (conn != null)
            {
                System.out.println("Database - getting a customer");

                String query = "select * from customer where CUST_ID = " + id

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next())
                {                                            
                    String name = rs.getString("name");             
                    String phone = rs.getString("phone_no");
                    String email = rs.getString("email");
                    String acc = rs.getString("account_number");
                    int ps = rs.getInt("priority_status");
                    String cnic = rs.getString("cnic");
                    int age = rs.getInt("age");

                    Customer cust = new Customer(name, cnic, age, phone, email, acc, ps);
                    return cust;
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

        Customer emptyObj = new Customer();
        return emptyObj;
    }

	//Builder Functionality for creating multiple customers
        public void insertCustomer()
    {
        Customer cust = new Customer();

        Scanner input = new Scanner(System.in);

        System.out.print("Enter name: ");
        cust.setName(input.nextLine());

        System.out.print("Enter phone no: ");
        cust.setContact_info(input.nextLine());

        System.out.print("Enter email: ");
        cust.setEmail_address(input.nextLine());

        System.out.print("Enter cnic: ");
        cust.setCnic(input.nextLine());

        System.out.print("Enter account number: ");
        cust.setAccount_number(input.nextLine());

        System.out.print("Enter Priority Status: ");
        cust.setPriority_status(input.nextInt());

        System.out.print("Enter age: ");
        cust.setAge(input.nextInt());

        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Globals.getDb_name(), Globals.getDb_username(), Globals.getDb_pass());
        )
        {
            if (conn != null) {
                System.out.println("Database - inserting a customer");

                String query = "select * from customer"; 

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                String last_id = "10000";

                while (rs.next()) {      
                    last_id = rs.getString("cust_id");     
                }

                int temp = Integer.parseInt(last_id);
                temp++;
                String new_id = Integer.toString(temp);

                query = "insert into customer(CUST_ID, name, cnic, age, phone_no, email, ACCOUNT_NUMBER, PRIORITY_STATUS)" +
                        "values('" + new_id + "','" + cust.getName() + "','" + cust.getCnic() + "'," + Integer.toString(cust.getAge()) + ",'" + cust.getContact_info() +
                        "','" + cust.getEmail_address() + "','" + cust.getAccount_number() + "'," + Integer.toString(cust.getPriority_status()) + ")";

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

        
    }