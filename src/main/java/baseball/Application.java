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
    	
    	// 값 입력  ===================
    	System.out.print("숫자를 입력해주세요 : ");
    	INPUT_VAL = Console.readLine();
    	System.out.println(INPUT_VAL);
    	
    	
    	// 게임 평가 ===================
    	
    	// 초기화
    	STRIKE = 0;
    	BALL = 0;
    	int tmpBall = 0;
    	
    	// 스트라이크/볼 판독
    	for(int i=0; i < INPUT_VAL.length(); i++) {
    		
    		if(INPUT_VAL.charAt(i) == RANDOM_VAL.charAt(i)) {
    			STRIKE++;
        	}
    		
    		if(RANDOM_VAL.indexOf(INPUT_VAL.charAt(i)) > -1) {
    			tmpBall++;
    		}
    		
    	}
    	BALL = tmpBall - STRIKE;
    	
    	// 결과 리턴  ===================
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
    
    public static String random() {
    	String randomVal = String.valueOf(Randoms.pickNumberInRange(1, 9)) + String.valueOf(Randoms.pickNumberInRange(1, 9)) + String.valueOf(Randoms.pickNumberInRange(1, 9));
    	System.out.println("randomNumber : " + randomVal);  
    	return randomVal;
    }

    
}
