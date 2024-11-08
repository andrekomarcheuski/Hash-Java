import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        final int size = 10000; // Tamanho da tabela Hash

        HashTable hashTable1 = new HashTable1(size);
        HashTable hashTable2 = new HashTable2(size);

        long start1, end1, start2, end2;
        List<String> names = readFile("female_names.txt");

        long time1 = 0;
        long time2 = 0;

        for (String name : names) {
            start1 = System.nanoTime();
            hashTable1.insert(name);
            end1 = System.nanoTime();
            time1 += (end1 - start1);

            start2 = System.nanoTime();
            hashTable2.insert(name);
            end2 = System.nanoTime();
            time2 += (end2 - start2);
        }

        String searchKey = "Janel";

        start1 = System.nanoTime();
        boolean found1 = hashTable1.search(searchKey);
        end1 = System.nanoTime();
        long searchTime1 = (end1 - start1);  


        start2 = System.nanoTime();
        boolean found2 = hashTable2.search(searchKey);
        end2 = System.nanoTime();
        long searchTime2 = (end2 - start2);

 
        int collisions1 = hashTable1.getCollisions();
        int collisions2 = hashTable2.getCollisions();

        double percentage1 = ((double) collisions1 / names.size()) * 100;
        double percentage2 = ((double) collisions2 / names.size()) * 100;


        double totalTime1 = time1 / 1_000_000.0;
        double totalTime2 = time2 / 1_000_000.0;

        System.out.println("\n     Tabela Hash 1:");
        System.out.println(">> Total de nomes inseridos: " + hashTable1.getNumElements());
        System.out.println(">> Número total de colisões: " + collisions1);
        System.out.printf(">> Porcentagem total de colisões: %.2f%%%n", percentage1);
        System.out.printf(">> Tempo total de inserção: %.2f milissegundos%n", totalTime1);
        System.out.println(">> Nome 'Janel' encontrado: " + found1);
        System.out.printf(">> Tempo de busca: %d nanossegundos%n", searchTime1);

        System.out.println("\n      Tabela Hash 2:");
        System.out.println(">> Total de nomes inseridos: " + hashTable2.getNumElements());
        System.out.println(">> Número total de colisões: " + collisions2);
        System.out.printf(">> Porcentagem total de colisões: %.2f%%%n", percentage2);
        System.out.printf(">> Tempo total de inserção: %.2f milissegundos%n", totalTime2);
        System.out.println(">> Nome 'Janel' encontrado: " + found2);
        System.out.printf(">> Tempo de busca: %d nanossegundos%n", searchTime2);

    }

    public static List<String> readFile(String fileName) {
        List<String> names = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim(); 
                if (!line.isEmpty()) { 
                    names.add(line);
                }
            }
            System.out.println("Total de nomes lidos: " + names.size());
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return names;
    }
}
