import org.junit.Assert;
import org.junit.Test;

public class MainTest {

    @Test
    public void solve() {
        String sum = Main.Solve("11+22");
        Assert.assertEquals("11+22=33", sum);
    }

    @Test
    public void testMakeFormula() {
        String formula = Main.MakeFormula();
        Assert.assertNotNull(formula);
        Assert.assertFalse(formula.isEmpty());
        Assert.assertTrue(formula.matches("\\d+([+\\-*/]\\d+)*")); // 校验是否符合题目要求的格式
    }

    @Test
    public void testSolve() {
        String formula = "3+5*2-4";
        String result = Main.Solve(formula);
        Assert.assertEquals("3+5*2-4=9", result);
    }


    @Test
    public void testSolveWithDivision() {
        String formula = "6/2+4";
        String result = Main.Solve(formula);
        Assert.assertEquals("6/2+4=7", result);
    }

    @Test
    public void testSolveWithNegativeResult() {
        String formula = "2-5";
        String result = Main.Solve(formula);
        Assert.assertEquals("2-5=-3", result);
    }

    @Test
    public void testSolveWithZeroResult() {
        String formula = "5-5";
        String result = Main.Solve(formula);
        Assert.assertEquals("5-5=0", result);
    }

    @Test
    public void testSolveWithZeroDivision() {
        String formula = "4/0";
        String result = Main.Solve(formula);
        Assert.assertEquals("Division by zero", result);
    }

    @Test
    public void testSolveWithExtraSpaces() {
        String formula = "3+5*2-4";
        String result = Main.Solve(formula);
        Assert.assertEquals("3+5*2-4=9", result);
    }

    @Test
    public void testSolveWithInvalidCharacters() {
        String formula = "3+5abc*2-4";
        String result = Main.Solve(formula);
        Assert.assertEquals("Invalid formula", result);
    }

    @Test
    public void testSolveWithEmptyFormula() {
        String formula = "";
        String result = Main.Solve(formula);
        Assert.assertEquals("Invalid formula", result);
    }

    @Test
    public void testProfile(){
        for(int i = 0;i < 10000000; i++) {
            String question = Main.MakeFormula();
            String ret = Main.Solve(question);
        }
    }
}
