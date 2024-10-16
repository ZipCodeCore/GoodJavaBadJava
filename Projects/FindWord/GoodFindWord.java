import java.util.Scanner;

// this is a decent example of why static simetimes works.

public class GoodFindWord {

    // this is real work in this class
    // notice it is static, which works fine for a method which takes some arguments
    // and returns a result
    // no need to create any objects here.
    public static int countWords(String input, String target) {
        int wordsLen, i, count=0;
        input = input.toLowerCase();
        target = target.toLowerCase();

        String words[] = input.split("\\s+");
        wordsLen = words.length;

        for(i=0; i<wordsLen; i++) {
            if(word.equals(words[i])) {
                count++;
            }
        }

        return count;
    }

    // main, in this case, should just run a single test
    public static void main(String[] args) {
        System.out.println(runInteractiveTest());
    }

    // this is a good example of why static sometimes works
    // this static test provides a way to test the function and an example of how to use it
    public static String runInteractiveTest() {
        String str, word;
        int wordsLen, i, count=0;
        Scanner s = new Scanner(System.in);

        System.out.print("Enter the String: ");
        str = s.nextLine();
        System.out.print("\nEnter a Word to Find its Occurrence: ");
        word = s.next();

        /*
         * an example of how to use the function
         */
        count = new GoodFindWord.countWords(str, word); 


        if(count==0)
            return "\nThe word \"" +word+ "\" is not found in the String.";
        else if(count==1)
            return "\nThe word \"" +word+ "\" occurs only one time.";
        else
            return "\nThe word \"" +word+ "\" found, " +count+ " times.";
    }
}
