package satyavemuri.practice;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Satya Vemuri on 3/2/2017.
 */
public class Multiplication {

    public static void main(String[] args) throws Exception{
        Scanner input  =  new Scanner(System.in);

        try {
            System.out.println(multiply(input.nextInt(), input.nextInt()));
        } catch (InputMismatchException ime) {
            throw new IllegalArgumentException("Input should be number");
        } finally {
            if (input != null)
                input.close();
        }

    }
    /**
     *
     * @param input1 1st operand
     * @param input2 2nd operand
     * @return multiplication result
     */
    public static long multiply(long input1, long input2 ) throws Exception{
        long sum =0;
        while (input1 != 0) {
            if( (input1 & 1L )!= 0){
                sum = add(sum, input2);
            }
            input1>>>=1;
            input2<<=1;
        }
        return  sum;
    }

    private static long add(long input1, long  input2) throws Exception{

        long sum =0, carryIn = 0, bitIndex=1, tempInput1 = input1, tempInput2 = input2;

        while(tempInput1 != 0 || tempInput2 !=0){

            long input1K=  input1 & bitIndex, input2K = input2 & bitIndex;

            long carryOut =  (input1K & input2K) | (input1K & carryIn) | (input2K & carryIn);

            sum |= (input1K ^ input2K ^ carryIn);

            carryIn = carryOut << 1;

            bitIndex <<= 1;
            tempInput1 >>>= 1;
            tempInput2 >>>=1;

        }
        return sum | carryIn;
    }
}
