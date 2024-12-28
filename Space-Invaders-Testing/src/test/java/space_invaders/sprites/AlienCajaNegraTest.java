package space_invaders.sprites;

import space_invaders.main.Commons;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlienCajaNegraTest {

    @Test
    public void testNominal() {
        Alien alien = new Alien(50, 50);
        assertAll("Verificar init nominal",
                () -> assertEquals(50, alien.getX()),
                () -> assertEquals(50, alien.getY())
        );
    }

    @Test
    public void testAlienMax() {
        Alien alien = new Alien(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        assertAll("Verificar init máximo",
                () -> assertEquals(Commons.BOARD_WIDTH, alien.getX()),
                () -> assertEquals(Commons.BOARD_HEIGHT, alien.getY())
        );
    }

    @Test
    public void testAlienMin() {
        Alien alien = new Alien(0, 0);
        assertAll("Verificar init mínimo",
                () -> assertEquals(0, alien.getX()),
                () -> assertEquals(0, alien.getY())
        );
    }

    @Test
    public void testAlienBeforeMax() {
        Alien alien = new Alien(Commons.BOARD_WIDTH-1, Commons.BOARD_HEIGHT-1);
        assertAll("Verificar init máximo",
                () -> assertEquals(Commons.BOARD_WIDTH-1, alien.getX()),
                () -> assertEquals(Commons.BOARD_HEIGHT-1, alien.getY())
        );
    }

    @Test
    public void testAlienBeforeMin() {
        Alien alien = new Alien(1, 1);
        assertAll("Verificar init mínimo",
                () -> assertEquals(1, alien.getX()),
                () -> assertEquals(1, alien.getY())
        );
    }

    @Test
    public void testAlienExceedsMax() {
        Alien alien = new Alien(400, 400);
        assertAll("Verificar init por encima",
                () -> assertEquals(Commons.BOARD_WIDTH, alien.getX()),
                () -> assertEquals(Commons.BOARD_HEIGHT, alien.getY())
        );
    }

    @Test
    public void testAlienBelowMin() {
        Alien alien = new Alien(-10, -10);
        assertAll("Verificar init por debajo",
                () -> assertEquals(0, alien.getX()),
                () -> assertEquals(0, alien.getY())
        );
    }

    @Test
    @Tag("Act")
    public void actNormalRight() {
        Alien alien = new Alien(150, 150);
        alien.act(-1); // Move right
        assertEquals(151, alien.getX());
    }

    @Test
    @Tag("Act")
    public void actNormalLeft() {
        Alien alien = new Alien(150, 150);
        alien.act(1); // Move left
        assertEquals(149, alien.getX());
    }

    @Test
    @Tag("Act")
    public void actNotMove(){
        Alien alien = new Alien(150, 150);
        alien.act(0);
        assertEquals(150, alien.getX());
    }

    @Test
    @Tag("Act")
    public void actNotValid(){
        Alien alien = new Alien(150, 150);
        alien.act(2);
        assertEquals(150, alien.getX());
    }
}


