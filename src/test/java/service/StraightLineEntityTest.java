package org.example.entity;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class StraightLineEntityTest {

    @Test
    public void testConstructor3D() {
        // Arrange
        double px = 1.0, py = 2.0, pz = 3.0, dx = 4.0, dy = 5.0, dz = 6.0;

        // Act
        StraightLineEntity line = new StraightLineEntity(px, py, pz, dx, dy, dz);

        // Assert
        assertEquals(line.getPx(), px, 0.001, "X coordinate of the point is incorrect");
        assertEquals(line.getPy(), py, 0.001, "Y coordinate of the point is incorrect");
        assertEquals(line.getPz(), pz, 0.001, "Z coordinate of the point is incorrect");
        assertEquals(line.getDx(), dx, 0.001, "X direction is incorrect");
        assertEquals(line.getDy(), dy, 0.001, "Y direction is incorrect");
        assertEquals(line.getDz(), dz, 0.001, "Z direction is incorrect");
    }

    @Test
    public void testConstructor2D() {
        // Arrange
        double px = 1.0, py = 2.0, dx = 3.0, dy = 4.0;

        // Act
        StraightLineEntity line = new StraightLineEntity(px, py, dx, dy);

        // Assert
        assertEquals(line.getPx(), px, 0.001, "X coordinate of the point is incorrect");
        assertEquals(line.getPy(), py, 0.001, "Y coordinate of the point is incorrect");
        assertEquals(line.getPz(), 0.0, 0.001, "Z coordinate of the point should be 0");
        assertEquals(line.getDx(), dx, 0.001, "X direction is incorrect");
        assertEquals(line.getDy(), dy, 0.001, "Y direction is incorrect");
        assertEquals(line.getDz(), 0.0, 0.001, "Z direction should be 0");
    }

    @Test
    public void testEqualsAndHashCode() {
        // Arrange
        StraightLineEntity line1 = new StraightLineEntity(1.0, 2.0, 3.0, 4.0, 5.0, 6.0);
        StraightLineEntity line2 = new StraightLineEntity(1.0, 2.0, 3.0, 4.0, 5.0, 6.0);
        StraightLineEntity line3 = new StraightLineEntity(7.0, 8.0, 9.0, 10.0, 11.0, 12.0);

        // Act & Assert
        assertEquals(line1, line2, "Lines with the same attributes should be equal");
        assertNotEquals(line1, line3, "Lines with different attributes should not be equal");
        assertEquals(line1.hashCode(), line2.hashCode(), "Hash codes of equal lines should match");
        assertNotEquals(line1.hashCode(), line3.hashCode(), "Hash codes of different lines should not match");
    }

    @Test
    public void testToString() {
        // Arrange
        StraightLineEntity line = new StraightLineEntity(1.0, 2.0, 3.0, 4.0, 5.0, 6.0);

        // Act
        String result = line.toString();

        // Assert
        String expected = "StraightLineEntity{point(1.00, 2.00, 3.00), direction[4.00, 5.00, 6.00]}";
        assertEquals(result, expected, "toString output is incorrect");
    }
}