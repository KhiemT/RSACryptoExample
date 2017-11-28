package com.example.crypt.api.dto;

public class PaymentDTO {
    private String primaryBankAccount;
    private Double amount;

    public String getPrimaryBankAccount() {
        return primaryBankAccount;
    }

    public void setPrimaryBankAccount(String primaryBankAccount) {
        this.primaryBankAccount = primaryBankAccount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
