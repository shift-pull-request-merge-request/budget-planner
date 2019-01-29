package ftc.shift.models;

import java.io.Serializable;
import java.util.List;

public class Months implements Serializable {
    public String monthId;
    public Integer value;
    public Category category[];

    public Months(String monthId, Integer value, Category[] categories){
        this.monthId = monthId;
        this.value = value;
        this.category = categories;
    }


}