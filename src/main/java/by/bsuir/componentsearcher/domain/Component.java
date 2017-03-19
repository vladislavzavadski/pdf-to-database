package by.bsuir.componentsearcher.domain;

import java.io.Serializable;

/**
 * Created by vladislav on 08.03.17.
 */
public class Component implements Serializable{
    private String code;
    private String manufacturer;
    private String name;
    private String price;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Component component = (Component) o;

        if (code != null ? !code.equals(component.code) : component.code != null) return false;
        if (manufacturer != null ? !manufacturer.equals(component.manufacturer) : component.manufacturer != null)
            return false;
        if (name != null ? !name.equals(component.name) : component.name != null) return false;
        return price != null ? price.equals(component.price) : component.price == null;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Component{" +
                "code='" + code + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
