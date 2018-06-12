package Logic.SketchyChecker;

import java.util.HashMap;
import java.util.Map;

public class Keyboard {
    private static Map<String, String> qwertyKeyboard = new HashMap<String, String>();

    //create all possible typos on a qwerty keyboard, all letters next to each other are valid.
    public void CreatePossibleTyposQwerty(){
        qwertyKeyboard.put("q", "wsa");
        qwertyKeyboard.put("w", "qasde");
        qwertyKeyboard.put("e", "wsdfr");
        qwertyKeyboard.put("r", "edfgt");
        qwertyKeyboard.put("t", "rfghy");
        qwertyKeyboard.put("y", "tghju");
        qwertyKeyboard.put("u", "yhjki");
        qwertyKeyboard.put("i", "ujklo");
        qwertyKeyboard.put("p", "ol;'[");
        qwertyKeyboard.put("a", "qwsxz");
        qwertyKeyboard.put("s", "wqazxcde");
        qwertyKeyboard.put("d", "werfvcxs");
        qwertyKeyboard.put("f", "ertgbvcd");
        qwertyKeyboard.put("g", "rtyhnbvf");
        qwertyKeyboard.put("h", "tyujmnbg");
        qwertyKeyboard.put("j", "yuik,mnh");
        qwertyKeyboard.put("k", "uiol.,mj");
        qwertyKeyboard.put("l", "iop;/.,k");
        qwertyKeyboard.put("z", "asx");
        qwertyKeyboard.put("x", "zasdc ");
        qwertyKeyboard.put("c", "xsdfv ");
        qwertyKeyboard.put("v", "cdfgb ");
        qwertyKeyboard.put("b", "cdfgb ");
        qwertyKeyboard.put("n", "bghjm ");
        qwertyKeyboard.put("m", "nhjk, ");
    }

    //method to make the keyboard public and accessible in another class
    public Map<String, String> QwertyKeyboard(){
        CreatePossibleTyposQwerty();
        //System.out.println(qwertyKeyboard.get("r"));
        return qwertyKeyboard;
    }
}
