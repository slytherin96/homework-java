package task2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CreatePhoneBook {
    private HashSet<String> phones;
    private Map<String, Set<String>> phoneBook;
    CreatePhoneBook(){
        phoneBook = new HashMap<>();
    }

    public void add(String name, String phone){

        phones = new HashSet<>();
        if (phoneBook.containsKey(name)){
            phones.addAll(phoneBook.get(name));
        }
        phones.add(phone);
        phoneBook.put(name,phones);
    }

    public void get(String name){
        System.out.printf("%s: %s",name,phoneBook.get(name).toString().replace("]","").replace("[",""));
    }

}
