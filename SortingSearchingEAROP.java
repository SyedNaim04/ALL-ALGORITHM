import java.util.Arrays;

public class SortingSearchingEAROP 
{

    public static void insertionSort(int[] responseTimes) 
    {
        for (int i = 1; i < responseTimes.length; i++) 
        {
            int key = responseTimes[i];
            int j = i - 1;
            while (j >= 0 && responseTimes[j] > key) 
            {
                responseTimes[j + 1] = responseTimes[j];
                j--;
            }
            responseTimes[j + 1] = key;
        }
    }

    public static int binarySearch(int[] arr, int target) 
    {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) 
        {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) return mid;
            if (arr[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args)
    {
        int[] emergencyResponseTimes = {8, 3, 5, 1, 9, 2};
        int targetTime = 5;

        System.out.println("Original Array: " + Arrays.toString(emergencyResponseTimes));
        
        insertionSort(emergencyResponseTimes);
        System.out.println("Sorted Array:   " + Arrays.toString(emergencyResponseTimes));
        
        int resultIndex = binarySearch(emergencyResponseTimes, targetTime);
        System.out.println("Target time (" + targetTime + " mins) found at index: " + resultIndex);
    }
}