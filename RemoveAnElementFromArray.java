import java.util.Arrays;

public class RemoveAnElementFromArray {

    public static int[] removeTheElement(int[] arr, int index) {
        // If the array is empty
        // or the index is not in array range
        // return the original array
        if (arr == null || index < 0 || index >= arr.length) {
            return arr;
        }
        // Create another array of size one less
        int[] anotherArray = new int[arr.length - 1];

        // Copy the elements except the index
        // from original array to the other array
        for (int i = 0, k = 0; i < arr.length; i++) {

            // if the index is
            // the removal element index
            if (i == index) {
                continue;
            }
            // if the index is not
            // the removal element index
            anotherArray[k++] = arr[i];
        }

        // return the resultant array
        return anotherArray;
    }

    // Driver Code
    public static void main(String[] args) {

        // Get the array
        int[] pre = {1, 2, 3, 4, 5};
        int[] post = {1, 2, 8, 6, 5};

        System.out.println("pre Array: " + Arrays.toString(pre));
        System.out.println("post Array: " + Arrays.toString(post));

        // Get the specific index
//            int index = 2;
        for (int i = 0; i < pre.length; i++) {
            for (int j = 0; j < post.length; j++) {
                if (pre[i] == post[j]) {
                    System.out.println("Index to be removed: " + pre[i]);
                    pre = removeTheElement(pre, pre[i]);
                    post = removeTheElement(post, post[j]);
                }

            }

        }
        System.out.println("Deleted Array: " + Arrays.toString(pre));
        System.out.println("Added Array: " + Arrays.toString(post));
    }
}


