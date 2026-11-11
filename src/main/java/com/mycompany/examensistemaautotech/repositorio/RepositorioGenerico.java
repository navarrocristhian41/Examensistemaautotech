/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examensistemaautotech.repositorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
/**
 *
 * @author Cristhian Navarro
 */


public class RepositorioGenerico<T> {
    private List<T> listaElementos;
    private HashMap<String, T> mapaElementos;
    private Stack<EstadoRepositorio<T>> pilaDeshacer;

    private static class EstadoRepositorio<T> {
        List<T> listaCopia;
        HashMap<String, T> mapaCopia;

        public EstadoRepositorio(List<T> lista, HashMap<String, T> mapa) {
            this.listaCopia = new ArrayList<>(lista);
            this.mapaCopia = new HashMap<>(mapa);
        }
    }

    public RepositorioGenerico() {
        this.listaElementos = new ArrayList<>();
        this.mapaElementos = new HashMap<>();
        this.pilaDeshacer = new Stack<>();
    }

    private void guardarEstado() {
        pilaDeshacer.push(new EstadoRepositorio<>(listaElementos, mapaElementos));
    }

    public void agregar(String claveUnica, T elemento) {
        guardarEstado();
        listaElementos.add(elemento);
        mapaElementos.put(claveUnica, elemento);
    }

    public void eliminar(String claveUnica) {
        if (mapaElementos.containsKey(claveUnica)) {
            guardarEstado();
            T elemento = mapaElementos.remove(claveUnica);
            listaElementos.remove(elemento);
        }
    }

    public T buscarPorClave(String claveUnica) {
        return mapaElementos.get(claveUnica);
    }

    public List<T> obtenerTodos() {
        return new ArrayList<>(listaElementos);
    }

    public boolean deshacerOperacion() {
        if (!pilaDeshacer.isEmpty()) {
            EstadoRepositorio<T> estadoAnterior = pilaDeshacer.pop();
            this.listaElementos = estadoAnterior.listaCopia;
            this.mapaElementos = estadoAnterior.mapaCopia;
            return true;
        }
        return false;
    }
}
