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


    }

}
    class Bender {
        private char[][] tablero;
        private int AlturaMapa;
        private int AnchoMapa;

        public Bender(String mapa) {
            AlturaMapa = NumeroLineas(mapa);
            AnchoMapa = ContarCaracter(mapa);
            tablero = CrearMapa(mapa, AlturaMapa, AnchoMapa);
        }

        private int NumeroLineas(String mapa){
            int contador = 0;
            for (int i = 0; i < mapa.length(); i++) {
                if (mapa.charAt(i) == '\n') {
                    contador++;

                }
            }
            contador++;
            return contador;
        }

        private int ContarCaracter(String mapa){
            int i = 0;
            int caracter = 0;
            while(mapa.charAt(i) != '\n'){

                caracter++;
                i++;
            }
            return caracter;
        }

        private char[][] CrearMapa(String mapa , int altura,int anchura){
            tablero = new char[altura][anchura];
            int p = 0;
            for (int i = 0; i < altura ; i++) {
                for (int j = 0; j < anchura; j++) {
                    tablero[i][j] = mapa.charAt(p);

                }


            }

return null;
        }

    }


