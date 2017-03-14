package by.bsuir.componentsearcher.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by vladislav on 12.03.17.
 */
public class FieldMapping {

    @NotNull(message = "Производитель должен быть задан")
    @Size(max = 300, message = "Поле производитель должно быть меньше 300 символов")
    private String manufacturer;

    @NotNull(message = "Код должен быть задан")
    @Size(max = 300, message = "Поле код должно быть меньше 300 символов")
    private String code;

    @NotNull(message = "Имя должено быть задано")
    @Size(max = 300, message = "Поле имя должно быть меньше 300 символов")
    private String name;

    @NotNull(message = "Цена должено быть задана")
    @Size(max = 300, message = "Поле цена должно быть меньше 300 символов")
    private String price;

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

        FieldMapping that = (FieldMapping) o;

        if (manufacturer != null ? !manufacturer.equals(that.manufacturer) : that.manufacturer != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        return (name != null ? name.equals(that.name) : that.name == null) && (price != null ? price.equals(that.price) : that.price == null);
    }

    @Override
    public int hashCode() {
        int result = manufacturer != null ? manufacturer.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FieldMapping{" +
                "manufacturer='" + manufacturer + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
