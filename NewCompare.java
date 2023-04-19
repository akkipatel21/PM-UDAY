import java.util.ArrayList;

public class NewCompare {

	public static void main(String[] args) {

            ArrayList<Integer> added = new ArrayList<>();
            ArrayList<Integer> deleted = new ArrayList<>();

            int arr1[] = {2, 3, 4, 5};
            int arr2[] = {1, 3, 4, 6};
            boolean contains = false;

            for (int i = 0; i < arr1.length; i++) {
                for (int j = 0; j < arr2.length; j++) {
                    if(arr2[j]==arr1[i]){
                       contains=true;
                       break;
                    }
                }

                if(!contains){
                    added.add(arr2[i]);
                    deleted.add(arr1[i]);
                }
                else {

//                  deleted.add(arr1[i]);
                    contains=false;
                }

            }

            /*for (int i = 0; i < arr2.length; i++) {
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

            }*/
System.out.println("added " +added);
System.out.println("deleted "+ deleted);

        }


}
