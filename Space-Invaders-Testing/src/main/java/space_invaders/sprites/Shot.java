package space_invaders.sprites;

import space_invaders.main.Commons;

import javax.swing.ImageIcon;

public class Shot extends Sprite{

    public Shot() {
    }
    /**
     * Inicializa un nuevo objeto disparo en las coordenadas indicadas
     * @param x coordenada X de la posición del nuevo disparo
     * @param y coordenada Y de la posición del nuevo disparo
     * */
    public Shot(int x, int y) {

        initShot(x, y);
    }
    /**
     * Inicializa un nuevo objeto disparo en las coordenadas indicadas y le asigna la imagen correspondiente en la interfaz
     * @param x coordenada X de la posición del nuevo disparo
     * @param y coordenada Y de la posición del nuevo disparo
     * Si alguna de las coordenadas indicadas es mayor al máximo permitido, se le asignará el valor máximo permitido.
     * Si se introducen valores negativos de coordenada, se asignará el mínimo permitido (0).
     * */
    private void initShot(int x, int y) {
        var shotImg = "src/main/resources/images/shot.png";
        var ii = new ImageIcon(shotImg);
        setImage(ii.getImage());

        int H_SPACE = 6;
        int V_SPACE = 1;

        //Añadido la comprobación de los valores inciales
        if(x> Commons.BOARD_WIDTH-H_SPACE) {
            setX(Commons.BOARD_WIDTH);
        }else if(x<0-H_SPACE) {
            setX(0);
        }else{
            setX(x + H_SPACE);
        }

        //Añadido la comprobación de los valores inciales
        if(y> Commons.BOARD_HEIGHT+V_SPACE) {
            setY(Commons.BOARD_HEIGHT);
        }else if(y<0+V_SPACE) {
            setY(0);
        }else{
            setY(y - V_SPACE);
        }
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
    }
}

