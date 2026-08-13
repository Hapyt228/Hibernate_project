package org.example.service;



import org.example.Order;
import org.example.TransactionHelper;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class OrderService {

    private final SessionFactory sessionFactory;
    private final TransactionHelper transactionHelper;

    public OrderService(SessionFactory sessionFactory,
                          TransactionHelper transactionHelper
    ) {
        this.sessionFactory = sessionFactory;
        this.transactionHelper = transactionHelper;
    }

    public Order saveOrder (Order order) {
        return transactionHelper.executeTransaction(session -> {
            session.persist(order);
            return order;
        });
    }

    public Order findOrderById (int id) {
        return transactionHelper.executeTransaction(session -> {
            Order order = session.get(Order.class, id);
            return order;
        });
    }

    public List<Order> findOrders(String status, BigDecimal totalAmount, LocalDateTime fromDate) {
        return transactionHelper.executeTransaction(session -> {
            StringBuilder hql = new StringBuilder(
                    "FROM Order o WHERE (:status IS NULL OR o.status = :status) " +
                            "AND (:totalAmount IS NULL OR o.totalAmount >= :totalAmount)"
            );

            if (fromDate != null) {
                hql.append(" AND o.orderDate >= :orderDate");
            }

            Query<Order> query = session.createQuery(hql.toString(), Order.class);
            query.setParameter("status", status);
            query.setParameter("totalAmount", totalAmount);

            if (fromDate != null) {
                query.setParameter("orderDate", fromDate);
            }

            return query.getResultList();
        });
    }

}
