package org.eolang.lints;

public class PrintAllLints {
    public static void main(String[] args) {
        Program.LINTS.forEach(lint -> {
            try {
                System.out.println(lint.motive());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

}
