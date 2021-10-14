/*
    選擇Comebuy飲料店的菜單 選完飲料之後還要能選擇冰塊甜度, 還可以增加一些附加的東西
    (ex. 珍珠, 野果 ....)
    然後算出他的價錢
    最後秀出她的飲料甜度冰塊 以及價錢
 */

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.event.*;

/**
 * Enum drink: 是Comebuy飲料的類別, 為的是方便取得資料, 也比較好對應
 */
enum drink
{
    DRINKCLASS("飲料類別"),
    ORIGINTEA("原葉鮮萃茶"),
    FRUITTEA("鮮調果茶"),
    MILKTEA("奶茶可可"),
    WINTERONLY("冬季限定");

    private String className;

    // Constructor
    drink(String className)
    {this.className = className;}

    public String getName()
    { return this.className; }
}

/**
 * drinkList: 是對應到各個類別裡面的飲料( 價錢 and 名字 )
 */
enum drinkList
{
    A(35, 0, "鮮萃麥香紅茶"),
    B(40, 0, "碧螺春"),
    C(40, 0, "四季春"),
    D(40, 0, "玫瑰普洱"),
    E(40, 0, "海神"),
    F(40, 0, "烏龍綠茶"),
    G(30, 25, "錫蘭紅茶"),
    H(30, 25, "茉莉綠茶"),
    I(45, 40, "蜂蜜紅/綠茶"),
    J(45, 0, "玉荷冰綠"),
    K(50, 45, "檸檬冰茶"),
    L(50, 45, "梅果茶"),
    M(45, 40, "招牌奶茶"),
    N(45, 40, "黃金奶綠"),
    O(50, 45, "珍珠奶茶"),
    P(55, 50, "絕代雙Q奶茶"),
    Q(55, 0, "海神奶茶"),
    R(50, 0, "鮮萃麥香奶茶"),
    S(50, 45, "熱檸檬"),
    T(55, 50, "紫米可可"),
    U(80, 60, "抹茶拿鐵"),
    V(60, 0, "宇治抹茶"),
    W(40, 0, "桂圓紅棗"),
    X(50, 0, "暖薑奶茶");

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
 * additional: 指的是 額外的附加料 ( 價錢 and 名字 )
 */
enum additional
{
    A(5, "珍珠"),
    B(10, "椰果"),
    C(10, "荔枝凍"),
    D(10, "寒天晶球"),
    E(10, "小芋園"),
    F(15, "布丁"),
    G(15, "仙草凍");

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
    JComboBox preBox;  // 用來儲存當前顯示的飲料類別 Ex. originTea, fruitTea, milkTea 或是 winterOnly 其中一個
    JLabel l_ice;
    JLabel l_suger;
    JComboBox cb_ice;
    JComboBox cb_suger;
    JComboBox cb_addOn;
    JRadioButton rb_SizeL;
    JRadioButton rb_SizeM;


    ComebuyMenu_Practice(MainPageOfTool frm)
    {
        // 把傳過來的MainPageOfTool 放進frm變數裡
        this.frm = frm;

        setTitle("Comebuy menu Order System");
//        String[] drinkClass = {"飲料類別", "原葉鮮萃茶", "鮮調果茶", "奶茶/可可", "冬季限定"};
        String[] drinkClass = {drink.DRINKCLASS.getName(), drink.ORIGINTEA.getName(), drink.FRUITTEA.getName(), drink.MILKTEA.getName(), drink.WINTERONLY.getName()};
        String[] originTea = {"鮮萃麥香紅茶", "碧螺春", "四季春", "玫瑰普洱", "海神", "烏龍綠茶"};
        String[] fruitTea = {"錫蘭紅茶", "茉莉綠茶", "蜂蜜紅/綠茶", "玉荷冰綠", "檸檬冰茶", "梅果茶"};
        String[] milkTea = {"招牌奶茶", "黃金奶綠", "珍珠奶茶", "絕代雙Q奶茶", "海神奶茶", "鮮萃麥香奶茶"};
        String[] winterOnly = {"熱檸檬", "紫米可可", "抹茶拿鐵", "宇治抹茶", "桂圓紅棗", "暖薑奶茶"};



        String[] ice = {"正常冰", "少冰", "去冰"};
        String[] suger = {"正常", "少糖", "半糖", "微糖", "無糖"};
        String[] addOn = {"選擇是否加料", "珍珠", "椰果", "荔枝凍", "寒天晶球", "小芋園", "布丁", "仙草凍"};

        l_drinkClass = new JLabel("請選擇你的飲料類別:");
        cb_drinkClass = new JComboBox(drinkClass);
        cb_originTea = new JComboBox(originTea);
        cb_fruitTea = new JComboBox(fruitTea);
        cb_milkTea = new JComboBox(milkTea);
        cb_winterOnly = new JComboBox(winterOnly);
        l_ice = new JLabel("請選擇冰塊:");
        l_suger = new JLabel("請選擇甜度:");
        cb_ice = new JComboBox(ice);
        cb_suger = new JComboBox(suger);
        cb_addOn = new JComboBox(addOn);
        l_total = new JLabel();
        b_count = new JButton("試算目前總金額??");
        b_checkOut = new JButton("CheckOut!!");
        rb_SizeL = new JRadioButton("Large! 大杯");
        rb_SizeM = new JRadioButton("Medium! 中杯");
        //ButtonGroup bg = new ButtonGroup();

        // 飲料類別
        l_drinkClass.setBounds(20, 20, 200, 30);
        cb_drinkClass.setBounds(20, 50, 200, 30);
        add(l_drinkClass); add(cb_drinkClass);

        // 冰塊甜度, 加料
        l_ice.setBounds(20, 150, 80, 30);
        l_suger.setBounds(120, 150, 80, 30);
        cb_ice.setBounds(20, 180, 80, 30);
        cb_suger.setBounds(120, 180, 80, 30);
        cb_addOn.setBounds(20, 240, 100, 30);

        // TODO: 加料可以做複選的
        add(l_ice); add(l_suger);
        add(cb_ice); add(cb_suger); add(cb_addOn);

        // Size
        rb_SizeL.setBounds(280, 50, 130, 30);
        rb_SizeM.setBounds(280, 100, 130, 30);
        ButtonGroup bg = new ButtonGroup();
        bg.add(rb_SizeL); bg.add(rb_SizeM);
        add(rb_SizeL); add(rb_SizeM);


        // 試算多少錢
        b_count.setBounds(260, 150, 140, 50);
        l_total.setBounds(270, 200, 200, 30);
        add(l_total); add(b_count);

        // Checkout
        b_checkOut.setBounds(150, 280, 150, 50);
        add(b_checkOut);

        // addActionListener (指派事件的發生)
        cb_drinkClass.addActionListener(this);

        /*
        if (preBox != null)
            preBox.addActionListener(this);
        如果放在這邊的話 因為preBox 一開始是null, 也就是說一開始不會讀進去, 之後也不會讀進去(所以放在這邊沒有用)
        要放在當 drinkClass 被點擊之後的最後面
        */

        b_count.addActionListener(this);
        b_checkOut.addActionListener(this);


        setSize(450, 400);
        setLayout(null);
        setVisible(true);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //這邊不能放這個 不然當你關掉飲料畫面的時候 其他的thread也會一同被關掉
    }

    /**
     * <p>
     * 這段code主要是為了監聽物件觸發的事件發生, 當事件發生就會跳進這邊, 裡面我用了if...else來判斷
     * 是哪一個物件.
     *
     * @param e ActionEvent 會包含物件的名稱以及相關資訊
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

            // 當使用者在選擇的時候, 可能會反覆切換, 所以當選擇一個飲料類別的時候, 要把上一個顯示的給關掉, 才不會影響到新的飲料清單的顯示
            if (preBox != null)
            { remove(preBox); }

            // 當上面得到飲料類別的名字後, 為了找到對應的飲料清單, 所以製作了兩個methods 為的是可以在視窗上秀出對應的飲料清單
            preBox = showComboBox(getDrinkClass(drinkClassName));
            preBox.addActionListener(this);
        }
        else if (e.getSource() == preBox)
        {
            // TODO: 判斷如果只有大杯的 就只秀出大杯的選項
            // 如果cost_M = 0 就代表沒有中杯的
            boolean station = false;

            drinkName = String.valueOf(preBox.getItemAt(preBox.getSelectedIndex()));
            generateSize(drinkName);
        }
        else if (e.getSource() == b_count || e.getSource() == b_checkOut)
        {
            // TODO: 判斷使用者點的是哪一個類型的飲料 可以用preBox來做
            // 只有在使用者有選擇類別之後 點擊試算按鈕, 或是checkout按鈕才會有動作
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
                    // 取得RadioBox裡面的Large關鍵字
                    size = rb_SizeL.getText().split("!")[0];
                }
                else if (rb_SizeM.isSelected())
                {
                    // 取得RadioBox裡面的Medium關鍵字
                    size = rb_SizeM.getText().split("!")[0];
                }

                try
                {
                    // convert String size to Type drinkSize
                    drinkSize dSize = drinkSize.valueOf(size.toUpperCase());
                    // TODO: totalCost
                    totalCost = getDrinkCost(drinkName, dSize);

                    if (addOn != "選擇是否加料")
                        totalCost += getAdditionalCost(addOn);

                    l_total.setText("你目前的總金額是" + totalCost + "元");
                }
                catch (NullPointerException ec)
                {
                    JOptionPane.showMessageDialog(this, "請選擇您要的尺寸 大杯或中杯");
                    System.out.println(ec.getMessage());
                    return;  // 這個return的用意是: 當使用者沒有選擇Size的時候 執行完這邊之後就結束這個methods, 防止下面的清單被執行.
                }

                // TODO: 做出清單明細
                if (e.getSource() == b_checkOut)
                {
                    // TODO: 如果是checkOut的話才做這一部份
                    ice = String.valueOf(cb_ice.getItemAt(cb_ice.getSelectedIndex()));
                    suger = String.valueOf(cb_suger.getItemAt(cb_suger.getSelectedIndex()));
                    addOn = String.valueOf(cb_addOn.getItemAt(cb_addOn.getSelectedIndex()));

                    String msg = "";
                    msg += "飲料: " + drinkName;
                    if (!addOn.equals("選擇是否加料"))
                    {
                        msg += "加" + addOn + "\n";
                    }
                    else
                    {
                        msg += "\n";
                    }
                    msg += "Size: " + size + "\n";
                    msg += "冰塊: " + ice + "\n甜度: " + suger + "\n";
                    msg += "--------------------\n";
                    msg += "您消費的金額一共是: " + totalCost + "元";

                    JOptionPane.showMessageDialog(this, msg);
                    frm.tf_drink.setText("您消費的金額一共是: " + totalCost + "元");
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

            // 上面的方式是刪掉 或是重新添加, 但我們可以使用隱藏的方式讓他不顯示 (比較直接)
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
     * @param drinkClassName 表示你選擇的飲料類別的名字
     * @return 回傳一個drink Object (好讓你使用showComboBox)
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
     * @param d 要求你丟一個 資料型態是 drink 的參數
     * @return 把你丟進的飲料類別顯示在視窗上, 並回傳當下操作的ComboBox Object, 以便之後刪除使
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