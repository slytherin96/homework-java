package lesson1;
import org.springframework.stereotype.Component;

@Component("PlanCabinets")
public class PlanCabinets {
    public void cabinet(){
        System.out.println("Пациент направлен в нужный кабинет");
    }
}
