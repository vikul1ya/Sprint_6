package testing;

import com.example.Feline;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class FelineTest {

    Feline feline = new Feline();


    @Test
    public void testEatMeat() throws Exception {
        assertEquals(List.of("Животные", "Птицы", "Рыба"), feline.eatMeat());
    }
    @Test
    public void testGetFamily() {
        assertEquals("Кошачьи", feline.getFamily());
    }
    @Test
    public void testGetKittens() {
        assertEquals(1, feline.getKittens());
    }
    @Test
    public void testGetKittens2() {
        int randomInt = (int)(Math.random()*10);
        assertEquals(randomInt, feline.getKittens(randomInt));
    }
}