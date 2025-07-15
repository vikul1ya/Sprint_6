package testing;

import com.example.Feline;
import com.example.Lion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LionTest {

    @Mock
    Feline feline;

    @Test
    public void testGetFood() throws Exception {
        when(feline.getFood("Хищник")).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        var lion = new Lion("Самец", feline);
        // Оставляем только одно утверждение
        assertEquals(List.of("Животные", "Птицы", "Рыба"), lion.getFood());
    }

    @Test
    public void testGetKittens() throws Exception {
        when(feline.getKittens()).thenReturn(2);
        var lion = new Lion("Самец", feline);
        // Оставляем только одно утверждение
        assertEquals(2, lion.getKittens());
    }

    @Test
    public void testExceptionInvalidSex() throws Exception {
        // Проверка исключения при некорректном поле
        Exception exception = assertThrows(Exception.class, () -> new Lion("Неопределенный", feline));
        assertEquals("Используйте допустимые значения пола животного - самец или самка", exception.getMessage());
    }

    @Test
    public void testExceptionNullSex() throws Exception {
        Exception exception = assertThrows(Exception.class, () -> new Lion(null, feline));
        assertEquals("Используйте допустимые значения пола животного - самец или самка", exception.getMessage());
    }

    @Test
    public void testExceptionWhitespaceSex() throws Exception {
        Exception exception = assertThrows(Exception.class, () -> new Lion("  ", feline));
        assertEquals("Используйте допустимые значения пола животного - самец или самка", exception.getMessage());
    }

    @Test
    public void testHasManeNotChangingAfterCreation() throws Exception {
        Lion lion = new Lion("Самец", feline);
        boolean firstCall = lion.doesHaveMane();
        boolean secondCall = lion.doesHaveMane();
        // Проверяем, что значение не меняется
        assertEquals(firstCall, secondCall);
    }

    @Test
    public void testHasManeForFemale() throws Exception {
        Lion lion = new Lion("Самка", feline);
        // Проверяем только через метод
        assertFalse(lion.doesHaveMane());
    }
}
