package ftc.shift.models;

import java.io.Serializable;
import ftc.shift.models.Month;

public class Months implements Serializable {
    public Month[] month;

    public Months(Month month[]) {
        this.month = month;
    }
}
