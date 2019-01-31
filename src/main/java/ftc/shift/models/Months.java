package ftc.shift.models;

public class Months {
    private Month[] months;

    private static int MONTH_NUM = 12;
    private static int CATEGORIES_NUM = 6;

    public Months() {
        months = new Month[MONTH_NUM];
        for (int i = 0; i < MONTH_NUM; i++) {
            months[i] = new Month(i + 1, 0, new Category[CATEGORIES_NUM]);
            for (int j = 0; j < CATEGORIES_NUM; j++) {
                months[i].getCategories()[j] = new Category();
                months[i].getCategories()[j].setName(CategoryName.values()[j]);
            }
        }
    }

    public Month[] getMonths() {
        return months;
    }

    public void setMonths(Month[] months) {
        this.months = months;
    }
}
