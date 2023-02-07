import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class age {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton actualizarButton;
    private JButton buscarButton;
    private JPanel panel1;

    public age(){

    }
    public static void main (String[]args){
        JFrame frame = new JFrame("Calculadora");
        frame.setContentPane(new age().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(300,300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static Connection getConetion(){
        Connection con = null;
        String base = "estudiante";
        String url = "jdbc:mysql://localhost:3306/" + base;
        String user = "root";
        String password = "marlon";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,user,password);

        }catch (Exception e){
            e.printStackTrace();
        }
        return con;
    }
}

