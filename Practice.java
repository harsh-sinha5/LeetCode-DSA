import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Practice {

    public static void main(String[] args) {

        Set<Example> exampleSet = new HashSet<>();
        //Sample2 sample2 = new Example1();
        //sample2.same();
        Example1 example1 = new Example1();
        exampleSet.add(new Example());
        exampleSet.add(new Example());
        exampleSet.add(new Example());
        List<Example> list = new ArrayList<>();
        list.add(new Example("Vinay"));
        list.add(new Example("Harsh"));
        list.add(new Example("Vinit"));
        list.forEach(System.out::println);

        double d = 1.234;
        int[] arr = {2, 5, -4, 6};
        int[] arr2 = {2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] arr3 = {10, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] arr4 = {1, 1, 1, 2, 2, 3, 4, 4, 5, 5};
        int[] arr5 = {7, 9, 4, 5, 8, 1};
        System.out.println("non duplicate:" + nonDuplicate(arr4));
        String str = "Success";
        System.out.println(charCount(str, 'c'));
        System.out.println(countAllChar(str));
        countChar(str);
        System.out.println("Success");
        System.out.println(getSecondHighest(arr));
        System.out.println(sumSubArray(arr));
        System.out.println("Fibonacci:" + fibonacci(6));
        System.out.println("Factorial:" + factorial(5));
        for (int i = 0; i < 10; i++) {
            System.out.print(fib(i) + ", ");
        }
        System.out.println("element  found at index:" + binarySearch(arr2, 8));
        System.out.println("element found at index:" + binarySearch(arr3, 6));
        System.out.println("Converted to Int:" + convertStringToInt("1234"));
        int[] arr6 = twoSum(new int[]{1, 2, 3, 4, 5}, 6);
        System.out.println("first element of two sum:" + arr6[0] + " second element:" + arr6[1]);
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Future<Integer> integerFuture = executorService.submit(() -> new Random().nextInt());
        CompletableFuture<Integer> intCompletableFuture = CompletableFuture.supplyAsync(() -> new Random().nextInt());
        quickSort(arr5, 0, arr5.length - 1);
        System.out.println("quick sored");
        for (int i = 0; i < arr5.length; i++) {

            System.out.println(arr5[i]);
        }
    }
                /**
     * method counts the occurrences of a given character in the string
     */
    public static long charCount(String str, char c) {
        return str
                .chars()
                .filter(s ->(char) s == c )
                .count();
    }

    /*
    * method counts the occurrences of all the characters in a given string
     */
    public static Map<Character, Integer> countAllChar(String str) {
        Map<Character, Integer> countMap =  new HashMap<>();
        str.chars().mapToObj(chars->(char) chars ).forEach(chars ->  {
            if(countMap.containsKey(chars)){
                countMap.put(chars,  countMap.get(chars) +1);
            }
            else{
                countMap.put(chars, 1);
            }
        });
        str.chars()
                .mapToObj(c ->(char) c)
                .forEach(s -> {
            if(countMap.containsKey(s)){
                countMap.put(s,  countMap.get(s) +1);
            }
            else{
                countMap.put(s, 1);
            }
        });
        return countMap;
    }

    /*
    method to count occurrences of al he character using Stream
     */
    public static void countChar(String str){
            str.chars().mapToObj(s -> (char)s).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().forEach(System.out::println);
    }

    public static int getSecondHighest(int[] arr){
        int highest = Integer.MIN_VALUE, secondHighest = Integer.MIN_VALUE;
        for(int i = 0; i < arr.length; i++){
            if( arr[i] > highest ){// && secondHighest >= highest){
                secondHighest = highest;
                highest = arr[i];
            }
            if(arr[i] < highest && arr[i] > secondHighest){
                secondHighest = arr[i];
            }
//            else if(arr[i] <arr[i+1]){
//                highest = arr[i+1];
//                secondHighest = arr[i];
//            }
        }
        return secondHighest;
    }

    public static int sumSubArray(int[] arr){
         if(arr.length <= 1){
             return arr[0];
         }
         int maxAsNow = Integer.MIN_VALUE;
         int maxEndingHere = 0, start = 0, end = 0, begin = 0;
         for(int i = 0; i < arr.length; i++){
             maxEndingHere = maxEndingHere + arr[i];

             if(maxEndingHere < arr[i]) {
                 maxEndingHere = arr[i];
             }

             if(maxAsNow < maxEndingHere) {
                 maxAsNow = maxEndingHere;
             }
         }
        return maxAsNow;
    }

    private static int fibonacci(int n){
        if(n<=1) return n;
        return fibonacci(n-1) +fibonacci(n-2);
    }

    private static int factorial(int n){
        if(n<=1) return 1;
        return n * factorial(n-1);
    }

    private static int fib(int n){
       int[] fib = new int[n+2];
       fib[0]=0;
       fib[1]=1;
       for(int i = 2; i <=n; i++){
           fib[i] = fib[i-1] + fib[i - 2];
       }
       return fib[n];
    }

    private static int binarySearch(int[] arr, int n){
        int start = 0, end = arr.length -1;
        while(start <= end) {
            int mid = (start +end)/2;
            if (arr[mid] == n)
                return mid;
            if(arr[start] < arr[end]) {
                if (n < arr[mid])
                    end = mid - 1;
                if (n > arr[mid])
                    start = mid + 1;
            }
            else{
                if (n > arr[mid])
                    end = mid - 1;
                if (n < arr[mid])
                    start = mid + 1;
            }
        }
        return -1;
    }

    private static int binarySearch(int[] arr, int begin, int end, int num ){
        while(begin <= end){
            int mid = (begin + end) /2;
            if(arr[mid] == num)
                return  mid;
            if(arr[mid] > num)
                return binarySearch(arr, begin, mid-1, num);
            if(arr[mid] < num)
                return binarySearch(arr, mid + 1, end, num);
        }
        return 0;
    }
    static void fun(Integer i){}
    static void fun(String s){}

    private static int nonDuplicate(int[] arr){
        int j = -1;
        for(int i = 0; i < arr.length ; i+=2){
            if(arr[i] != arr[i+1]){
                j = arr[i];
                break;
            }
        }
       if(arr[arr.length - 2] != arr[arr.length- 1]) {
           j = arr[arr.length - 1];
       }
        return j;
    }
    public static int convertStringToInt(String s){
        int n = 0;
        for(int i = 0; i < s.length(); i++){
            n = (n * 10 )+ ((int)s.charAt(i) - 48);
        }
        return  n;
    }

    public static int[] twoSum(int[] arr, int target){
        Map<Integer, Integer> integerMap = new HashMap<>();
        for(int i = 0; i < arr.length; i++ ){
            int delta = target - arr[i];
            if(integerMap.containsKey(delta)){
                return new int[]{i, integerMap.get(delta)};
            }
            integerMap.put(arr[i], i);
        }
        return  new int[]{};
    }

    public static int trapWater(int[] arr){
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = arr[0];
        for(int i = 1; i < n; i++){
            left[i] = Math.max(left[i-1], arr[i]);
        }
        right[0] = arr[n-1];
        for(int i = n-2; i >= 0 ; i--){
            right[i] =Math.max(right[i+1], arr[i]);
        }
        int trappedWater = 0;
        for(int i = 0; i < n ; i++ ){
            trappedWater += (Math.min(left[i], right[i]) )- arr[i];
        }
        return trappedWater;
    }

   public static int trapWaterNoSapce(int[] arr){
        var highest = 0;
        var totalWater = 0;
        int maxIndex = 0;
        if(arr == null) return 0;
        for(int i = 0 ; i < arr.length; i++ ){
            if(arr[i] > highest)
                highest = arr[i];
               maxIndex = i;
        }
        int leftMax = 0;
        for(int i = 0; i < maxIndex; i++){
            leftMax = Math.max(leftMax, arr[i]);
            totalWater+=Math.min(highest, leftMax) - arr[i];
        }
        leftMax = 0;
        for(int i = arr.length - 1 ; i > maxIndex; i--){
            leftMax = Math.max(leftMax, arr[i]);
            totalWater+=Math.min(highest, leftMax) - arr[i];
        }
        return totalWater;
   }
   static int partition(int[] arr, int low, int high){
        int pivot = arr[high];
        int i = low;
        int j = low;
        while(i <=high){
            if(arr[i] <= pivot){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                j++;
            }
            i++;
        }
        return j-1;
   }
    static void quickSort(int[] arr, int low, int high){
        if(low < high) {
            int p = partition(arr, low, high);
            quickSort(arr, p + 1, high);
            quickSort(arr, low, p - 1);
        }
    }
}
