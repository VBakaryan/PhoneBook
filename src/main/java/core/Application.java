package core;

import dao.ContactDAO;
import dao.ContactDaoImpl;
import model.Contact;
import util.Helper;

import java.util.List;
import java.util.Scanner;

/**
 * Created by ASUS on 10-May-17.
 */
public class Application {
    Scanner scanner = new Scanner(System.in);
    Helper helper = new Helper();
    ContactDAO contactDAO = ContactDaoImpl.getContactDaoInstance();


    public void addContactToDB(){

        helper.getMessage("enterFirstName");
        String firstName = scanner.nextLine();
        helper.getMessage("enterLastName");
        String lastName = scanner.nextLine();
        helper.getMessage("enterPhoneNumber");
        String phoneNumber = scanner.nextLine();

        Contact contact = new Contact(firstName,lastName,phoneNumber);
        contactDAO.addContact(contact);

    }

    public void getContactListFromDB(){
        List<Contact> contactList = contactDAO.getContactList();
        helper.getMessage("contactList");
        for(Contact element:contactList){
            System.out.println(element.toString());
        }

    }

    public void findContactFromDbById(){
        helper.getMessage("contactId");
        int contactID = scanner.nextInt();
        Contact contact = contactDAO.findContactById(contactID);
        System.out.println(contact.toString());
    }

    public void findContactFromDbByName(){
        helper.getMessage("findByName");
        String contactflName = scanner.nextLine();
        List<Contact> contactList = contactDAO.findContactByName(contactflName);
        for(Contact element:contactList){
            System.out.println(element.toString());
        }
    }

    public void deleteFromDbById(){
        helper.getMessage("deleteById");
        int contactID = scanner.nextInt();
        int rowCount = contactDAO.deleteContact(contactID);
        System.out.print(rowCount+" ");helper.getMessage("deleteByIdResult");
    }

    public void updateContactInDb(){
        helper.getMessage("updateById");
        String strInput = scanner.nextLine();
        String[] contactInfo = strInput.split(",");
        int rowCount = contactDAO.updateContact(Integer.parseInt(contactInfo[0]),contactInfo[1],contactInfo[2]);
        System.out.print(rowCount+" ");helper.getMessage("updateByIdResult");
    }

    public void exit(){
        helper.getMessage("goodBye");
        System.exit(0);
    }

}
