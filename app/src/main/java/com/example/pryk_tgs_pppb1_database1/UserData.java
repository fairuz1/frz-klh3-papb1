package com.example.pryk_tgs_pppb1_database1;
public class UserData {
    private String name, email, image, subject, key;

    public UserData() {
        // empty constructor
    }

    public UserData(String name, String email, String image, String subject) {
        this.name = name;
        this.email = email;
        this.image = image;
        this.subject = subject;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }

    public String getSubject() { return subject; }

    public void setSubject(String subject) { this.subject = subject; }

    public String getKey() { return key; }

    public void setKey(String key) { this.key = key; }
}
