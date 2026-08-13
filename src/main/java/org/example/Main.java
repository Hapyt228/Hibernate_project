package org.example;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MAIN_FUn mf = new MAIN_FUn();
        boolean work = true;
        while (work) {
            System.out.println("Выберите действие:");
            System.out.println("1 - Добавить клиента");
            System.out.println("2 - Удалить клиента");
            System.out.println("3 - Редактировать профиль");
            System.out.println("4 - Добавить заказ");
            System.out.println("5 - Редактировать купоны");
            System.out.println("6 - Найти заказы");
            System.out.println("7 - Выход");
            System.out.println("\nВведите номер команды:");
            int switchNumberByMain = Integer.parseInt(sc.nextLine());
            switch (switchNumberByMain) {
                case 1:
                    System.out.println("Введите имя");
                    String name = sc.nextLine();
                    System.out.println("Введите номер телефона");
                    String phoneClient = sc.nextLine();
                    System.out.println("Введите адрес");
                    String addressClient = sc.nextLine();
                    System.out.println("Введите почту");
                    String email = sc.nextLine();

                    mf.saveClient(name, phoneClient, addressClient, email);
                    break;

                case 2:
                    System.out.println("Введите id клиента, которого хотите удалить");
                    mf.deleteClient(Integer.parseInt(sc.nextLine()));
                    break;

                case 3:
                    System.out.println("Введите id профиля, которого хотите изменить");
                    int idProfile = Integer.parseInt(sc.nextLine());
                    System.out.println("Выберите что хотелось бы изменить");
                    System.out.println("1 - Изменить адрес");
                    System.out.println("2 - Изменить телефон");
                    System.out.println("3 - Изменить все");
                    System.out.println("4 - Случайно зашел");
                    int switchNumberByProfile = Integer.parseInt(sc.nextLine());
                    String address = null;
                    String phone = null;
                    switch (switchNumberByProfile) {
                        case 1:
                            System.out.println("Ведите измененный адрес");
                            address = sc.nextLine();
                            break;
                        case 2:
                            System.out.println("Ведите измененный номер телефона");
                            phone = sc.nextLine();
                            break;
                        case 3:
                            System.out.println("Ведите измененный адрес");
                            address = sc.nextLine();
                            System.out.println("Ведите измененный номер телефона");
                            phone = sc.nextLine();
                            break;
                        case 4:
                            System.out.println("Бывает :)");
                            break;
                    }
                    mf.updateProfile(idProfile, address, phone);
                    break;

                case 4:
                    System.out.println("Ведите id клиента");
                    int idBySaveOrder = Integer.parseInt(sc.nextLine());
                    System.out.println("Ведите Сумму");
                    BigDecimal totalAmountBySave = BigDecimal.valueOf(Long.parseLong(sc.nextLine()));
                    System.out.println("Ведите статус");
                    String statusBySave = sc.nextLine();
                    mf.saveOrder(idBySaveOrder, totalAmountBySave, statusBySave);
                    break;

                case 5:
                    System.out.println("Введите id купона, которого хотите изменить");
                    int idCoupon = Integer.parseInt(sc.nextLine());
                    System.out.println("Выберите что хотелось бы изменить");
                    System.out.println("1 - Изменить код");
                    System.out.println("2 - Изменить значение скидки");
                    System.out.println("3 - Изменить все");
                    System.out.println("4 - Случайно зашел");
                    int switchNumberByCoupon = Integer.parseInt(sc.nextLine());
                    String codeUpdate = null;
                    Float discount = null;
                    switch (switchNumberByCoupon) {
                        case 1:
                            System.out.println("Ведите измененный код");
                            codeUpdate = sc.nextLine();
                            break;
                        case 2:
                            System.out.println("Ведите измененную скидку");
                            discount = Float.valueOf(sc.nextLine());
                            break;
                        case 3:
                            System.out.println("Ведите измененный код");
                            codeUpdate = sc.nextLine();
                            System.out.println("Ведите измененную скидку");
                            discount = Float.valueOf(sc.nextLine());
                            break;
                        case 4:
                            System.out.println("Бывает :)");
                            break;
                    }
                    mf.updateCupon(idCoupon, codeUpdate, discount);
                    break;

                case 6:
                    System.out.println("Выберите по чем хотелось бы искать");
                    System.out.println("1 - По дате");
                    System.out.println("2 - По сумме");
                    System.out.println("3 - По статусу");
                    System.out.println("4 - Случайно зашел");
                    int switchNumberByOrder = Integer.parseInt(sc.nextLine());
                    LocalDateTime orderDate = null;
                    BigDecimal totalAmount = null;
                    String status = null;
                    switch (switchNumberByOrder) {
                        case 1:
                            System.out.println("Ведите дату");
                            orderDate = LocalDateTime.parse(sc.nextLine());
                            break;
                        case 2:
                            System.out.println("Ведите сумму");
                            totalAmount = BigDecimal.valueOf(Long.parseLong(sc.nextLine()));
                            break;
                        case 3:
                            System.out.println("Ведите статус");
                            status = sc.nextLine();
                            break;
                        case 4:
                            System.out.println("Бывает :)");
                            break;
                    }
                    System.out.println(mf.findOrders(status, totalAmount, orderDate));
                    break;

                case 7:
                    System.out.println("Пока :)");
                    work = false;
                    break;
            }

        }
    }
}