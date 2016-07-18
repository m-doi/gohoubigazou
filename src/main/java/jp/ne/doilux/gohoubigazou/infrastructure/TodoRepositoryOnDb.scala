package jp.ne.doilux.gohoubigazou.infrastructure

import jp.ne.doilux.gohoubigazou.domain.{Status, Todo, TodoRepository}
import org.springframework.stereotype.Component
import scalikejdbc._
import scalikejdbc.jsr310._


@Component
class TodoRepositoryOnDb extends TodoRepository {

  // initialize JDBC driver & connection pool
  Class.forName("org.h2.Driver")
  ConnectionPool.singleton("jdbc:h2:mem:todo", "sa", "")

  // ad-hoc session provider on the REPL
  implicit val session = AutoSession

  sql"""
create table todo (
  id int(10) NOT NULL AUTO_INCREMENT,
  title varchar(64),
  done int(1) default 0,
  created_at timestamp default current_timestamp
)
""".execute.apply()

  override def register(title: String): Unit = {
    sql"insert into todo(title) values (${title})".update.apply()
  }

  override def find: Seq[Todo] = {
    sql"select * from todo".map(
      rs => Todo(
        rs.int("id"),
        rs.string("title"),
        Status.createBy(rs.int("done")),
        rs.get("created_at")
      )
    ).list.apply()
  }

  override def save(obj: Todo): Unit = {
    val (status, id) = (obj.getStatus().getIndex(), obj.getId())
    sql"update todo set done = ${status} where id = ${id}"
      .update.apply()
  }

  override def find(id: Integer): Option[Todo] = {
    sql"SELECT * FROM todo where id = ${id}".map(
      rs => Todo(
        rs.int("id"),
        rs.string("title"),
        Status.createBy(rs.int("done")),
        rs.get("created_at")
      )
    ).single.apply()
  }
}
