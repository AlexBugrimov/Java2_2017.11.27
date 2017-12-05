import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Main {
    public static void main(String[] args) {
        DemoProperty db = new DemoProperty();
        db.getKProperty().addListener((observable, oldValue, newValue) -> {

            System.out.println("Old value: " + oldValue);
            System.out.println("New value: " + newValue);
        });

        db.setK(15.0);
        db.setK(-1.7);
        db.setK(100);

        DoubleProperty d = new SimpleDoubleProperty(10);
        DoubleBinding db1 = new DoubleBinding() {
            {
                super.bind(db.getKProperty(), d);
            }
            @Override
            protected double computeValue() {
                return db.getK() + d.get();
            }
        };

        System.out.println(db1.get());
    }
}

class DemoProperty {
    private DoubleProperty k = new SimpleDoubleProperty(0);

    public double getK() {
        return k.get();
    }

    public void setK(double k) {
        this.k.set(k);
    }

    public DoubleProperty getKProperty() {
        return k;
    }
}
