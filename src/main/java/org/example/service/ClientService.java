package org.example.service;

import org.example.Client;
import org.example.Coupon;
import org.example.Order;
import org.example.TransactionHelper;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final SessionFactory sessionFactory;
    private final TransactionHelper transactionHelper;

    public ClientService(SessionFactory sessionFactory,
                          TransactionHelper transactionHelper
    ) {
        this.sessionFactory = sessionFactory;
        this.transactionHelper = transactionHelper;
    }

    public void saveClient(Client client, Order order, List<Coupon> coupons) {
        transactionHelper.executeTransaction(session -> {
            session.save(client);
            session.save(client.getProfile());
            if (order != null) {
                session.save(order);
                if (coupons != null) {
                    for (Coupon coupon : coupons) {
                        session.save(coupon);
                    }
                    client.setCoupons(coupons);
                }
            }
        });
    }

    public void deleteClient(int id) {
        transactionHelper.executeTransaction(session -> {
            Client clientForDelete = session.get(Client.class, id);
            session.remove(clientForDelete);
        });
    }

    public Client getClientById(int id) {
        return transactionHelper.executeTransaction(session -> {
            Client client = session.get(Client.class, id);
            return client;
        });
    }


}
