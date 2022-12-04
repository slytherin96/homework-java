package lesson1;

import org.springframework.stereotype.Component;

@Component("Cards")
public class Cards {

    public void cardPatient(){
        System.out.println("Получена карта пациента");

    }
}
