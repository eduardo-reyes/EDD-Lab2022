package Clases;

import java.util.Iterator;
import java.util.NoSuchElementException;
// iterador
//next

public class Lista<T> implements Collection<T> {

    // Clase Nodo
    private class Nodo {
        public T elemento;
        public Nodo anterior;
        public Nodo siguiente;

        public Nodo(T elemento){
            this.elemento = elemento;
        }
    }

    // Iterador
    private class Iterador implements IteradorLista<T> {
        public Nodo anterior;
        public Nodo siguiente;

        public Iterador(){
            siguiente = cabeza;
        }

        @Override public boolean hasNext(){
            return siguiente != null;
        }

        @Override public T next(){
            if(!hasNext())
                throw new NoSuchElementException();
            T regresar = siguiente.elemento;

            this.anterior = this.siguiente ;
            this.siguiente=siguiente.siguiente;
            return regresar;

        }

        @Override
        public boolean hasPrevious() {
            return anterior != null;
        }

        @Override
        public T previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();
            T regresar = anterior.elemento;

            this.siguiente = this.anterior;
            this.anterior = anterior.anterior;
            return regresar;

        }

        @Override
        public void start(){
            this.anterior = null;
            this.siguiente = cabeza;
        }

        @Override
        public void end() {
            this.anterior = ultimo;
            this.siguiente = null;
        }

    }

    private Nodo cabeza;
    private Nodo ultimo;
    private int longi;

    /**
     * Agrega un elemento a la lista.
     *
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    @Override
    public void add(T elemento){
        if(elemento == null){
            throw new IllegalArgumentException("El elemento es null");
        }
        agregaFinal(elemento);
    }


    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaInicio(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("El elemento es null");
        }
        Nodo nuevo = new Nodo(elemento);
        if (cabeza == null) {
            this.cabeza = this.ultimo = nuevo;
        } else {
            this.cabeza.anterior = nuevo;
            nuevo.siguiente = this.cabeza;
            this.cabeza = nuevo;
        }
        longi++;
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaFinal(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("El elemento es null");
        }
        Nodo nuevo = new Nodo(elemento);
        if(cabeza == null){
            this.cabeza = this.ultimo = nuevo;
        }
        else{
            this.ultimo.siguiente = nuevo;
            nuevo.anterior = this.ultimo;
            this.ultimo = nuevo;
        }
        longi++;
    }

    private Nodo buscaElemento(T elemento){
        Nodo n = cabeza;
        while(n !=null){
            if (elemento.equals(n.elemento)) {
                return n;
            }
            n=n.siguiente;
        }
        return null;
    }

    /**
     * Elimina un elemento de la lista.
     *
     * @param elemento el elemento a eliminar.
     */
    public boolean delete(T elemento){
        if(elemento == null)
            return false;
        Nodo n = buscaElemento(elemento);
        if(n==null){
            return false;
        }
        if(longi == 1){
            empty();
            return true;
        }
        if (n == cabeza) {
            cabeza = cabeza.siguiente;
            cabeza.anterior = null;
            longi --;
            return true;
        }
        if (n == ultimo) {
            ultimo = ultimo.anterior;
            ultimo.siguiente = null;
            longi --;
            return true;
        }
        n.siguiente.anterior = n.anterior;
        n.anterior.siguiente = n.siguiente;
        longi --;
        return true;
    }



    /**
     * Regresa un elemento de la lista. (Ultimo)
     * y lo elimina.
     *
     * @return El elemento a sacar.
     */
    public T pop(){
        T valor = ultimo.elemento;
        ultimo = ultimo.anterior;
        ultimo.siguiente = null;
        longi --;
        return valor;
    }

    /**
     * Regresa el número de elementos en la lista.
     *
     * @return el número de elementos en la lista.
     */
    public int size(){
        return longi;
    }

    /**
     * Nos dice si un elemento está contenido en la lista.
     *
     * @param elemento el elemento que queremos verificar si está contenido en
     *                 la lista.
     * @return <code>true</code> si el elemento está contenido en la lista,
     *         <code>false</code> en otro caso.
     */
    public boolean contains(T elemento){
        if(buscaElemento(elemento) == null){
            return false;
        }
        return true;
    }

    /**
     * Vacía la lista.
     *
     */
    public void empty(){
        cabeza = ultimo = null;
        longi = 0;
    }

    /**
     * Nos dice si la lista es vacía.
     *
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    public boolean isEmpty(){
        return longi == 0;
    }



    /**
     * Regresa una copia de la lista.
     *
     * @return una copia de la lista.
     */
    public Lista<T> clone() {
        Lista<T> nueva = new Lista<T>();
        Nodo nodo = cabeza;
        while (nodo != null) {
            nueva.add(nodo.elemento);
            nodo = nodo.siguiente;
        }
        return nueva;
    }

    /**
     * Nos dice si la coleccion es igual a otra coleccion recibida.
     *
     * @param coleccion la coleccion con el que hay que comparar.
     * @return <tt>true</tt> si la coleccion es igual a la coleccion recibido
     *         <tt>false</tt> en otro caso.
     */
    public boolean equals(Collection<T> coleccion){
        // lo vemos en clase
        if(coleccion instanceof Lista) {
            return true;
        }
        return false;
    }

    /**
     * Regresa un elemento de la lista. (Primero)
     * y lo elimina.
     *
     * @return El elemento a sacar.
     */
    private T popInicio(){
        if(cabeza == null) throw new IllegalArgumentException("La lista está vacía.");
        T valor = cabeza.elemento;
        cabeza = cabeza.siguiente;
        if(cabeza == null) {
          longi --;
          return valor;
        }
        else {
          cabeza.anterior = null;
          longi --;
          return valor;
        }
    }

    /**
     * Metodo que invierte el orden de la lista .
     *
     */
    public void reverse() {
        /* Lista<T> reversa = new Lista<T>();
        Nodo nodo = cabeza;
        while(nodo != null) {
          reversa.agregaInicio(nodo.elemento);
          nodo = nodo.siguiente;
        }
        empty();
        while (!reversa.isEmpty()) {
            add(reversa.popInicio());
        } */
        if (isEmpty()) return;
        int count = 0;
        T elem = popInicio();
        Nodo nuevo = new Nodo(elem);
        ultimo.siguiente = nuevo;
        nuevo.anterior = ultimo;
        ultimo = nuevo;
        longi++;
        Nodo nodo = ultimo;
        while(count < longi-2) {
          elem = popInicio();
          nuevo = new Nodo(elem);
          nuevo.anterior = nodo.anterior;
          nuevo.siguiente = nodo;
          nodo.anterior.siguiente = nuevo;
          nodo.anterior = nuevo;
          nodo = nuevo;
          longi++;
          count++;
        }
    }

    /**
     * Regresa una representación en cadena de la coleccion.
     *
     * @return una representación en cadena de la coleccion.
     * a -> b -> c -> d
     */
    public String toString() {
        if (isEmpty()) return "";
        String lista = "";
        Nodo n = cabeza;
        while(n.siguiente != null) {
          lista += n.elemento.toString() + " -> ";
          n = n.siguiente;
        }
        lista += n.elemento.toString();
        return lista;
    }

    /**
     * Regresa la cabeza de una lista.
     *
     * @return el nodo cabeza.
     */
    private Nodo getCabeza() {
      return this.cabeza;
    }

    /**
     * Junta dos listas siempre y cuando sean del mismo tipo.
     *
     */
    public void append(Lista<T> lista) {
      if(lista instanceof Lista) {
          ultimo.siguiente = lista.getCabeza();
      }
    }

    /**
     * Regresa un entero con la posicion del elemento.
     * Solo nos importara la primera aparición del elemento
     * Empieza a contar desde 0.
     *
     * @param elemento elemento del cual queremos conocer la posición.
     * @return entero con la posicion del elemento
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public int indexOf(T elemento) {
        int count = 0;
        if(contains(elemento)) {
          Nodo n = cabeza;
          while(n !=null){
              if (elemento.equals(n.elemento)) {
                return count;
              }
              count++;
              n = n.siguiente;
            }
        }
        return 0;
    }

    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor que cero, el elemento se agrega al inicio de la
     * lista. Si el índice es mayor o igual que el número de elementos en la
     * lista, el elemento se agrega al fina de la misma. En otro caso, después
     * de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     *
     * @param i        el índice dónde insertar el elemento. Si es menor que 0 el
     *                 elemento se agrega al inicio, y si es mayor o igual que el
     *                 número
     *                 de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    public void insert(int i, T elemento) {
        if(i < 0) {
          agregaInicio(elemento);
          longi++;
        } else if(i >= longi) {
          agregaFinal(elemento);
          longi++;
        } else {
          int count = 1;
          Nodo n = cabeza;
          while(count < i) {
            count++;
            n = n.siguiente;
          }
          Nodo nuevo = new Nodo(elemento);
          n.siguiente.anterior = nuevo;
          nuevo.siguiente = n.siguiente;
          nuevo.anterior = n;
          n.siguiente = nuevo;
          longi++;
        }

        return ;
    }

    // Tu comentario
    public void mezclaAlternada(Lista<T> lista) {
      Nodo nodo = cabeza;
      Nodo nuevo;
      int count = 0;
      while(count < longi-1) {
        if(!lista.isEmpty()){
          T elem = lista.popInicio();
          System.out.println("Elemento: " + elem);
          nuevo = new Nodo(elem);
          nuevo.anterior = nodo;
          nuevo.siguiente = nodo.siguiente;
          nodo.siguiente.anterior = nuevo;
          nodo.siguiente = nuevo;
          nodo = nuevo.siguiente;
          count++;
        }
        else break;
      }
    }

    /**
     * Regresa un iterador para recorrer la lista en una dirección.
     * @return un iterador para recorrer la lista en una dirección.
     */
    public Iterator<T> iterator() {
        return new Iterador();
    }

    /**
     * Regresa un iterador para recorrer la lista en ambas direcciones.
     * @return un iterador para recorrer la lista en ambas direcciones.
     */
    public IteradorLista<T> iteradorLista() {
        return new Iterador();
    }
}
