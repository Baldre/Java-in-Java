import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Compiler {

    public static void main(String[] args) {
        final String PATH = "src/TestCorrect.java";
        try {
            System.out.println(returnedInt(PATH));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static int returnedInt(String PATH) throws Exception {
        String className = PATH.split("/")[PATH.split("/").length - 1].split("\\.")[0];
        Process gen = Runtime.getRuntime().exec("javac -cp src " + PATH); //make into array members if breaks
        gen.waitFor();
        File file = new File("src\\" + className + ".class");

        Process pro = Runtime.getRuntime().exec("java -cp src " + className);
        String line;
        int time;
        BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
        if ((line = in.readLine()) != null) {
            time = Integer.parseInt(line);
            pro.waitFor();
            file.delete();
            return time;
        }
        else {
            pro.waitFor();
            file.delete();
            throw new RuntimeException("No int returned or bad return format");
        }
    }

}
