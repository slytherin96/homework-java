package task3;

/*
3. Большая задача:
a. Есть классы Fruit -> Apple, Orange;(больше фруктов не надо)
b. Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта, поэтому в одну коробку
 нельзя сложить и яблоки, и апельсины;
c. Для хранения фруктов внутри коробки можете использовать ArrayList;
d. Сделать метод getWeight() который высчитывает вес коробки, зная количество фруктов и вес одного фрукта(вес яблока
 - 1.0f, апельсина - 1.5f, не важно в каких это единицах);
e. Внутри класса коробка сделать метод compare, который позволяет сравнить текущую коробку с той, которую подадут
 в compare в качестве параметра, true - если их веса равны, false в противном случае(коробки с яблоками мы можем
  сравнивать с коробками с апельсинами);
f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку(помним про сортировку
 фруктов, нельзя яблоки высыпать в коробку с апельсинами), соответственно в текущей коробке фруктов не остается,
  а в другую перекидываются объекты, которые были в этой коробке;
g. Не забываем про метод добавления фрукта в коробку.
 */
public class Main {
    public static void main(String[] args) {
    Apple apple1 = new Apple();
    Apple apple2 = new Apple();
    Apple apple3 = new Apple();
    Apple apple4 = new Apple();
    Orange orange1 = new Orange();
    Orange orange2 = new Orange();
    Orange orange3 = new Orange();

    Box<Apple> box1= new Box<>();
    Box<Apple> box11= new Box<>();
    Box<Orange> box2= new Box<>();

    box1.addBox(apple1);
    box1.addBox(apple3);
    box1.addBox(apple2);//добавление в коробку


    System.out.println(box1.getWeight()); // вес коробки

    box2.addBox(orange2);
    box2.addBox(orange1);


    System.out.println(box2.getWeight());
    System.out.println(box1.boxCompare(box2)); // сравнение коробок


    box1.pourTo(box11);//пересыпание

    System.out.println(box11.getWeight());
    System.out.println(box1.getWeight());




    }
}
