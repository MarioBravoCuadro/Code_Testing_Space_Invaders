package testIntegracion;

import space_invaders.main.*;
import space_invaders.sprites.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UpdateIT {

    @BeforeEach
    public void setUp(){

    }

    @Test
    public void updateConStubs() {

        // Inicialización de mocks
        Player mockplayer = mock(Player.class);

        // Crea una instancia real de la clase Board.
        Board board = new Board();
        Board spyBoard = spy(board);

        // Asignamos el mock de Player a la instancia de Board
        spyBoard.setPlayer(mockplayer);

        // Mockeo de los métodos que se van a llamar
        doNothing().when(mockplayer).act();
        doNothing().when(spyBoard).update_shots();
        doNothing().when(spyBoard).update_aliens();
        doNothing().when(spyBoard).update_bomb();

        spyBoard.setDeaths(Commons.NUMBER_OF_ALIENS_TO_DESTROY);
        spyBoard.update();

        assertFalse(spyBoard.isInGame(), "El juego no ha terminado correctamente.");
        assertEquals("Game won!", spyBoard.getMessage(), "El mensaje no es el esperado.");

        // Verificamos si los mocks han sido llamados en update
        verify(mockplayer).act();
        verify(spyBoard).update_shots();
        verify(spyBoard).update_aliens();
        verify(spyBoard).update_bomb();
    }

    @Test
    public void updateConActPlayerReal() {
        // Creamos una instancia real de la clase Player y Board.
        Player player = spy(new Player());
        Board board = new Board();
        Board spyBoard = spy(board);

        spyBoard.setPlayer(player);

        // Mockeo de los métodos que se van a llamar
        doNothing().when(spyBoard).update_shots();
        doNothing().when(spyBoard).update_aliens();
        doNothing().when(spyBoard).update_bomb();

        spyBoard.setDeaths(Commons.NUMBER_OF_ALIENS_TO_DESTROY);
        spyBoard.update();

        assertFalse(spyBoard.isInGame(), "El juego no ha terminado correctamente.");
        assertEquals("Game won!", spyBoard.getMessage(), "El mensaje no es el esperado.");

        // Verificamos si los métodos reales han sido llamados
        verify(player).act();
        // Verificamos si los mocks han sido llamados en update
        verify(spyBoard).update_shots();
        verify(spyBoard).update_aliens();
        verify(spyBoard).update_bomb();
    }

    @Test
    public void updateConUpdateShotsReal() {
        // Creamos una instancia real de la clase Player y Board.
        Player player = spy(new Player());
        Board board = new Board();
        Board spyBoard = spy(board);

        spyBoard.setPlayer(player);

        //Creamos el shot para probar que se actualiza correctamente
        Shot shot = new Shot(100, 280);
        shot.setVisible(true);
        board.setShot(shot);

        // Mockeo de los métodos que se van a llamar
        doNothing().when(spyBoard).update_aliens();
        doNothing().when(spyBoard).update_bomb();

        spyBoard.setDeaths(Commons.NUMBER_OF_ALIENS_TO_DESTROY);
        spyBoard.update();

        assertFalse(spyBoard.isInGame(), "El juego no ha terminado correctamente.");
        assertEquals("Game won!", spyBoard.getMessage(), "El mensaje no es el esperado.");

        assertAll("Verificar disparo sin colisión",
                () -> assertTrue(board.getShot().isVisible(), "El disparo debería estar visible porque no ha impactado"),

                // Verificamos que los alienígenas siguen visibles
                () -> assertTrue(board.getAliens().get(0).isVisible(), "Alien 1 debería seguir visible porque no ha sido alcanzado"),
                () -> assertTrue(board.getAliens().get(1).isVisible(), "Alien 2 debería seguir visible porque no ha sido alcanzado"),

                // Verificamos que el número de muertes sigue siendo cero
                () -> assertEquals(0, board.getDeaths(), "El número de muertes debería ser 0")
        );
        // Verificamos si los métodos reales han sido llamados
        verify(player).act();
        verify(spyBoard).update_shots();

        // Verificamos si los mocks han sido llamados en update
        verify(spyBoard).update_aliens();
        verify(spyBoard).update_bomb();
    }

    @Test
    public void updateConUpdateAliensReal() {
        // Creamos una instancia real de la clase Player y Board.
        Player player = spy(new Player());
        Board board = new Board();
        Board spyBoard = spy(board);

        spyBoard.setPlayer(player);

        //Guardamos la posición actual de los aliens
        int posX = board.getAliens().get(0).getX();
        board.setDirection(-1);

        // Mockeo de los métodos que se van a llamar
        doNothing().when(spyBoard).update_bomb();

        spyBoard.setDeaths(Commons.NUMBER_OF_ALIENS_TO_DESTROY);
        spyBoard.update();

        assertFalse(spyBoard.isInGame(), "El juego no ha terminado correctamente.");
        assertEquals("Game won!", spyBoard.getMessage(), "El mensaje no es el esperado.");
        assertAll("Verificar cambio de posición",
                () -> assertNotEquals(posX, board.getAliens().get(0).getX()),
                () -> assertTrue(board.getAliens().get(0).getX() > posX, "Movimiento hacia la derecha"),
                () -> System.out.println("Posición antes de llamar al método: " + posX),
                () -> System.out.println("Posición después de llamar al método: " + board.getAliens().get(0).getX())

        );
        // Verificamos si los métodos reales han sido llamados
        verify(player).act();
        verify(spyBoard).update_shots();
        verify(spyBoard).update_aliens();

        // Verificamos si el mock de update_bomb ha sido llamado en update
        verify(spyBoard).update_bomb();
    }

    @Test
    public void updateConUpdateBombReal() {
        // Creamos una instancia real de la clase Player y Board.
        Player player = spy(new Player());
        Board board = new Board();
        Board spyBoard = spy(board);

        Alien alien = board.getAliens().get(0);
        Alien.Bomb bomb = alien.getBomb();
        //Bomba activa
        bomb.setDestroyed(false);
        //Bomba en el suelo
        bomb.setY(Commons.GROUND - Commons.BOMB_HEIGHT);

        spyBoard.setPlayer(player);

        spyBoard.setDeaths(Commons.NUMBER_OF_ALIENS_TO_DESTROY);
        spyBoard.update();

        assertFalse(spyBoard.isInGame(), "El juego no ha terminado correctamente.");
        assertEquals("Game won!", spyBoard.getMessage(), "El mensaje no es el esperado.");
        assertTrue(bomb.isDestroyed(), "La bomba no está destruida");

        // Verificamos si los métodos reales han sido llamados
        verify(player).act();
        verify(spyBoard).update_shots();
        verify(spyBoard).update_aliens();
        verify(spyBoard).update_bomb();
    }
}







