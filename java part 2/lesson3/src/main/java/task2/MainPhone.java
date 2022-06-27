package task2;

public class MainPhone {

    public static void main(String[] args) {
        CreatePhoneBook createPhoneBook = new CreatePhoneBook();
        createPhoneBook.add("test",911);
        createPhoneBook.add("test",922);
        createPhoneBook.add("test1",892);
        createPhoneBook.add("test1",832);
        createPhoneBook.add("test",945);

        createPhoneBook.get("test");
        }
}


