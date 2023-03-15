package support.verification;

// lam cho biet
// static la class method
public class Verifier {

    public static void verifyEquals(String actualResult, String expectedResult){
        if (!actualResult.equals(expectedResult)){
            System.out.printf("Actual is %s\nExpected is %s\n", actualResult, expectedResult);
            throw new AssertionError("Actual result and expected result is different!");
        }
    }
}
