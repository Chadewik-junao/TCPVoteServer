import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
    JTextField voteName;
    JButton update, newcandidate, del, updatevotename;
    JTextField newvoteid, newvotename;
    JButton newvote;

    String starttime,stoptime;

    List<Vote> Voteresult;
    List<Candidate> Candidateresult;
    DataBaseConnect dbConnect;

    public ServerUI() {
        dbConnect= new DataBaseConnect();
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
        voteid.setText("投票id");
        //提示文字消失触发器
//        voteid.addMouseListener(new MouseListener() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                voteid.setText("");
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//
//            }
//
//        });
        candidateid = new JTextField(6);
        candidateid.setText("候选人id");
//        candidateid.addMouseListener(new MouseListener() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                candidateid.setText("");
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//
//            }
//
//        });
        name = new JTextField(6);
        name.setText("候选人名字");
//        name.addMouseListener(new MouseListener() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                name.setText("");
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//
//            }
//
//        });
        update = new JButton("修改");
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println(dbConnect.updatecandidate(voteid.getText(), candidateid.getText(), name.getText()));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                updateUI();
            }
        });
        newcandidate = new JButton("新增");
        newcandidate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println(dbConnect.newcandidate(voteid.getText(), candidateid.getText(), name.getText()));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                updateUI();
            }
        });
        //del = new JButton("删除");
        candidate.add(choice);


        voteData.add(choicecandidate);
        choicecandidate.setVisible(true);
        voteData.add(CandidateDate);
        voteData.add(voteid);
        voteData.add(candidateid);
        voteData.add(name);
        voteData.add(update);
        voteData.add(newcandidate);
        //voteData.add(del);
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
                //voteName.setText(Voteresult.get(index).getVoteName());
                try {
                    Candidateresult = dbConnect.selectCandidateTable(new TCPVoteMsg(000, Voteresult.get(index).getVoteId()));
//                    choicecandidate.removeItemListener(ItemEvent e);
//                    choicecandidate.removeAllItems();
                    for (Candidate i : Candidateresult) {
                        int n = choicecandidate.getSelectedIndex();
                        choicecandidate.addItem(i.getCandidateName());
                        CandidateDate.append("\t\t           编号：" + i.getCandidateId() + "  名字：" + i.getCandidateName() + "   获得票数：" + i.getVotes() + "\n");
//                        choicecandidate.addItemListener(new ItemListener() {
//                            @Override
//                            public void itemStateChanged(ItemEvent e) {
//                                int index = choicecandidate.getSelectedIndex();
//                                //System.out.println(index);
//                                voteid.setText(Candidateresult.get(index).getVoteId());
//                                candidateid.setText(Candidateresult.get(index).getCandidateId());
//                                name.setText(Candidateresult.get(index).getCandidateName());
//                            }
//                        });
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

        voteName = new JTextField(6);
        voteName.setText("投票名修改");
//        voteName.addMouseListener(new MouseListener() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                voteName.setText("");
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//
//            }
//        });
        updatevotename = new JButton("更新");
        updatevotename.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = choice.getSelectedIndex();
                if (index > 0) index--;
                try {
                    System.out.println(dbConnect.updatevote(voteName.getText(), Voteresult.get(index).getVoteId()));
                    updateUI();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            }
        });
        candidate.add(voteName);
        candidate.add(updatevotename);
        candidate.add(dateSet);

        newvoteid = new JTextField(5);
        newvotename = new JTextField(5);
        newvote = new JButton("新增");
        newvote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newvote();
            }
        });
        candidate.add(newvoteid);
        candidate.add(newvotename);
        candidate.add(newvote);
//        JButton newvotetime=new JButton("时间");
//        candidate.add(newvotetime);
//        newvotetime.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                dateChoose();
//            }
//        });


        //底部以及根
        //menu.add(chooseVote);
        //menu.add(dateSet);
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
        dateFrame.getContentPane().setLayout(new FlowLayout());
        DateChooser dateStart = new DateChooser(dateFrame);
        DateChooser dateStop = new DateChooser(dateFrame);
        dateFrame.add(labelStart);
        dateFrame.getContentPane().add(dateStart);
        dateFrame.add(labelStop);
        dateFrame.getContentPane().add(dateStop);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dateFrame.dispose();
            }
        });
        JButton submit = new JButton("确认");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("起始:"+dateStart.getText());
                System.out.println("结束:"+dateStop.getText());
                starttime=dateStart.getText();
                stoptime=dateStop.getText();
                updatetime();
                dateFrame.dispose();
            }
        });
        dateFrame.add(submit);
        dateFrame.add(cancel);
        dateFrame.setSize(300, 130);
        dateFrame.setLocation(500, 200);
        dateFrame.setVisible(true);
        dateFrame.setResizable(false);

    }

    public void updatetime(){
        try {
            System.out.println();
            int index = choice.getSelectedIndex();
            if (index > 0) index--;
            System.out.println(dbConnect.updatevotetime(Voteresult.get(index).getVoteId(),starttime,stoptime));
            updateUI();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void newvote(){
        try {
            dbConnect.newvote(newvoteid.getText(),newvotename.getText());
            updateUI();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void updateUI() {
        new ServerUI();
        this.root.dispose();
    }
}
