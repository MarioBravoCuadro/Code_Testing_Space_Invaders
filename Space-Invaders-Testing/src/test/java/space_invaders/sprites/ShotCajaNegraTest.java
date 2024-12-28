package space_invaders.sprites;

import space_invaders.main.Commons;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShotCajaNegraTest {

    @Test
    public void testNominal() {
        Shot shot = new Shot(50, 50);
        assertAll("Verificar init nominal",
                () -> assertEquals(50+6, shot.getX()),
                () -> assertEquals(50-1, shot.getY())
        );
    }

    @Test
    public void testShotMax() {
        Shot shot = new Shot(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        assertAll("Verificar init máximo",
                () -> assertEquals(Commons.BOARD_WIDTH, shot.getX()),
                () -> assertEquals(Commons.BOARD_HEIGHT-1, shot.getY())
        );
    }

    @Test
    public void testShotMin() {
        Shot shot = new Shot(0, 0);
        assertAll("Verificar init mínimo",
                () -> assertEquals(6, shot.getX()),
                () -> assertEquals(0, shot.getY())
        );
    }

    @Test
    public void testShotBeforeMax() {
        Shot shot = new Shot(Commons.BOARD_WIDTH-1, Commons.BOARD_HEIGHT-1);
        assertAll("Verificar init máximo",
                () -> assertEquals(Commons.BOARD_WIDTH, shot.getX()),
                () -> assertEquals(Commons.BOARD_HEIGHT-1-1, shot.getY())
        );
    }

    @Test
    public void testShotBeforeMin() {
        Shot shot = new Shot(1, 1);
        assertAll("Verificar init mínimo",
                () -> assertEquals(1+6, shot.getX()),
                () -> assertEquals(1-1, shot.getY())
        );
    }

    @Test
    public void testShotExceedsMax() {
        Shot shot = new Shot(400, 400);
        assertAll("Verificar init por encima",
                () -> assertEquals(Commons.BOARD_WIDTH, shot.getX()),
                () -> assertEquals(Commons.BOARD_HEIGHT, shot.getY())
        );
    }

    @Test
    public void testShotBelowMin() {
        Shot shot = new Shot(-10, -10);
        assertAll("Verificar init por debajo",
                () -> assertEquals(0, shot.getX()),
                () -> assertEquals(0, shot.getY())
        );
    }
}
