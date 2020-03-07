package mypetstore.domain;

import java.io.Serializable;

public class SignOn implements Serializable {
    private static final long serialVersionUID = 3992469837058393712L;

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
