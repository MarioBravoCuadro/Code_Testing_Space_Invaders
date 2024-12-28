package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import space_invaders.main.Board;
import space_invaders.main.Commons;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Shot;


import javax.swing.*;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BoardCajaBlancaTest {
    private Board board;

    @BeforeEach
    public void setUp(){
        board = new Board();

    }

    @Test
    void gameInit() {
        board.gameInit();
        assertAll("Comprobaciones de la inicialización del juego",
                () -> assertEquals(24, board.getAliens().size(),
                        "Número incorrecto de aliens"),
                () -> assertNotNull(board.getPlayer(),
                        "El jugador no ha sido creado"),
                () -> assertNotNull(board.getShot(),
                        "El disparo no ha sido creado")
        );
    }
    
    @Test
    void updateCamino1() {
        board.setDeaths(0);
        boolean inGameBeforeTest = board.isInGame();
        String messageBeforeTest = board.getMessage();
        board.update();
        assertAll("Comprobaciones de la actualización del juego",

                () -> assertEquals(inGameBeforeTest, board.isInGame(),
                        "El estado de la partida no debería haber cambiado"),

                () -> assertEquals(messageBeforeTest,board.getMessage(),
                        "El mensaje no debería haber cambiado")
        );
    }

    @Test
    void updateCamino2() {
        board.setDeaths(Commons.NUMBER_OF_ALIENS_TO_DESTROY);
        board.update();
        assertAll("Comprobaciones de la actualización del juego",

                () -> assertFalse(board.isInGame(), "El juego debería haber terminado"),

                () -> assertEquals("Game won!",board.getMessage(),
                        "El mensaje obtenido no es correcto")
        );
    }

    @Test // JESÚS
    void update_shots_Camino1() {

        Shot shot = new Shot(20,20);
        shot.setVisible(false);
        board.setShot(shot);

        board.getAliens().clear();//vaciamos el array de aliens
        Alien alien = new Alien(20,20);
        alien.setVisible(true);
        board.getAliens().add(alien);

        int deathAntesTest= board.getDeaths();

        board.update_shots();

        assertAll(
                ()-> assertEquals(deathAntesTest,board.getDeaths()),
                ()-> assertTrue(alien.isVisible())
        );
    }

    @Test // JESÚS
    void update_shots_Camino2() {
        Shot shot = new Shot(20,20);
        shot.setVisible(true);
        board.setShot(shot);
        board.getAliens().clear();//vaciamos el array de aliens

        int deathAntesTest= board.getDeaths();

        board.update_shots();
        assertAll(
                ()->assertEquals(15,board.getShot().getY()),
                ()-> assertEquals(deathAntesTest,board.getDeaths())
        );

    }

    @Test // JESÚS
    void update_shots_Camino3() {


        Shot shot = new Shot(20,3);
        shot.setVisible(true);
        board.setShot(shot);
        board.getAliens().clear();//vaciamos el array de aliens

        int deathAntesTest= board.getDeaths();

        board.update_shots();
        assertAll(
                ()-> assertFalse(shot.isVisible(),"El shot no tiene que ser visible"),
                ()-> assertEquals(deathAntesTest,board.getDeaths())
        );

    }

    @Test // JESÚS
    void update_shots_Camino4() {
        Shot shot = new Shot(20,20);
        shot.setVisible(true);
        board.setShot(shot);

        board.getAliens().clear();//vaciamos el array de aliens
        Alien alien = new Alien(20,20);
        alien.setVisible(false);
        board.getAliens().add(alien);//añadimos un alien con las caracteristicas necesarias

        int deathAntesTest= board.getDeaths();

        board.update_shots();
        assertAll(
                ()->assertEquals(15,board.getShot().getY()),
                ()-> assertEquals(deathAntesTest,board.getDeaths())
        );
    }

    @Test // JESÚS
    void update_shots_Camino5() {
    }

    @Test // JESÚS
    void update_shots_Camino6() {
        Shot shot = new Shot(3,20);
        shot.setVisible(true);
        board.setShot(shot);

        board.getAliens().clear();//vaciamos el array de aliens
        Alien alien = new Alien(8,20);
        alien.setVisible(true);
        board.getAliens().add(alien); //añadimos un alien con las caracteristicas necesarias

        int deathAntesTest= board.getDeaths();

        board.update_shots();

        assertAll(
                ()-> assertEquals(deathAntesTest,board.getDeaths()),
                ()-> assertTrue(alien.isVisible()),
                ()->assertEquals(15,board.getShot().getY())
        );

    }

    @Test // JESÚS
    void update_shots_Camino7() {
        Shot shot = new Shot(30,20);
        shot.setVisible(true);
        board.setShot(shot);

        board.getAliens().clear();//vaciamos el array de aliens
        Alien alien = new Alien(5,20);
        alien.setVisible(true);
        board.getAliens().add(alien); //añadimos un alien con las caracteristicas necesarias

        int deathAntesTest= board.getDeaths();

        board.update_shots();

        assertAll(
                ()-> assertEquals(deathAntesTest,board.getDeaths()),
                ()-> assertTrue(alien.isVisible()),
                ()-> assertEquals(15,board.getShot().getY())
        );
    }

    @Test // JESÚS
    void update_shots_Camino8() {
        Shot shot = new Shot(10,10);
        shot.setVisible(true);
        board.setShot(shot);

        board.getAliens().clear();//vaciamos el array de aliens
        Alien alien = new Alien(5,20);
        alien.setVisible(true);
        board.getAliens().add(alien); //añadimos un alien con las caracteristicas necesarias

        int deathAntesTest= board.getDeaths();

        board.update_shots();
        assertAll(
                ()->assertEquals(5,board.getShot().getY()),
                ()-> assertEquals(deathAntesTest,board.getDeaths()),
                ()-> assertTrue(alien.isVisible())
        );

    }

    @Test // JESÚS
    void update_shots_Camino9() {
        Shot shot = new Shot(10,30);
        shot.setVisible(true);
        board.setShot(shot);

        board.getAliens().clear();//vaciamos el array de aliens
        Alien alien = new Alien(5,5);
        alien.setVisible(true);
        board.getAliens().add(alien); //añadimos un alien con las caracteristicas necesarias

        int deathAntesTest= board.getDeaths();

        board.update_shots();
        assertAll(
                ()->assertEquals(25,board.getShot().getY()),
                ()-> assertEquals(deathAntesTest,board.getDeaths()),
                ()-> assertTrue(alien.isVisible())
        );
    }

    @Test // JESÚS
    void update_shots_Camino10() {
        Shot shot = new Shot(10,10);
        shot.setVisible(true);
        board.setShot(shot);

        board.getAliens().clear();//vaciamos el array de aliens
        Alien alien = new Alien(5,5);
        alien.setVisible(true);
        board.getAliens().add(alien); //añadimos un alien con las caracteristicas necesarias

        String explImg = "src/main/resources/images/explosion.png";
        var ii = new ImageIcon(explImg);


        board.update_shots();
        assertAll(
                ()-> assertEquals(5,board.getShot().getY()),
                ()-> assertEquals(1,board.getDeaths()),
                ()-> assertEquals(ii.getImage(), alien.getImage()),
                ()-> assertTrue(alien.isDying()),
                ()-> assertFalse(shot.isVisible())
        );
    }




    // Tests de caminos de updateAliens()
    @Test
    public void update_aliensC1() {
        board.setAliens(new ArrayList<>());
        board.update_aliens();
        assertTrue(board.getAliens().isEmpty());
    }

    @Test
    public void update_aliensC2() {
        // CAMINO IMPOSIBLE, EXPLICADO EN LA DOCUMENTACIÓN
    }

    @Test
    public void update_aliensC3() {
        // CAMINO IMPOSIBLE, EXPLICADO EN LA DOCUMENTACIÓN
    }

    @Test
    public void update_aliensC4() {
        // CAMINO IMPOSIBLE, EXPLICADO EN LA DOCUMENTACIÓN
    }

    @Test
    public void update_aliensC5() {
        // CAMINO IMPOSIBLE, EXPLICADO EN LA DOCUMENTACIÓN
    }

    @Test
    public void update_aliensC6() {
        // CAMINO IMPOSIBLE, EXPLICADO EN LA DOCUMENTACIÓN
    }

    @Test
    public void update_aliensC7() {
        board.setDirection(1);
        board.gameInit();

        board.getAliens().get(0).setVisible(true);
        int x = - 1;
        int y = board.getAliens().get(0).getY();
        board.getAliens().get(0).setX(x);

        board.update_aliens();
        assertAll("ABEG[HG][IJ]IF",
                () -> assertTrue(board.getDirection() == -1),
                () -> assertTrue(board.getAliens().get(0).getY() == (y + Commons.GO_DOWN)),
                () -> assertTrue(board.getAliens().get(0).isVisible()),
                () -> assertTrue(board.isInGame())
        );
    }

    @Test
    public void update_aliensC8() {
        board.setDirection(-1);
        board.gameInit();

        board.getAliens().get(0).setVisible(true);
        int x = 50000;
        int y = board.getAliens().get(0).getY();
        board.getAliens().get(0).setX(x);

        board.update_aliens();
        assertAll("ABEG[HG][IJ]IF",
                () -> assertTrue(board.getDirection() == 1),
                () -> assertTrue(board.getAliens().get(0).getY() == (y + Commons.GO_DOWN)),
                () -> assertTrue(board.getAliens().get(0).isVisible()),
                () -> assertTrue(board.isInGame())
        );
    }

    @Test
    public void update_aliensC9() {
        // CAMINO IMPOSIBLE, EXPLICADO EN LA DOCUMENTACIÓN
    }

    @Test
    void update_bomb_Camino3() {
        board.getAliens().clear();//vaciamos el array de aliens
        Alien alien = new Alien(150,150);
        alien.setVisible(true);
        alien.getBomb().setDestroyed(true);
        board.getAliens().add(alien); //añadimos un alien con las caracteristicas necesarias
        //int shot está inicializado a 5.
        board.getPlayer().setX(150);
        board.getPlayer().setY(150);
        board.getPlayer().setVisible(true);

        board.update_bomb();
        assertAll(
                () -> assertTrue(board.getPlayer().isDying()),
                () -> assertTrue(alien.getBomb().isDestroyed())
        );
    }

    @Test
    void update_bomb_Camino4() {
        board.getAliens().clear();//vaciamos el array de aliens
        Alien alien = new Alien(150,150);
        alien.setVisible(true);
        alien.getBomb().setDestroyed(true);
        board.getAliens().add(alien); //añadimos un alien con las caracteristicas necesarias
        //int shot está inicializado a 5.
        board.getPlayer().setX(150);
        board.getPlayer().setY(100);
        board.getPlayer().setVisible(true);

        board.update_bomb();
        assertAll(
                () -> assertFalse(board.getPlayer().isDying()),
                () -> assertEquals(151, alien.getBomb().getY())
        );
    }

    @Test
    void update_bomb_Camino5() {
        board.getAliens().clear();//vaciamos el array de aliens
        Alien alien = new Alien(150,100);
        alien.setVisible(true);
        alien.getBomb().setDestroyed(true);
        board.getAliens().add(alien); //añadimos un alien con las caracteristicas necesarias
        //int shot está inicializado a 5.
        board.getPlayer().setX(150);
        board.getPlayer().setY(150);
        board.getPlayer().setVisible(true);

        board.update_bomb();
        assertAll(
                () -> assertFalse(board.getPlayer().isDying()),
                () -> assertEquals(101, alien.getBomb().getY())
        );
    }

    @Test
    void update_bomb_Camino6() {
        board.getAliens().clear();//vaciamos el array de aliens
        Alien alien = new Alien(150,100);
        alien.setVisible(true);
        alien.getBomb().setDestroyed(true);
        board.getAliens().add(alien); //añadimos un alien con las caracteristicas necesarias
        //int shot está inicializado a 5.
        board.getPlayer().setX(100);
        board.getPlayer().setY(150);
        board.getPlayer().setVisible(true);

        board.update_bomb();
        assertAll(
                () -> assertFalse(board.getPlayer().isDying()),
                () -> assertEquals(101, alien.getBomb().getY())
        );
    }

    @Test
    void update_bomb_Camino7() {
        board.getAliens().clear();//vaciamos el array de aliens
        Alien alien = new Alien(100,100);
        alien.setVisible(true);
        alien.getBomb().setDestroyed(true);
        board.getAliens().add(alien); //añadimos un alien con las caracteristicas necesarias
        //int shot está inicializado a 5.
        board.getPlayer().setX(150);
        board.getPlayer().setY(150);
        board.getPlayer().setVisible(true);

        board.update_bomb();
        assertAll(
                () -> assertFalse(board.getPlayer().isDying()),
                () -> assertEquals(101, alien.getBomb().getY())
        );
    }

    @Test
    void update_bomb_Camino8() {
        board.getAliens().clear();//vaciamos el array de aliens
        Alien alien = new Alien(100,100);
        alien.setVisible(true);
        alien.getBomb().setDestroyed(true);
        board.getAliens().add(alien); //añadimos un alien con las caracteristicas necesarias
        //int shot está inicializado a 5.
        board.getPlayer().setX(150);
        board.getPlayer().setY(150);
        board.getPlayer().setVisible(false);

        board.update_bomb();
        assertAll(
                () -> assertFalse(board.getPlayer().isDying()),
                () -> assertEquals(101, alien.getBomb().getY())
        );
    }

    @Test
    void update_bomb_Camino9() {
        board.getAliens().clear();//vaciamos el array de aliens
        Alien alien = new Alien(100,100);
        alien.setVisible(true);
        alien.getBomb().setX(100);
        alien.getBomb().setY(100);
        alien.getBomb().setDestroyed(false);
        board.getAliens().add(alien); //añadimos un alien con las caracteristicas necesarias
        //int shot está inicializado a 5.
        board.getPlayer().setX(150);
        board.getPlayer().setY(150);
        board.getPlayer().setVisible(true);

        board.update_bomb();
        assertAll(
                () -> assertFalse(board.getPlayer().isDying()),
                () -> assertEquals(101, alien.getBomb().getY())
        );
    }

    @Test
    void update_bomb_Camino10() {
        board.getAliens().clear();//vaciamos el array de aliens
        Alien alien = new Alien(100,100);
        alien.setVisible(false);
        alien.getBomb().setDestroyed(true);
        board.getAliens().add(alien); //añadimos un alien con las caracteristicas necesarias
        //int shot está inicializado a 5.
        board.getPlayer().setX(150);
        board.getPlayer().setY(150);
        board.getPlayer().setVisible(true);

        board.update_bomb();
        assertAll(
                () -> assertFalse(board.getPlayer().isDying()),
                () -> assertTrue(alien.getBomb().isDestroyed())
        );
    }

    @Test
    void update_bomb_Camino11() {
        board.getAliens().clear();//vaciamos el array de aliens
        Alien alien = new Alien(100,100);
        alien.setVisible(true);
        alien.getBomb().setDestroyed(true);
        board.getAliens().add(alien); //añadimos un alien con las caracteristicas necesarias
        //int shot está inicializado a 10 != CHANCE.
        board.getPlayer().setX(150);
        board.getPlayer().setY(150);
        board.getPlayer().setVisible(true);

        board.update_bomb();
        assertAll(
                () -> assertFalse(board.getPlayer().isDying()),
                () -> assertTrue(alien.getBomb().isDestroyed())
        );
    }

    @Test
    void update_bomb_Camino12() {
        board.getAliens().clear();//vaciamos el array de aliens
        Alien alien = new Alien(100,100);
        alien.setVisible(true);
        alien.getBomb().setX(100);
        alien.getBomb().setX(300);
        alien.getBomb().setDestroyed(false);
        board.getAliens().add(alien); //añadimos un alien con las caracteristicas necesarias
        //int shot está inicializado a 10 != CHANCE.
        board.getPlayer().setX(150);
        board.getPlayer().setY(150);
        board.getPlayer().setVisible(false);

        board.update_bomb();
        assertAll(
                () -> assertFalse(board.getPlayer().isDying()),
                () -> assertTrue(alien.getBomb().isDestroyed())
        );
    }
}
