package dao;

import model.Contact;
import util.Helper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 09-May-17.
 */

public class ContactDaoImpl implements ContactDAO {

    Helper helper = new Helper();
    private Connection conn;

    private static volatile ContactDaoImpl contactDaoInstance;
    private ContactDaoImpl(){}
    public static ContactDaoImpl getContactDaoInstance(){
        if(contactDaoInstance == null){
            synchronized (ContactDaoImpl.class) {
                if(contactDaoInstance == null) {
                    contactDaoInstance = new ContactDaoImpl();
                }
            }
        }
        return contactDaoInstance;
    }

    private Connection getDBConnection() throws SQLException,ClassNotFoundException {
        Class.forName(helper.getProperties().getProperty("jdbcDriver"));
        Connection conn = DriverManager.getConnection(
                helper.getProperties().getProperty("jdbcUrl"),
                helper.getProperties().getProperty("jdbcUserName"),
                helper.getProperties().getProperty("jdbcPassword")
        );
        return conn;
    }


    public void addContact(Contact contact) {
        try (PreparedStatement ps = getDBConnection().prepareStatement(helper.getProperties().getProperty("addContact")))
        {
            ps.setString(1, contact.getFirstName());
            ps.setString(2, contact.getLastName());
            ps.setString(3, contact.getPhoneNumber());
            boolean result = ps.execute();
            if(result){
                helper.getMessage("addSuccess");
            }else{
                helper.getMessage("addError");
            }
        }catch(SQLException ex1){
            ex1.printStackTrace();
        }catch(ClassNotFoundException ex2) {
            ex2.printStackTrace();
        }
    }

    public List<Contact> getContactList() {
        List<Contact> contactList = new ArrayList<>();

        try (PreparedStatement ps = getDBConnection().prepareStatement(helper.getProperties().getProperty("getContactList")))
        {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Contact tempContact = new Contact(rs.getString(2),rs.getString(3),rs.getString(4));
                tempContact.setId(rs.getInt(1));
                contactList.add(tempContact);
            }
        }catch(SQLException ex1){
            ex1.printStackTrace();
        }catch(ClassNotFoundException ex2){
            ex2.printStackTrace();
        }

        return contactList;
    }

    public Contact findContactById(int id) {

        Contact tempContact = null;
        try(PreparedStatement ps = getDBConnection().prepareStatement(helper.getProperties().getProperty("findContactById")))
        {
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                tempContact = new Contact(rs.getString(2),rs.getString(3),rs.getString(4));
                tempContact.setId(rs.getInt(1));
            }

        }catch(SQLException ex1){
            ex1.printStackTrace();
        }catch (ClassNotFoundException ex2){
            ex2.printStackTrace();
        }

        return tempContact;
    }

    public List<Contact> findContactByName(String filterText) {

        List<Contact> contactList = new ArrayList<Contact>();

        try (PreparedStatement ps = getDBConnection().prepareStatement(helper.getProperties().getProperty("findContactByName")))
        {
            ps.setString(1,filterText);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Contact tempContact = new Contact(rs.getString(2),rs.getString(3),rs.getString(4));
                tempContact.setId(rs.getInt(1));
                contactList.add(tempContact);
            }
        }catch(SQLException ex1){
            ex1.printStackTrace();
        }catch(ClassNotFoundException ex2){
            ex2.printStackTrace();
        }

        return contactList;
    }

    public int deleteContact(int id) {
        int rowCount = 0;
        try (PreparedStatement ps = getDBConnection().prepareStatement(helper.getProperties().getProperty("deleteContact")))
        {
            ps.setInt(1,id);
            rowCount = ps.executeUpdate();

        }catch(SQLException ex1){
            ex1.printStackTrace();
        }catch (ClassNotFoundException ex2){
            ex2.printStackTrace();
        }

        return rowCount;
    }

    public int updateContact(int id, String firstName, String lastName) {
        int rowCount = 0;
        try(PreparedStatement ps = getDBConnection().prepareStatement(helper.getProperties().getProperty("updateContact")))
        {
            ps.setInt(1,id);
            ps.setString(2,firstName);
            ps.setString(3,lastName);
            rowCount = ps.executeUpdate();

        }catch(SQLException ex1){
            ex1.printStackTrace();
        }catch (ClassNotFoundException ex2){
            ex2.printStackTrace();
        }
        return rowCount;
    }
}
