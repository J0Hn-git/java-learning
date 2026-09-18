package DAY_1;

import java.util.*;

public class challenge1 {
    public static void main(String[] args) {
        
    }
}

class Contact {
    
    private String name;
    private String phoneNumber;

    public Contact(String name, String phoneNumber) {

        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static Contact createContact(String name, String phoneNumber) {

        return new Contact(name, phoneNumber);
    }
}

class MobilePhone {

    private String myNumber;
    private ArrayList<Contact> myContacts;

    public MobilePhone(String myNumber) {

        this.myNumber = myNumber;
        this.myContacts = new ArrayList<>();

    }

    public boolean addNewContact(Contact contact) {

        if(findContact(contact) >= 0) {
            return false;
        }
        myContacts.add(contact);
        return true;
    }

    public boolean updateContacts(Contact  oldContact, Contact newContact) {

        int index = findContact(oldContact);

        if(index < 0){
            return false;
        }
        myContacts.set(index, newContact);
        return true;
    }

    public boolean removeContact(Contact contact) {

        int index = findContact(contact);

        if(index < 0){
            return false;
        }
        myContacts.remove(index);
        return true;
    }

    public int findContact(Contact contact) {

        for(int i = 0; i < myContacts.size(); i++) {
            if(myContacts.get(i).getName().equals(contact.getName())) {

                return i;
            }
        }
        return -1;
    }

    public int findContact(String name) {

        for(int i = 0; i < myContacts.size(); i++) {

            if(myContacts.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public Contact queryContact(String name) {

        int index = findContact(name);

        if(index < 0){
            return null;
        }
        return myContacts.get(index);
    }

    public void printContact() {

        System.out.println("Contact List: ");

        for(int i = 0; i < myContacts.size(); i++) {

            Contact contact = myContacts.get(i);

            System.out.println(
                (i + 1) + ". " + contact.getName() + " -> " + contact.getPhoneNumber()
            );
        }
    }

}
