import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.List;

//定义界面
public class ServerUI extends JFrame {
    JFrame root;
    JPanel display, candidate, voteData, menu;
    JButton chooseVote, dateSet, stop;
    JComboBox choice, choicecandidate;
    TextArea VoteDate, CandidateDate;
    JTextField voteid, candidateid, name;
    JButton update, newcandidate, del;

    List<Vote> Voteresult;
    List<Candidate> Candidateresult;

    public ServerUI() {
        DataBaseConnect dbConnect = new DataBaseConnect();
        try {
            choice = new JComboBox();
            choicecandidate = new JComboBox();
            Voteresult = dbConnect.selectVoteTable();
            System.out.println(Voteresult.size());
            choice.addItem("选择投票");
            for (int i = 0; i < Voteresult.size(); i++) {
                Vote item = Voteresult.get(i);
                System.out.println(item.getVoteName());
                choice.addItem(item.getVoteName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        //初始化控件
        root = new JFrame();
        display = new JPanel(new GridLayout(1, 2));
        candidate = new JPanel();
        //candidate.setBackground(Color.red);
        voteData = new JPanel();
        //voteData.setBackground(Color.yellow);
        CandidateDate = new TextArea();
        voteid = new JTextField(6);
        candidateid = new JTextField(6);
        name = new JTextField(6);
        update = new JButton("修改");
        newcandidate = new JButton("新增");
        del = new JButton("删除");
        candidate.add(choice);
        voteData.add(choicecandidate);
        voteData.add(CandidateDate);
        voteData.add(voteid);
        voteData.add(candidateid);
        voteData.add(name);
        voteData.add(update);
        voteData.add(newcandidate);
        voteData.add(del);
        VoteDate = new TextArea();

//        VoteDate.setSize(50,50);
//        VoteDate.setLocation(550, 250);

        choice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                CandidateDate.setText("");
                int index = choice.getSelectedIndex();
                if (index > 0) index--;
                VoteDate.setText("\t\t\t投票编号：" + Voteresult.get(index).getVoteId() + "\n\t\t\t投票名：" + Voteresult.get(index).getVoteName() + "\n\t\t\t开始时间：" + Voteresult.get(index).getStartDate().toString() + "\n\t\t\t结束时间：" + Voteresult.get(index).getStopDate().toString() + "\n\t\t\t当前票数：" + Voteresult.get(index).getAllVotes());
                try {
                    Candidateresult = dbConnect.selectCandidateTable(new TCPVoteMsg(000, Voteresult.get(index).getVoteId()));
//                    choicecandidate.removeItemListener(ItemEvent e);
//                    choicecandidate.removeAllItems();
                    for (Candidate i : Candidateresult) {
                        int n = choicecandidate.getSelectedIndex();
                        choicecandidate.addItem(i.getCandidateName());
                        CandidateDate.append("\t\t           编号：" + i.getCandidateId() + "  名字：" + i.getCandidateName() + "   获得票数：" + i.getVotes() + "\n");
                        choicecandidate.addItemListener(new ItemListener() {
                            @Override

                            public void itemStateChanged(ItemEvent e) {
                                int index = choicecandidate.getSelectedIndex();
                                System.out.println(index);
                                voteid.setText(Candidateresult.get(index).getVoteId());
                                candidateid.setText(Candidateresult.get(index).getCandidateId());
                                name.setText(Candidateresult.get(index).getCandidateName());
                            }
                        });
                    }
//                    update();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });


        //VoteDate.setBounds(550,250,100,200);
        candidate.add(VoteDate);
        menu = new JPanel();
        //chooseVote = new JButton("当前投票");
        dateSet = new JButton("更改时间");
        dateSet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dateChoose();
            }
        });

        stop = new JButton("停止服务");
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        //上
//        candidate.setBackground(Color.red);
//        voteData.setBackground(Color.blue);
//        menu.setBackground(Color.yellow);
        display.add(candidate);
        display.add(voteData);


        //底部以及根
        //menu.add(chooseVote);
        menu.add(dateSet);
        menu.add(stop);
        root.setSize(500, 400);
        root.setLocation(500, 200);
        root.setLayout(new BorderLayout());
        root.add(display, BorderLayout.CENTER);
        root.add(menu, BorderLayout.SOUTH);
        root.setVisible(true);
        root.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        root.setResizable(false);
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
        dateFrame.setLocation(500, 200);
        dateFrame.setVisible(true);
        dateFrame.setResizable(false);

    }

    public void update() {

    }

}
