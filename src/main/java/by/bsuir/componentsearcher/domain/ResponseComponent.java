package by.bsuir.componentsearcher.domain;

import java.util.List;

/**
 * Created by vladislav on 28.03.17.
 */
public class ResponseComponent {
    private int totalComponentNumber;
    private List<Component> components;


    public ResponseComponent(){

    }

    public ResponseComponent(int totalComponentNumber, List<Component> components) {
        this.totalComponentNumber = totalComponentNumber;
        this.components = components;
    }

    public int getTotalComponentNumber() {
        return totalComponentNumber;
    }

    public void setTotalComponentNumber(int totalComponentNumber) {
        this.totalComponentNumber = totalComponentNumber;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }
}
