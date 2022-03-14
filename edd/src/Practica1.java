package Clases;

import java.util.Iterator;

//Alumno: Reyes López Eduardo Alfonso
public class Practica1 {



  /**
   * Dado un ejemplar de nuestra clase Lista ordenado se le agrega un elemen-
   * to de manera ordenada.
   *
   * Tiempo: O(n). Contiene un único while que en una iteración cuando el
   * elemento es mayor a los anteriores en la lista, se inserta en el lugar
   * según la cantidad de elementos que ya pasó.
   *
   * Espacio: O(1). No crea otra lista o colección que se quede en memoria
   * ocupando espacio.
   *
   * @param lista Es la lista a agregarle el elemento.
   * @param nuevo el entero a agregar según su valor en la lista.
   */
    public static Lista<Integer> AgregaOrdenado(Lista<Integer> lista, int nuevo) {
        int count = 0;
        IteradorLista<Integer> iter = lista.iteradorLista();
        while(iter.hasNext()) {
          if(nuevo <= iter.next()) {
              lista.insert(count,nuevo);
              return lista;
          }
          count++;
        }
        return null;
    }

    /**
     * Dados dos ejemplares de nuestra clase Lista obtenemos la unión de estos.
     * Se considera que no hay duplicados en lista1 y lista 2.
     * No se regresa en un orden específico más allá de la operatividad
     * del algoritmo.
     *
     * Una posible mejora al método consistiría en primero unir ambas listas como
     * con el método append o similar que haga una especie de "merge" a las listas,
     * porque toma tiempo constante. Y ya después algún método podría verificar
     * si existen duplicados. Como es una sola lista, y presuponemos que en una
     * misma lista no hay duplicados, la verificación podría comenzar a partir de
     * la longitud de la primera lista. Es más óptimo, porque el método insert
     * que uso aquí toma tiempo inicialmente n, y va incrementando conforme se
     * le agregan más elementos. Por lo que tener ya todo en una misma lista
     * podría llegar a ser más conveniente.
     *
     * Tiempo: O(n*m).  Con n el tamaño de lista 1 y m de lista2.
     * Dentro del ciclo for, para evaluar la condicional
     * if, se llama al método contains, que a su vez llama al método
     * buscaElemento que recorre lista1 con n elementos hasta encontrar
     * la coincidencia de existir, que en el peor caso es n lugares.
     * Como el iterador sobre lista2 hace m ciclos por sus m elementos,
     * la complejidad resulta en n*m.
     *
     * Espacio: O(n+m). La lista resultante de la unión mínimo tiene n
     * elementos de la lista1, ya que consideramos que no hay duplicados.
     * Como en el peor caso no hay elementos repetidos entre ambas listas,
     * la longitud final de la lista es n+m (espacio en memoria).
     *
     * @param lista1 es la lista sobre la que se hará la unión.
     * @param lista2 es la lista a unir con lista 1.
     */
    public static void Union(Lista<Integer> lista1,Lista<Integer> lista2) {
        IteradorLista<Integer> iter2 = lista2.iteradorLista();
        while(iter2.hasNext()) {
          Integer int2 = iter2.next();
          if(!lista1.contains(int2)) {
            lista1.add(int2);
          }
        }
        return;
    }

    /**
     * Dados dos ejemplares de nuestra clase Lista obtenemos la intersección de estos.
     * Se considera que no hay duplicados en lista y lista 2.
     * No se regresa en un orden específico más allá de la operatividad
     * del algoritmo.
     *
     * La única mejora que se me ocurre con esta estructura, podría ser
     * pedir desde un inicio que las listas estén ordenadas de menor a mayor
     * lo que nos permitiría tomar la lista con el intervalo de números más
     * chico, y quitar los elementos fuera de ese rango para mo iterarlos
     * al buscar coindidencias.
     * Por ejemplo, tomemos: lista1 = 1 -> 2 -> 3 -> 4 -> 5 -> 9 -> 20
     * lista2 = 3 -> 5. Entonces quitamos los elementos de list1 que sean
     * menores qie 3 y mayores que 5 para quitarlos de las iteraciones en
     * la búsqueda de contains().
     * Es sólo una mejora al considerar que están ordenadas, de otra forma
     * habría que considerar la complejidad de un algoritmo de ordenamiento.
     *
     * Tiempo: O(n*m). Con n el tamaño de lista 1 y m de lista2.
     * Dentro del ciclo for, para evaluar la condicional
     * if, se llama al método contains, que a su vez llama al método
     * buscaElemento que recorre lista2 con m elementos hasta encontrar
     * la coincidencia de existir, que en el peor caso es m lugares.
     * Como el iterador sobre lista hace n ciclos por sus n elementos,
     * la complejidad resulta en n*m.
     *
     * Espacio: O(n) si n > m. De lo contrario es O(m).
     * Como la lista tiene n elementos, y le vamos quitando aquellos
     * que no comparta con la lista 2, el espacio final en memoria,
     * en el peor caso donde ambas listas son iguales, es que su
     * complejidad sea O(n).
     *
     * @param lista es la lista sobre la que se hará la intersección.
     * @param lista2 es la lista a intersectar con lista 1.
     */
    public static void Interseccion(Lista<Integer> lista,Lista<Integer> lista2) {
      IteradorLista<Integer> iter = lista.iteradorLista();
      while(iter.hasNext()) {
        Integer elemento = iter.next();
        if(!lista2.contains(elemento)) {
          lista.delete(elemento);
        }
      }
      return ;
    }



    public static void main(String[] args) {
        Lista<Integer> primera = new Lista<Integer>();
        Lista<Integer> segunda = new Lista<Integer>();
        Lista<Integer> tercera = new Lista<Integer>();


        // Tests toString
        for (int i = 0; i <= 5; i++) {
            primera.add(i);
        }

        String test = "0 -> 1 -> 2 -> 3 -> 4 -> 5";
        if (!primera.toString().equals(test)) {
            System.out.println("1 El toString no funciona!");
        }
        primera = new Lista<Integer>();
        if (!primera.toString().equals("")) {
            System.out.println("2 El toString no funciona!");
        }

        // Tests Reverse
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();

        for (int i = 0; i <= 10; i++) {
            primera.add(i);
            segunda.agregaInicio(i);
        }

        primera.reverse();
        if (!primera.toString().equals(segunda.toString())) {
            System.out.println("1 El reverse no funciona!");
        }
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        primera.reverse();
        if (!primera.toString().equals(segunda.toString())) {
            System.out.println("2 El reverse no funciona!");
        }

        // Tests Append
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        for (int i = 0; i <= 10; i++) {
            primera.add(i);
            segunda.add(i);
        }
        for (int i = 0; i <= 10; i++) {
            segunda.add(i);
        }
        primera.append(primera.clone());

        if (!primera.toString().equals(segunda.toString())) {
            System.out.println("1 El Append no funciona!");
        }

        // Tests IndexOf
        if (primera.indexOf(0) != 0) {
            System.out.println("1 El IndexOf no funciona!");
        }
        if (primera.indexOf(1) != 1) {
            System.out.println("2 El IndexOf no funciona!");
        }
        if (primera.indexOf(10) != 10) {
            System.out.println("3 El IndexOf no funciona!");
        }

        // Tests Insert
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        for (int i = 0; i <= 10; i++) {
            primera.add(i);

        }
        for (int i = 0; i <= 4; i++) {
            segunda.add(i);

        }
        segunda.add(6);
        for (int i = 5; i <= 10; i++) {
            segunda.add(i);

        }

        primera.insert(5, 6);
        if (!primera.toString().equals(segunda.toString())) {
            System.out.println("1 El insert no funciona!");
        }

        // Tests Mezcla Alternada
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        for (int i = 0; i <= 10; i++) {
            if (i % 2 == 0) {
                primera.add(i);
            }
        }
        primera.add(11);
        for (int i = 0; i <= 10; i++) {
            if (i % 2 != 0) {
                segunda.add(i);
            }

        }
        for (int i = 0; i <= 11; i++) {

                tercera.add(i);

        }

        primera.mezclaAlternada(segunda);
        if (!primera.toString().equals(tercera.toString())) {
            System.out.println("1 la mezclaAlternada no funciona!");
        }

        // Tests Agrega Ordenado
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        for (int i = 0; i <= 10; i++) {
            primera.add(i);
        }
        for (int i = 0; i <= 9; i++) {
            segunda.add(i);
        }
        segunda.add(9);
        segunda.add(10);

        tercera = AgregaOrdenado(primera,9);
        if (!tercera.toString().equals(segunda.toString())) {
            System.out.println("1 el agregaOrdenado no funciona!");
        }

        // Tests Union
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        tercera = new Lista<Integer>();
        primera.add(1);
        primera.add(2);
        primera.add(3);
        segunda.add(2);
        Union(primera, segunda);

        if (!(primera.contains(1) && primera.contains(2) && primera.contains(3) && primera.size() == 3)) {
            System.out.println("1 La union no funciona!");
        }

        // Tests interseccion
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        tercera = new Lista<Integer>();
        primera.add(1);
        primera.add(2);
        primera.add(3);
        segunda.add(2);
        Interseccion(primera, segunda);

        if (!(primera.contains(2) && primera.size() == 1)) {
            System.out.println("1 La intersección no funciona!");
        }





    }





}
