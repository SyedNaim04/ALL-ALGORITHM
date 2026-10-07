public class AmbulanceDispatchSystem 
{

    static class EmergencyMinHeap 
    {
        private int[] heap;
        private int size;

        public EmergencyMinHeap(int capacity) 
        {
            heap = new int[capacity];
            size = 0;
        }

        private int parent(int i) 
        { return (i - 1) / 2; 
        }
        private int leftChild(int i) 
        { return (2 * i) + 1; 
        }
        private int rightChild(int i) 
        { return (2 * i) + 2; 
        }

        public void insertETA(int eta) 
        {
            if (size == heap.length) 
            {
                System.out.println("Dispatch queue is full.");
                return;
            }
            
            int current = size;
            heap[size++] = eta;

            while (current > 0 && heap[current] < heap[parent(current)]) 
            {
                swap(current, parent(current));
                current = parent(current);
            }
        }

        public int dispatchFastest() 
        {
            if (size <= 0) return -1;
            if (size == 1) return heap[--size];

            int root = heap[0];
            heap[0] = heap[--size];
            heapify(0);

            return root;
        }

        private void heapify(int i) 
        {
            int smallest = i;
            int left = leftChild(i);
            int right = rightChild(i);

            if (left < size && heap[left] < heap[smallest]) smallest = left;
            if (right < size && heap[right] < heap[smallest]) smallest = right;

            if (smallest != i) 
            {
                swap(i, smallest);
                heapify(smallest);
            }
        }

        private void swap(int a, int b) 
        {
            int temp = heap[a];
            heap[a] = heap[b];
            heap[b] = temp;
        }
        
        public void displayQueue() 
        {
            System.out.print("Active ETA Queue (Array Representation): ");
            for (int i = 0; i < size; i++) 
            {
                System.out.print("[" + heap[i] + "] ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        EmergencyMinHeap dispatchQueue = new EmergencyMinHeap(10);

        System.out.println("--- System Online: Receiving Emergency Calls ---");
        dispatchQueue.insertETA(25);
        dispatchQueue.insertETA(15);
        dispatchQueue.insertETA(12);
        dispatchQueue.insertETA(10);
        dispatchQueue.insertETA(8);
        dispatchQueue.insertETA(5);

        System.out.println("\n--- Current Ambulance Dispatch Queue ---");
        dispatchQueue.displayQueue(); 

        System.out.println("\n--- Dispatching Closest Ambulance ---");
        int dispatched = dispatchQueue.dispatchFastest();
        System.out.println("ACTION: Dispatched ambulance for case with ETA: " + dispatched + " minutes.");
        
        System.out.println("\n--- Queue After Dispatch (Heap Reorganized) ---");
        dispatchQueue.displayQueue(); 
    }
}