package arrays;

import java.util.ArrayList;
import java.util.Scanner;

public class VarReader {

    // список переменных, хранит массив из 3х элементов: название, выражение, значение
    static ArrayList<String[]> datas = new ArrayList<String[]>();

    public static void main(String[] arg) {

	String text = "";
	String[] input;
	Scanner sc = new Scanner(System.in);

	do {
	    text = sc.nextLine();
	    text = text.replace(" ", "");

	    if (text.contains("=")) {
		input = text.split("=");
		datas.add(new String[]{input[0], input[1], ""});
		check();
	    }
	} while (text.length() > 0);// возможность завершения ввода, иначе "while(true)"

    }

    // рассчет выражений для переменных, собранных в списке
    public static void check() {
	for (String[] data : datas) {
	    if (data[2].equals("")) {
		data[2] = calcExpression(data[1]);
		if (!(data[2].equals(""))) {
		    System.out.println("===> " + data[0] + " = " + data[2]);
		    check();
		}
	    }
	}
    }

    // расчет переданного выражения
    public static String calcExpression(String exp) {
	String val = "";
	int result = 0;

	String[] args = exp.split("[+]");

	// перебираем аргументы выражения
	for (int i = 0; i < args.length; i++) {
	    String arg = args[i];
	    //если аргумент число
	    if (arg.matches("[\\d]+")) {
		result += Integer.valueOf(arg);
	    } //иначе аргумент - переменная
	    else {
		val = getVal(arg);
		if (!val.equals("")) {
		    result += Integer.valueOf(getVal(arg));
		} else {
		    return "";
		}
	    }
	}
	return String.valueOf(result);
    }

    // поиск переменной и возврат "выражения"
    public static String getExp(String varName) {
	String exp = "";

	for (String[] data : datas) {
	    if (data[0].equals(varName)) {
		exp = data[1];
	    }
	}

	return exp;
    }

    // поиск переменной и возврат "значения"
    public static String getVal(String varName) {
	String val = "";

	for (String[] data : datas) {
	    if (data[0].equals(varName)) {
		val = data[2];
	    }
	}

	return val;
    }
}
