package jp.ne.doilux.gohoubigazou.domain

import java.time.LocalDate

case class Todo
(
  id: Integer,
  title: String,
  status: Status.Value,
  date: LocalDate
) {

  def getId(): Integer = {
    id
  }

  def getTitle(): String = {
    title
  }

  def done(): Todo = {
    Todo(id, title, Status.DONE, date)
  }

  def isDoing(): Boolean = {
    status == Status.DOING
  }

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