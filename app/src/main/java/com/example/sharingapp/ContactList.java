package com.example.sharingapp;

import java.util.ArrayList;

public class ContactList {

    private ArrayList<Contact> contacts;
    private String FILENAME;

    public ContactList() {
        contacts = new ArrayList<Contact>();
    }

    public void setContacts(ArrayList<Contact> contacts_list) {
        this.contacts = contacts_list;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public ArrayList<String> getAllUserNames() {

        ArrayList<String> usernamesList;

        for (String contact: contacts) {
            String username = contact.getUsername();

            usernamesList.add(contact.username);
        }
    }
}
