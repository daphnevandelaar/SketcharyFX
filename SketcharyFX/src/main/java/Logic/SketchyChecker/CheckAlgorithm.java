package Logic.SketchyChecker;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CheckAlgorithm {

    //TODO: spatie tussen een woord controleren
    //TODO: letters en cijfers

    public static boolean checkSketchy(String sketchy, String message){
        List<String> listMsg = createListOfWordsFromMessage(message);
        System.out.println("In ALG sktc: "+sketchy + " msg: " + message);
        return CheckWord(sketchy, listMsg);
    }

    //method to compare two words, and validate the guessed word. requires a word to guess(sketchy) and a guessed word.
    private static boolean CheckWord(String sketchy, List<String> message){

        //Step 1: check whole message for sketchy word
        for (String guessedWord : message) {

        //Step 2: check the words in message
            if(sketchy.equals(guessedWord)){
                //break; //staat het goede woord er tussen, stoppen met zoeken
                return true;
            }

        //Step 3: check for typos
            else{
        //Step 3: is there less than 2 typos?
                if(!MoreThanOneTypo(guessedWord, sketchy)){

        //Step 4: check place of typo, whether it is a nearby key on the keyboard
                    if(IsItANearbyKey(sketchy, guessedWord)){
                        return true;
                    }

                }
                else{ }

            }
        }
        
        return false; //wanneer niets overeenkomt met de sketchy 
    }

    private static boolean IsItANearbyKey(String sketchy, String guessedWord){
        int i = 0;
        int indexOfTypo = 0;
        Keyboard keyboard = new Keyboard();

    //Step 1: Check all letters in guessed word
        for (char letter : guessedWord.toCharArray()) {

    //Step 2: if the current letter is the same as sketchy letter on same place
            if(letter == sketchy.toCharArray()[i]){ }

    //Step 3: Check if Typo is a nearby key
            else{
                indexOfTypo = i;

    //Step 4: all possible nearby keys of typo
                Map<String, String> keys = keyboard.QwertyKeyboard();

                String typoLetter = ""+ letter;
                String nearbyKeys = keys.get(typoLetter);

    //Step 5: try all the nearby keys on typo place
                for (char typoCorrection : nearbyKeys.toCharArray()) {
                    String check = guessedWord.replace(guessedWord.charAt(i), typoCorrection);

    //Step 6: compare the guessed word (with typos) to sketchy
                    if(sketchy.equals(check)){ return true; }
                    else{  }
                }
            }
            i++; //new char index on sketchy
        }

       return false;
    }

    private static boolean MoreThanOneTypo(String sketchy, String guessedWord){
        int count = 0;
        int i = 0;

    //Step 1: Check all letters in guessed word
        for (char letter : guessedWord.toCharArray()) {
    //Step 2: if the current letter is the same as sketchy letter on same place
            if(letter == sketchy.toCharArray()[i]){

    //Step 3: if there is more than 1 typo, quit looking because there are too many typos
                //if(count > 1){ return true; }
            }
            else{
                count++;
                if(count > 1){ return true; }
            }
            i++; //new char index on sketchy
        }

        return false;
    }

    //method to create loose words from a message in chat
    //TODO: parameter string to message
    private static List<String> createListOfWordsFromMessage(String message){
        message = lowercaseAllWords(message);
        return Arrays.asList(message.split("\\s+"));
    }

    public static void main(String [] args)
    {
        List<String> message = createListOfWordsFromMessage("hallo, de plany kan best groot worden");
        CheckWord("plant", message);
        System.out.println("DITTTTT: " + checkSketchy("plant", "dit is een plant"));
        System.out.println(message);
    }

    private static String lowercaseAllWords(String message){
        return message.toLowerCase();
    }
}
