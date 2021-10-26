package controllers

class BusinessLayer(val dal: DataAccessLayer) {
  def save(obj: SomeType): Unit ={
    dal.save(obj)
  }
}
