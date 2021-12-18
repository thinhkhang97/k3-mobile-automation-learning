package test_data.authentication;

public class LoginCred {
    private String email;

    private String password;

    public LoginCred(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginCred{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
