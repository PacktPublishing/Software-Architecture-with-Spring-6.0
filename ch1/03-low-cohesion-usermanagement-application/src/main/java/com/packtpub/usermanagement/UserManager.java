package com.packtpub.usermanagement;

public class UserManager {
    public void addUser(User user) {
        System.out.printf("Added %s", user.toString());
    }
    public void updateUser(User user) {
        System.out.printf("Updated %s", user.toString());
    }

    public void validateEmail(String email) {
        System.out.printf("%s validated", email);
    }
    public void sendEmail() {
        System.out.printf("Send email");
    }

}
