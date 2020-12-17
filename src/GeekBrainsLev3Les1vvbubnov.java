import java.util.ArrayList;

public class GeekBrainsLev3Les1vvbubnov {
    public static void main(String[] args) {

        // 1.
        MyCustomClass[] myCustomClassArr = {
                new MyCustomClass("one", 1),
                new MyCustomClass("two", 2),
                new MyCustomClass("three", 3)
        };
        String[] customStringArr = {"one", "two", "three"};

        invertTwoArrElls(myCustomClassArr, 2, 1);
        invertTwoArrElls(customStringArr, 0, 2);

        for (MyCustomClass myCustomClass : myCustomClassArr) {
            System.out.println(myCustomClass.toString());
        }
        for (String string : customStringArr) {
            System.out.println(string);
        }

        // 2.
        System.out.println("---------------------------------------------------------");
        ArrayList<MyCustomClass> myCustomArrayList = arrToArrList(myCustomClassArr);
//        ArrayList<MyCustomClass> myCustomArrayList = new ArrayList<>(Arrays.asList(myCustomClassArr));
        System.out.println(myCustomArrayList.toString());

        // 3.
        System.out.println("---------------------------------------------------------");
        Box boxOne = new Box();
        Box boxTwo = new Box();

        for (int i = 0; i < 5; i++) {
            boxOne.putOne(new Apple());
        }
        for (int i = 0; i < 5; i++) {
            boxTwo.putOne(new Orange());
        }

        boxOne.putOne(new Orange());
        boxTwo.putOne(new Apple());

        System.out.println("Вес первой коробки = " + boxOne.getWeight());
        System.out.println("Вес второй коробки = " + boxTwo.getWeight());
    }

    /*
        1. Написать метод, который меняет два элемента массива местами
        (массив может быть любого ссылочного типа);
     */
    static void invertTwoArrElls(Object[] srcArr, int firstIndex, int secondIndex) {
        Object buffer = srcArr[firstIndex];
        srcArr[firstIndex] = srcArr[secondIndex];
        srcArr[secondIndex] = buffer;
    }

    /*
        2. Написать метод, который преобразует массив в ArrayList;
     */
    static <T> ArrayList<T> arrToArrList(T[] srcArr) {
        ArrayList<T> result = new ArrayList<>();
        for (int i = 0; i < srcArr.length; i++) {
            result.add(srcArr[i]);
        }
        return result;
//        return new ArrayList<>(Arrays.asList(srcArr));
    }

}

/*
    3.      Даны классы Fruit, Apple extends Fruit, Orange extends Fruit;
            Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта,
        поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
            Для хранения фруктов внутри коробки можно использовать ArrayList;
            Сделать метод getWeight(), который высчитывает вес коробки, зная вес одного фрукта и их количество:
        вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);
            Внутри класса Box сделать метод compare(), который позволяет сравнить текущую коробку с той,
        которую подадут в compare() в качестве параметра. true – если их массы равны,
        false в противоположном случае. Можно сравнивать коробки с яблоками и апельсинами;
            Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую.
            Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами.
            Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты,
        которые были в первой;
            Не забываем про метод добавления фрукта в коробку.
 */
abstract class Fruit {
    // todo вспомнить наследование. почему не получается через поле??
    abstract double getWeight();
}

class Apple extends Fruit {
    @Override
    double getWeight() {
        return 1.0;
    }
}

class Orange extends Fruit {
    @Override
    double getWeight() {
        return 1.5;
    }
}

class Box {
    ArrayList<Fruit> content;
    Class<? extends Fruit> boxType;

    void putOne(Fruit f) {
        if (content == null) {
            boxType = f.getClass();
            content = new ArrayList<>();
            content.add(f);
        } else {
            if (f.getClass().equals(boxType)) {
                content.add(f);
            } else {
                System.out.println(f.getClass().getName()
                                        + " нельзя положить в коробку из под "
                                        + boxType.getName());
            }
        }
    }

    double getWeight() {
        return content.stream()
                        .map(Fruit::getWeight)
                        .reduce(0.0, Double::sum);
    }
}

/*-------------------------------------------------------------------------------------*/
class MyCustomClass {
    String string;
    int anInt;

    public MyCustomClass(String string, int anInt) {
        this.string = string;
        this.anInt = anInt;
    }

    @Override
    public String toString() {
        return "FirstMethodClass{" +
                "string='" + string + '\'' +
                ", anInt=" + anInt +
                '}';
    }
}
