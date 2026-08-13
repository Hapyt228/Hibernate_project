package org.example;

import org.example.service.ClientService;
import org.example.service.CouponService;
import org.example.service.OrderService;
import org.example.service.ProfileService;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MAIN_FUn {

    AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext("org.example");
    SessionFactory sessionFactory = context.getBean(SessionFactory.class);

    ClientService clientService = context.getBean(ClientService.class);
    ProfileService profileService = context.getBean(ProfileService.class);
    CouponService couponService = context.getBean(CouponService.class);
    OrderService orderService = context.getBean(OrderService.class);
    private Scanner scanner = new Scanner(System.in);

    public void saveClient(String name, String email, String address, String phone) {
        Profile profile = new Profile(address, phone);
        Client client = new Client(name, email, profile);
        profile.setClient(client);
        List<Coupon> coupon = new ArrayList<>();

        Order order = askOrder(client);
        if (order != null) {
           coupon =  askCoupons();
        }

        clientService.saveClient(client, order, coupon);
    }

    private Order askOrder(Client client) {
        System.out.println("Хотите добавить начальный заказ? (yes/no)");
        String answer = scanner.nextLine();

        if (!answer.equalsIgnoreCase("yes")) {
            return null;
        }

        System.out.println("Enter total amount");
        BigDecimal totalAmount = new BigDecimal(scanner.nextLine());
        System.out.println("Enter status");
        String status = scanner.nextLine();

        Order order = new Order(totalAmount, status);
        order.setClient(client);

        return order;
    }

    private List<Coupon> askCoupons() {
        System.out.println("Привязать купоны? (yes/no)");
        String answer = scanner.nextLine();

        if (!answer.equalsIgnoreCase("yes")) {
            return null;
        }

        List<Coupon> coupons = new ArrayList<>();
        boolean help = false;
        while (!help) {
            System.out.println("Введите код купона");
            String code =  scanner.nextLine();
            System.out.println("Введите количество скидки");
            Float discount = Float.parseFloat(scanner.nextLine());
            coupons.add(new Coupon(code, discount));
            System.out.println("Есть еще купоны(yes,no)");
            String next = scanner.nextLine();
            if (next.equalsIgnoreCase("no")) {
                help = true;
            }
        }

        return coupons;
    }


    public String deleteClient(int id) {
        clientService.deleteClient(id);
        return "Клиент удален";
    }

    public String updateCupon(int id, String code, Float discount) {
        Coupon coupon = couponService.getCouponById(id);
        if (code != null) {
            coupon.setCode(code);
        }
        if (discount != null) {
            coupon.setDiscount(discount);
        }
        couponService.updateCoupon(coupon);
        return "Купон был изменен";
    };

    public String findOrders(String status, BigDecimal totalAmount, LocalDateTime orderDate){
        List<Order> orderList =  orderService.findOrders(status, totalAmount, orderDate);

        if (orderList.isEmpty()) {
            return "Заказы не найдены";
        }

        StringBuilder result = new StringBuilder();
        for (Order order : orderList) {
            result.append("\n---\n")
                    .append("Статус: ").append(order.getStatus())
                    .append("\nСумма: ").append(order.getTotalAmount())
                    .append("\nДата: ").append(order.getOrderDate())
                    .append("\n---\n");
        }

        return result.toString();
    }

    public String saveOrder(int id, BigDecimal totalAmount, String status){
        Client client = clientService.getClientById(id);
        Order order = new Order(totalAmount, status, client);
        orderService.saveOrder(order);
        return "Заказ сохранен";
    }

    public String updateProfile(int id, String address, String phone) {
        Profile profile = profileService.findProfileByID(id);
        if (address != null) {
            profile.setAddress(address);
        }
        if (phone != null) {
            profile.setPhone(phone);
        }
        profileService.saveProfile(profile);
        return "Профиль был изменен";
    };

}
