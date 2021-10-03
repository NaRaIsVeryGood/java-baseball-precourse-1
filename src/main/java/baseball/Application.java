package baseball;

import nextstep.utils.Console;
import nextstep.utils.Randoms;

public class Application {
	
	public static void main(String[] args) {

		String randomVal = String.valueOf(Randoms.pickNumberInRange(1, 9)) + String.valueOf(Randoms.pickNumberInRange(1, 9)) + String.valueOf(Randoms.pickNumberInRange(1, 9));
    	System.out.println("randomNumber : " + randomVal);  
        
    	System.out.print("숫자를 입력해주세요 : ");
    	String inputVal = Console.readLine();
    	
    	System.out.println(inputVal);
    	
    }
}
