package com.spyros.springbank.dao;

import com.spyros.springbank.entity.BankClient;

public interface BankClientDao {

    public Float getBalance(String username);
    public BankClient deposit(BankClient bankClient, Float money);
    public BankClient withdraw(BankClient bankClient, Float money);
    public BankClient login(BankClient bankClient, String password);
    public void logout(BankClient bankClient);
}
