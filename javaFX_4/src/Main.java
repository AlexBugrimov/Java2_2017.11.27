import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> simpleList = new ArrayList<>();
        simpleList.add(new Person("Ivan", 24));
        simpleList.add(new Person("Alex", 30));

        ObservableList<Person> fxList = FXCollections.observableArrayList(simpleList);
        fxList.addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> change) {
                System.out.println("!");
                while (change.next()) {
                    if (change.wasAdded()) { // Данные были добавлены
                        for (Person person : change.getAddedSubList()) {
                            System.out.println("Added: " + person);
                        }
                    }

                    if (change.wasRemoved()) { // Данные были Удалены
                        for (Person person : change.getRemoved()) {
                            System.out.println("Deleted: " + person);
                        }
                    }
                }
            }
        });

        simpleList.add(new Person("Slava", 51));
        fxList.add(new Person("Slava", 51));

        for (Person person : fxList) {
            person.age.addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    System.out.println(person + " - old: " + oldValue + ", new: " + newValue);
                }
            });
        }

        fxList.remove(0);
        fxList.get(0).age.set(fxList.get(0).age.get() + 1);

        for (Person person : simpleList) {
            System.out.println(person);
        }
        System.out.println("----------------");
        for (Person person : fxList) {
            System.out.println(person);
        }
    }
}

class Person {

    String name;
//    int age;
    IntegerProperty age = new SimpleIntegerProperty();

    public Person(String name, int age) {
        this.name = name;
        this.age.set(age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age.get() +
                '}';
    }
}
