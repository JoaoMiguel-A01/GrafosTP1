/**
 * Classe que representa um vértice em um grafo.
 * 
 * Pontifícia Universidade de Minas Gerais
 * Instituto de Ciências Exatas e Informática
 * Engenharia de Computação
 * Última Atualização: 30/03/2024
 * Integrantes: David Lanna de Moraes, João Miguel de Abreu Constâncio, 
 * Paulo Eder Medeiros Cardoso e Paulo Ricardo Ferreira Gualberto.
 */

 import java.util.ArrayList;

 public class Vertice<TIPO> {
     private TIPO dado;
     private ArrayList<Aresta<TIPO>> arestasEntrada;
     private ArrayList<Aresta<TIPO>> arestasSaida;
     
     public Vertice(TIPO valor){
         this.dado = valor;
         this.arestasEntrada = new ArrayList<Aresta<TIPO>>();
         this.arestasSaida = new ArrayList<Aresta<TIPO>>();
     }
     
     public TIPO getDado() {
         return dado;
     }
     
     public void setDado(TIPO dado) {
         this.dado = dado;
     }
     
     public void adicionarArestaEntrada(Aresta<TIPO> aresta){
         this.arestasEntrada.add(aresta);
     }
     
     public void adicionarArestaSaida(Aresta<TIPO> aresta){
         this.arestasSaida.add(aresta);
     }
     
     public void removerArestaEntrada(Aresta<TIPO> aresta){
         this.arestasEntrada.remove(aresta);
     }
   
     public void removerArestaSaida(Aresta<TIPO> aresta){
         this.arestasSaida.remove(aresta);
     }
     
     public ArrayList<Aresta<TIPO>> getArestasEntrada() {
         return arestasEntrada;
     }
     
     public ArrayList<Aresta<TIPO>> getArestasSaida() {
         return arestasSaida;
     }
     
     // Método para retornar os sucessores do vértice
     public ArrayList<Vertice<TIPO>> getSucessores() {
         ArrayList<Vertice<TIPO>> sucessores = new ArrayList<>();
         for (Aresta<TIPO> aresta : arestasSaida) {
             sucessores.add(aresta.getFim());
         }
         return sucessores;
     }
     
     // Método para retornar os predecessores do vértice
     public ArrayList<Vertice<TIPO>> getPredecessores() {
         ArrayList<Vertice<TIPO>> predecessores = new ArrayList<>();
         for (Aresta<TIPO> aresta : arestasEntrada) {
             predecessores.add(aresta.getInicio());
         }
         return predecessores;
     }
 }
 