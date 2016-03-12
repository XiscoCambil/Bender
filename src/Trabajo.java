/**
 * Created by fjcambilr on 10/03/16.
 */
public class Trabajo {
    public static void main(String[] args) {
        String mapa = "" +
                "#######\n" +
                "#T    #\n" +
                "#     #\n" +
                "#     #\n" +
                "#  $ T#\n" +
                "#  X###\n" +
                "#I    #\n" +
                "#######";
        Bender bender = new Bender(mapa);

        Bender b = new Bender(mapa);
       System.out.println(b.run());
        System.out.print(b);

    }

}

class Bender {
    private char[][] tablero;
    private final int alturaMapa;
    private final int anchoMapa;
    private final String mapa;
    private  int verticalX;
    private  int horizontalX;


    public Bender(String mapa) {
        this.mapa = mapa;
        alturaMapa = CalcularAltoMapa();
        anchoMapa = CalcularAnchoMapa();
        tablero = CrearMapa();
        int[] posicionX = LocalizarX();
        verticalX = posicionX[0];
        horizontalX = posicionX[1];


    }

    private int CalcularAltoMapa() {
        int contador = 0;
        for (int i = 0; i < mapa.length(); i++) {
            if (mapa.charAt(i) == '\n') {
                contador++;

            }
        }
        contador++;
        return contador;
    }

    private int CalcularAnchoMapa() {
        int i = 0;
        int caracter = 0;
        while (mapa.charAt(i) != '\n') {

            caracter++;
            i++;
        }
        return caracter;
    }

    private char[][] CrearMapa() {
        tablero = new char[alturaMapa][anchoMapa];
        int linea = 0;
        for (int i = 0; i < alturaMapa; i++) {
            for (int j = 0, c = 0; j < anchoMapa; j++, c++) {
                tablero[i][j] = mapa.charAt(c + linea);
            }
            linea += anchoMapa + 1;
        }
        return tablero;
    }

    public String run() {
        String resultado = "";
        String direccion = "SENW";
        int contador = 0;
        boolean obstaculos;
        boolean ganar = false;
        String cambio;
        int contadorInvertir = 0;
        int[] teleporter;
        salir:
        while (!ganar) {
            obstaculos = Obstaculo(direccion.charAt(contador));
            if (tablero[verticalX][horizontalX] == '$') {
                break salir;
            } else if (Teleporter()) {

                teleporter = LocalizacionT();
                verticalX = teleporter[0];
                horizontalX = teleporter[1];

            } else if(Invertir() && contadorInvertir < 1) {

            cambio = direccion.substring(0,2);
                direccion = direccion.substring(2,4);
                direccion += cambio;
                contador = 0;
                contadorInvertir = 1;
                continue;

            }else if (obstaculos) {
                contador = 0;
                while (obstaculos) {
                   obstaculos= Obstaculo(direccion.charAt(contador));
                    contador = (obstaculos) ? contador + 1 : contador;
                }
            }
            resultado += direccion.charAt(contador);
            MoverX(direccion.charAt(contador));
        }

        return resultado;
    }


    private int[] LocalizarX() {
        int[] cordenadas = new int[2];
        salir:
        for (int i = 0; i < alturaMapa; i++) {
            for (int j = 0; j < anchoMapa; j++) {
                if (tablero[i][j] == 'X') {
                    cordenadas[0] = i;
                    cordenadas[1] = j;
                    break salir;
                }
            }
        }
        return cordenadas;
    }

    private boolean Obstaculo(char direccion) {
        switch (direccion) {
            case 'S':
                return tablero[verticalX + 1][horizontalX] == '#';
            case 'E':
                return tablero[verticalX][horizontalX + 1] == '#';
            case 'N':
                return tablero[verticalX - 1][horizontalX] == '#';
            case 'W':
                return tablero[verticalX][horizontalX - 1] == '#';
        }
        return false;
    }

    private void MoverX(char direccion) {
        switch (direccion) {
            case 'S':
                verticalX += 1;
                break;
            case 'E':
                horizontalX += 1;
                break;
            case 'N':
                verticalX -= 1;
                break;
            case 'W':
                horizontalX -= 1;
                break;
        }
    }

    private boolean Teleporter() {
        return tablero[verticalX][horizontalX] == 'T';
    }

    private int[] LocalizacionT() {
        int[] localizacionT = new int[2];
        for (int i = 0; i < alturaMapa; i++) {
            for (int j = 0, c = 0; j < anchoMapa; j++, c++) {
                if (tablero[i][j] == 'T' && i != verticalX && j != horizontalX) {
                    localizacionT[0] = i;
                    localizacionT[1] = j;
                }
            }
        }
        return localizacionT;
    }

    private boolean Invertir(){
        return tablero[verticalX][horizontalX] == 'I';
    }

    @Override
    public String toString() {
        String resultado = "";
        for (int i = 0; i < alturaMapa; i++) {
            for (int j = 0; j < anchoMapa; j++) {
                resultado += tablero[i][j];
            }
            resultado += '\n';
        }
        return resultado;
    }
}
