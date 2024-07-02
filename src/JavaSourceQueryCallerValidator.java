import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class JavaSourceQueryCallerValidator {
    public static void main(String[] args) throws IOException {
        //C:\Users\OJH\Documents\GitHub\Algorithm\src\recursion\별찍기10
        BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\OJH\\Documents\\GitHub\\Algorithm\\src\\recursion\\별찍기10.java"));
        String line = null;
        while((line = readLine(in)) != null) {
            if(line.trim().length() == 0) continue;
            if(isMethodDeclare(line)) {
                String method = line;
                System.out.println("method  : " + method);
                List<String> methodLines = new ArrayList<>();
                Stack<Character> brackets = new Stack<>();
                String mLine = getMethodLevelBracketLine(in, method);
                while(true) {
                    char[] chars = mLine.toCharArray();
                    // System.out.println(chars);
                    for (char c : chars) {
                        if (c == '{') {
                            brackets.push(c);
                        } else if ( c== '}') {
                            if(brackets.peek() == '{') brackets.pop();
                        } else {
                            // skip
                        }
                    }
                    methodLines.add(new String(chars));
                    if(brackets.isEmpty()) {
                        System.out.println("end of method, start analyze");
                        analyzeQueryCaller(method, methodLines);
                        break;
                    }
                    // method level 단위로 소스를 분석할 떄는 소스가 정상이라는 전제가 있으므로
                    // EOF 가 발생하지 않음을 기대할 수 있다.
                    mLine = readLine(in);
                }
            }
        }
    }

    /**
     * Query Calller Logic 분석 method
     * 호출하는 Query의 binding 변수와 Parameter 객체의 binding String 개수와 일치하는지 확인한다
     * @param methodLines
     */
    static void analyzeQueryCaller(String methodName, List<String> methodLines) {

        Map<Parameter, Boolean> paramChecekd = new HashMap<>();
        Map<Parameters, Boolean> paramListChecked = new HashMap<>();
        // write analyze Code


        //
        methodLines.forEach(System.out::println);
    }


    static boolean isMethodDeclare(String line) {
        return line.matches("(\\s)*(public|private|static).*(\\(.*\\)).*");
    }

    static String getMethodLevelBracketLine(BufferedReader in, String methodDeclareLine) throws IOException {
        String line = methodDeclareLine;
        while(line != null && !line.contains("{")) {
            line = readLine(in);
        }
        return line;
    }

    static String readLine(BufferedReader in) throws IOException {
        String line = in.readLine();

        if(line == null) {
            // System.out.println("is EOF");
            return null;
        }
        // 단일 행 주석
        if(line.matches("(\\s)*//.*")) {
            return line.replaceAll("(\\s)*//.*", "");
        } else if(line.matches("(\\s)*/\\*(\\*?)*.*"))
        // javadoc 주석 (/**
        {
            String nextLine = line;
            while(!nextLine.matches(".*\\*/(\\s)*")) {
                nextLine = in.readLine();
            }
            return "";
        }
        return line.trim();

    }


    static class Parameter {
        String var;
        Parameters params;
    }

    static class Parameters {
        String var;
        int bindCnt;

    }
}
