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
        assertEquals(List.of("Животные", "Птицы", "Рыба"), lion.getFood());
    }

    @Test
    public void testGetKittens() throws Exception {
        when(feline.getKittens()).thenReturn(2);
        var lion = new Lion("Самец", feline);
        assertEquals(2, lion.getKittens());
    }

    @Test
    public void testException_InvalidSex() throws Exception {
        Exception exception = assertThrows(Exception.class, () -> new Lion("Неопределенный", feline));
        assertEquals("Используйте допустимые значения пола животного - самец или самка", exception.getMessage());
    }

    @Test
    public void testException_NullSex() throws Exception {
        Exception exception = assertThrows(Exception.class, () -> new Lion(null, feline));
        assertEquals("Используйте допустимые значения пола животного - самец или самка", exception.getMessage());
    }

    @Test
    public void testException_WhitespaceSex() throws Exception {
        Exception exception = assertThrows(Exception.class, () -> new Lion("  ", feline));
        assertEquals("Используйте допустимые значения пола животного - самец или самка", exception.getMessage());
    }

    @Test
    public void testHasMane_NotChangingAfterCreation() throws Exception {
        Lion lion = new Lion("Самец", feline);
        boolean firstCall = lion.doesHaveMane();
        boolean secondCall = lion.doesHaveMane();
        assertEquals(firstCall, secondCall); // Проверяем, что значение не меняется
    }

    @Test
    public void testHasManeForFemale() throws Exception {
        Lion lion = new Lion("Самка", feline);
        assertFalse(lion.doesHaveMane()); // Проверяем, что самка не имеет гривы
        assertFalse(lion.hasMane);        // Прямо проверяем значение поля (если доступно)
    }
}