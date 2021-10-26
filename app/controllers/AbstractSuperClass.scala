package controllers

abstract class AbstractSuperClass {
  protected[controllers] def someFunction(num: Int): Int
  def addition(another: AbstractSuperClass, num: Int): Int
}

class SubclassSquare extends AbstractSuperClass {
  override protected[controllers] def someFunction(num: Int): Int = num * num
  override def addition(another: AbstractSuperClass, num: Int): Int =
    someFunction(num) + another.someFunction(num)

  def main(args: Array[String]): Unit = {
    val ss1 = new SubclassSquare()
    val ss2 = new SubclassSquare()
    println("res: "+ss1.addition(ss2, 2))
  }
}
