/**
 * Algoritmos em Grafos
 * Pontifícia Universidade de Minas Gerais
 * Instituto de Ciências Exatas e Informática
 * Engenharia de Computação
 * Última Atualização: 30/03/2024
 * Integrantes: David Lanna de Moraes, João Miguel de Abreu Constâncio, 
 * Paulo Eder Medeiros Cardoso e Paulo Ricardo Ferreira Gualberto. 
 */

 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.HashSet;
 import java.util.LinkedList;
 import java.util.Queue;
 import java.util.Scanner;
 import java.util.Set;
// import java.util.Stack;
 //import java.util.ArrayDeque;
 import java.util.HashMap;
//import java.util.HashSet;
 import java.util.Map;
 //import java.util.Queue;
 //import java.util.Set;
 
 
 public class Grafo<TIPO> {
     private int numvertices;
     private ArrayList<Vertice<TIPO>> vertices;
     private ArrayList<Aresta<TIPO>> arestas;
     Scanner sc = new Scanner (System.in);
 
     /**
      * Construtor da classe Grafo. Inicializa as listas de vértices e arestas.
      */
     public Grafo(){
 
         this.vertices = new ArrayList<Vertice<TIPO>>();
         this.arestas = new ArrayList<Aresta<TIPO>>();
 
     }
 
      //Adiciona a quantidade de vertices do grafo
 
     void addnumvertices(int numerovertices) {
 
         this.numvertices = numerovertices;
 
     }
 
     // Adiciona um novo vertice ao grafo.
 
     public void adicionarVertice(TIPO dado){
         Vertice<TIPO> novoVertice = new Vertice<TIPO>(dado);
         this.vertices.add(novoVertice);
     }
 
      //Adiciona uma aresta direcionada com peso entre dois vertices.
 
     public void adicionarArestaDir(Double peso, TIPO dadoInicio, TIPO dadoFim){
 
         Vertice<TIPO> inicio = this.getVertice(dadoInicio);
         Vertice<TIPO> fim = this.getVertice(dadoFim);
 
 
         if(inicio != null && fim != null ) {
 
         Aresta<TIPO> aresta = new Aresta<TIPO>(peso, inicio, fim);
         inicio.adicionarArestaSaida(aresta);
         fim.adicionarArestaEntrada(aresta);
         this.arestas.add(aresta);
         }
         else{
             System.out.println("Algum vertice informado nao existe!");
         }
     }
 
      //  Adiciona uma aresta nao direcionada entre dois vertices.
 
     public void adicionarArestaNaoDir(Double peso, TIPO dadoInicio, TIPO dadoFim) {
         Vertice<TIPO> inicio = getVertice(dadoInicio);
         Vertice<TIPO> fim = getVertice(dadoFim);
 
         if (inicio != null && fim != null) {
             Aresta<TIPO> aresta1 = new Aresta<TIPO>(peso, inicio, fim);
             Aresta<TIPO> aresta2 = new Aresta<TIPO>(peso, fim, inicio);
 
             inicio.adicionarArestaSaida(aresta1);
             inicio.adicionarArestaEntrada(aresta2);
             fim.adicionarArestaSaida(aresta2);
             fim.adicionarArestaEntrada(aresta1);
 
             arestas.add(aresta1);
             arestas.add(aresta2);
         } else {
             System.out.println("Algum v�rtice informado n�o existe!");
         }
     }
 
     // Remove uma aresta direcionada
     public void removeraresta(TIPO dadoInicio, TIPO dadoFim) {
         Vertice<TIPO> inicio = this.getVertice(dadoInicio);
         Vertice<TIPO> fim = this.getVertice(dadoFim);
 
         if (inicio != null && fim != null) {
             Aresta<TIPO> arestaParaRemover = null;
 
             // Encontre a aresta correta para remover.
             for (Aresta<TIPO> aresta : inicio.getArestasSaida()) {
                 if (aresta.getFim() == fim) {
                     arestaParaRemover = aresta;
                     break;
                 }
             }
 
             if (arestaParaRemover != null) {
                 inicio.removerArestaSaida(arestaParaRemover);
                 fim.removerArestaEntrada(arestaParaRemover);
                 this.arestas.remove(arestaParaRemover);
             }
         }
     }
 
     // Remove uma aresta nao direcionada
     public void removerArestaNaoDir(TIPO dadoInicio, TIPO dadoFim) {
         Vertice<TIPO> inicio = getVertice(dadoInicio);
         Vertice<TIPO> fim = getVertice(dadoFim);
 
         if (inicio != null && fim != null) {
             // Remove a primeira direcao da aresta (inicio para fim)
             Aresta<TIPO> aresta1 = new Aresta<TIPO>(null, inicio, fim);
             inicio.removerArestaSaida(aresta1);
             inicio.removerArestaEntrada(aresta1);
             arestas.remove(aresta1);
 
             // Remove a segunda direcao da aresta (fim para inicio)
             Aresta<TIPO> aresta2 = new Aresta<TIPO>(null, fim, inicio);
             fim.removerArestaSaida(aresta2);
             fim.removerArestaEntrada(aresta2);
             arestas.remove(aresta2);
         } else {
             System.out.println("Algum v�rtice informado n�o existe!");
         }
     }
 
     // Obtem um vertice pelo seu dado associado
     public Vertice<TIPO> getVertice(TIPO dadoInicio){
         Vertice<TIPO> vertice = null;
         for(int i=0; i < this.vertices.size(); i++){
             if (this.vertices.get(i).getDado().equals(dadoInicio)){
                 vertice = this.vertices.get(i);
             }
         }
         return vertice;
     }
 
     // Obtem o numero de vizinhos de um vertice em um grafo direcionado
     public int  getVizinhos(TIPO dado ){
 
         String vizinhos;
         TIPO test;
         int countVizinhos=0;
                 // Se existir uma aresta com um vertice inicial igual ao fornecido, ent�o o vertice final � vizinho
                 for(int i=0; i<this.arestas.size();i++) {
                     test = this.arestas.get(i).getInicio().getDado();
                     if(test.equals(dado)) {
                        vizinhos = (String) this.arestas.get(i).getFim().getDado();
                        System.out.println(countVizinhos + " Vizinho = " + vizinhos);
                        countVizinhos++;
                     }
                 }
 
                 if(countVizinhos == 0 ){
                     System.out.println(dado +" nao tem vizinhos");
                 }
 
            return countVizinhos;
     }
 
      //Obtem o numero de vizinhos de um vertice em um grafo N direcionado
     public int  getVizinhosNaoDir(TIPO dado ){
 
         String vizinhos;
         TIPO test;
         int countVizinhos=0;
 
                 for(int i=0; i<this.arestas.size();i++) {
                     test = this.arestas.get(i).getInicio().getDado();
                     if(test.equals(dado)) {
                        vizinhos = (String) this.arestas.get(i).getFim().getDado();
                        System.out.println(countVizinhos + " Vizinho = " + vizinhos);
                        countVizinhos++;
                     }
                 }
                 for(int i=0; i<this.arestas.size();i++) {
                     test = this.arestas.get(i).getFim().getDado();
                     if(test.equals(dado)) {
                        vizinhos = (String) this.arestas.get(i).getInicio().getDado();
                        System.out.println(countVizinhos + " Vizinho = " + vizinhos);
                        countVizinhos++;
                     }
                 }
                 if(countVizinhos == 0 ){
                     System.out.println(dado +" nao tem vizinhos");
                 }
 
            return countVizinhos;
     }
 
     //Obtem grau do vertice direcionado
     public int getGrauverticeDir(TIPO dado) {
         int grauSaida = 0;
         int grauEntrada = 0;
         // Se o valor for igual ao vertice de entrada da aresta, entao pegar o vertice de saida e somar 1 no grau.
         for (Aresta<TIPO> aresta : this.arestas) {
             if (dado.equals(aresta.getInicio().getDado())) {
                 // Aresta de saida do vertice
                 grauSaida++;
             }
             if (dado.equals(aresta.getFim().getDado())) {
                 // Aresta de entrada para o vertice
                 grauEntrada++;
             }
         }
 
         int grauTotal = grauSaida + grauEntrada;
 
         System.out.println("Grau de saida do vertice " + dado + ": " + grauSaida);
         System.out.println("Grau de entrada do vertice " + dado + ": " + grauEntrada);
         System.out.println("Grau total do vertice " + dado + ": " + grauTotal);
 
         return grauTotal;
     }
 
     // Obtem grau de um vertice N direcionado
     public int getGrauVerticeNaoDir(TIPO dado) {
         int grau = 0;
         Set<TIPO> vizinhos = new HashSet<>();
 
         for (Aresta<TIPO> aresta : this.arestas) {
             if (dado.equals(aresta.getInicio().getDado())) {
                 // Aresta de sa�da do v�rtice
                 TIPO vizinho = aresta.getFim().getDado();
                 if (!vizinhos.contains(vizinho)) {
                     grau++;
                     vizinhos.add(vizinho);
                 }
             } else if (dado.equals(aresta.getFim().getDado())) {
                 // Aresta de entrada para o v�rtice
                 TIPO vizinho = aresta.getInicio().getDado();
                 if (!vizinhos.contains(vizinho)) {
                     grau++;
                     vizinhos.add(vizinho);
                 }
             }
         }
         System.out.println("Grau do vertice " + dado + ": " + grau);
         return grau;
     }
 
     // Obtem o grau do grafo em um grafo direcionado.
 
     public void getGrauGrafoDir() {
         int grauTotal = 0;
         for (Vertice<TIPO> vertice : this.vertices) {
             int grauSaida = 0;
             for (Aresta<TIPO> aresta : vertice.getArestasSaida()) {
                 grauSaida++;
             }
             grauTotal += grauSaida;
         }
        System.out.println(" Grau do grafo : "+ grauTotal);
     }
 
      /**
      * Obtém o grau do grafo em um grafo não direcionado.
      */
     public void getGrauGrafoNaoDir() {
         int grauTotal = 0;
         Set<TIPO> verticesProcessados = new HashSet<>();
 
         for (Aresta<TIPO> aresta : this.arestas) {
             Vertice<TIPO> inicio = aresta.getInicio();
             Vertice<TIPO> fim = aresta.getFim();
 
             if (!verticesProcessados.contains(inicio.getDado())) {
                 grauTotal += getGrauVerticeNaoDir(inicio.getDado());
                 verticesProcessados.add(inicio.getDado());
             }
 
             if (!verticesProcessados.contains(fim.getDado())) {
                 grauTotal += getGrauVerticeNaoDir(fim.getDado());
                 verticesProcessados.add(fim.getDado());
             }
         }
 
         System.out.println("Grau do grafo: " + grauTotal);
 
         return ;
     }
 
 
 
 
     /**
      * Verifica se o grafo direcionado é regular.
      */
     public void regularDir() {
         if (vertices.isEmpty()) {
             System.out.println("Grafo est� vazio");
             return;
         }
 
         int grauPrimeiroVertice = getGrauverticeDir(vertices.get(0).getDado());
 
         for (Vertice<TIPO> vertice : vertices) {
             int grau = getGrauverticeDir(vertice.getDado());
             if (grau != grauPrimeiroVertice) {
                 System.out.println("O grafo � nao � regular");
                 return;
             }
         }
         System.out.println("O grafo � "+ grauPrimeiroVertice + "regular");
 
         return;
     }
 
      /**
      * Verifica se o grafo não direcionado é regular.
      */
     public void regularNaoDir() {
         if (vertices.isEmpty()) {
             System.out.println("Grafo est� vazio");
             return ;
         }
 
         int grauPrimeiroVertice = getGrauVerticeNaoDir(vertices.get(0).getDado());
 
         for (Vertice<TIPO> vertice : vertices) {
             int grau = getGrauVerticeNaoDir(vertice.getDado());
             if (grau != grauPrimeiroVertice) {
                 System.out.println("O grafo � nao � regular");
 
                 return ;
             }
         }
         System.out.println("O grafo � "+ grauPrimeiroVertice + "regular");
 
         return ;
     }
 
     /**
      * Verifica se o grafo não direcionado é completo.
      */
     public void CompletoNaoDir() {
         int totalVertices = vertices.size();
         int totalArestas = arestas.size();
 
         // O n�mero total de arestas em um grafo completo n�o direcionado � dado por C(n, 2), onde n � o n�mero de v�rtices.
         int arestasCompletas = (totalVertices * (totalVertices - 1)) / 2;
         if(totalArestas == arestasCompletas) {
             System.out.println("O grafo � completo");
         }
         else {
             System.out.println("O grafo nao � completo");
 
         }
 
         return ;
     }
 
      /**
      * Verifica se o grafo direcionado é completo.
      */
     public void CompletoDir() {
         int totalVertices = vertices.size();
         int totalArestas = arestas.size();
 
         // O n�mero total de arestas em um grafo completo direcionado � n * (n - 1), onde n � o n�mero de v�rtices.
         int arestasCompletas = totalVertices * (totalVertices - 1);
         if(totalArestas == arestasCompletas) {
             System.out.println("O grafo � completo");
         }
         else {
             System.out.println("O grafo nao � completo");
 
         }
 
         return;
     }
 
     // Método para identificar sucessores e predecessores de um vértice (grafo direcionado)
 public void IdentificarSucePred(TIPO dado) {
     Vertice<TIPO> vertice = getVertice(dado);
     if (vertice == null) {
         System.out.println("O vértice não foi encontrado.");
         return;
     }
     System.out.println("Sucessores de " + dado + ":");
     for (Aresta<TIPO> aresta : vertice.getArestasSaida()) {
         System.out.println(aresta.getFim().getDado());
     }
     System.out.println("Predecessores de " + dado + ":");
     for (Aresta<TIPO> aresta : vertice.getArestasEntrada()) {
         System.out.println(aresta.getInicio().getDado());
     }
 }
 
 // Método para testar se o grafo é simples
 public void testarGrafoSimples() {
     for (Aresta<TIPO> aresta : arestas) {
         if (aresta.getInicio().equals(aresta.getFim())) {
             System.out.println("O grafo não é simples. Possui laços.");
             return;
         }
         for (Aresta<TIPO> outraAresta : arestas) {
             if (aresta != outraAresta && aresta.getInicio().equals(outraAresta.getInicio()) && aresta.getFim().equals(outraAresta.getFim())) {
                 System.out.println("O grafo não é simples. Possui múltiplas arestas entre os mesmos vértices.");
                 return;
             }
         }
     }
     System.out.println("O grafo é simples.");
 }
 
 // Método para testar se o grafo é conexo
 public void testarConexo() {
     Set<Vertice<TIPO>> visitados = new HashSet<>();
     Queue<Vertice<TIPO>> fila = new LinkedList<>();
     fila.add(vertices.get(0));
     while (!fila.isEmpty()) {
         Vertice<TIPO> vertice = fila.poll();
         visitados.add(vertice);
         for (Aresta<TIPO> aresta : vertice.getArestasSaida()) {
             if (!visitados.contains(aresta.getFim())) {
                 fila.add(aresta.getFim());
             }
         }
         for (Aresta<TIPO> aresta : vertice.getArestasEntrada()) {
             if (!visitados.contains(aresta.getInicio())) {
                 fila.add(aresta.getInicio());
             }
         }
     }
     if (visitados.size() == vertices.size()) {
         System.out.println("O grafo é conexo.");
     } else {
         System.out.println("O grafo não é conexo.");
     }
 }


// Método para testar se o grafo é bipartido
public boolean testarGrafoBipartido() {
    Map<Vertice<TIPO>, Integer> coloracao = new HashMap<>();

    for (Vertice<TIPO> vertice : vertices) {
        coloracao.put(vertice, -1);
    }

    for (Vertice<TIPO> vertice : vertices) {
        if (coloracao.get(vertice) == -1) {
            if (!bipartidoBFS(vertice, coloracao)) {
                return false;
            }
        }
    }

    return true;
}

// Função auxiliar para verificar se o grafo é bipartido usando BFS
private boolean bipartidoBFS(Vertice<TIPO> vertice, Map<Vertice<TIPO>, Integer> coloracao) {
    Queue<Vertice<TIPO>> fila = new LinkedList<>();
    fila.add(vertice);
    coloracao.put(vertice, 1);

    while (!fila.isEmpty()) {
        Vertice<TIPO> atual = fila.poll();
        int corAtual = coloracao.get(atual);

        for (Vertice<TIPO> vizinho : atual.getSucessores()) {
            if (coloracao.get(vizinho) == -1) {
                coloracao.put(vizinho, 1 - corAtual);
                fila.add(vizinho);
            } else if (coloracao.get(vizinho) == corAtual) {
                return false;
            }
        }
    }

    return true;
}

 
 // Método para exportar o grafo para o formato GEXF
 public void exportarParaGEXF(String nomeArquivo) {
     try {
         FileWriter arquivo = new FileWriter(nomeArquivo);
         PrintWriter gravarArquivo = new PrintWriter(arquivo);
 
         gravarArquivo.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
         gravarArquivo.println("<gexf xmlns=\"http://www.gexf.net/1.2draft\" version=\"1.2\">");
         gravarArquivo.println("<graph>");
         gravarArquivo.println("<nodes>");
 
         // Escreve os vértices
         for (Vertice<TIPO> vertice : vertices) {
             gravarArquivo.println("<node id=\"" + vertice.getDado() + "\" label=\"" + vertice.getDado() + "\"/>");
         }
 
         gravarArquivo.println("</nodes>");
         gravarArquivo.println("<edges>");
 
         // Escreve as arestas
         for (Aresta<TIPO> aresta : arestas) {
             gravarArquivo.println("<edge source=\"" + aresta.getInicio().getDado() + "\" target=\"" + aresta.getFim().getDado() + "\"/>");
         }
 
         gravarArquivo.println("</edges>");
         gravarArquivo.println("</graph>");
         gravarArquivo.println("</gexf>");
 
         gravarArquivo.close();
         arquivo.close();
 
         System.out.println("Grafo exportado para " + nomeArquivo + " com sucesso.");
 
     } catch (IOException e) {
         System.out.println("Erro ao exportar o grafo para GEXF: " + e.getMessage());
     }
 }

 public void imprimirMatrizAdjacencia() {
    int numVertices = vertices.size();
    int[][] matrizAdjacencia = new int[numVertices][numVertices];

    // Inicializa a matriz de adjacência com zeros
    for (int i = 0; i < numVertices; i++) {
        for (int j = 0; j < numVertices; j++) {
            matrizAdjacencia[i][j] = 0;
        }
    }

    // Preenche a matriz de adjacência com as conexões entre vértices
    for (Aresta<TIPO> aresta : arestas) {
        int inicioIndex = vertices.indexOf(aresta.getInicio());
        int fimIndex = vertices.indexOf(aresta.getFim());
        matrizAdjacencia[inicioIndex][fimIndex] = 1; 
    }

    // Imprime a matriz de adjacência
    System.out.println("Matriz de Adjacência:");
    System.out.print("   ");
    for (int i = 0; i < numVertices; i++) {
        System.out.printf("%-5s", vertices.get(i).getDado());
    }
    System.out.println();
    for (int i = 0; i < numVertices; i++) {
        System.out.print(vertices.get(i).getDado() + "  ");
        for (int j = 0; j < numVertices; j++) {
            System.out.printf("%-5d", matrizAdjacencia[i][j]);
        }
        System.out.println();
    }
}


public void imprimirListaAdjacencia() {
    System.out.println("Lista de Adjacência:");
    for (Vertice<TIPO> vertice : vertices) {
        System.out.print(vertice.getDado() + ": ");
        for (Aresta<TIPO> aresta : vertice.getArestasSaida()) {
            System.out.print(aresta.getFim().getDado() + " ");
        }
        System.out.println();
    }
}

 }
 
 