package cs1302.calc;
/**
 * Provides a iterrative implementation of the operations defined in the <code>MathOps</code>
 * interface. This implementation assumes that the inputs to each method are
 * non-negative integers.
 */
public class IterativeMathOps implements MathOps{

  /**
   * Returns the result of the addition operation.
   *
   * @param lhs the first operand
   * @param rhs the second operand
   * @return the result of the operation
   */
  @Override
  public int add(int lhs, int rhs){//pased basic testing
    while(rhs!=0){
      lhs = succ(lhs);
      rhs = pred(rhs);
    }
    return lhs;
  }


  /**
   * Returns the result of the subtraction operation.
   *
   * @param lhs the first operand
   * @param rhs the second operand
   * @return the result of the operation
   */
  public int sub(int lhs, int rhs){//passed basic testing
    while(rhs!=0){
      rhs = pred(rhs);
      lhs = pred(lhs);
    }
    if(lhs<0){//if putput would be zero return 0
      return 0;
    }
    else{
      return lhs;
    }

  }

  /**
   * Returns the result of the binary multiplication.
   *
   * @param lhs the first operand
   * @param rhs the second operand
   * @return the result of the operation
   */
  public int mul(int lhs, int rhs){//passsed basic tests
    int output=0;//ask if we are alowed to create a new variable for this
    if(lhs==0||rhs==0){
      return 0;
    }
    else{
      while (rhs!=0){
        output = add(output,lhs);
        rhs = pred(rhs);
      }
    }
    return output;
  }

  /**
   * Returns the result of the division operation.
   * <code>lhs / rhs</code>.
   *
   * @param lhs the first operand
   * @param rhs the second operand
   * @return the result of the operation
   * @throws ArithmeticException if the second operand is equal to zero.
   */
  public int div(int lhs, int rhs) throws ArithmeticException{
    int output =0;
    if(rhs==0){
      throw new ArithmeticException("Divide by zero error");
      //return 0;
    }
    else if(lhs==0||lhs<rhs){//if lhs is 0 or the denomiator is larger than num =0
      return 0;
    }
    else{
      while(sub(lhs,rhs)>=0){
        lhs = sub(lhs,rhs);
        output = succ(output);
      }
      return output;
    }
  }

  /**
   * Returns the result of the factorial operation.
   *
   * @param num the operand
   * @return the result of the operation
   */
  public int fac(int num){//passed basic tests
    int output =1;//passes basic test for pos numbers
    while(num!=0){//if num==0 it goes strait to return
      output = mul(output, num);
      num = pred(num);
    }
    return output;
  }

  /**
   * Returns the result of the exponentiation operation
   * <code>lhs ^^ rhs</code>.
   *
   * @param lhs the first operand
   * @param rhs the second operand
   * @return the result of the operation
   */
  public int pow(int lhs, int rhs){//passed basic tests
    int output = 1;
    while(rhs!=0){//if rhs==0 it goes strait to return
      output = mul(output,lhs);
      rhs = pred(rhs);
    }
    return output;
  }

  /**
   * Returns the result of the left-shift operation.
   *
   * @param lhs the first operand
   * @param rhs the second operand
   * @return the result of the operation
   */
  public int lshift(int lhs, int rhs){
    //if (lhs>1073741824){//2^30 in // need to have a case that deals with large ints
    //  return lhs;
    //}
    while(rhs!=0){
      lhs = mul(lhs, 2);
      rhs = pred(rhs);
    }
    return lhs;
  }

  /**
   * Returns the result of the right-shift operation.
   *
   * @param lhs the first operand
   * @param rhs the second operand
   * @return the result of the operation
   */
  public int rshift(int lhs, int rhs){//seams to pass basic testing
    while(rhs!=0){
      lhs = div(lhs, 2);
      rhs = pred(rhs);
    }
    return lhs;
  }

} // IterativeMathOps