public class HashTable2 extends HashTable {

    public HashTable2(int size) {
        super(size);
    }

    private long fact(int n) {
        long result = 1;
        for (int i = 2; i <= n && i <= 10; i++) {
            result *= i;
        }
        return result;
    }

    @Override
    public int hashFunction(String key) {
        long hash = 0;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            hash += c * fact(i + 1);  
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
                System.out.println(">> Colisão detectada para '" + key + "' em índice " + index);
            }

            index = (index + 1) % size; 
            attempts++;
            if (attempts >= size) {
                System.out.println("!! TABELA CHEIA !! Não foi possível inserir: " + key);
                return;
            }
        }
        table[index] = key;
        numElements++;
    }
}
