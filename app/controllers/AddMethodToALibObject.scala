package controllers

//https://stackoverflow.com/questions/17072184/how-can-i-add-new-methods-to-a-library-object
object AddMethodToALibObject extends App {
  case class Foo()
  class Block(x: Foo){
    def getBlockElements = println("here are block elements")
  }
  implicit def FooToBlock(x: Foo)  = new Block(x)
  val foo1 = new Foo()
  foo1.getBlockElements
}
