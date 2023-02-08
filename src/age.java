import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.xml.transform.Result;

public class  age {
    private JTextField txtID;
    private JTextField txtNombre;
    private JTextField txtCel;
    private JTextField txtEmail;
    private JButton actualizarButton;
    private JButton buscarButton;
    private JPanel panel1;

    Statement ps;
    PreparedStatement st;


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
                        txtNombre.setText(rs.getString("Nom_Clie"));
                        txtCel.setText(rs.getString("Cel_Clie"));
                        txtEmail.setText(rs.getString("Email_Clie"));
                    }
                }catch (Exception s){

                }
            }
        });

        actualizarButton.addActionListener(new ActionListener() {
            Connection con2;
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    con2 = getConection();
                    st = con2.prepareStatement("UPDATE clientes.persona SET Nom_Clie = ?, Cel_Clie = ?, Email_Clie = ? WHERE Id_Clie ="+txtID.getText() );

                    st.setString(1,txtNombre.getText());
                    st.setString(2,txtCel.getText());
                    st.setString(3,txtEmail.getText());

                    System.out.println(ps);
                    int res = st.executeUpdate();

                    if(res > 0 ){
                        JOptionPane.showMessageDialog(null,"La actualización se realizado con EXITO!");
                    }else{
                        JOptionPane.showMessageDialog(null,"Error, datos invalidos!! ERROR !!");
                    }
                    con2.close();
                }catch (HeadlessException | SQLException f){
                    System.out.println(f);
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

