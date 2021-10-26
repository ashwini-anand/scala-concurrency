package controllers

trait SomeType
trait DataAccessLayer {
  def save(obj: SomeType)
}

class PostgresDataAccessLayer extends DataAccessLayer {
  override def save(obj: SomeType) = {
    //logic to save in postgres DB
  }
}

class JsonDataAccessLayer extends DataAccessLayer {
  override def save(obj: SomeType) = {
    //logic to save as json in local file system
  }
}