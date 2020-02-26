package cs1302.calc;

//note that all recursive opps passed basic tests
/**
 * Provides a recursive implementation of the operations defined in the <code>MathOps</code>
 * interface. This implementation assumes that the inputs to each method are
 * non-negative integers.
 */
public class RecursiveMathOps implements MathOps{

    /**
     * Returns the result of the addition operation.
     *
     * @param lhs the first operand
     * @param rhs the second operand
     * @return the result of the operation
     */
    @Override
    public int add(int lhs, int rhs){//seams to pass basic tests
        if (rhs==0) return lhs;
        return add(succ(lhs), pred(rhs));
    }


    /**
     * Returns the result of the subtraction operation.
     *
     * @param lhs the first operand
     * @param rhs the second operand
     * @return the result of the operation
     */
    public int sub(int lhs, int rhs){//seams to pass basic tests
        if (rhs==0){//base case
            if(lhs<0){//if out put would be neg return 0
                return 0;
            }
            else{
                return lhs;
            }
        }
        else{
            //lhs = pred(lhs);
            //rhs= pred(rhs);
            return sub(pred(lhs), pred(rhs));
        }
    }

    /**
     * Returns the result of the binary multiplication.
     *
     * @param lhs the first operand
     * @param rhs the second operand
     * @return the result of the operation
     */
    public int mul(int lhs, int rhs){//seams to pass basic tests
        if (rhs ==0) return 0;
        if (rhs ==1) return lhs;
        return add(lhs, mul(lhs, pred(rhs)));
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
        if(rhs==0){
            throw new ArithmeticException("Divide by zero error");
            //return 0;
        }
        else{
            if(lhs<rhs){//if numerator is bigger than denom return 0
                return 0;
            }
            else if(rhs==1){
                return lhs;
            }
            else{//what do i do about this i need a counter
                return add(1,div(sub(lhs,rhs), rhs));
            }
        }
    }//div

    /**
     * Returns the result of the factorial operation.
     *
     * @param num the operand
     * @return the result of the operation
     */
    public int fac(int num){//passed basic testing
        if (num==0) return 1;
        else{
            return mul(num,fac(pred(num)));//return n*(n-1)repeting
        }
    }

    /**
     * Returns the result of the exponentiation operation 
     * <code>lhs ^^ rhs</code>. 
     *
     * @param lhs the first operand
     * @param rhs the second operand
     * @return the result of the operation
     */
    public int pow(int lhs, int rhs){//passed basic testing
        if (rhs==0){//case of num^0
            return 1;
        }
        else if (rhs==1){//case num^1
            return lhs;
        }
        else{
            return mul(lhs,pow(lhs, pred(rhs)));
        }
    }

    /**
     * Returns the result of the left-shift operation.
     *
     * @param lhs the first operand
     * @param rhs the second operand
     * @return the result of the operation
     */
    public int lshift(int lhs, int rhs){//for lshift mult by two
        if(rhs==0){
            return lhs;
        }
        else{
            return lshift(mul(lhs,2),pred(rhs));
        }
    }


    /**
     * Returns the result of the right-shift operation.
     *
     * @param lhs the first operand
     * @param rhs the second operand
     * @return the result of the operation
     */
    public int rshift(int lhs, int rhs){//for right shift div by 2
        if(rhs==0){
            return lhs;
        }
        else{
            return rshift(div(lhs,2),pred(rhs));
        }
    }

} // RecursiveMathOps