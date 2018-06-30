package pl.mpas.parent_children.model;

public enum AdultOrChild {
    ADULT('A'),
    CHILD('C');

    AdultOrChild(char marker) {
        this.marker = marker;
    }

    public char getMarker() {
        return marker;
    }

    private char marker;
}
