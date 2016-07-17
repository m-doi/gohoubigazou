package jp.ne.doilux.gohoubigazou.domain

import java.time.LocalDate

case class Todo
(
  id: Integer,
  title: String,
  status: Status.Value,
  date: LocalDate
) {

}

object Status extends Enumeration {
  val DOING, DONE = Value
}


trait TodoRepository {

  def register(title: String)

  def find: Seq[Todo]

  def find(id: Integer): Option[Todo]

  def save(obj: Todo)
}