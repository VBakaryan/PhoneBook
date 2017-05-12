package dao;

import model.Contact;

import java.util.List;

/**
 * Created by ASUS on 09-May-17.
 */
public interface ContactDAO {


    void addContact(Contact contact);

    List<Contact> getContactList();

    Contact findContactById(int id);

    List<Contact> findContactByName(String firstName);

    int deleteContact(int id);

    int updateContact(int id, String firstName, String lastName);



}
