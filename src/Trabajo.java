/**
 * Created by fjcambilr on 10/03/16.
 */
public class Trabajo {
    public static void main(String[] args) {
        String mapa = "" +
                "#######\n" +
                "# X   #\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "# $   #\n" +
                "#     #\n" +
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
    private int verticalX;
    private int horizontalX;
    private String direccion = "SENW";

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
        int caracter = 0;
        int caracter2 = 0;
        for (int j = 0; j < mapa.length() ; j++) {
            if(mapa.charAt(j) != '\n'){
                caracter++;
            }else{
                if(caracter > caracter2){
                    caracter2 = caracter;
                    caracter = 0;
                }else{
                    caracter = 0;
                }
            }
        }
        return caracter2;
    }

    private char[][] CrearMapa() {
        tablero = new char[alturaMapa][anchoMapa];
        int linea = 0;
        for (int j = 0; j < alturaMapa ; j++) {
            int i = 0;
            int c = 0;
            while (mapa.charAt(i+linea) != '\n') {
                if(j == alturaMapa-1 && i+linea == mapa.length()-1){
                    tablero[j][c] = mapa.charAt(i+linea);
                    break;
                }
                tablero[j][c] = mapa.charAt(i+linea);
                i++;
                c++;
            }
            linea += c+1 ;
        }
        return tablero;
        }

    public String run() {
        String resultado = "";
        int contador = 0;
        int pasos = 0;
        salir:
        while (true) {
            if (tablero[verticalX][horizontalX] == '$') {
                break salir;
            }else if (pasos > AreaMapa()){
                return "El robot a entrado en bucle";
            } else if (Teleporter()) {
                LocalizacionT();
            } else if (Invertir()) {
                contador = 0;
                direccion = InvertirDireccion();
            }
            if((Obstaculo(direccion.charAt(contador)))) {
                contador = 0;
                contador = ResolverObstaculo(contador);
            }
            resultado += direccion.charAt(contador);
            MoverX(direccion.charAt(contador));
            pasos++;
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

    private int ResolverObstaculo(int contador) {
        boolean obstaculos = Obstaculo(direccion.charAt(contador));
        while (obstaculos) {
            obstaculos = Obstaculo(direccion.charAt(contador));
            contador = (obstaculos) ? contador + 1 : contador;
        }
        return contador;
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

    private void LocalizacionT() {
        for (int i = 0; i < alturaMapa; i++) {
            for (int j = 0, c = 0; j < anchoMapa; j++, c++) {
                if (tablero[i][j] == 'T' && i != verticalX && j != horizontalX) {
                    verticalX = i;
                    horizontalX = j;
                    return;
                }
            }
        }
    }

    private boolean Invertir() {
        return tablero[verticalX][horizontalX] == 'I';
    }

    private String InvertirDireccion() {
        String cambio;
        cambio = direccion.substring(0, 2);
        direccion = direccion.substring(2, 4);
        direccion += cambio;

        return direccion;
    }

    private int AreaMapa() {
        int area = 0;
        for (int i = 0; i < alturaMapa; i++) {
            for (int j = 0; j < anchoMapa; j++) {
                if (tablero[i][j] == ' ') {
                    area++;
                }
            }
        }
        return area;
    }
    //Es es una rama
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


