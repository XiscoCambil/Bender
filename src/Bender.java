/**
 * Created by fjcambilr on 10/03/16.
*/

public class Bender {
    //Variables miembro que van a ser utilizadas en la mayoria de metodos
    private char[][] tablero;
    private final int alturaMapa;
    private final int anchoMapa;
    private final String mapa;
    private int verticalX;
    private int horizontalX;
    private String direccion = "SENW";

    //Constructor Bender
    public Bender(String mapa) {
        this.mapa = mapa;
        //LLamada a funciones para la obtencion de datos
        alturaMapa = CalcularAltoMapa();
        anchoMapa = CalcularAnchoMapa();
        tablero = CrearMapa();
        int[] posicionX = LocalizarX();
        //Valores de la localizacion de X
        verticalX = posicionX[0];
        horizontalX = posicionX[1];
    }

    //Metodo para calcular la altura del mapa
    private int CalcularAltoMapa() {
        int contador = 0;
        for (int i = 0; i < mapa.length(); i++) {
            if (mapa.charAt(i) == '\n') {
                contador++;
            }
        }
        //Sumamos un valor para compensar la ultima linea sin el caracter del salto de linea \n
        contador++;
        return contador;
    }

    //Metodo para calcular el ancho del mapa
    private int CalcularAnchoMapa() {
        int linea = 0;
        int lineaGrande = 0;
        for (int j = 0; j < mapa.length() ; j++) {
            if(mapa.charAt(j) != '\n'){
                linea++;
            }else{
                //Siempre hay que reiniciar la variable lina cuando hay un salto de linea
                if(linea > lineaGrande){
                    lineaGrande = linea;
                    linea = 0;
                }else{
                    linea = 0;
                }
            }
        }
        return lineaGrande;
    }

    //Metodo para crear el mapa en forma de array multidimensional
    private char[][] CrearMapa() {
        tablero = new char[alturaMapa][anchoMapa];
        int linea = 0;
        for (int j = 0; j < alturaMapa ; j++) {
            int i = 0;
            while (mapa.charAt(i+linea) != '\n') {
                //Controlamos si estamos en la ultima fila y en el ultimo caracter del mapa.
                if(j == alturaMapa-1 && i+linea == mapa.length()-1){
                    tablero[j][i] = mapa.charAt(i+linea);
                    break;
                }
                tablero[j][i] = mapa.charAt(i+linea);
                i++;
            }
            //Le sumamos a linea el valor de i mas 1 para evitar introducir el caracter \n
            linea += i+1 ;
        }
        return tablero;
        }

    //Metodo para devolver los pasos que haria para encontrar el caracter $ el robot Bender
    public String run() {
        String resultado = "";
        int contador = 0;
        int pasos = 0;
        //Mientras que no encontremos el caracter $ seguiremos movieendo el robot bender
        while (tablero[verticalX][horizontalX] != '$') {
            //Control para comprobar si hay un bucle en el mapa
             if (pasos > AreaMapa()){
                return "El robot a entrado en bucle";
                 //Control para comprobar si hay un teleporter y aplicarlo
            } else if (HayTeleporter()) {
                LocalizacionT();
                 //COntrol para comprobar si hay un invertir y aplicarlo
            } else if (HayInvertir()) {
                contador = 0;
                direccion = InvertirDireccion();
            }
            //Control para saber si hay un obstaculo y sortearlo.
            if((HayObstaculo(direccion.charAt(contador)))) {
                contador = 0;
                contador = ResolverObstaculo(contador);
            }
            resultado += direccion.charAt(contador);
            //Movemos el robot Bender despues de cada iteracion
            MoverX(direccion.charAt(contador));
            pasos++;
        }
        return resultado;
    }

    //Metodo para Localizar la X dentro del mapa.
    private int[] LocalizarX() {
        int[] cordenadas = new int[2];
        for (int i = 0; i < alturaMapa; i++) {
            for (int j = 0; j < anchoMapa; j++) {
                if (tablero[i][j] == 'X') {
                    //Guardamos las cordenas de altura y anchura respectivamente.
                    cordenadas[0] = i;
                    cordenadas[1] = j;
                    return cordenadas;
                }
            }
        }
       return null;
    }

    //Metodo para mover la x dependiendo de la direccion que tiene que tomar.
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

    //Metodo para saber si hay un obstaculo en la posicion siguiente dependiendo de la direccion.
    private boolean HayObstaculo(char direccion) {
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

    //Metodo para encontrar la direccion correcta cuando hay un obstaculo
    private int ResolverObstaculo(int contador) {
        boolean obstaculos = HayObstaculo(direccion.charAt(contador));
        //Mientras encontremos un obstaculo seguiremos buscando la nueva direccion teniendo en cuenta las prioridades.
        while (obstaculos) {
            obstaculos = HayObstaculo(direccion.charAt(contador));
            //Obtenemos una nueva direccion
            contador = (obstaculos) ? contador + 1 : contador;
        }
        return contador;
    }

    //Metodo para saber si hay un teleporter en la posicion que nos encontramos
    private boolean HayTeleporter() {
        return tablero[verticalX][horizontalX] == 'T';
    }

    //Metodo para localizar el siguiente teleporter y obtener los datos de la posicion
    private void LocalizacionT() {
        for (int i = 0; i < alturaMapa; i++) {
            for (int j = 0; j < anchoMapa; j++) {
                //Si encontramos una T que no sea la misma en la que nos encontramos, movemos la X a dicha posicion.
                if (tablero[i][j] == 'T' && i != verticalX && j != horizontalX) {
                    verticalX = i;
                    horizontalX = j;
                    return;
                }
            }
        }
    }

    //Metodo para saber si estamos sobre un invertir
    private boolean HayInvertir() {
        return tablero[verticalX][horizontalX] == 'I';
    }

    //Metodo que invierte la prioridad de direccion del robot Bender.
    private String InvertirDireccion() {
        String cambio;
        //Selecion de los dos primeros valor de direccion
        cambio = direccion.substring(0, 2);
        //Direccion ahora valdra el valor de los dos ultimos caracteres de direccion
        direccion = direccion.substring(2, 4);
        //Union de las dos secciones para completar el invertido de la prioridad de la direccion
        direccion += cambio;

        return direccion;
    }

    //Obtener el area del mapa.
    private int AreaMapa() {
        int area = 0;
        for (int i = 0; i < alturaMapa; i++) {
            for (int j = 0; j < anchoMapa; j++) {
                //Si el valor es igual a un espacio, entonces al valo del atributo are se le suma 1
                if (tablero[i][j] == ' ') {
                    area++;
                }
            }
        }
        return area;
    }

    //Override del metodo ToString para imprimir la array en forma de String.
    @Override
    public String toString() {
        String resultado = "";
        for (int i = 0; i < alturaMapa; i++) {
            for (int j = 0; j < anchoMapa; j++) {
                resultado += tablero[i][j];
            }
            //Añadimos un salto de linea en cada iteracion.
            resultado += '\n';
        }
        return resultado;
    }
}


