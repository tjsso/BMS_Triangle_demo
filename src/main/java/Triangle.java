import java.util.Arrays;

/**
 * Identify types of triangle based on sides
 * Equilateral => all sides are equal
 * Isosceles => two sides of equal length
 * Scalene => all sides are different length
 *
 * Considerations:
 * * Validate that there are only 3 inputs; decision throw exception if != 3
 * * all lengths > 0
 * * Should we validate that you can actually make a triangle? e.g. 100 1 1 is an impossible connecting shape.
 *    Googled how to tend a valid triangle: "Add the two shorter sides together. If their sum is greater than the longest side, the three sides form a valid triangle. If the sum equals or is less than the longest side, they do not."
 *    Decision: We should validate if triangle is valid.
 */
public class Triangle {

  //This could be an enum, but for this exercise... why bother?
  public static final String EQUILATERAL = "Equilateral";
  public static final String SCALENE = "Scalene";
  public static final String ISOSCELES = "Isosceles";

  /**
  *
  */
  public static void main(String[] args) {
    //realised during working that this is also a nice time to check that all String[] are also ints
      if (args.length != 3) {
        throw new IllegalStateException("Must provide 3 and only 3 integers");
      }

      //AI generated code for java Stream() idea
      // Prompt: for an array is there a java stream to check all String[] are integers using Integer.valueOf(str) or .parseInt(str)?
      Arrays.stream(args).allMatch(s -> {
          if (s == null) throw new NumberFormatException("All inputs must be whole ints");

          int side = Integer.parseInt(s);

          //valid it is a positive integer > 0
          if (side <= 0) throw new IllegalArgumentException("all lengths must be greater than zero");
          return true;
      });

      //we have validated all are ints by this point
      int[] numbers = Arrays.stream(args)
                      .mapToInt(Integer::parseInt)
                      .toArray();

      int longestSide = Arrays.stream(numbers).max().getAsInt();
      int sumTheRest = Arrays.stream(numbers).filter(n -> n != longestSide).sum();
      // In testing the 'equals' in this statement accidentally catches EQUILATERAL triangles, sum should be zero as all sides are equal and filtered out
      if (sumTheRest > 0 && sumTheRest <= longestSide) {
        //It is not a valid triangle
        throw new IllegalArgumentException("the 2 of the 3 sides MUST sum to be greater than the longest side; Therefore not a valid triangle.");
      }

      System.out.println(whichTriangle(numbers[0], numbers[1], numbers[2]));
  }

  protected static String whichTriangle(int a, int b, int c) {
    if (a == b && a == c) {
      return EQUILATERAL;
    } else if (a == b || a == c || b == c) {
      return ISOSCELES;
    } else {
      return SCALENE;
    }
  }

}
