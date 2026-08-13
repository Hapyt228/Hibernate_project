package org.example.service;

import org.example.Coupon;
import org.example.TransactionHelper;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
public class CouponService {

    private final SessionFactory sessionFactory;
    private final TransactionHelper transactionHelper;

    public CouponService(SessionFactory sessionFactory,
                          TransactionHelper transactionHelper
    ) {
        this.sessionFactory = sessionFactory;
        this.transactionHelper = transactionHelper;
    }

    public Coupon saveCoupon(Coupon coupon) {
        return transactionHelper.executeTransaction(session -> {
            session.persist(coupon);
            return coupon;
        });
    }

    public Coupon getCouponById(long id) {
        return transactionHelper.executeTransaction(session -> {
            return session.get(Coupon.class, id);
        });
    }

    public Coupon updateCoupon(Coupon coupon) {
        return transactionHelper.executeTransaction(session -> {
            session.merge(coupon);
            return coupon;
        });
    }


}
