import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import java.text.SimpleDateFormat;

public class Voter {
    private String voteId;//投票id
    private String voteCandidateId;//被投票人
    private String voterName;//投票人名字
    private String time;//时间
    private String cpuid;//
    private InetAddress ip4;//

    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        time = df.format(new Date());
        try {
            ip4 = Inet4Address.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        try {
            long start = System.currentTimeMillis();
            Process process = Runtime.getRuntime().exec(
                    new String[]{"wmic", "cpu", "get", "ProcessorId"});
            process.getOutputStream().close();
            Scanner sc = new Scanner(process.getInputStream());
            sc.next();
            cpuid = sc.next();
            System.out.println(cpuid);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getVoteId() {
        return voteId;
    }

    public void setVoteId(String voteId) {
        this.voteId = voteId;
    }

    public String getVoteCandidateId() {
        return voteCandidateId;
    }

    public void setVoteCandidateId(String voteCandidateId) {
        this.voteCandidateId = voteCandidateId;
    }

    public String getVoterName() {
        return voterName;
    }

    public void setVoterName(String voterName) {
        this.voterName = voterName;
    }

    public String getTime() {
        return time;
    }


    public InetAddress getIp4() {
        return ip4;
    }

    public String getCpuid() {
        return cpuid;

    }


    public Voter(String voteId, String voteCandidateId, String voterName) {
        this.voteId = voteId;
        this.voteCandidateId = voteCandidateId;
        this.voterName = voterName;
    }
}
