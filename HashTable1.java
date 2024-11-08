public class HashTable1 extends HashTable {
    private static final int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 
        37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107};

    public HashTable1(int size) {
        super(size);
    }

    @Override
    public int hashFunction(String key) {
        long hash = 0;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            int primeToUse;

            if (Character.isLetter(c)) {
                int index = Character.toLowerCase(c) - 'a';
                primeToUse = primes[index];
            } else {
                primeToUse = primes[26]; 
            }
            hash += (int) c * primeToUse * 523;
        }
        return Math.abs((int) (hash % this.size));
    }

    @Override
    public void insert(String key) {
        int index = hashFunction(key);
        int attempts = 0;
        boolean collisionOccurred = false;

        while (table[index] != null) {
            if (table[index].equals(key)) {
                return;
            }

            if (!collisionOccurred) {
                collisions++;
                collisionOccurred = true;
                System.out.println(">> TABELA 1:: Colisão detectada para '" + key + "' em índice " + index);
            }

            index = (index + 1) % size;
            attempts++;

            if (attempts >= size) {
                System.out.println("!! TABELA 1 CHEIA !! Não foi possível inserir: " + key);
                return;
            }
        }

        table[index] = key;
        numElements++;
    }
}
