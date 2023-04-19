import java.util.ArrayList;

public class CompareTwoArrayList {


    public static void main(String[] args) {



      /*  ArrayList<Integer> added = new ArrayList<>();
        ArrayList<Integer> deleted = new ArrayList<>();
        int arr1[] = {2, 3, 4, 5};
        int arr2[] = {1, 3, 4, 6};*/
        boolean contains = false;





/*        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                if(arr2[j]==arr1[i]){
                    System.arraycopy(arr2, arr2[j] + 1, arr2, arr2[j], arr2.length - 1 - arr2[j]);
                   contains=true;
                   break;
                }
            }
            if(!contains){
                added.add(arr2[i]);
            }
            else {


                contains=false;
            }

        }

        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < arr1.length; j++) {
                if(arr1[j]==arr2[i]){
                    contains=true;

                    break;
                }
            }
            if(!contains){
                deleted.add(arr1[i]);
            }
            else {
                contains=false;
            }

        }

        System.out.println("added " +added);
        System.out.println("deleted " +deleted);

    }
//        for (int i = 0; i < arr2.length; i++) {
//            for (int j = 0; j < arr1.length; j++) {
//                if(arr1[j]!=arr2[i]){
//                    System.out.println(arr1[j]);
//                }
//            }
//
//        }
    }*/


        ArrayList<Integer> arr1 = new ArrayList<>();
        arr1.add(1);
        arr1.add(2);
        arr1.add(3);
        arr1.add(4);
        ArrayList<Integer> arr2 = new ArrayList<>();
        arr2.add(1);
        arr2.add(2);
        arr2.add(5);
        arr2.add(6);
        arr2.add(8);
        ArrayList<Integer> added = new ArrayList<>();
        ArrayList<Integer> deleted = new ArrayList<>();

        for (int i = 0; i < arr1.size(); i++) {
            for (int j = 0; j < arr2.size(); j++) {
                    if(arr1.get(i).equals(arr2.get(i))) {
                        arr1.remove(arr1.get(i));
                        arr2.remove(arr2.get(i));
                    }
            }
        }


        System.out.println("Added  : " + arr2);
        System.out.println("deleted  : " + arr1);

    }
}



        /*for (int b : arr2) {
            if (!arr1.contains(b)) {
                added.add(b);
            }
        }
        for (int a : arr1) {
            if (!arr2.contains(a)) {
                deleted.add(a);
            }
        }*/









