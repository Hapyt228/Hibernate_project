package org.example.service;

import org.example.Client;
import org.example.Profile;
import org.example.TransactionHelper;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final SessionFactory sessionFactory;
    private final TransactionHelper transactionHelper;

    public ProfileService(SessionFactory sessionFactory,
                         TransactionHelper transactionHelper
    ) {
        this.sessionFactory = sessionFactory;
        this.transactionHelper = transactionHelper;
    }

    public Profile saveProfile(Profile profile) {
        return transactionHelper.executeTransaction(session -> {
            session.persist(profile);
            return profile;
        });
    }

    public Profile updateProfile(Profile profile) {
        return transactionHelper.executeTransaction(session -> {
            session.merge(profile);
            return profile;
        });
    }

    public Profile findProfileByID(int id) {
        return transactionHelper.executeTransaction(session -> {
            return session.get(Profile.class, id);
        });
    }

}
