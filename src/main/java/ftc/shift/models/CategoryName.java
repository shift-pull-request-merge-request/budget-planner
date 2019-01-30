package ftc.shift.models;

public enum CategoryName {
    FOOD {
        int id = 0;

        @Override
        public int getId() {
            return id;
        }
    },
    RESTAURANT {
        int id = 1;

        @Override
        public int getId() {
            return id;
        }
    },
    TRANSPORT {
        int id = 2;

        @Override
        public int getId() {
            return id;
        }
    },
    COMMUNICATION {
        int id = 3;

        @Override
        public int getId() {
            return id;
        }
    },
    FUN {
        int id = 4;

        @Override
        public int getId() {
            return id;
        }
    },
    PURCHASE {
        int id = 5;

        @Override
        public int getId() {
            return id;
        }
    };


    public abstract int getId();
    }