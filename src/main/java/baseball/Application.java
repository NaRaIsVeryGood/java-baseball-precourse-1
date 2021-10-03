package baseball;

import nextstep.utils.Console;
import nextstep.utils.Randoms;

public class Application {
	
	static String RANDOM_VAL;
	static String INPUT_VAL;
	
	static int STRIKE = 0;
	static int BALL = 0;
	
	public static void main(String[] args) {

		RANDOM_VAL = random();
        
        while(true) {
        	gamePlay();
        }
    }
	
    public static void gamePlay() {
    	
    	// 값 입력
    	System.out.print("숫자를 입력해주세요 : ");
    	INPUT_VAL = Console.readLine();
    	System.out.println(INPUT_VAL);
    	
    	// 게임 평가
    	playEvaluation();
    	
    	// 게임 결과
    	playResult();

    	// 게임 재시작 여부 문의
    	if(STRIKE == 3) {
    		System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 끝");
    		
    		int gameRestartInputVal = 0;
        	while(gameRestartInputVal == 0) {
        		//gameRestartInputVal = gameRestartKeyIn();
        		System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        		
        		String inputVal = Console.readLine();
        		if( inputVal == null || ( !"1".equals(inputVal) && !"2".equals(inputVal) )) {
        			System.out.println("잘못입력했습니다.");
        			gameRestartInputVal = 0;
            	}
        		gameRestartInputVal = Integer.parseInt(inputVal);
        	}
        	if(gameRestartInputVal == 1) {
        		RANDOM_VAL = random();
        	}
        	if(gameRestartInputVal == 2) {
        		System.exit(0);
        	}
        	
    	}
    }
    
    public static String random() {
    	String randomVal = String.valueOf(Randoms.pickNumberInRange(1, 9)) + String.valueOf(Randoms.pickNumberInRange(1, 9)) + String.valueOf(Randoms.pickNumberInRange(1, 9));
    	System.out.println("randomNumber : " + randomVal);  
    	return randomVal;
    }

    
    public static int strikeCheck(char iv, char rv) {
    	
    	if(iv == rv) {
    		return 1;
    	}
    	
    	return 0;
    }
    
    public static int ballCheck(char iv, String rv) {
    	
    	if(rv.indexOf(iv) > -1) {
    		return 1;
    	}
    	
    	return 0;
    }
    
    public static void playEvaluation() {
    	// 초기화
    	STRIKE = 0;
    	BALL = 0;
    	int tmpBall = 0;
    	
    	// 스트라이크/볼 판독
    	for(int i=0; i < INPUT_VAL.length(); i++) {
    		STRIKE = STRIKE + strikeCheck(INPUT_VAL.charAt(i), RANDOM_VAL.charAt(i));
    		tmpBall = tmpBall + ballCheck(INPUT_VAL.charAt(i), RANDOM_VAL);
    	}
    	BALL = tmpBall - STRIKE;
    }

    //결과 리턴
    public static void playResult() {
    	
    	StringBuilder result = new StringBuilder();
    	if(STRIKE > 0) {
    		result.append(STRIKE).append("스트라이크 ");
    	}
    	if(BALL > 0) {
    		result.append(BALL).append("볼");
    	}
    	if(STRIKE == 0 && BALL == 0) {
    		result.append("낫싱");
    	}
    	
    	
    	System.out.println(result);
    }
    

}
