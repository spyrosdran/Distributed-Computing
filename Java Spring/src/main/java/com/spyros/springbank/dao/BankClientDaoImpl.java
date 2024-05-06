package com.spyros.springbank.dao;

import com.spyros.springbank.entity.BankClient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class BankClientDaoImpl implements BankClientDao {

    private EntityManager entityManager;

    @Autowired
    public BankClientDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Float getBalance(String username) {

        BankClient bankClient = entityManager.find(BankClient.class, username);

        if (!(bankClient == null))  return bankClient.getBalance();
        return null;
    }

    @Override
    public BankClient deposit(BankClient bankClient, Float money) {

        Float currentBalance = bankClient.getBalance();
        Float newBalance = currentBalance + money;
        bankClient.setBalance(newBalance);

        entityManager.merge(bankClient);

        return bankClient;
    }

    @Override
    public BankClient withdraw(BankClient bankClient, Float money) {

        Float currentBalance = bankClient.getBalance();

        // If money is enough and dividable by 20 or 50, then proceed
        if (money <= currentBalance && money >= 20 && (money/20 == 0 || money/50 == 0)) {
            Float newBalance = currentBalance - money;
            bankClient.setBalance(newBalance);
            entityManager.merge(bankClient);
            return bankClient;
        }

        return null;
    }

    @Override
    public BankClient login(BankClient bankClient, String password) {

        // Create query to find the client
        TypedQuery<BankClient> query = entityManager.createQuery("FROM Client WHERE username=:u AND password:=p", BankClient.class);
        query.setParameter("u", bankClient.getUsername());
        query.setParameter("p", password);

        ArrayList<BankClient> bankClients = (ArrayList<BankClient>) query.getResultList();

        if (!bankClients.isEmpty()) {
            bankClient.setLoggedIn(1);
            entityManager.merge(bankClient);
            return bankClient;
        }

        return null;
    }

    @Override
    public void logout(BankClient bankClient) {
        bankClient.setLoggedIn(0);
        entityManager.merge(bankClient);
    }
}
