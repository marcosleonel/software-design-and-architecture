package com.example.sharingapp;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ContactList implements Comparable<Contact> {

    private ArrayList<Contact> contacts;
    private String FILENAME = "contacts.sav";
    private ArrayList<String> usernameList = new ArrayList<String>();

    public ContactList() {
        contacts = new ArrayList<Contact>();
    }

    public void setContacts(ArrayList<Contact> contact_list) {
        contacts = contact_list;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public ArrayList<String> getAllUsernames() {
        for (Contact contact: contacts) {
            String username = contact.getUsername();
            usernameList.add(username);
        }
        return usernameList;
    }

    public void addContact(Contact contact) {
        this.contacts.add(contact);
    }

    public void deleteContact(Contact contact) {
        int index = compareTo(contact);

        if (index != -1) {
            contacts.remove(contact);
        }
    }

    public Contact getContact(int index) {
        Contact contactFound = contacts.get(index);
        return contactFound;
    }

    public int getSize() {
        return contacts.size();
    }

    public int getIndex(Contact contact) {
        int index = compareTo(contact);
        return index;
    }

    public boolean hasContact(Contact contact) {
        int index = compareTo(contact);

        if (index != -1) {
            return true;
        } else {
            return false;
        }
    }

    public Contact getContactByUsername(String username) {
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);

            if (contact.getUsername().equals(username)) {
                return contact;
            }
        }
        return null;
    }

    public void loadContacts(Context context) {

        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Contact>>() {}.getType();
            contacts = gson.fromJson(isr, listType); // temporary
            fis.close();
        } catch (FileNotFoundException e) {
            contacts = new ArrayList<Contact>();
        } catch (IOException e) {
            contacts = new ArrayList<Contact>();
        }
    }

    public void saveContacts(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(contacts, osw);
            osw.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isUsernameAvailable(String username) {
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);

            if (contact.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int compareTo(@NonNull Contact contact) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).equals(contact)) {
                return i;
            }
        }
        return -1;
    }
}
