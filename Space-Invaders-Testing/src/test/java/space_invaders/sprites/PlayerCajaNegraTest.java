package space_invaders.sprites;

import space_invaders.main.Board;
import space_invaders.main.Commons;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

class PlayerCajaNegraTest {
    private Board board;
    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player();
        board = new Board();
    }




    @Test
    public void actBordePantallaDer() {
        //Colocamos Jugador fuera de los límites del borde derecho
        player.setX(Commons.BOARD_WIDTH - player.getWidth() + 10);
        player.act();
        int posBordeDer = player.getX();

        assertTrue(posBordeDer == Commons.BOARD_WIDTH - Commons.BORDER_RIGHT,"El jugador no se encuentra" +
                                 "dentro de los límites del borde de la pantalla (derecho)");




    }

    @Test
    public void actBordePantallaIzq(){

        // Colocamos Jugador fuera de los límites del borde izquierdo
        player.setX(-20);
        player.act();
        int posBordeIzq = player.getX();

        assertTrue(posBordeIzq >= 0,"El jugador no se encuentra dentro de los límites" +
                " del borde de la pantalla (izquierdo)");

    }

    @Test
    public void testInicializacion(){
        assertAll("Verificar la inicialización correcta,",
                () -> assertEquals(Commons.BOARD_WIDTH/2, player.getX()),
                () -> assertEquals(280, player.getY())
        );
    }
    
    //TESTs DE PRESIONAR TECLA
    @Test
    public void testMoverIzquierda(){
        KeyEvent leftKeyEvent = new KeyEvent(new java.awt.Component(){},
                KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_LEFT, ' ');
        player.keyPressed(leftKeyEvent);

        assertEquals(-2, player.dx,
       "El movimiento en x \n " +
               "no debería ser 2 al presionar " +
               "la tecla izquierda");
    }

    @Test
    public void testMoverDerecha(){
        KeyEvent rightKeyEvent = new KeyEvent(new java.awt.Component() {
        }, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_RIGHT, ' ');
        player.keyPressed(rightKeyEvent);

        assertEquals(2, player.dx,
        "El movimiento en x \n " +
                "debería ser 2 al presionar " +
                "la tecla derecha");

    }

    @Test
    public void testPresionarTecla(){
        KeyEvent otherKeyEvent = new KeyEvent(new java.awt.Component() {
        }, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_UP, ' ');
        player.keyPressed(otherKeyEvent);

        assertEquals(0, player.dx,
        "El movimiento en x \n" +
                "debería ser 0 al presionar " +
                "una tecla no relacionada con el movimiento");
    }

    // TESTs DE SOLTAR TECLA
    @Test
    public void keyReleasedIzquierda(){
        KeyEvent leftKeyEvent = new KeyEvent(new java.awt.Component() {
        }, KeyEvent.KEY_RELEASED, System.currentTimeMillis(),
                0, KeyEvent.VK_LEFT, ' ');
        player.keyReleased(leftKeyEvent);

        // Movimiento esperado: Quedarse Quieto
        assertEquals(0, player.dx,
        "El movimiento en x \n " +
                "debería ser 270 al soltar " +
                "la tecla izquierda");
    }

    @Test
    public void keyReleasedDerecha(){
        KeyEvent rightKeyEvent = new KeyEvent(new java.awt.Component() {
        }, KeyEvent.KEY_RELEASED, System.currentTimeMillis(),
                0, KeyEvent.VK_RIGHT, ' ');
        player.keyReleased(rightKeyEvent);

        // Movimiento esperado: Quedarse Quieto
        assertEquals(0, player.dx,
        "El movimiento en x \n" +
                "debería ser 270 al soltar " +
                "la tecla derecha");
    }

    @Test
    public void keyReleasedOtra(){
        KeyEvent otherKeyEvent = new KeyEvent(new java.awt.Component() {
        }, KeyEvent.KEY_RELEASED, System.currentTimeMillis(),
                0, KeyEvent.VK_UP, ' ');
        player.keyReleased(otherKeyEvent);

        // Movimiento esperado: Quedarse Quieto
        assertEquals(0, player.dx,
        "El movimiento en x \n" +
                "debería ser 270 al soltar " +
                "una tecla no relacionada con el movimiento");
    }
}