package pl.mpas.parent_children.model;

import java.util.Arrays;
import java.util.stream.Stream;

public enum AdultOrChild {
    ADULT('A'),
    CHILD('C');

    AdultOrChild(char marker) {
        this.marker = marker;
    }

    public static AdultOrChild fromChar(char marker) {
//       Stream.of(values()).filter()
        if (marker == 'A') {
            return ADULT;
        } else {
            return CHILD;
        }
    }


    public char getMarker() {
        return marker;
    }

    private char marker;
}
