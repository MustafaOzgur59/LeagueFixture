public class Main {
    public static void main(String[] args) {
        //arguments: team names
        // length correction for odd number of teams
        int length = args.length % 2 == 0 ? args.length : args.length + 1;

        Team[][] team2D = populate(length,  args);
        turnArray(team2D);
        getFixture(length,team2D);
    }

    public static Team[][] populate(int length, String[] args) {
        Team[][] team2D = new Team[2][length/2];
        try {
            for (int i = 0; i < 2; i++) {
                for(int j=0; j < length/2 ;  j++){
                    team2D[i][j] = new Team(args[i * (length/2) + j], i * (length/2) + j);
                }
            }
        } catch (Exception e) {
            // do nothing
        }

        // means total team count increased because of odd number of teams
        if(length> args.length) team2D[1][length/2-1] = new Team("Bay",length);
        return team2D;
    }

    public static void getFixture(int length, Team[][] teams){
        StringBuilder entireFixture = new StringBuilder();
        for (int i=0;i< length-1;i++){
            entireFixture.append("Round ").append(i + 1).append("\n");
            for(int j=0;j<teams[0].length;j++){
                entireFixture.append(teams[0][j].getName()).append(" vs ").append(teams[1][j].getName()).append("\n");
            }
            turnArray(teams);
            System.out.println();
        }
        //second half
        for (int i=length;i< 2 *length-2;i++){
            entireFixture.append("Round ").append(i + 1).append("\n");
            for(int j=0;j<teams[0].length;j++){
                entireFixture.append(teams[0][j].getName()).append(" vs ").append(teams[1][j].getName()).append("\n");
            }
            turnArray(teams);
            System.out.println();
        }
        System.out.println(entireFixture);
    }

    // turns the array every week of the fixture
    // because two opposing teams in the first and second rows of the array do the match
    // this func ensure that every round different pairs have a match
    public static void turnArray(Team[][] teams2D){
        /*
        * Ex
        * 1 2 3 4  -----> 5 1 2 3
        * 5 6 7 8         6 7 8 4
        * */
        int rowCount = 2;
        int colCount = teams2D[0].length;
        Team temp = teams2D[0][0];
        Team temp2 = teams2D[0][1];
        // first row turn;
        for (int i=1; i<colCount;i++){
            if (i == colCount-1){
                temp2 = teams2D[0][colCount-1];
                teams2D[0][colCount-1] = temp;
                temp = temp2;
                temp2 = teams2D[1][colCount-1];
                teams2D[1][colCount-1] = temp;
                temp = temp2;
            } else{
                temp2 = teams2D[0][i];
                teams2D[0][i] = temp;
                temp = temp2;
            }
        }
        // second row turn
        for (int i =colCount-2;i>=0;i--){
            if (i == 0){
                temp2 = teams2D[1][0];
                teams2D[1][0] = temp;
                teams2D[0][0] = temp2;
            } else {
                temp2 = teams2D[1][i];
                teams2D[1][i] = temp;
                temp = temp2;
            }
        }
    }
}