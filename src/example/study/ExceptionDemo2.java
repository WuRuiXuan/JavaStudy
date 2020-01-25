package example.study;

/**
 * 自定义异常
 */

public class ExceptionDemo2 {
    public static void main(String[] args) {
        try {
            boolean b = login("abc", "123");
            System.out.println("login: " + b);
        }
        catch (MyException e) {
            e.printStackTrace();

        }
    }

    public static boolean login(String name, String pass) throws MyException {
        if (!name.equals("admin")) {
            throw new MyException("用户名不正确");
        }
        if (!pass.equals("123")) {
            throw new MyException("密码不正确");
        }
        return true;
    }
}

class MyException extends Exception {
	private static final long serialVersionUID = 1L;
	private String message;
	
    public MyException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}


