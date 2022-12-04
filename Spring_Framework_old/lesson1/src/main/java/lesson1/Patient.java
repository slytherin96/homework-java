package lesson1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Patient {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(lesson1.AppConfig.class);

        Registratura registratura = context.getBean("registration", Registratura.class);

        registratura.registratura();

    }
}
