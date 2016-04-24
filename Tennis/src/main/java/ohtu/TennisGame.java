package ohtu;

public class TennisGame {
    
    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;
    private final String[] SCORE_NAMES = {"Love", "Fifteen", "Thirty", "Forty"};
    private final int SCORE_MAX = 4; 
    
    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name))
            m_score1 += 1;
        else
            m_score2 += 1;
    }

    public String getScore() {
        if (m_score1==m_score2) {
            return this.getEvenScore();
        }
        else if (m_score1>=SCORE_MAX || m_score2>=SCORE_MAX) {
            return this.getScoreAtMax();
        }
        return this.getScoreUnderMax();
    }
    
    private String getScoreAtMax(){
        int minusResult = m_score1-m_score2;
        if (minusResult==1) return "Advantage player1";
        else if (minusResult ==-1)return "Advantage player2";
        else if (minusResult>=2) return "Win for player1";
        else return "Win for player2"; 
    }
    
    private String getEvenScore(){
        if(m_score1 >= SCORE_MAX){
            return "Deuce";
        }
        return this.SCORE_NAMES[m_score1] + "-All";
    }
    
    private String getScoreUnderMax(){
        String score = "";
        int tempScore=0;
        for (int i=1; i<3; i++) {
            if (i==1) tempScore = m_score1;
            else { score+="-"; tempScore = m_score2;}
            score += this.SCORE_NAMES[tempScore];
        } 
        return score;
    }
}