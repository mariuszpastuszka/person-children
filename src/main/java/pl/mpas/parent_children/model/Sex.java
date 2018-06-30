package pl.mpas.parent_children.model;

public enum Sex {

    MALE('M'),
    FEMALE('F');

    public static Sex fromChar(char sex) {
        if (sex == 'M') {
            return MALE;
        } else {
            return FEMALE;
        }
    }

    public char getSex() {
        return sex;
    }

    private final char sex;

    Sex(char sex) {
        this.sex = sex;
    }
}
