import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainPageOfTool implements ActionListener
{

    JFrame f;
    JPanel panel3;
    JPanel panel2 ;
    JButton b_bmi;
    JButton b_drink;
    JTextField tf_bmi;
    JTextField tf_drink;

    public void createMainPage()
    {
        f = new JFrame();
        panel2 = new JPanel();
        panel3 = new JPanel();
        tf_bmi = new JTextField();
        tf_drink = new JTextField();
        b_bmi = new JButton("�ӬݬݧA��BMI�a");
        b_drink = new JButton("�f���F��!! �ӪM���Ƨa");
        panel2.setBackground(Color.ORANGE);

        // set the font
        b_bmi.setFont(new Font(null, 1, 18));
        b_drink.setFont(new Font(null, 1, 18));
        tf_drink.setFont(new Font(null, 1, 18));
        tf_bmi.setFont(new Font(null, 1, 18));

        // === �]�wpanel�̭���Layout ===
        GroupLayout layout = new GroupLayout(panel2);  //�Ы�layout����
        panel2.setLayout(layout);  //�]�wpanel2 ��layout�n�ϥ�groupLayout
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Horizontal
        GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        hpg1.addComponent(b_bmi, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE);
        hpg1.addComponent(tf_bmi, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE);

        GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        hpg2.addComponent(b_drink, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE);
        hpg2.addComponent(tf_drink, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(hpg1)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, 20)
                .addGroup(hpg2)
        );

        // �� tf_bmi ��b_bmi tf_drink ������size�Τ@
        layout.linkSize(SwingConstants.VERTICAL, b_bmi, tf_bmi, tf_drink);

        // Vertical
        GroupLayout.ParallelGroup vpg1 = layout.createParallelGroup(GroupLayout.Alignment.BASELINE);
        vpg1.addComponent(b_bmi);//, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE);
        vpg1.addComponent(b_drink);//, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE);

        GroupLayout.ParallelGroup vpg2 = layout.createParallelGroup(GroupLayout.Alignment.BASELINE);
        vpg2.addComponent(tf_bmi); //,  GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE);
        vpg2.addComponent(tf_drink);//, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE);

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(vpg1)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, 20)
                .addGroup(vpg2)
        );


        // add EventListener
        b_bmi.addActionListener(this);
        b_drink.addActionListener(this);

        // �]�wFrame
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(panel2);
        f.pack();
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == b_bmi)
        {
            // Create BmiThreads
            BmiThreads bmiObj = new BmiThreads(this);
            bmiObj.start();
        }
        if (e.getSource() == b_drink)
        {
            // Create ComebuyMenu
            ComebuyThread drink_T = new ComebuyThread(this);
            Thread t = new Thread(drink_T);
            t.start();
        }
    }

    public static void main(String[] args) {
        MainPageOfTool mainPage = new MainPageOfTool();
        mainPage.createMainPage();
        // TODO: ���D�{���b�������bmain�U������ �åB�i�H�Q��Lclass�~�� �åB�O�d�ݭn����
    }
}


/**
 * �h�����(Thread)���I�sBMI�D�{����
 */
class BmiThreads extends Thread
{
    private MainPageOfTool frm;
    BmiThreads(MainPageOfTool frm)
    {
        this.frm = frm;
    }

    public void run()
    {
        new BMI_MultiThread_Swing_Practice(frm);
    }
}

/**
 * BMI Main Application.
 */
class BMI_MultiThread_Swing_Practice implements ActionListener {
    MainPageOfTool frm;
    JFrame f;
    JButton b_Bmi;
    JTextField tf1;
    JTextField tf2;
    JLabel l_BMI;
    float height;
    float weight;
    double ans;

    BMI_MultiThread_Swing_Practice(MainPageOfTool frm) {
        this.frm = frm;

        // Setting components
        f = new JFrame("Small tool");
        JPanel panel1 = new JPanel();
        JLabel l_height = new JLabel("����(��� cm)");
        JLabel l_weight = new JLabel("�魫(��� kg)");
        tf1 = new JTextField(10);
        tf2 = new JTextField(10);
        b_Bmi = new JButton("calc");
        l_BMI = new JLabel();

        // Set the font size
        l_height.setFont(new Font(null, 1, 16));
        l_weight.setFont(new Font(null, 1, 16));
        l_BMI.setFont((new Font(null, 1, 12)));
        panel1.setBackground(Color.orange);

        // Panel Layout
        GroupLayout layout = new GroupLayout(panel1);
        panel1.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // === Horizontal ===
        // ���W�������label
        GroupLayout.ParallelGroup hpg1 = layout.createParallelGroup(GroupLayout.Alignment.TRAILING);
        hpg1.addComponent(l_height, GroupLayout.DEFAULT_SIZE, 30, 100);  //���]��label���خ��H�۵e������j�]��۩�j
        hpg1.addComponent(l_weight, GroupLayout.DEFAULT_SIZE, 30, 100);

        // �k�W������ӿ�J��
        GroupLayout.ParallelGroup hpg2 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        hpg2.addComponent(tf1);
        hpg2.addComponent(tf2);

        // ��W����Ӹj�b�@�_
        GroupLayout.SequentialGroup hpg_s = layout.createSequentialGroup();
        hpg_s.addGroup(hpg1);
        hpg_s.addGroup(hpg2);

        // �U�������s(button) �� �̫���ܪ�����(label) +++ (�baddComponent �᭱������DEFAULT_SIZE�]�w, will make the button be resize when the window is)
        GroupLayout.ParallelGroup hpg3 = layout.createParallelGroup(GroupLayout.Alignment.CENTER);
        hpg3.addComponent(b_Bmi, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE);
        hpg3.addComponent(l_BMI);

        // ��W����ӳ��� �� �U�������� ��Parallel �]�w
        GroupLayout.ParallelGroup hpg4 = layout.createParallelGroup(GroupLayout.Alignment.CENTER);
        hpg4.addGroup(hpg_s);
        hpg4.addGroup(hpg3);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                        .addGroup(hpg4)
//                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, 10)
        );

        // === Vertical ===
        GroupLayout.ParallelGroup vpg1 = layout.createParallelGroup(GroupLayout.Alignment.BASELINE);
        vpg1.addComponent(l_height);
        vpg1.addComponent(tf1);

        GroupLayout.ParallelGroup vpg2 = layout.createParallelGroup(GroupLayout.Alignment.BASELINE);
        vpg2.addComponent(l_weight);
        vpg2.addComponent(tf2);

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(vpg1)
                .addGroup(vpg2)
                // this will add the Gap between "b_Bmi" and "vpg2"
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, 20)
                .addComponent(b_Bmi)
                .addComponent(l_BMI)
        );

        // add the EventListener
        b_Bmi.addActionListener(this);

        // === set the Frame ===
        // TODO: ������p�G�ϥ� "JFrame.EXIT_ON_CLOSE" �|�ɭP������BMI��, �W����MainPageOfTool�]������� ???
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*
            stackOverflow : {@link https://stackoverflow.com/questions/10873668/terminate-running-threads-on-jframe-close}

            Reason Why !!
            Using JFrame.EXIT_ON_CLOSE actually terminates the JVM (System.exit). All running threads will automatically be stopped.
            If you want to perform some action when a JFrame is about to close, use a WindowListener.
         */
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
            }
        });

        f.add(panel1);
        f.pack();
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b_Bmi) {
            height = Float.parseFloat(tf1.getText()) / 100;
            weight = Float.parseFloat(tf2.getText());

            // *** �Ыؤ@�� BmiThreads����ӳB�z�p�� (�Y�p��q���e�j, ��b�]���ɭԤ��v�T�A����L�Ʊ�, MultiThread ���Q�j��)
            Bmi_Calc_Threads bmiObj = new Bmi_Calc_Threads(this);
            Thread t = new Thread(bmiObj);
            t.start();
        }
    }
}


/**
 * This will handle the calculation when the user click the button<br>
 * �]�N�O��ݭn�B�⪺�ɭԨϥΦh����� �קK�Q�e�j���B�⵹�j��.
 */
class Bmi_Calc_Threads implements Runnable {
    private final BMI_MultiThread_Swing_Practice frm_bmi;  //�s��BMI�D���������
    private double ans;

    Bmi_Calc_Threads(BMI_MultiThread_Swing_Practice frm_bmi) {
        this.frm_bmi = frm_bmi;
    }

    public double calcBmi(float height, float weight) {
        ans = weight / (height * height);
        return ans;
    }

    @Override
    public void run() {
        // ���L�b�I��B��@����(�b�B�⪺�L�{���C�]�@���N�L�X�F���ܥL�٦b�]) �P�@�ɶ��ݨ�L�{���ٯ�_�B�@
        long start = System.currentTimeMillis();
        int count = 0;
        while (true) {
            count += 1;
            long elapsedTime = System.currentTimeMillis();

            // �C�����1500�����ƴN�L�@��
            if (count % 1500 == 0)
            {
                System.out.println("Still running.... ");
            }

            // �@�����@��N���X�o�Ӱj��
            if ((elapsedTime - start) / (60 * 1000) == 1) {
                break;
            }
        }

        System.out.println("know i got out !!! ");
        ans = calcBmi(frm_bmi.height, frm_bmi.weight);
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.##");
                frm_bmi.l_BMI.setText("�A��BMI: " + df.format(ans));
                String tf_data = frm_bmi.l_BMI.getText();

                if (tf_data != null) {
                    frm_bmi.frm.tf_bmi.setText(tf_data);
                }

//        // ������n�ϥγo�� invokeLater �̭���message�~�ॿ�T�a��� ????
//        EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                JOptionPane.showMessageDialog(f, "waiting! You can do somthing else right now\n�I��p�⤤....");
//            }
//        });
    }
}

