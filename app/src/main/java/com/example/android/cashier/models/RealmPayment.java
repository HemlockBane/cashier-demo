package com.example.android.cashier.models;

import io.realm.RealmObject;

public class RealmPayment extends RealmObject {
    public String accountName;
    public String accountNumber;
    public String depositAmount;
    public String depositorName;
    public String depositorPhoneNumber;
    public String depositorEmail;

    public RealmPayment(){

    }

    public RealmPayment(String accountName, String accountNumber){
        this.accountName = accountName;
        this.accountNumber = accountNumber;
    }

    public RealmPayment(String accountName, String accountNumber, String depositAmount, String depositorName, String depositorPhoneNumber){
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.depositAmount = depositAmount;
        this.depositorName = depositorName;
        this.depositorPhoneNumber = depositorPhoneNumber;
    }
    public RealmPayment(String accountName, String accountNumber, String depositAmount, String depositorName, String depositorPhoneNumber, String depositorEmail){
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.depositAmount = depositAmount;
        this.depositorName = depositorName;
        this.depositorPhoneNumber = depositorPhoneNumber;
        this.depositorEmail = depositorEmail;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(String depositAmount) {
        this.depositAmount = depositAmount;
    }

    public String getDepositorName() {
        return depositorName;
    }

    public void setDepositorName(String depositorName) {
        this.depositorName = depositorName;
    }

    public String getDepositorPhoneNumber() {
        return depositorPhoneNumber;
    }

    public void setDepositorPhoneNumber(String depositorPhoneNumber) {
        this.depositorPhoneNumber = depositorPhoneNumber;
    }

    public String getDepositorEmail() {
        return depositorEmail;
    }

    public void setDepositorEmail(String depositorEmail) {
        this.depositorEmail = depositorEmail;
    }
}
