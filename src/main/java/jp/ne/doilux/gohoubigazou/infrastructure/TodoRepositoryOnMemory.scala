package jp.ne.doilux.gohoubigazou.infrastructure

import java.time.LocalDate

import jp.ne.doilux.gohoubigazou.domain.{Status, Todo, TodoRepository}
import org.springframework.stereotype.Component

import scala.collection.mutable


@Component
class TodoRepositoryOnMemory extends TodoRepository {

  var map = mutable.Map(0 -> Todo(0, "dummy", Status.DOING, LocalDate.now()))

  override def register(title: String): Unit = {
    def id = map.keySet.max + 1
    def todo = Todo(id, title, Status.DOING, LocalDate.now())
    map.put(id, todo)
  }

  override def find: Seq[Todo] = {
    map.values.toSeq
  }

  override def save(obj: Todo): Unit = {
    map.remove(obj.id)
    map.put(obj.id, obj)
  }

  override def find(id: Integer): Option[Todo] = {
    map.get(id)
  }
}
