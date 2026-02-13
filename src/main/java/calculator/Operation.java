package calculator;

import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Operation is an abstract class that represents arithmetic operations,
 * which are a special kind of Expressions, just like numbers are.
 *
 * @see Expression
 * @see MyNumber
 */
public abstract class Operation implements Expression
{
	/**
	 * The list of expressions passed as an argument to the arithmetic operation
	 */
	public List<Expression> args;

  /**
   * The character used to represent the arithmetic operation (e.g. "+", "*")
   */
  protected String symbol;

  /**
   * The neutral element of the operation (e.g. 1 for *, 0 for +)
   */
  protected int neutral;

  /**
   * The notation used to render operations as strings.
   * By default, the infix notation will be used.
   */
  public Notation notation = Notation.INFIX;

  /** It is not allowed to construct an operation with a null list of expressions.
   * Note that it is allowed to have an EMPTY list of arguments.
   *
   * @param elist	The list of expressions passed as argument to the arithmetic operation
   * @throws IllegalConstruction	Exception thrown if a null list of expressions is passed as argument
   */
  protected /*constructor*/ Operation(List<Expression> elist)
		  throws IllegalConstruction
	{
		this(elist, null);
    }

	/** To construct an operation with a list of expressions as arguments,
	 * as well as the Notation used to represent the operation.
	 *
	 * @param elist	The list of expressions passed as argument to the arithmetic operation
	 * @param n 	The notation to be used to represent the operation
	 * @throws IllegalConstruction	Exception thrown if a null list of expressions is passed as argument
	 */
	protected /*constructor*/ Operation(List<Expression> elist,Notation n)
			throws IllegalConstruction
	{
		if (elist == null) {
			throw new IllegalConstruction(); }
		else {
			args = new ArrayList<>(elist);
		}
		if (n!=null) notation = n;
	}

	/**
	 * getter method to return the number of arguments of an arithmetic operation.
	 *
	 * @return	The number of arguments of the arithmetic operation.
	 */
	public List<Expression> getArgs() {
  	return args;
  }

	/**
	 * Abstract method representing the actual binary arithmetic operation to compute
	 * @param l	 first argument of the binary operation
	 * @param r	second argument of the binary operation
	 * @return	result of computing the binary operation
	 */
   public abstract int op(int l, int r);
    // the operation itself is specified in the subclasses

	/** Add more parameters to the existing list of parameters
	 *
	 * @param params	The list of parameters to be added
	 */
	public void addMoreParams(List<Expression> params) {
  	args.addAll(params);
  }

	/**
	 * Accept method to implement the visitor design pattern to traverse arithmetic expressions.
	 * Each operation will delegate the visitor to each of its arguments expressions,
	 * and will then pass itself to the visitor object to get processed by the visitor object.
	 *
	 * @param v	The visitor object
	 */
  public void accept(Visitor v) {
  	for(Expression a:args) { a.accept(v); }
  	v.visit(this);
  }

	/**
	 * Count the depth of an arithmetic expression recursively,
	 * using Java 8 functional programming capabilities (streams, maps, etc...)
	 *
 	 * @return	The depth of the arithmetic expression being traversed
	 */
	public final int countDepth() {
	    // use of Java 8 functional programming capabilities
	return 1 + args.stream()
			   .mapToInt(Expression::countDepth)
			   .max()
			   .getAsInt();  
  }

	/**
	 * Count the number of operations contained in an arithmetic expression recursively,
	 * using Java 8 functional programming capabilities (streams, maps, etc...)
	 *
	 * @return	The number of operations contained in an arithmetic expression being traversed
	 */
	public final int countOps() {
	    // use of Java 8 functional programming capabilities
	return 1 + args.stream()
			   .mapToInt(Expression::countOps)
			   .reduce(Integer::sum)
			   .getAsInt();
  }

  public final int countNbs() {
	    // use of Java 8 functional programming capabilities
	return args.stream()
			   .mapToInt(Expression::countNbs)
			   .reduce(Integer::sum)
			   .getAsInt();  
  }

  /**
   * Convert the arithmetic operation into a String to allow it to be printed,
   * using the default notation (prefix, infix or postfix) that is specified in some variable.
   *
   * @return	The String that is the result of the conversion.
   */
  @Override
  public final String toString() {
  	return toString(notation);
  }

  /**
   * Convert the arithmetic operation into a String to allow it to be printed,
   * using the notation n (prefix, infix or postfix) that is specified as a parameter.
   *
   * @param n	The notation to be used for representing the operation (prefix, infix or postfix)
   * @return	The String that is the result of the conversion.
   */
  public final String toString(Notation n) {
	   Stream<String> s = args.stream().map(Object::toString);
	   return switch (n) {
		   case INFIX -> "( " +
				   s.reduce((s1, s2) -> s1 + " " + symbol + " " + s2).get() +
				   " )";
		   case PREFIX -> symbol + " " +
				   "(" +
				   s.reduce((s1, s2) -> s1 + ", " + s2).get() +
				   ")";
		   case POSTFIX -> "(" +
				   s.reduce((s1, s2) -> s1 + ", " + s2).get() +
				   ")" +
				   " " + symbol;
	   };
  }

	/**
	 * Two operation objects are equal if their list of arguments is equal and they correspond to the same operation.
	 *
	 * @param o	The object to compare with
	 * @return	The result of the equality comparison
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null) return false; // No object should be equal to null

		if (this == o) return true; // If it's the same object, they're obviously equal

		if (getClass() != o.getClass()) return false; // getClass() instead of instanceof() because an addition is not the same as a multiplication

		Operation other = (Operation) o;
		return this.args.equals(other.getArgs());
	  }

	/** The method hashCode needs to be overridden it the equals method is overridden;
	 * 	otherwise there may be problems when you use your object in hashed collections
	 * 	such as HashMap, HashSet, LinkedHashSet.
	 *
	 * @return	The result of computing the hash.
	 */
	@Override
	public int hashCode()
	{
		int result = 5;
		int prime = 31;
		result = prime * result + neutral;
		result = prime * result + symbol.hashCode();
		result = prime * result + args.hashCode();
		return result;
	}

}
