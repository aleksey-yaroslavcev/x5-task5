import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CustomLinkedListTests {

    @Test
    public void addToEndTest() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        assertEquals(0, list.size());

        assertThatCode(() -> list.add("1st String")).doesNotThrowAnyException();
        assertEquals(1, list.size());

        list.add("2nd String");
        assertEquals(2, list.size());

        list.add("3rd String");
        assertEquals(3, list.size());

        assertEquals("1st String", list.get(0));
        assertEquals("2nd String", list.get(1));
        assertNotEquals("2nd String", list.get(2));
    }

    @Test
    public void addRandomTest() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("1st String");
        list.add("2nd String");

        list.add("Zero string", 0);
        assertEquals("Zero string", list.get(0));
        assertEquals(3, list.size());

        list.add("Last string", 3);
        assertEquals("Last string", list.get(3));
        assertEquals(4, list.size());

        list.add("3rd string", 3);
        assertEquals("3rd string", list.get(3));
        assertEquals(5, list.size());

        assertThatExceptionOfType(CustomListException.class)
                .isThrownBy(() -> list.add("String with bad index", 6))
                .withMessage("Index out of range");

        assertThatExceptionOfType(CustomListException.class)
                .isThrownBy(() -> list.add("String with negative index", -1))
                .withMessage("Index out of range");
    }

    @Test
    public void updateTest() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("1st String");
        list.add("2nd String");

        assertEquals("2nd String", list.update("Last string", 1));

        assertThatExceptionOfType(CustomListException.class)
                .isThrownBy(() -> list.update("3rd String", 2))
                .withMessage("Index out of range");

        assertThatExceptionOfType(CustomListException.class)
                .isThrownBy(() -> list.update("String with negative index", -1))
                .withMessage("Index out of range");
    }

    @Test
    public void deleteTest() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        assertThatExceptionOfType(CustomListException.class)
                .isThrownBy(() -> list.delete(0))
                .withMessage("Empty list");

        list.add("1st String");
        list.add("2nd String");
        list.add("3rd String");
        list.add("4th String");
        list.add("5th String");

        assertEquals("1st String", list.delete(0));
        assertEquals("3rd String", list.delete(1));
        assertEquals("5th String", list.delete(2));
        assertEquals(2, list.size());

        assertThatExceptionOfType(CustomListException.class)
                .isThrownBy(() -> list.delete(5))
                .withMessage("Index out of range");
    }
}
