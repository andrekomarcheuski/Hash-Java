public abstract class HashTable {
    protected String[] table;
    protected int size;
    protected int numElements;
    protected int collisions;

    public HashTable(int size) {
        this.size = size;
        this.table = new String[size];
        this.numElements = 0;
        this.collisions = 0;
    }

    public int getNumElements() {
        return numElements;
    }

    public int getCollisions() {
        return collisions;
    }

    public abstract int hashFunction(String key);

    public abstract void insert(String key);

    public boolean search(String key) {
        int index = hashFunction(key);
        int attempts = 0;

        while (table[index] != null) {
            if (table[index].equals(key)) {
                return true;
            }
            index = (index + 1) % size;
            attempts++;

            if (attempts >= size) {
                break;
            }
        }
        return false;
    }
}
