package testIntegracion;

import space_invaders.main.*;
import space_invaders.sprites.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class UpdateAliensIT {

    @BeforeEach
    public void setUp(){

    }

    @Test
    public void update_aliensStub(){
        // Inicializacion de mock para Alien
        Alien alien = spy(new Alien(100,0));
        //array para inicializar alien
        ArrayList <Alien> aliens = new ArrayList<>();
        aliens.add(alien);
        // Crea una instancia real de la clase Board.
        Board board = new Board();
        Board spyBoard = spy(board);

        // Mockeo del método act(direction)
        doNothing().when(alien).act(-1);


        // Añadimos el mockAlien a la lista de aliens del spyBoard
        spyBoard.setAliens(aliens);

        // Ejecutamos el método update_aliens
        spyBoard.update_aliens();

        // Verificamos que hemos llamado al mock de act
        verify(alien).act(-1);
    }

    @Test
    public void update_aliensConActReal(){
        // Crea una instancia real de la clase Board.
        Board board = new Board();
        Board spyBoard = spy(board);
        //array para inicializar alien
        ArrayList <Alien> aliens = new ArrayList<>();


        // Inicializacion de un Alien de verdad
        Alien alien = spy(new Alien(100,0));
        aliens.add(alien);
        spyBoard.setAliens(aliens);
        // Ejecutamos el método update_aliens
        spyBoard.update_aliens();

        // Verificamos si se ha llamado al act del alien de verdad con el alien
        verify(alien).act(-1);
    }
}
