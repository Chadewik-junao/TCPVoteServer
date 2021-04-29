import java.io.Serializable;
import java.sql.Date;

//候选者类
public class Candidate implements Serializable {
    private String voteId;//投票id
    private String candidateId;//候选人编号
    private String candidateName;//候选人名字

    public void setVoteId(String voteId) {
        this.voteId = voteId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    private int votes;//当前候选人获得票数

    public String getVoteId() {
        return voteId;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public int getVotes() {
        return votes;
    }
}
