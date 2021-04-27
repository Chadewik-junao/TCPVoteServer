import java.sql.Date;

//投票类
public class Vote {
    private String voteId;//投票id
    private String voteName;//投票名
    private Date startDate, StopDate;//起止时间
    private int allVotes;//总票数

    public String getVoteId() {
        return voteId;
    }

    public void setVoteId(String voteId) {
        this.voteId = voteId;
    }

    public String getVoteName() {
        return voteName;
    }

    public void setVoteName(String voteName) {
        this.voteName = voteName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStopDate() {
        return StopDate;
    }

    public void setStopDate(Date stopDate) {
        StopDate = stopDate;
    }

    public int getAllVotes() {
        return allVotes;
    }

    public void setAllVotes(int allVotes) {
        this.allVotes = allVotes;
    }


}
