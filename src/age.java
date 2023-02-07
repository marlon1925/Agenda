import javax.swing.*;

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
}

