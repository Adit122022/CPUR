// Lesson 1: Classes, Objects, & Encapsulation, package 

import pkgs.myapp.User;

public class Lesson01 {
    public static void main(String[] args) {
       
        User user1 = new User(101, "Aditya", "aditya@example.com");
     
        user1.displayUser();

       
        user1.setName("Aditya Sharma");
        
     
        user1.setEmail("invalidemail.com"); 

      
        System.out.println("Updated Name: " + user1.getName());
        user1.displayUser();
    }
}
