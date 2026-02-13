package visitor;

import calculator.Expression;
import calculator.MyNumber;
import calculator.Operation;

import java.util.ArrayList;

/** Evaluation is a concrete visitor that serves to
 * compute and evaluate the results of arithmetic expressions.
 */
public class Evaluator extends Visitor {

     public Evaluator() {
         //Default constructor of the class. Does not initialise anything.
     }

    /** The result of the evaluation will be stored in this private variable */
    private int computedValue;

    /** getter method to obtain the result of the evaluation
     *
     * @return an Integer object containing the result of the evaluation
     */
    public Integer getResult() { return computedValue; }

    /** Use the visitor design pattern to visit a number.
     *
     * @param n The number being visited
     */
    public void visit(MyNumber n) {
        computedValue = n.getValue();
    }

    /** Use the visitor design pattern to visit an operation
     *
     * @param o The operation being visited
     */
    public void visit(Operation o) {
        ArrayList<Integer> evaluatedArgs = new ArrayList<>();
        //first loop to recursively evaluate each subexpression
        for(Expression a:o.args) {
            a.accept(this);
            evaluatedArgs.add(computedValue);
        }
        //second loop to accumulate all the evaluated subresults
        int temp = evaluatedArgs.get(0);
        int max = evaluatedArgs.size();
        for(int counter=1; counter<max; counter++) {
            temp = o.op(temp,evaluatedArgs.get(counter));
        }
        // store the accumulated result
        computedValue = temp;
    }

}
