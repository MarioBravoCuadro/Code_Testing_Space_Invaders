package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import space_invaders.main.Board;
import space_invaders.main.Commons;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Player;
import space_invaders.sprites.Shot;


import javax.swing.*;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class BoardCajaNegraTest {
    private Board board;

    private Shot shot;

    @BeforeEach
    public void setUp() {
        board = new Board();
        board.setPlayer(new Player());
        shot = new Shot();
        board.setShot(shot);
        board.gameInit();
    }


    @Test
    public void disparoSinColisionCV1() {
        // Colocamos el disparo en una posición sin colisión
        shot.setX(100);
        shot.setY(100);
        board.setShot(shot);
        board.update_shots();

        assertAll("Verificar disparo sin colisión",
                () -> assertTrue(board.getShot().isVisible(), "El disparo debería estar visible porque no ha impactado"),

                // Verificamos que los alienígenas siguen visibles
                () -> assertTrue(board.getAliens().get(0).isVisible(), "Alien 1 debería seguir visible porque no ha sido alcanzado"),
                () -> assertTrue(board.getAliens().get(1).isVisible(), "Alien 2 debería seguir visible porque no ha sido alcanzado"),

                // Verificamos que el número de muertes sigue siendo cero
                () -> assertEquals(0, board.getDeaths(), "El número de muertes debería ser 0")
        );

    }

    @Test
    public void disparoConColisionCV2() {

        // Crear disparo en la posición del primer alienígena
        shot.setX(board.getAliens().get(0).getX() + Commons.ALIEN_WIDTH / 2);
        shot.setY(board.getAliens().get(0).getY() + Commons.ALIEN_HEIGHT / 2);
        board.setShot(shot);
        board.update_shots();


        assertAll("Verificar colisión de disparo con alien",
                () -> assertTrue(board.getAliens().get(0).isDying(), "El disparo NO ha impactado con Alien 1"),
                () -> assertFalse(board.getShot().isVisible(), "El disparo ya no está disponible"),
                () -> assertEquals(1, board.getDeaths(), "La cantidad de muertes no es la esperada")
        );
    }

    @Test
    public void disparoFueraLimitesCNV1() {

        shot.setX(100);
        shot.setY(-34);
        board.setShot(shot);
        board.update_shots();
        assertFalse(board.getShot().isVisible(), "El disparo se encuentra fuera de los límites, no está disponible");

    }

    @Test
    public void inicializarJuegoCV1() {

        assertAll("Verificar que el número de alienígenas sea correcto al iniciar el juego",
                () -> assertEquals(Commons.NUMBER_OF_ALIENS_TO_DESTROY, board.getAliens().size(), "El número de alienígenas no es correcto"),
                () -> {

                    for (Alien alien : board.getAliens()) {
                        assertNotNull(alien, "Alien no inicializado");
                        assertTrue(alien.getX() >= 0 && alien.getX() <= Commons.BOARD_WIDTH,
                                "Alien fuera de los límites en la posición X: " + alien.getX());
                        assertTrue(alien.getY() >= 0 && alien.getY() <= Commons.BOARD_HEIGHT,
                                "Alien fuera de los límites en la posición Y: " + alien.getY());
                    }
                }
        );
    }

    @Test
    public void inicializarJuegoCV2() {
        assertNotNull(board.getPlayer(), "No se ha inicializado ningún jugador");

    }

    @Test
    public void inicializarJuegoCV3() {

        assertNotNull(board.getShot(), "No se ha inicializado ningún disparo");

    }

    @Test
    public void gameInit() {
        Set<String> posiciones = new HashSet<>();
        assertAll("Verificar posiciones iniciales",
                () -> {

                    for (Alien alien : board.getAliens()) {
                        String posicion = alien.getX() + "," + alien.getY();
                        assertFalse(posiciones.contains(posicion), "Posiciones iniciales duplicadas: " + posicion);
                        posiciones.add(posicion);
                    }

                },
                () -> assertEquals(Commons.NUMBER_OF_ALIENS_TO_DESTROY, posiciones.size(),
                        "El número de posiciones únicas no coincide con el número esperado de alienígenas.")
        );

    }



    @Test
    public void movimientoAlienDer() {
        //Posiciones antes del posible movimiento
        int posX = board.getAliens().get(0).getX();
        //dirección derecha
        board.setDirection(-1);
        board.update_aliens();
        assertAll("Verificar cambio de posición",
                //Verificamos si hay cambio de posción y para que lado es
                () -> assertNotEquals(posX, board.getAliens().get(0).getX()),
                () -> assertTrue(board.getAliens().get(0).getX() > posX, "Movimiento hacia la derecha"),
                () -> System.out.println("Posición antes de llamar al método: " + posX),
                () -> System.out.println("Posición después de llamar al método: " + board.getAliens().get(0).getX())

        );

    }

    @Test
    public void movimientoAlienIzq() {
        //Posiciones antes del posible movimiento
        int posX = board.getAliens().get(0).getX();
        //dirección izquierda
        board.getAliens().get(0).setX(0);
        board.setDirection(1);
        board.update_aliens();
        assertAll("Verificar cambio de posición",
                //Verificamos si hay cambio de posción y para que lado es
                () -> assertNotEquals(posX, board.getAliens().get(0).getX()),
                () -> assertTrue(board.getAliens().get(0).getX() < posX, "Movimiento hacia la izquierda"),
                () -> System.out.println("Posición antes de llamar al método: " + posX),
                () -> System.out.println("Posición después de llamar al método: " + board.getAliens().get(0).getX())

        );

    }

    @Test
    public void finPartidaInvasion() {

        //Alienígena en el borde inferior del tablero
        board.getAliens().get(0).setY(Commons.GROUND - Commons.ALIEN_HEIGHT+1);

        board.update_aliens();

        assertAll("¡Fin del juego por INVASIÓN!",
                () -> assertFalse(board.isInGame(), "El juego debe terminar"),
                () -> assertEquals("Invasion!", board.getMessage()),
                () -> System.out.println(board.getMessage())

        );
    }

    @Test
    public void movimientoVerticalDer() {

        //Posición inicial Y
        board.getAliens().get(0).setX(Commons.BOARD_WIDTH + Commons.ALIEN_WIDTH- Commons.BORDER_RIGHT);
        int y = board.getAliens().get(0).getY();

        //dirección derecha
        board.setDirection(-1);
        board.update_aliens();

        //Comprobamos si se la ha sumado   int GO_DOWN = 15 a la variable y
        assertAll("Cambio de posición vertical:",
                () -> assertEquals(y + Commons.GO_DOWN, board.getAliens().get(0).getY()),
                () -> System.out.println("Posción inicial: " + y),
                () -> System.out.println("Posicion inicial + 15: " + board.getAliens().get(0).getY())

        );

    }

    @Test
    public void movimientoVerticalIzq() {
        //Posición inicial Y

        board.getAliens().get(0).setX(0);
        int y = board.getAliens().get(0).getY();

        board.setDirection(1);

        board.update_aliens();

        //Comprobamos si se la ha sumado   int GO_DOWN = 15 a la variable y
        assertAll("Cambio de posición vertical:",
                () -> assertEquals(y + Commons.GO_DOWN, board.getAliens().get(0).getY()),
                () -> System.out.println("Posción inicial: " + y),
                () -> System.out.println("Posicion inicial + 15: " + board.getAliens().get(0).getY())

        );


    }

    @Test
    public void movimientoVerticalNoValido() {
        //Posición inicial Y
        int y = board.getAliens().get(0).getY();
        //dirección no válido
        board.setDirection(0);
        board.update_aliens();

        //Comprobamos si la variable Y no ha cambiado
        assertAll("Cambio de posición vertical:",
                () -> assertEquals(y, board.getAliens().get(0).getY()),
                () -> System.out.println("Posción inicial: " + y),
                () -> System.out.println("Posicion actual: " + board.getAliens().get(0).getY())

        );

    }
 /*
    @Test
    public void destruccionBomba() {
        Alien alien = board.getAliens().get(0);
        Alien.Bomb bomb = alien.getBomb();
        bomb.setDestroyed(false); //La bomba está activa
        bomb.setX(board.getShot().getX());
        bomb.setY(board.getShot().getY());

        board.update_bomb();

        assertTrue(bomb.isDestroyed(), "La bomba no está destruida");


    }
*/
    @Test
    public void destruccionBombaDestruida() {

        Alien alien = board.getAliens().get(0);
        Alien.Bomb bomb = alien.getBomb();
        bomb.setDestroyed(true); //La bomba destruida
        bomb.setX(board.getShot().getX());
        bomb.setY(board.getShot().getY());

        board.update_bomb();

        assertTrue(bomb.isDestroyed(), "La bomba no está destruida");

    }

    @Test
    public void bombaSuelo() {

        Alien alien = board.getAliens().get(0);
        Alien.Bomb bomb = alien.getBomb();
        //Bomba activa
        bomb.setDestroyed(false);
        //Bomba en el suelo
        bomb.setY(Commons.GROUND - Commons.BOMB_HEIGHT);
        board.update_bomb();

        assertTrue(bomb.isDestroyed(), "La bomba no está destruida");
    }

    @Test
    public void bombaSueloDestruida() {
        Alien alien = board.getAliens().get(0);
        Alien.Bomb bomb = alien.getBomb();
        //Bomba NO activa
        bomb.setDestroyed(true);
        //Bomba en el suelo
        bomb.setY(Commons.GROUND - Commons.BOMB_HEIGHT);
        board.update_bomb();

        assertTrue(bomb.isDestroyed(), "La bomba no está destruida");

    }

    @Test
    public void jugadorBombaActiva() {

        Alien alien = board.getAliens().get(0);
        Alien.Bomb bomb = alien.getBomb();
        // Bomba activa
        bomb.setDestroyed(false);
        // Colocar la bomba en la posición del jugador
        bomb.setX(board.getPlayer().getX());
        bomb.setY(board.getPlayer().getY());
        ImageIcon explosion = new ImageIcon(board.getExplImg());

        board.update_bomb();
        assertAll("Bomba impacta jugador:",

                () -> assertTrue(board.getPlayer().isDying(), "El jugador no está destruido"),

                () -> assertEquals(explosion.getImage(), board.getPlayer().getImage(), "La imagen no es la de la explosión")

        );

    }

    @Test
    public void jugadorBombaDestruida() {
        Alien alien = board.getAliens().get(0);
        Alien.Bomb bomb = alien.getBomb();
        // Bomba NO activa
        bomb.setDestroyed(true);
        // Colocar la bomba en la posición del jugador
        bomb.setX(board.getPlayer().getX());
        bomb.setY(board.getPlayer().getY());

        board.update_bomb();
        assertFalse(board.getPlayer().isDying(), "El jugador NO está destruido");

    }

    @Test
    public void updateGameWon() {

        //Todos los alienígenas destruidos
        board.setDeaths(Commons.NUMBER_OF_ALIENS_TO_DESTROY);
        board.update();
        assertAll("Juego terminado por Game Won!",

                () -> assertFalse(board.isInGame(), "El juego debería haber terminado"),
                () -> assertEquals("Game won!", board.getMessage(), "Mensaje incorrecto")
        );


    }
}