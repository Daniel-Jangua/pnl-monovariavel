import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.imageio.*;

public class Janela extends JFrame{

    JTextField txtFx,txtA,txtB,txtD,txtE,txtX;
    JButton btnGo,btnStop,btnClear;
    JRadioButton buni,bdic,fib,bis,newton,secau;
    JTextArea txtIt;
    ButtonGroup selecMet;

    String f = "";
    double a = 0, b = 0;
    double deltaEpsilon = 0;
    
    Janela(String titulo){
        super(titulo);
        JLabel lblFx = new JLabel("f(x) = ");
        JLabel lblA = new JLabel("a = ");
        JLabel lblB = new JLabel("b = ");
        JLabel lblD = new JLabel("Δ = ");
        //JLabel lblE = new JLabel("ε = ");
        JLabel lblX = new JLabel("x* = ");
        txtFx = new JTextField(30);
        txtA = new JTextField(10);
        txtB = new JTextField(10);
        txtE = new JTextField(10);
        txtD = new JTextField(10);
        txtX = new JTextField(10);
        txtX.setEditable(false);
        Image imGo = null,imClear = null,imStop = null;
        try{
            imGo = ImageIO.read(getClass().getResource("Imagens/forward.gif"));
            imClear = ImageIO.read(getClass().getResource("Imagens/delete.gif"));
            imStop = ImageIO.read(getClass().getResource("Imagens/stop2.gif")); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Imagem de ícone não encontrada!\n" + e.toString());
            System.exit(1);
        }
        btnGo = new JButton(new ImageIcon(imGo));
        btnGo.setContentAreaFilled(false);
        btnClear = new JButton(new ImageIcon(imClear));
        btnClear.setContentAreaFilled(false);
        btnStop = new JButton(new ImageIcon(imStop));
        btnStop.setContentAreaFilled(false);
        
        buni = new JRadioButton("Busca Uniforme",true);
        bdic = new JRadioButton("Busca Dicotômica");
        secau = new JRadioButton("Seção Áurea");
        fib = new JRadioButton("Fibonacci");
        bis = new JRadioButton("Bisseção");
        newton = new JRadioButton("Newton");

        selecMet = new ButtonGroup();
        selecMet.add(buni);
        selecMet.add(bdic);
        selecMet.add(secau);
        selecMet.add(fib);
        selecMet.add(bis);
        selecMet.add(newton);

        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new GridLayout(2,3));
        radioPanel.add(buni);
        radioPanel.add(fib);
        radioPanel.add(bis);
        radioPanel.add(bdic);
        radioPanel.add(secau);
        radioPanel.add(newton);

        radioPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Métodos"));

        lblFx.setBounds(10,20,40,16);
        lblA.setBounds(318, 20, 30, 16);
        lblB.setBounds(427, 20, 30, 16);
        txtFx.setBounds(55, 20, 250, 20);
        txtA.setBounds(350, 20, 64, 20);
        txtB.setBounds(458, 20, 64, 20);
        lblD.setBounds(535, 20, 30, 16);
        txtD.setBounds(567, 20,64,20);
        btnGo.setBounds(534,56,95,30);
        btnClear.setBounds(534,97,95,30);

        JPanel pnSup = new JPanel(null);
        pnSup.add(lblFx);
        pnSup.add(lblA);
        pnSup.add(lblB);
        pnSup.add(txtFx);
        pnSup.add(txtA);
        pnSup.add(txtB);
        pnSup.add(lblD);
        pnSup.add(txtD);
        pnSup.add(btnGo);
        pnSup.add(btnClear);
        pnSup.add(radioPanel);

        pnSup.setBorder(BorderFactory.createTitledBorder("Função, intervalo de busca e seleção do método"));
        UIManager.put("TitledBorder.border", new LineBorder(new Color(0,0,0), 1));
        pnSup.setBounds(10,10,641, 140);
        radioPanel.setBounds(10,50,514,80);

        JPanel pnInf = new JPanel(null);
        pnInf.setBorder(BorderFactory.createTitledBorder("Solução e iterações"));
        pnInf.setBounds(10,160,641,280);

        lblX.setBounds(20,20,35,16);
        txtX.setBounds(60,20,250,20);
        pnInf.add(lblX);
        pnInf.add(txtX);

        ActionListener radioChangeListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == buni || e.getSource() == bdic){
                    lblD.setText("Δ = ");
                    txtD.setToolTipText("Valor do Delta");
                }else{
                    lblD.setText("ε = ");
                    txtD.setToolTipText("Valor do Epsilon");
                }
            }
        };

        buni.addActionListener(radioChangeListener);
        bdic.addActionListener(radioChangeListener);
        secau.addActionListener(radioChangeListener);
        fib.addActionListener(radioChangeListener);
        bis.addActionListener(radioChangeListener);
        newton.addActionListener(radioChangeListener);

        btnClear.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                txtFx.setText("");
                txtA.setText("");
                txtB.setText("");
                txtD.setText("");
                txtX.setText("");
                txtIt.setText("");
            }
        });

        txtIt = new JTextArea();
        //txtIt.setEditable(false);
        JScrollPane paneIt = new JScrollPane(txtIt);
        paneIt.setBounds(20,50, 601,210);
        pnInf.add(paneIt);

        btnGo.setToolTipText("Calcular");
        btnClear.setToolTipText("Limpar dados");
        txtFx.setToolTipText("Função para minimização");
        txtA.setToolTipText("Início do intervalo");
        txtB.setToolTipText("Fim do intervalo");
        txtD.setToolTipText("Valor do Delta");
        txtX.setToolTipText("Solução do PNL");
        txtIt.setToolTipText("Descrição das iterações");
        txtIt.setEditable(false);

        btnGo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    f = txtFx.getText();
                    a = Double.parseDouble(txtA.getText());
                    b = Double.parseDouble(txtB.getText());
                    deltaEpsilon = Double.parseDouble(txtD.getText());
                }catch(NullPointerException npe){
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente!\n\n"+npe.toString(),"Erro",JOptionPane.ERROR_MESSAGE);
                    return;
                }catch(NumberFormatException nfe){
                    JOptionPane.showMessageDialog(null, "Preencha todos os caampos corretamente!\n\n"+nfe.toString(),"Erro",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try{
                    Interpretador.FxR1(f, a);
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Função inválida!\n\n"+ex.toString(),"Erro",JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(buni.isSelected()){
                    buscaUniforme(f, a, b, deltaEpsilon);
                }else if(bdic.isSelected()){
                    buscaDico(f, a, b, deltaEpsilon);
                }else if(secau.isSelected()){
                    secaoAurea(f, a, b, deltaEpsilon);
                }else if(fib.isSelected()){
                    fibonacci(f, a, b, deltaEpsilon);
                }else if(bis.isSelected()){
                    bissecao(f, a, b, deltaEpsilon);
                }else{
                    newton(f, a, b, deltaEpsilon);
                }
                
            }
        });

        this.setLayout(null);
        this.add(pnSup);
        this.add(pnInf);
        this.setSize(661,480);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void buscaUniforme(String f, double a, double b, double delta){
        JOptionPane.showMessageDialog(this, "Método não implementado!", "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public void buscaDico(String f, double a, double b, double delta){
        JOptionPane.showMessageDialog(this, "Método não implementado!", "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public void secaoAurea(String f, double a, double b, double epsilon){
        JOptionPane.showMessageDialog(this, "Método não implementado!", "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public void fibonacci(String f, double a, double b, double epsilon){
        JOptionPane.showMessageDialog(this, "Método não implementado!", "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public void bissecao(String f, double a, double b, double epsilon){
        JOptionPane.showMessageDialog(this, "Método não implementado!", "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public void newton(String f, double a, double b, double epsilon){
        JOptionPane.showMessageDialog(this, "Método não implementado!", "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String args []){
        new Janela("Trabalho PO II: PNL Monovariável");
    }
}