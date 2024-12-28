package space_invaders.sprites;

import space_invaders.main.Commons;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlienCajaBlancaTest {
    @Test
    public void initAlienCamino1() {
        Alien alien= new Alien(30,30);
        assertAll(
                () -> assertEquals(30, alien.getX()),
                () -> assertEquals(30, alien.getY())
        );
    }

    @Test
    public void initAlienCamino2() {
        Alien alien= new Alien(30,-3);
        assertAll(
                () -> assertEquals(30, alien.getX()),
                () -> assertEquals(0, alien.getY())
        );
    }

    @Test
    public void initAlienCamino3() {
        Alien alien= new Alien(400,-3);
        assertAll(
                () -> assertEquals(Commons.BOARD_WIDTH, alien.getX()),
                () -> assertEquals(0, alien.getY())
        );
    }

    @Test
    public void initAlienCamino4() {
        Alien alien= new Alien(-3,-3);
        assertAll(
                () -> assertEquals(0, alien.getX()),
                () -> assertEquals(0, alien.getY())
        );
    }

    @Test
    public void initAlienCamino5() {
        Alien alien= new Alien(30,400);
        assertAll(
                () -> assertEquals(30, alien.getX()),
                () -> assertEquals(Commons.BOARD_HEIGHT, alien.getY())
        );
    }
}

