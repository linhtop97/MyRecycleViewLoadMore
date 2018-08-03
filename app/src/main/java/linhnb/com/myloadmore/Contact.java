package linhnb.com.myloadmore;

import java.util.ArrayList;
import java.util.List;

public class Contact {
    private String email;
    private String phone;

    public Contact(String e, String p) {
        email = e;
        phone = p;
    }

    public static List<Contact> initContact() {
        List<Contact> contacts = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            contacts.add(new Contact("linh" + i, "phone" + i));
        }
        return contacts;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}