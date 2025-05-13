package org.example.creator.impl;

import org.example.creator.StraightLineFactory;
import org.example.entity.StraightLineEntity;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.testng.Assert.*;

public class StraightLineFactoryImplTests {

    private final StraightLineFactory straightLineFactory = new StraightLineFactoryImpl();

    @DataProvider(name = "valid2DLineData")
    public Object[][] provideValid2DData() {
        return new Object[][]{
                {0.0, 0.0, 1.0, 1.0},
                {3.0, 4.0, -1.0, 2.0},
                {1.5, 2.5, 0.0, 5.0}
        };
    }

    @DataProvider(name = "invalid2DLineData")
    public Object[][] provideInvalid2DData() {
        return new Object[][]{
                {0.0, 0.0, 0.0, 0.0},  // Zero direction vector
                {1.0, 1.0, 0.0, 0.0}   // Another zero direction vector
        };
    }

    @DataProvider(name = "valid3DLineData")
    public Object[][] provideValid3DData() {
        return new Object[][]{
                {0.0, 0.0, 0.0, 1.0, 1.0, 1.0},  // Simple valid 3D line
                {5.0, -3.0, 2.0, -1.0, 4.0, 0.0}, // Mixed positive and negative direction
                {-1.0, -1.0, -1.0, 0.0, 0.0, 1.0} // Line parallel to the z-axis
        };
    }

    @DataProvider(name = "invalid3DLineData")
    public Object[][] provideInvalid3DData() {
        return new Object[][]{
                {0.0, 0.0, 0.0, 0.0, 0.0, 0.0},  // Zero direction vector
                {3.0, 3.0, 3.0, 0.0, 0.0, 0.0}   // Another zero direction vector
        };
    }

    @Test(dataProvider = "valid2DLineData")
    public void testCreateValid2DLine(double px, double py, double dx, double dy) {
        // Act
        Optional<StraightLineEntity> lineOpt = straightLineFactory.create2D(px, py, dx, dy);

        // Assert
        assertTrue(lineOpt.isPresent(), "Expected valid 2D line to be created.");
        StraightLineEntity line = lineOpt.get();
        assertEquals(line.getPx(), px, 0.001);
        assertEquals(line.getPy(), py, 0.001);
        assertEquals(line.getDx(), dx, 0.001);
        assertEquals(line.getDy(), dy, 0.001);
    }

    @Test(dataProvider = "invalid2DLineData")
    public void testCreateInvalid2DLine(double px, double py, double dx, double dy) {
        Optional<StraightLineEntity> lineOpt = straightLineFactory.create2D(px, py, dx, dy);

        assertFalse(lineOpt.isPresent(), "Expected invalid 2D line to not be created.");
    }

    @Test(dataProvider = "valid3DLineData")
    public void testCreateValid3DLine(double px, double py, double pz, double dx, double dy, double dz) {
        Optional<StraightLineEntity> lineOpt = straightLineFactory.create3D(px, py, pz, dx, dy, dz);

        assertTrue(lineOpt.isPresent(), "Expected valid 3D line to be created.");
        StraightLineEntity line = lineOpt.get();
        assertEquals(line.getPx(), px, 0.001);
        assertEquals(line.getPy(), py, 0.001);
        assertEquals(line.getPz(), pz, 0.001);
        assertEquals(line.getDx(), dx, 0.001);
        assertEquals(line.getDy(), dy, 0.001);
        assertEquals(line.getDz(), dz, 0.001);
    }

    @Test(dataProvider = "invalid3DLineData")
    public void testCreateInvalid3DLine(double px, double py, double pz, double dx, double dy, double dz) {
        // Act
        Optional<StraightLineEntity> lineOpt = straightLineFactory.create3D(px, py, pz, dx, dy, dz);

        // Assert
        assertFalse(lineOpt.isPresent(), "Expected invalid 3D line to not be created.");
    }
}