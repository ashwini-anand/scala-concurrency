package controllers

object MultipleStuff extends App {

  def  id(x:  Int) = x

  def func1(f : (Int) => Int, x: Int): Int = f(x)

  def func2() = {
     z:Int => (y: Int) =>(x: Int) => (x*x*x)
  }

  def func3(a: Int)(b:  Int)(c : Int) = {
    c*c*c
  }
  val xy: Int => Int => Int = func3(3)
  //Generic multiply using currying
  def multiplyByX(x :Int) = (y : Int) => x*y
  val multiplyBy2 = multiplyByX(2)
  val twiceOf3 = multiplyBy2(3)
  println(twiceOf3)
  //Generic multiply using currying ends here
}
