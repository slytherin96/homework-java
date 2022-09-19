package lesson1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component ("registration")
@Scope("prototype")
public class Registratura {

    @Autowired
    @Qualifier("Cards")
    Cards cards;

    @Autowired
    @Qualifier("PlanCabinets")
    PlanCabinets planCabinets;

    public void registratura(){
        cards.cardPatient();
        planCabinets.cabinet();

    }

}
