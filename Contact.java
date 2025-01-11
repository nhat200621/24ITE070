package com.example.demo2;
import java.time.LocalDate;
class Contact {
    private String name;
    private String phoneNumber;
    private String email;
    private LocalDate birthday;
    private String cccd;
    private String accountNumber;
    private String bankName;
    private String facebookLink;
    private String telegramUsername;
    private String threadsLink;
    private String twitterLink;
    private String zaloLink;

    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    public String getFacebookLink() {
        return facebookLink;
    }

    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }

    public String getTelegramUsername() {
        return telegramUsername;
    }

    public void setTelegramUsername(String telegramUsername) {
        this.telegramUsername = telegramUsername;
    }

    public String getThreadsLink() {
        return threadsLink;
    }

    public void setThreadsLink(String threadsLink) {
        this.threadsLink = threadsLink;
    }

    public String getTwitterLink() {
        return twitterLink;
    }

    public void setTwitterLink(String twitterLink) {
        this.twitterLink = twitterLink;
    }

    public String getZaloLink() {
        return zaloLink;
    }

    public void setZaloLink(String zaloLink) {
        this.zaloLink = zaloLink;
    }

    public String toString() {
        String birthdayStr = (birthday != null) ? ", Birthday: " + birthday.toString() : "";
        String cccdStr = (cccd != null) ? ", CCCD: " + cccd.toString() : "";
        String accountNumberStr = (accountNumber != null) ? ", Account: " + accountNumber.toString() : "";
        String bankNameStr = (bankName != null) ? ", Bank: " + bankName.toString() : "";
        String facebookLinkStr = (facebookLink != null) ? ", Facebook: " + facebookLink.toString() : "";
        String telegramUsernameStr = (telegramUsername != null) ? ", Telegram: " + telegramUsername.toString() : "";
        String threadsLinkStr = (threadsLink != null) ? ", Threads: " + threadsLink.toString() : "";
        String twitterLinkStr = (twitterLink != null) ? ", Twitter: " + twitterLink.toString() : "";
        String zaloLinkStr = (zaloLink != null) ? ", Zalo: " + zaloLink.toString() : "";
        String nameStr = (name != null) ? "Name: " + name.toString() : "";
        String phoneNumberStr = (phoneNumber != null) ? ", Phone: " + phoneNumber.toString() : "";
        String emailStr = (email != null) ? " Email: " + email.toString() : "";
        return nameStr + phoneNumberStr + emailStr +
                birthdayStr + cccdStr + accountNumberStr + bankNameStr +
                facebookLinkStr + telegramUsernameStr +
                threadsLinkStr + twitterLinkStr + zaloLinkStr;
    }
}
