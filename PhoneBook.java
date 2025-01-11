package com.example.demo2;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
class PhoneBook {
    private ArrayList<Contact> contacts;
    public List<Contact> getContacts() {
        return new ArrayList<>(contacts);
    }

    public PhoneBook() {
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
        System.out.println("Liên hệ đã được thêm thành công.");
    }

    public boolean updateBirthday(String name, LocalDate birthday) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                contact.setBirthday(birthday);
                return true;
            }
        }
        return false;
    }

    public boolean updateCccd(String name, String cccd) {
        for (Contact contact : contacts) {
            if (contact.getName().equals(name)) {
                contact.setCccd(cccd);
                return true;
            }
        }
        return false;
    }

    public boolean updateAccountInfo(String name, String accountNumber, String bankName) {
        for (Contact contact : contacts) {
            if (contact.getName().equals(name)) {
                contact.setAccountNumber(accountNumber);
                contact.setBankName(bankName);
                return true;
            }
        }
        return false;
    }

    public boolean updateSocialInfo(String name, String facebook, String telegram, String threads, String twitter, String zalo) {
        for (Contact contact : contacts) {
            if (contact.getName().equals(name)) {
                if (facebook != null && !facebook.isEmpty()) contact.setFacebookLink(facebook);
                if (telegram != null && !telegram.isEmpty()) contact.setTelegramUsername(telegram);
                if (threads != null && !threads.isEmpty()) contact.setThreadsLink(threads);
                if (twitter != null && !twitter.isEmpty()) contact.setTwitterLink(twitter);
                if (zalo != null && !zalo.isEmpty()) contact.setZaloLink(zalo);
                return true;
            }
        }
        return false;
    }

    public String displayAllContacts() {
        if (contacts.isEmpty()) {
            return "Danh bạ trống.";
        }
        StringBuilder result = new StringBuilder();
        for (Contact contact : contacts) {
            result.append(contact.toString()).append("\n");
        }
        return result.toString();
    }

    public Contact searchContact(String keyword) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(keyword) || contact.getPhoneNumber().equals(keyword)) {
                return contact;
            }
        }
        return null;
    }

    public boolean updateContact(String oldName, String newName, String newPhone, String newEmail) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(oldName)) {
                contact.setName(newName);
                contact.setPhoneNumber(newPhone);
                contact.setEmail(newEmail);
                return true;
            }
        }
        return false;
    }
    public boolean deleteContact(String keyword) {
        boolean removed = contacts.removeIf(contact ->
                contact.getName().equalsIgnoreCase(keyword) ||
                        contact.getPhoneNumber().equals(keyword)
        );
        if (removed) {
            System.out.println("Xóa liên hệ thành công.");
        } else {
            System.out.println("Không tìm thấy liên hệ để xóa.");
        }
        return removed;
    }
}


