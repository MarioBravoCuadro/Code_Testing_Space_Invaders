package space_invaders.sprites;

import space_invaders.main.Commons;
import space_invaders.sprites.Alien.Bomb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BombCajaBlancaTest {

    @Test
    public void initCamino1(){
        Bomb bomb = new Bomb(100, 100);
        assertAll("Verificar init nominal",
                () -> assertEquals(100, bomb.getX()),
                () -> assertEquals(100, bomb.getY())
        );
    }

    @Test
    public void initCamino2(){
        Bomb bomb = new Bomb(100, 400);
        assertAll("Verificar init nominal",
                () -> assertEquals(100, bomb.getX()),
                () -> assertEquals(Commons.BOARD_HEIGHT, bomb.getY())
        );
    }

    @Test
    public void initCamino3(){
        Bomb bomb = new Bomb(400, 400);
        assertAll("Verificar init por encima",
                () -> assertEquals(Commons.BOARD_WIDTH, bomb.getX()),
                () -> assertEquals(Commons.BOARD_HEIGHT, bomb.getY())
        );
    }
}
