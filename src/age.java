import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;

public class  age {
    private JTextField txtID;
    private JTextField txtNombre;
    private JTextField txtCel;
    private JTextField txtEmail;
    private JButton actualizarButton;
    private JButton buscarButton;
    private JPanel panel1;


    public age(){
        actualizarButton.setEnabled(false);
        txtNombre.setEnabled(false);
        txtCel.setEnabled(false);
        txtEmail.setEnabled(false);


        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarButton.setEnabled(true);
                txtNombre.setEnabled(true);
                txtCel.setEnabled(true);
                txtEmail.setEnabled(true);
            }
        });
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
        String password = "Wilson";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,user,password);

        }catch (Exception e){
            e.printStackTrace();
        }
        return con;
    }
}

