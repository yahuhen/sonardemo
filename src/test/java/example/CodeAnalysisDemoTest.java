package example;

import org.junit.Assert;
import org.junit.Test;

public class CodeAnalysisDemoTest {


    @Test
    public void nullPointerMethodTest(){
        CodeAnalysisDemo codeAnalysisDemo = new CodeAnalysisDemo();
        Assert.assertEquals("INPUT", codeAnalysisDemo.nullPointer("input"));
    }


}
