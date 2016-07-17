package jp.ne.doilux.gohoubigazou.controller

import jp.ne.doilux.gohoubigazou.domain.{Todo, TodoRepository}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RequestParam}

// これをimportしておけば、ScalaのコレクションからJavaのコレクションに暗黙変換してくれるらしい。
// 参考：http://docs.scala-lang.org/ja/overviews/collections/conversions-between-java-and-scala-collections
import scala.collection.JavaConversions._

@Controller
@RequestMapping(Array("/todo"))
class TodoController {

  @Autowired private val todoRepository: TodoRepository = null

  @RequestMapping(value = Array("/"), method = Array(RequestMethod.GET))
  def index(model: Model): String = {

    // thymeleafがScalaのコレクションに対応していないようだ
    val nodes: java.util.List[Todo] = todoRepository.find.filter(_.isDoing())

    model.addAttribute("nodes", nodes)
    "todo/index"
  }

  @RequestMapping(value = Array("/new"), method = Array(RequestMethod.POST))
  def post(@RequestParam("title") title: String): String = {

    todoRepository.register(title)

    "redirect:/todo/"
  }

  @RequestMapping(value = Array("/done"), method = Array(RequestMethod.POST))
  def done(@RequestParam("id") id: Integer): String = {

    val todo: Todo = todoRepository.find(id)
    val doneTodo: Todo = todo.done()
    todoRepository.save(doneTodo)

    "redirect:/todo/"
  }


}
