package stubs;

import java.util.ArrayList;

public class AppFlowStack {

    private static ArrayList<String> stack = new ArrayList<>();

    public static void addStack(String content)
    {
        stack.add(content);
    }

    public static ArrayList<String> getStack() {
        return stack;
    }
}
