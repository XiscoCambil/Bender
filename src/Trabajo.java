/**
 * Created by fjcambilr on 10/03/16.
 */
public class Trabajo {
    public static void main(String[] args) {

        String mapa =
                "" +
                "#######\n" +
                "# X   #\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "# $   #\n" +
                "#     #\n" +
                "#######";
        Bender b = new Bender(mapa);
           System.out.print(b);


    }

}

class Bender {
    private char[][] tablero;
    private final int alturaMapa;
    private final int anchoMapa;
    private final String mapa;


    public Bender(String mapa) {
        this.mapa = mapa;
        alturaMapa = CalcularAltoMapa();
        anchoMapa = CalcularAnchoMapa();
        tablero = CrearMapa();


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
        boolean ganar = false;
        while (!ganar) {



        }

        return "";
    }


    private int[] PosicionX() {
        int[] cordenadas = new int[2];
        salir:
        for (int i = 0; i < alturaMapa; i++) {
            for (int j = 0; j < anchoMapa; j++) {
                if (tablero[i][j] != 'X') {
                    continue;
                } else {
                    cordenadas[0] = i;
                    cordenadas[1] = j;
                    break salir;
                }
            }

        }
        return cordenadas;
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
