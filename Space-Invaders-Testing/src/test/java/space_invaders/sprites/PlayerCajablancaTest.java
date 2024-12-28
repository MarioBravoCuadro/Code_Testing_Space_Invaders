package space_invaders.sprites;

import space_invaders.main.Commons;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.event.KeyEvent;

import org.junit.jupiter.api.BeforeEach;

public class PlayerCajablancaTest {

    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player();
    }

    // Metodo act()
    @Test
    public void actCamino1() {
        player.x = 20;
        player.dx = 2;
        player.act();
        assertEquals(22, player.getX());
    }

    @Test
    public void actCamino2() {
        player.x = -40;
        player.dx = 2;
        player.act();
        assertEquals(0, player.getX());
    }

    @Test
    public void actCamino3() {
        player.x = Commons.BOARD_WIDTH - player.getWidth()+20;
        player.dx = 2;
        player.act();
        assertEquals((Commons.BOARD_WIDTH - Commons.BORDER_RIGHT), player.getX());
    }

    // metodo KeyPreessed(KeyEvenet e)
    @Test
    public void KeyPreessedCamino1() {
        int dxAntesTest = player.dx;
        KeyEvent UPtKeyEvent = new KeyEvent(new java.awt.Component() {
        }, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UP, ' ');
        player.keyPressed(UPtKeyEvent);
        assertEquals(dxAntesTest, player.dx);
    }

    @Test
    public void KeyPreessedCamino2() {
        KeyEvent leftKeyEvent = new KeyEvent(new java.awt.Component() {
        }, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, ' ');
        player.keyPressed(leftKeyEvent);
        assertEquals(-2, player.dx);
    }

    @Test
    public void KeyPreessedCamino3() {
        KeyEvent rightKeyEvent = new KeyEvent(new java.awt.Component() {
        }, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, ' ');
        player.keyPressed(rightKeyEvent);
        assertEquals(2, player.dx);
    }

    // metodo KeyReleased(KeyEvent e)
    @Test
    public void KeyReleasedCamino1() {
        int dxAntesTest = player.dx;
        KeyEvent UPtKeyEvent = new KeyEvent(new java.awt.Component() {
        }, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_UP, ' ');
        player.keyReleased(UPtKeyEvent);
        assertEquals(dxAntesTest, player.dx);
    }

    @Test
    public void KeyReleasedCamino2() {
        KeyEvent leftKeyEvent = new KeyEvent(new java.awt.Component() {
        }, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, ' ');
        player.keyReleased(leftKeyEvent);
        assertEquals(0, player.dx);
    }

    @Test
    public void KeyReleasedCamino3() {
        KeyEvent rightKeyEvent = new KeyEvent(new java.awt.Component() {
        }, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, ' ');
        player.keyReleased(rightKeyEvent);
        assertEquals(0, player.dx);
    }
}
