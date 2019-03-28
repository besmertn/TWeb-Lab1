package models;

import java.util.Date;

public class User {

    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String gender;
    private String recoveryEmail;
    private String phone;

    public User(String login, String password, String firstName, String lastName, Date birthDate, String gender, String recoveryEmail, String phone) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.recoveryEmail = recoveryEmail;
        this.phone = phone;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static User CreateExistingUser() {
        return new User("besmertn", "56445556");
    }
    public static User CreateNotExistingUser() {
        return new User("besmertnA", "56445556A");
    }

    public static User CreateValidUser() {
        return new User("test_user",
                "1056294738",
                "User",
                "Test",
                new Date(),
                "male",
                "test@mail.com",
                "+380111111111");
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRecoveryEmail() {
        return recoveryEmail;
    }

    public void setRecoveryEmail(String recoveryEmail) {
        this.recoveryEmail = recoveryEmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
