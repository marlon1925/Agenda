import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class  age {
    private JTextField txtID;
    private JTextField txtNombre;
    private JTextField txtCel;
    private JTextField txtEmail;
    private JButton actualizarButton;
    private JButton buscarButton;
    private JPanel panel1;

    Statement ps;


    public age(){
        actualizarButton.setEnabled(false);
        txtNombre.setEnabled(false);
        txtCel.setEnabled(false);
        txtEmail.setEnabled(false);

        // Restringir entrada en txtName a solo letras
        ((AbstractDocument) txtID.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string.matches("[0-9]+")) {
                    super.insertString(fb, offset, string, attr);
                                    }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text.matches("[0-9]+")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });


        //Controla el ingrso para que no imgrese mas de 10 numeros
        txtID.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(txtID.getText().length()>=10){
                    e.consume();
                }
            }
        });

        //Controla el ingrso para que no imgrese mas de 10 numeros
        txtCel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(txtCel.getText().length()>=10){
                    e.consume();
                }
            }
        });




        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarButton.setEnabled(true);
                txtNombre.setEnabled(true);
                txtCel.setEnabled(true);
                txtEmail.setEnabled(true);

                Connection con;

                try{
                    con = getConection();
                    ps = con.createStatement();
                    ResultSet rs;
                    rs=ps.executeQuery("select * from clientes.persona where Id_Clie="+txtID.getText()+";");
                    while (rs.next()){
                        txtNombre.setText(rs.getString("NOMBRE"));
                    }
                }catch (Exception s){

                }
            }
        });
    }


    public static Connection getConection(){
        Connection con = null;
        String base= "clientes";
        String url = "jdbc:mysql://localhost:3306/" + base;
        String user = "root";
        String password = "123456";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,user,password);
        }catch (ClassNotFoundException | SQLException e){
            System.err.println(e);
        }
        return con;
    }


    public static void main (String[]args){
        JFrame frame = new JFrame("Base Datos");
        frame.setContentPane(new age().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(300,300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


}

