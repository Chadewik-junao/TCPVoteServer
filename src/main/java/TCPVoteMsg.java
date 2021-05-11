import java.io.IOException;
import java.io.Serializable;
import java.util.List;


public class TCPVoteMsg implements Serializable {
    private static final long serialVersionUID = 111L;
    //自定义实体类，作为对象数据流传输，需要继承java.io.Serializable，使用对象进行序列化

    //private static final long serialVersionUID = 1111;
    private int statusCode;
    //状态码
    private String voteId;
    private String candidateId;
    private List<Vote> voteList;
    private List<Candidate> candidateList;
    private Voter voter;



    //状态码，查询全部投票；服务端返回消息
    public TCPVoteMsg(int statusCode) {
        this.statusCode = statusCode;
    }

    //状态码和投票id，查询某投票候选者
    public TCPVoteMsg(int statusCode, String voteId) {
        this.statusCode = statusCode;
        this.voteId = voteId;
    }

    //状态码、投票id、候选人id，查询某一候选人当前投票获得票数
    public TCPVoteMsg(int statusCode, String voteId, String candidateId) {
        this.statusCode = statusCode;
        this.voteId = voteId;
        this.candidateId = candidateId;
    }

    //返回投票表结果
    public TCPVoteMsg(int statusCode, List<Vote> voteList) {
        this.statusCode = statusCode;
        this.voteList = voteList;
    }

    //返回候选表对应数据
    public TCPVoteMsg(int statusCode, String voteId, String candidateId, List<Candidate> candidateList) {
        this.statusCode = statusCode;
        this.voteId = voteId;
        this.candidateId = candidateId;
        this.candidateList = candidateList;
    }

    public String getVoteId() {
        return voteId;
    }

    public String getCandidateId() {
        return candidateId;
    }


    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public List<Vote> getVoteList() {
        return voteList;
    }

    public List<Candidate> getCandidateList() {
        return candidateList;
    }

    public Voter getVoter() { return voter; }

}
