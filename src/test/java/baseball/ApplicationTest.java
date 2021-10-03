package baseball;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mockStatic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.MockedStatic;

import com.ginsberg.junit.exit.ExpectSystemExit;

import nextstep.test.NSTest;
import nextstep.utils.Randoms;

@TestMethodOrder(OrderAnnotation.class)
public class ApplicationTest extends NSTest {
	
	
    @BeforeEach
    void beforeEach() {
        super.setUp();
    }
    
    @Test
    @Order(1)
    void 낫싱() {
        try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
            mockRandoms
                    .when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
                    .thenReturn(1, 3, 5);
            running("246");
            verify("낫싱");
        }
    }

    @Test
    @Order(2)
    public void 게임_스트라이크_후_재시작() {
        try (final MockedStatic<Randoms> mockedStatic = mockStatic(Randoms.class)) {
        	mockedStatic
            		.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
                    .thenReturn(7, 1, 3)
                    .thenReturn(5, 8, 9);
        	
        	running("713", "1", "518");
        	verify("3스트라이크","게임 끝", "1스트라이크 1볼");
        }
        
    }
    
    @Test
    @Order(3)
    @ExpectSystemExit
    public void 게임_스트라이크_후_종료() {
        try (final MockedStatic<Randoms> mockedStatic = mockStatic(Randoms.class)) {
        	mockedStatic
            		.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
                    .thenReturn(7, 1, 3);
        	
        	run("713", "2");
        }
    }

    @Test
    @Order(4)
    void 게임종료_후_잘못입력() {
        try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
            mockRandoms.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
                    .thenReturn(7, 1, 3)
                    .thenReturn(5, 8, 9);
            running("713", "ㄹㄹ", "1", "597");
            verify("3스트라이크", "잘못입력했습니다.", "1스트라이크 1볼");
        }
    }

    
    @Disabled("단위 테스트 - 전체 test 제외")
    @Test
    @Order(5)
    void 볼_스트라이크_판독() {
    	
		Application.RANDOM_VAL = "234";
    	Application.INPUT_VAL = "234";
    	
    	Application.playEvaluation();
    	Application.playResult();
    	
    	//System.out.println(output());
    	assertEquals(output(), "3스트라이크");
    }

    
	@Disabled("단위 테스트 - 전체 test 제외")
    @Test
    @Order(6)
    void 랜덤_중복문자열체크() {
        //Application.main(null);
        
    	try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
            mockRandoms.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
                    .thenReturn(8)
            		.thenReturn(9);
            
            String randomVal = "78";
            while(randomVal.length() < 3) {
            	String tmvVal = String.valueOf(Randoms.pickNumberInRange(1, 9));
            	randomVal += Application.isUniqeRandomNumber(randomVal, tmvVal);
        	}
        	System.out.println("result : " + randomVal);  
        	
        	//verify("789");
        }
    	
    }
    
    @AfterEach
    void tearDown() {
        outputStandard();
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
