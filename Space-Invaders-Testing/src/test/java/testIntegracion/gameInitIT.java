package testIntegracion;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import space_invaders.main.*;
import space_invaders.sprites.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;



public class gameInitIT {

    @BeforeEach
    public void setUp(){

    }

    @Test
    public void gameInitStubs() throws Exception {
        // Inicialización de mocks
        Alien mockAlien = mock(Alien.class);
        Player mockPlayer = mock(Player.class);
        Shot mockShot = mock(Shot.class);

        // Mockeamos los constructores de Alien, Player y Shot
        PowerMockito.whenNew(Alien.class).withAnyArguments().thenReturn(mockAlien);
        PowerMockito.whenNew(Player.class).withNoArguments().thenReturn(mockPlayer);
        PowerMockito.whenNew(Shot.class).withNoArguments().thenReturn(mockShot);

        // Creamos una instancia de Board
        Board board = PowerMockito.spy(new Board());

        // Ejecuta el método gameInit
        board.gameInit();

        // Verificamos que el número de aliens es correcto y que Player y Shot no son nulos
        assertEquals(24, board.getAliens().size());
        assertNotNull(board.getPlayer());
        assertNotNull(board.getShot());


    }

    @Test
    public void gameInitConAlienReal() throws Exception {
        ArrayList <Alien> aliens = new ArrayList<>();
        // Inicialización de mocks
        Player mockPlayer = mock(Player.class);
        Shot mockShot = mock(Shot.class);

        // Mockeamos los constructores de  Player y Shot (el alien se llamará al real)
        PowerMockito.whenNew(Player.class).withNoArguments().thenReturn(mockPlayer);
        PowerMockito.whenNew(Shot.class).withNoArguments().thenReturn(mockShot);
        // Creamos una instancia de board, con aliens vacios
        Board board = new Board();
        board.setAliens(aliens);
        // Ejecuta el método
        board.gameInit();

        // Verificamos que el numero de aliens es correcto y el Player y el Shot no son nulos
        assertEquals(24, board.getAliens().size());
        assertNotNull(board.getPlayer()); assertNotNull(board.getShot());
    }
    @Test
    public void gameInitConPlayerReal() throws Exception {
        ArrayList <Alien> aliens = new ArrayList<>();
        // Inicialización el mock de Shot (último stub)
        Shot mockShot = mock(Shot.class);

        // Mockeamos los constructores de  Player y Shot (el alien se llamará al real)
        PowerMockito.whenNew(Shot.class).withNoArguments().thenReturn(mockShot);
        // Creamos una instancia de board, con aliens vacios
        Board board = new Board();
        board.setAliens(aliens);
        // Ejecuta el método
        board.gameInit();

        // Verificamos que el numero de aliens es correcto y el Player y el Shot no son nulos
        assertEquals(24, board.getAliens().size());
        assertNotNull(board.getPlayer()); assertNotNull(board.getShot());
    }
    @Test
    public void gameInitConShotReal() throws Exception {
        ArrayList <Alien> aliens = new ArrayList<>();
        // No inicializamos mocks ya que se ejecutarán todos los contructores reales
        // Creamos una instancia de board,con aliens vacios
        Board board = new Board();
        board.setAliens(aliens);
        // Ejecuta el método
        board.gameInit();

        // Verificamos que el numero de aliens es correcto y el Player y el Shot no son nulos
        assertEquals(24, board.getAliens().size());
        assertNotNull(board.getPlayer()); assertNotNull(board.getShot());
    }

        
}

