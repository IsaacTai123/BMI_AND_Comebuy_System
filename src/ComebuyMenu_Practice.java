/*
    ���Comebuy���Ʃ������ �粒���Ƥ����٭n���ܦB������, �٥i�H�W�[�@�Ǫ��[���F��
    (ex. �ï], ���G ....)
    �M���X�L������
    �̫�q�X�o�����Ʋ��צB�� �H�λ���
 */

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.event.*;

/**
 * Enum drink: �OComebuy���ƪ����O, �����O��K���o���, �]����n����
 */
enum drink
{
    DRINKCLASS("�������O"),
    ORIGINTEA("�츭�A�ѯ�"),
    FRUITTEA("�A�ժG��"),
    MILKTEA("�����i�i"),
    WINTERONLY("�V�u���w");

    private String className;

    // Constructor
    drink(String className)
    {this.className = className;}

    public String getName()
    { return this.className; }
}

/**
 * drinkList: �O������U�����O�̭�������( ���� and �W�r )
 */
enum drinkList
{
    A(35, 0, "�A�ѳ�������"),
    B(40, 0, "�����K"),
    C(40, 0, "�|�u�K"),
    D(40, 0, "�������|"),
    E(40, 0, "����"),
    F(40, 0, "�Q�s���"),
    G(30, 25, "��������"),
    H(30, 25, "�[�����"),
    I(45, 40, "���e��/���"),
    J(45, 0, "�ɲ��B��"),
    K(50, 45, "�f�c�B��"),
    L(50, 45, "���G��"),
    M(45, 40, "�۵P����"),
    N(45, 40, "��������"),
    O(50, 45, "�ï]����"),
    P(55, 50, "���N��Q����"),
    Q(55, 0, "��������"),
    R(50, 0, "�A�ѳ�������"),
    S(50, 45, "���f�c"),
    T(55, 50, "���̥i�i"),
    U(80, 60, "�ٯ����K"),
    V(60, 0, "�t�v�ٯ�"),
    W(40, 0, "�۶����"),
    X(50, 0, "�x������");

    private int cost_L;
    private int cost_M;
    private String drinkName;

    // Constructor
    drinkList(int cost_L, int cost_M, String drinkName)
    {
        this.cost_L = cost_L;
        //this.cost_M = cost_M == 0 ? 0 : cost_M;
        this.cost_M = cost_M;
        this.drinkName = drinkName;
    }

    public String getDrinkName()
    { return this.drinkName; }

    public int getCost_L()
    { return this.cost_L; }

    public int getCost_M()
    { return this.cost_M; }
}

enum drinkSize
{
    LARGE,
    MEDIUM
}

/**
 * additional: �����O �B�~�����[�� ( ���� and �W�r )
 */
enum additional
{
    A(5, "�ï]"),
    B(10, "���G"),
    C(10, "��K��"),
    D(10, "�H�Ѵ��y"),
    E(10, "�p����"),
    F(15, "���B"),
    G(15, "�P���");

    private int cost;
    private String addtional;

    // Constructor
    additional(int cost, String additional)
    {
        this.cost = cost;
        this.addtional = additional;
    }

    public String getName()
    {
        return this.addtional;
    }

    public int getCost()
    { return this.cost; }
}

class ComebuyThread implements Runnable
{
    private MainPageOfTool frm;
    public ComebuyThread(MainPageOfTool frm)
    {
        this.frm = frm;
    }

    public void run()
    {
        new ComebuyMenu_Practice(frm);
    }
}

public class ComebuyMenu_Practice extends JFrame implements ActionListener
{
    MainPageOfTool frm;
    JLabel l_total;
    JButton b_count;
    JButton b_checkOut;
    JLabel l_drinkClass;
    JComboBox cb_drinkClass;
    JComboBox cb_originTea;
    JComboBox cb_fruitTea;
    JComboBox cb_milkTea;
    JComboBox cb_winterOnly;
    JComboBox preBox;  // �Ψ��x�s��e��ܪ��������O Ex. originTea, fruitTea, milkTea �άO winterOnly �䤤�@��
    JLabel l_ice;
    JLabel l_suger;
    JComboBox cb_ice;
    JComboBox cb_suger;
    JComboBox cb_addOn;
    JRadioButton rb_SizeL;
    JRadioButton rb_SizeM;


    ComebuyMenu_Practice(MainPageOfTool frm)
    {
        // ��ǹL�Ӫ�MainPageOfTool ��ifrm�ܼƸ�
        this.frm = frm;

        setTitle("Comebuy menu Order System");
//        String[] drinkClass = {"�������O", "�츭�A�ѯ�", "�A�ժG��", "����/�i�i", "�V�u���w"};
        String[] drinkClass = {drink.DRINKCLASS.getName(), drink.ORIGINTEA.getName(), drink.FRUITTEA.getName(), drink.MILKTEA.getName(), drink.WINTERONLY.getName()};
        String[] originTea = {"�A�ѳ�������", "�����K", "�|�u�K", "�������|", "����", "�Q�s���"};
        String[] fruitTea = {"��������", "�[�����", "���e��/���", "�ɲ��B��", "�f�c�B��", "���G��"};
        String[] milkTea = {"�۵P����", "��������", "�ï]����", "���N��Q����", "��������", "�A�ѳ�������"};
        String[] winterOnly = {"���f�c", "���̥i�i", "�ٯ����K", "�t�v�ٯ�", "�۶����", "�x������"};



        String[] ice = {"���`�B", "�֦B", "�h�B"};
        String[] suger = {"���`", "�ֿ}", "�b�}", "�L�}", "�L�}"};
        String[] addOn = {"��ܬO�_�[��", "�ï]", "���G", "��K��", "�H�Ѵ��y", "�p����", "���B", "�P���"};

        l_drinkClass = new JLabel("�п�ܧA���������O:");
        cb_drinkClass = new JComboBox(drinkClass);
        cb_originTea = new JComboBox(originTea);
        cb_fruitTea = new JComboBox(fruitTea);
        cb_milkTea = new JComboBox(milkTea);
        cb_winterOnly = new JComboBox(winterOnly);
        l_ice = new JLabel("�п�ܦB��:");
        l_suger = new JLabel("�п�ܲ���:");
        cb_ice = new JComboBox(ice);
        cb_suger = new JComboBox(suger);
        cb_addOn = new JComboBox(addOn);
        l_total = new JLabel();
        b_count = new JButton("�պ�ثe�`���B??");
        b_checkOut = new JButton("CheckOut!!");
        rb_SizeL = new JRadioButton("Large! �j�M");
        rb_SizeM = new JRadioButton("Medium! ���M");
        //ButtonGroup bg = new ButtonGroup();

        // �������O
        l_drinkClass.setBounds(20, 20, 200, 30);
        cb_drinkClass.setBounds(20, 50, 200, 30);
        add(l_drinkClass); add(cb_drinkClass);

        // �B������, �[��
        l_ice.setBounds(20, 150, 80, 30);
        l_suger.setBounds(120, 150, 80, 30);
        cb_ice.setBounds(20, 180, 80, 30);
        cb_suger.setBounds(120, 180, 80, 30);
        cb_addOn.setBounds(20, 240, 100, 30);

        // TODO: �[�ƥi�H���ƿ諸
        add(l_ice); add(l_suger);
        add(cb_ice); add(cb_suger); add(cb_addOn);

        // Size
        rb_SizeL.setBounds(280, 50, 130, 30);
        rb_SizeM.setBounds(280, 100, 130, 30);
        ButtonGroup bg = new ButtonGroup();
        bg.add(rb_SizeL); bg.add(rb_SizeM);
        add(rb_SizeL); add(rb_SizeM);


        // �պ�h�ֿ�
        b_count.setBounds(260, 150, 140, 50);
        l_total.setBounds(270, 200, 200, 30);
        add(l_total); add(b_count);

        // Checkout
        b_checkOut.setBounds(150, 280, 150, 50);
        add(b_checkOut);

        // addActionListener (�����ƥ󪺵o��)
        cb_drinkClass.addActionListener(this);

        /*
        if (preBox != null)
            preBox.addActionListener(this);
        �p�G��b�o�䪺�� �]��preBox �@�}�l�Onull, �]�N�O���@�}�l���|Ū�i�h, ����]���|Ū�i�h(�ҥH��b�o��S����)
        �n��b�� drinkClass �Q�I�����᪺�̫᭱
        */

        b_count.addActionListener(this);
        b_checkOut.addActionListener(this);


        setSize(450, 400);
        setLayout(null);
        setVisible(true);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //�o�䤣���o�� ���M��A�������Ƶe�����ɭ� ��L��thread�]�|�@�P�Q����
    }

    /**
     * <p>
     * �o�qcode�D�n�O���F��ť����Ĳ�o���ƥ�o��, ��ƥ�o�ʹN�|���i�o��, �̭��ڥΤFif...else�ӧP�_
     * �O���@�Ӫ���.
     *
     * @param e ActionEvent �|�]�t���󪺦W�٥H�ά�����T
     *
     * @throws NullPointerException     If there is no input for size it will show {@code null}.
     * @see ActionEvent#getSource()
     */
    public void actionPerformed(ActionEvent e)
    {
        int totalCost = 0;
        String drinkName = null;

        if (e.getSource() == cb_drinkClass)
        {
            String drinkClassName = String.valueOf(cb_drinkClass.getItemAt(cb_drinkClass.getSelectedIndex()));
            int n = cb_drinkClass.getSelectedIndex();

            // ��ϥΪ̦b��ܪ��ɭ�, �i��|���Ф���, �ҥH���ܤ@�Ӷ������O���ɭ�, �n��W�@����ܪ�������, �~���|�v�T��s�����ƲM�檺���
            if (preBox != null)
            { remove(preBox); }

            // ��W���o�춼�����O���W�r��, ���F�����������ƲM��, �ҥH�s�@�F���methods �����O�i�H�b�����W�q�X���������ƲM��
            preBox = showComboBox(getDrinkClass(drinkClassName));
            preBox.addActionListener(this);
        }
        else if (e.getSource() == preBox)
        {
            // TODO: �P�_�p�G�u���j�M�� �N�u�q�X�j�M���ﶵ
            // �p�Gcost_M = 0 �N�N��S�����M��
            boolean station = false;

            drinkName = String.valueOf(preBox.getItemAt(preBox.getSelectedIndex()));
            generateSize(drinkName);
        }
        else if (e.getSource() == b_count || e.getSource() == b_checkOut)
        {
            // TODO: �P�_�ϥΪ��I���O���@������������ �i�H��preBox�Ӱ�
            // �u���b�ϥΪ̦�������O���� �I���պ���s, �άOcheckout���s�~�|���ʧ@
            if (preBox != null)
            {
                String size = null;
                String ice;
                String suger;
                String addOn;

                drinkName = String.valueOf(preBox.getItemAt(preBox.getSelectedIndex()));
                addOn = String.valueOf(cb_addOn.getItemAt(cb_addOn.getSelectedIndex()));

                // TODO: Large or Medium
                if (rb_SizeL.isSelected())
                {
                    // ���oRadioBox�̭���Large����r
                    size = rb_SizeL.getText().split("!")[0];
                }
                else if (rb_SizeM.isSelected())
                {
                    // ���oRadioBox�̭���Medium����r
                    size = rb_SizeM.getText().split("!")[0];
                }

                try
                {
                    // convert String size to Type drinkSize
                    drinkSize dSize = drinkSize.valueOf(size.toUpperCase());
                    // TODO: totalCost
                    totalCost = getDrinkCost(drinkName, dSize);

                    if (addOn != "��ܬO�_�[��")
                        totalCost += getAdditionalCost(addOn);

                    l_total.setText("�A�ثe���`���B�O" + totalCost + "��");
                }
                catch (NullPointerException ec)
                {
                    JOptionPane.showMessageDialog(this, "�п�ܱz�n���ؤo �j�M�Τ��M");
                    System.out.println(ec.getMessage());
                    return;  // �o��return���ηN�O: ��ϥΪ̨S�����Size���ɭ� ���槹�o�䤧��N�����o��methods, ����U�����M��Q����.
                }

                // TODO: ���X�M�����
                if (e.getSource() == b_checkOut)
                {
                    // TODO: �p�G�OcheckOut���ܤ~���o�@����
                    ice = String.valueOf(cb_ice.getItemAt(cb_ice.getSelectedIndex()));
                    suger = String.valueOf(cb_suger.getItemAt(cb_suger.getSelectedIndex()));
                    addOn = String.valueOf(cb_addOn.getItemAt(cb_addOn.getSelectedIndex()));

                    String msg = "";
                    msg += "����: " + drinkName;
                    if (!addOn.equals("��ܬO�_�[��"))
                    {
                        msg += "�[" + addOn + "\n";
                    }
                    else
                    {
                        msg += "\n";
                    }
                    msg += "Size: " + size + "\n";
                    msg += "�B��: " + ice + "\n����: " + suger + "\n";
                    msg += "--------------------\n";
                    msg += "�z���O�����B�@�@�O: " + totalCost + "��";

                    JOptionPane.showMessageDialog(this, msg);
                    frm.tf_drink.setText("�z���O�����B�@�@�O: " + totalCost + "��");
                }
            }
        }
    }

    public int getAdditionalCost(String additionalName)
    {
        additional[] aObj = additional.values();
        for (additional a : aObj)
        {
            if (additionalName.equals(a.getName()))
                return a.getCost();
        }
        return 0;
    }

    public void generateSize(String drinkName)
    {
        drinkList drinkObj = getSpeDrink(drinkName);
        if (drinkObj.getCost_M() == 0)
        {
//            remove(rb_SizeM);
//            revalidate();
//            repaint();

            // �W�����覡�O�R�� �άO���s�K�[, ���ڭ̥i�H�ϥ����ê��覡���L����� (�������)
            rb_SizeM.setVisible(false);
        }
        else if (drinkObj.getCost_M() != 0)
        {
//            add(rb_SizeM);
//            revalidate();
//            repaint();
            rb_SizeM.setVisible(true);
        }
    }

    public int getDrinkCost(String drinkName, drinkSize size)
    {
        drinkList[] dArray = drinkList.values();
        for (drinkList d : dArray)
        {
            if (drinkName.equals(d.getDrinkName()))
            {
                switch (size)
                {
                    case LARGE:
                        return d.getCost_L();
                    case MEDIUM:
                        return d.getCost_M();
                }
            }
        }
        return 0;
    }

    public drinkList getSpeDrink(String drinkName)
    {
        drinkList[] dArray = drinkList.values();
        for (drinkList d : dArray)
        {
            if (drinkName.equals(d.getDrinkName()))
            {
                return d;
            }
        }
        return null;
    }

    /**
     * @param drinkClassName ��ܧA��ܪ��������O���W�r
     * @return �^�Ǥ@��drink Object (�n���A�ϥ�showComboBox)
     */
    public drink getDrinkClass(String drinkClassName)
    {
        drink[] dArray = drink.values();
        for (drink d : dArray)
        {
            if (drinkClassName.equals(d.getName()))
                return d;
        }
        return null;
    }

    /**
     * @param d �n�D�A��@�� ��ƫ��A�O drink ���Ѽ�
     * @return ��A��i���������O��ܦb�����W, �æ^�Ƿ�U�ާ@��ComboBox Object, �H�K����R����
     */
    public JComboBox showComboBox(drink d)
    {
        switch(d)
        {
            case DRINKCLASS:
                break;
            case ORIGINTEA:
                cb_originTea.setBounds(20, 100, 200, 30);
                add(cb_originTea);
                return cb_originTea;
            case FRUITTEA:
                cb_fruitTea.setBounds(20, 100, 200, 30);
                add(cb_fruitTea);
                return cb_fruitTea;
            case MILKTEA:
                cb_milkTea.setBounds(20, 100, 200, 30);
                add(cb_milkTea);
                return cb_milkTea;
            case WINTERONLY:
                cb_winterOnly.setBounds(20, 100, 200, 30);
                add(cb_winterOnly);
                return cb_winterOnly;
        }
        return null;
    }
}