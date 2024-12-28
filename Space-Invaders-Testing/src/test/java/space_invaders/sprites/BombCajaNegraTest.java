package space_invaders.sprites;

import space_invaders.main.Commons;
import space_invaders.sprites.Alien.Bomb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class BombCajaNegraTest {

    @Test
    public void testNominal() {
        Bomb bomb = new Bomb(50, 50);
        assertAll("Verificar init nominal",
                () -> assertEquals(50, bomb.getX()),
                () -> assertEquals(50, bomb.getY())
        );
    }

    @Test
    public void testShotMax() {
        Bomb bomb = new Bomb(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        assertAll("Verificar init máximo",
                () -> assertEquals(Commons.BOARD_WIDTH, bomb.getX()),
                () -> assertEquals(Commons.BOARD_HEIGHT, bomb.getY())
        );
    }

    @Test
    public void testShotMin() {
        Bomb bomb = new Bomb(0, 0);
        assertAll("Verificar init mínimo",
                () -> assertEquals(0, bomb.getX()),
                () -> assertEquals(0, bomb.getY())
        );
    }

    @Test
    public void testShotBeforeMax() {
        Bomb bomb = new Bomb(Commons.BOARD_WIDTH-1, Commons.BOARD_HEIGHT-1);
        assertAll("Verificar init máximo",
                () -> assertEquals(Commons.BOARD_WIDTH-1, bomb.getX()),
                () -> assertEquals(Commons.BOARD_HEIGHT-1, bomb.getY())
        );
    }

    @Test
    public void testShotBeforeMin() {
        Bomb bomb = new Bomb(1, 1);
        assertAll("Verificar init mínimo",
                () -> assertEquals(1, bomb.getX()),
                () -> assertEquals(1, bomb.getY())
        );
    }

    @Test
    public void testShotExceedsMax() {
        Bomb bomb = new Bomb(400, 400);
        assertAll("Verificar init por encima",
                () -> assertEquals(Commons.BOARD_WIDTH, bomb.getX()),
                () -> assertEquals(Commons.BOARD_HEIGHT, bomb.getY())
        );
    }

    @Test
    public void testShotBelowMin() {
        Bomb bomb = new Bomb(-10, -10);
        assertAll("Verificar init por debajo",
                () -> assertEquals(0, bomb.getX()),
                () -> assertEquals(0, bomb.getY())
        );
    }
}
