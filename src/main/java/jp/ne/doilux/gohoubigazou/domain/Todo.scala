package jp.ne.doilux.gohoubigazou.domain

import java.time.LocalDate

case class Todo
(
  id: Integer,
  title: String,
  status: Status,
  date: LocalDate
  ) {

  def done(): Todo = {
    Todo(id, title, Status.DONE, date)
  }

  def isDoing(): Boolean = {
    status.isDoing
  }

}


trait TodoRepository {

  def register(title: String)

  def find: Seq[Todo]

  def find(id: Integer): Option[Todo]

  def save(obj: Todo)
}