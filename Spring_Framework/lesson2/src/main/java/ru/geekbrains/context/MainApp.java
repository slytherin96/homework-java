package ru.geekbrains.context;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;


public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("ru.geekbrains.context");
        Cart cart = context.getBean(Cart.class);
        //cart.delProduct(1L);
        //cart.addProduct(1L, "apples", 260.80);
        //cart.delProduct(1L);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()==true)

         {
            String command = sc.next();
            System.out.println(command);
            if (command.equals("del")) {
                Long id = sc.nextLong();
                cart.delProduct(id);
            }
            if (command.equals("add")) {
                Long id = sc.nextLong();
                String title = sc.next();
                Double cost = sc.nextDouble();
                cart.addProduct(id, title, cost);
            }
             if (command.equals("stop")) {
                 break;
             }
        }
    }
}
