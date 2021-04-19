import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerUI extends JFrame {
    JFrame root;
    JPanel display, candidate, voteData, menu;

    JButton chooseVote, dateSet, start, stop;

    public ServerUI() {
        //初始化控件
        root = new JFrame();
        display = new JPanel(new GridLayout(1, 2));
        candidate = new JPanel();
        voteData = new JPanel();
        menu = new JPanel();
        chooseVote = new JButton("当前投票：");
        dateSet = new JButton("更改时间");
        dateSet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dateChoose();
            }
        });
        start = new JButton("打开服务");
        stop = new JButton("停止服务");


        //上
        candidate.setBackground(Color.red);
        voteData.setBackground(Color.blue);
        menu.setBackground(Color.yellow);
        display.add(candidate);
        display.add(voteData);


        //底部以及根
        menu.add(chooseVote);
        menu.add(dateSet);
        menu.add(start);
        menu.add(stop);
        root.setSize(500, 400);
        root.setLayout(new BorderLayout());
        root.add(display, BorderLayout.CENTER);
        root.add(menu, BorderLayout.SOUTH);
        root.setVisible(true);
        root.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //dateChoose();
    }

    public void dateChoose() {
        JFrame dateFrame = new JFrame();
        JLabel labelStart = new JLabel("起始时间");
        JLabel labelStop = new JLabel("截至时间");
        JButton cancel = new JButton("取消");
        JButton submit = new JButton("确认");
        dateFrame.getContentPane().setLayout(new FlowLayout());
        DateChooser dateStart = new DateChooser(dateFrame);
        DateChooser dateStop = new DateChooser(dateFrame);
        dateFrame.add(labelStart);
        dateFrame.getContentPane().add(dateStart);
        dateFrame.add(labelStop);
        dateFrame.getContentPane().add(dateStop);
        dateFrame.add(submit);
        dateFrame.add(cancel);
        dateFrame.setSize(300, 130);
        dateFrame.setVisible(true);
        dateFrame.setResizable(false);
    }
}
