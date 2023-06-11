import java.lang.reflect.Method;
import java.util.Objects;

public class Main {
    static void runMain(String className, String inputFilePath, String outputFilePath) throws ReflectiveOperationException {
        System.out.println(className.substring(0, className.indexOf(".java")));
        Class clazz = Class.forName(className.substring(0, className.indexOf(".java")));
        Method method = clazz.getDeclaredMethod("main", String[].class);
        System.out.println(method.getName());
        System.out.println(method.isVarArgs());
        String[] params = {inputFilePath, outputFilePath};
        method.invoke(null, (Object) params); // this was tricky, initially using varargs or Object[]
    }
    public static void main(String[] args) throws ReflectiveOperationException {
        for(int i=0;i<3;i++) {
            Objects.nonNull(args[i]);
        }

        String mapOrReduceJavaFilePath = args[0]; // "Mapper.java"
        String inputFilePath =  args[1]; // "input.txt"
        String destinationFilePath =  args[2]; // "output.txt" or "reducer.txt"
        System.out.println(mapOrReduceJavaFilePath + "\t" + inputFilePath+ "\t" + destinationFilePath);
        runMain(args[0], args[1], args[2]);


    }
}
