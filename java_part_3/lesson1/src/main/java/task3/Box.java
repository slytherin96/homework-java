package task3;

import java.util.ArrayList;

public class Box<E extends Fruit> {
    ArrayList<E> fruitBox;

    public Box() {
        fruitBox = new ArrayList<E>();
    }

    public void addBox(E inFruit){
        fruitBox.add(inFruit);
    }

    public void clearBox(){
        fruitBox.clear();
    }


    public float getWeight(){
        float weight = 0.0f;
        for(E o : fruitBox){
            weight += o.getFruitWeight();
        }return weight;
    }


   public boolean boxCompare(Box anotherBox) {
       if(getWeight() == anotherBox.getWeight()) return true;
       return false;
   }
    public void pourTo(Box <E> anotherBox){
        anotherBox.fruitBox.addAll(fruitBox);
        fruitBox.clear();
    }
    public ArrayList<E> getFruitBox() {
        return fruitBox;
    }

}
