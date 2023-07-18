public class HashTable {

    private Entry [] hashArray;
    private final int sizeOfTable;
    private int numOfElements = 0;
    public HashTable(int sizeOfTable){
        if(isPrime(sizeOfTable)) {
            this.hashArray = new Entry[sizeOfTable];
            this.sizeOfTable = sizeOfTable;
            System.out.println("The size of the Hash Table is: "+sizeOfTable);
        }else{
            this.hashArray = new Entry[getNextPrime(sizeOfTable)];
            this.sizeOfTable = getNextPrime(sizeOfTable);
            System.out.println("The number entered is not a prime," +
                    "the size of the Hash Table is the next prime: "+getNextPrime(sizeOfTable));
        }
    }

    private boolean isPrime(int num){
        for (int i = 2; i*i <= num; i ++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    private int getNextPrime(int num){
        int nextNumber = num;
        if (isPrime(num)){
            nextNumber++;
        }
        while(true){
            if (isPrime(nextNumber)){
                return nextNumber;
            }
            nextNumber++;
        }
    }
    public void insert(int key, String value){
        if(this.numOfElements >= this.sizeOfTable){
            throw new IllegalArgumentException("The table is full. Can not insert another");
        }
        int primaryHash = hash1(key);
        int secondaryHash = hash2(key);
        int index = primaryHash;

        if (this.hashArray[index] == null) {
            this.hashArray[index] = new Entry(key, value);
            this.numOfElements++;
        }else {
            int i = 1;
            while(true) {
                index = (primaryHash + i * secondaryHash) % this.sizeOfTable;
                if (this.hashArray[index] == null){
                    this.hashArray[index] = new Entry(key, value);
                    this.numOfElements++;
                    break;
                }
                i++;
            }
        }
    }
    public void removeAvailableElements(){
        Entry[] newHashArray = new Entry[sizeOfTable];
        int newNumOfElements = 0;

        for (Entry entry:this.hashArray) {
            if (entry != null && entry.isAvailable()){
                int primaryHash = hash1(entry.getKey());
                int secondaryHash = hash2(entry.getKey());
                int index = primaryHash;

                if (newHashArray[index] == null){
                    newHashArray[index] = entry;
                    newNumOfElements++;
                }else {
                    int i = 1;
                    while(true){
                        index = (primaryHash + i * secondaryHash) % sizeOfTable;
                        if (newHashArray[index] == null){
                            newHashArray[index] = entry;
                            newNumOfElements++;
                            break;
                        }
                        i++;
                    }
                }
            }
        }
        this.hashArray = newHashArray;
        this.numOfElements = newNumOfElements;
    }
    public void setAvailableWithKey(int key){
        int primaryHash = hash1(key);
        int secondaryHash = hash2(key);
        int index = primaryHash;

        if (this.hashArray[index] != null && this.hashArray[index].getKey() == key){
            this.hashArray[index].setAvailable();
        }else {
            int i = 1;
            while(true){
                index = (primaryHash + i * secondaryHash) % sizeOfTable;
                if(this.hashArray[index] == null){
                    break;
                } else if (this.hashArray[index].getKey() == key) {
                    this.hashArray[index].setAvailable();
                }
                i++;
            }
        }
    }
    public void removeWithKey(int key){
        int primaryHash = hash1(key);
        int secondaryHash = hash2(key);
        int index = primaryHash;

        if (this.hashArray[index] != null && this.hashArray[index].getKey() == key){
            this.hashArray = null;
            this.numOfElements--;
            rearrange();
        }else {
            int i = 1;
            while(true) {
                index = (primaryHash + i * secondaryHash) % sizeOfTable;
                if (this.hashArray[index] == null){
                    break;
                } else if (this.hashArray[index].getKey() == key) {
                    this.hashArray[index] = null;
                    numOfElements--;
                    rearrange();
                    break;
                }
                i++;
            }
        }
    }

    public void rearrange(){
        Entry[] newHashArray = new Entry[sizeOfTable];
        int newNumOfElements = 0;

        for (Entry entry: this.hashArray){
            int primaryHash = hash1(entry.getKey());
            int secundaryHash = hash2(entry.getKey());
            int index = primaryHash;
            if (newHashArray[index] == null){
                newHashArray[index] = entry;
                newNumOfElements++;
            }else {
                int i = 1;
                while (true) {
                    index = (primaryHash + i * secundaryHash) % sizeOfTable;
                    if (newHashArray[index] == null){
                        newHashArray[index] = entry;
                        newNumOfElements++;
                        break;
                    }
                    i++;
                }
            }
        }
        hashArray = newHashArray;
        numOfElements = newNumOfElements;
    }



    public String search(int key){
        int primaryHash = hash1(key);
        int secondaryHash = hash2(key);
        int index = primaryHash;
        
        if (this.hashArray[index] == null){
            return null;
        } else if (this.hashArray[index].getKey() == key) {
            return this.hashArray[index].getElement();
        }else {
            int i = 1;
            while (true){
                index = (primaryHash + i * secondaryHash) % this.sizeOfTable;
                if (this.hashArray[index] == null){
                    return null;
                } else if (this.hashArray[index].getKey() == key) {
                    return this.hashArray[index].getElement();
                }
                i++;
            }
        }

    }
    public void printTable(){
        for(int i = 0; i < sizeOfTable; i ++){
            if (this.hashArray[i] != null) {
                System.out.println("Index " + i + ": key=" + this.hashArray[i].getKey()+
                        ", Element="+this.hashArray[i].getElement()+
                        ", Is available: "+ this.hashArray[i].isAvailable());
            }
        }
    }

    private int hash1(int key){
        return key % sizeOfTable;
    }
    private int hash2(int key){
        return this.sizeOfTable - (key % this.sizeOfTable);
    }

}
