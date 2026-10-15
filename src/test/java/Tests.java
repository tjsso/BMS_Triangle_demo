import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Tests {

  @Test
  void expectEquilateral() {
    String output = Triangle.whichTriangle(3, 3, 3);
    assertEquals(Triangle.EQUILATERAL, output);
  }

  @Test
  void expectScalene() {
    String output = Triangle.whichTriangle(3, 4, 5);
    assertEquals(Triangle.SCALENE, output);
  }

  @Test
  void expectIsosceles() {
    String output = Triangle.whichTriangle(4, 4, 6);
    assertEquals(Triangle.ISOSCELES, output);
  }

  @Test
  void expectException_1Input() {
    Exception exception = assertThrows(IllegalStateException.class, () -> {
      Triangle.main(new String[] {"5"});
    });

    assertEquals("Must provide 3 and only 3 integers", exception.getMessage());
  }

  @Test
  void expectException_ZeroInInputs() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      Triangle.main(new String[] {"2", "0", "5"});
    });

    assertEquals("all lengths must be greater than zero", exception.getMessage());
  }

  @Test
  void expectException_validNullAndEmptyInputs() {
    Exception exception = assertThrows(IllegalStateException.class, () -> {
      Triangle.main(new String[] {});
    });

    assertEquals("Must provide 3 and only 3 integers", exception.getMessage());

    Exception exception2 = assertThrows(IllegalStateException.class, () -> {
      Triangle.main(new String[] {null});
    });

    assertEquals("Must provide 3 and only 3 integers", exception2.getMessage());
  }

  // We should validate if triangle is valid.: "Add the two shorter sides together. If their sum is greater than the longest side, the three sides form a valid triangle. If the sum equals or is less than the longest side, they do not."
  // Success case == expectSuccess_valid3Inputs(
  @Test
  void expectException_IfSumTwoSidesIsLessThanThirdSide() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      Triangle.main(new String[] {"2", "2", "8"});
    });

    assertEquals("the 2 of the 3 sides MUST sum to be greater than the longest side; Therefore not a valid triangle.", exception.getMessage());
  }

  @Test
  void expectException_OneArgNotANumber() {
    Exception exception = assertThrows(NumberFormatException.class, () -> {
      Triangle.main(new String[] {"2", "T", "2"});
    });

    assertEquals("For input string: \"T\"", exception.getMessage());
  }

}
