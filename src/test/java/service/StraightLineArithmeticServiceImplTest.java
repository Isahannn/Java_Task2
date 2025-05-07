package org.example.service.impl;

import org.example.entity.StraightLineEntity;
import org.example.exception.StraightLineEntityException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class StraightLineArithmeticServiceImplTest {

    private StraightLineArithmeticServiceImpl arithmeticService;
    private StraightLineEntity line1;
    private StraightLineEntity line2;
    private SoftAssert softAssert;

    @BeforeClass
    public void setUp() {
        arithmeticService = new StraightLineArithmeticServiceImpl();
        softAssert = new SoftAssert();

        // Initialize two sample lines
        line1 = new StraightLineEntity(1.0, 2.0, 3.0, 4.0, 5.0, 6.0);
        line2 = new StraightLineEntity(6.0, 5.0, 4.0, 3.0, 2.0, 1.0);
    }

    @Test
    public void testAdd() {
        // Act
        StraightLineEntity result = arithmeticService.add(line1, line2);

        // Assert
        softAssert.assertEquals(result.getPx(), 7.0, "X position addition failed");
        softAssert.assertEquals(result.getPy(), 7.0, "Y position addition failed");
        softAssert.assertEquals(result.getPz(), 7.0, "Z position addition failed");
        softAssert.assertEquals(result.getDx(), 7.0, "X direction addition failed");
        softAssert.assertEquals(result.getDy(), 7.0, "Y direction addition failed");
        softAssert.assertEquals(result.getDz(), 7.0, "Z direction addition failed");
        softAssert.assertAll();
    }

    @Test
    public void testSubtract() {
        // Act
        StraightLineEntity result = arithmeticService.subtract(line1, line2);

        // Assert
        softAssert.assertEquals(result.getPx(), -5.0, "X position subtraction failed");
        softAssert.assertEquals(result.getPy(), -3.0, "Y position subtraction failed");
        softAssert.assertEquals(result.getPz(), -1.0, "Z position subtraction failed");
        softAssert.assertEquals(result.getDx(), 1.0, "X direction subtraction failed");
        softAssert.assertEquals(result.getDy(), 3.0, "Y direction subtraction failed");
        softAssert.assertEquals(result.getDz(), 5.0, "Z direction subtraction failed");
        softAssert.assertAll();
    }

    @Test
    public void testMultiplyByScalar() {
        // Act
        StraightLineEntity result = arithmeticService.multiplyByScalar(line1, 2.0);

        // Assert
        softAssert.assertEquals(result.getPx(), 2.0, "X position multiplication failed");
        softAssert.assertEquals(result.getPy(), 4.0, "Y position multiplication failed");
        softAssert.assertEquals(result.getPz(), 6.0, "Z position multiplication failed");
        softAssert.assertEquals(result.getDx(), 8.0, "X direction multiplication failed");
        softAssert.assertEquals(result.getDy(), 10.0, "Y direction multiplication failed");
        softAssert.assertEquals(result.getDz(), 12.0, "Z direction multiplication failed");
        softAssert.assertAll();
    }

    @Test(expectedExceptions = StraightLineEntityException.class, expectedExceptionsMessageRegExp = "Cannot divide by zero")
    public void testDivideByZero() throws StraightLineEntityException {
        // Expect an exception to be thrown
        arithmeticService.divideByScalar(line1, 0.0);
    }

    @Test
    public void testDivideByScalar() throws StraightLineEntityException {
        // Act
        StraightLineEntity result = arithmeticService.divideByScalar(line1, 2.0);

        // Assert
        softAssert.assertEquals(result.getPx(), 0.5, "X position division failed");
        softAssert.assertEquals(result.getPy(), 1.0, "Y position division failed");
        softAssert.assertEquals(result.getPz(), 1.5, "Z position division failed");
        softAssert.assertEquals(result.getDx(), 2.0, "X direction division failed");
        softAssert.assertEquals(result.getDy(), 2.5, "Y direction division failed");
        softAssert.assertEquals(result.getDz(), 3.0, "Z direction division failed");
        softAssert.assertAll();
    }
}