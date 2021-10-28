package laika.demo

sealed trait RunsOn
object RunsOn {
  case object JS extends RunsOn
  case object JVM extends RunsOn
}

sealed trait OutputFormat
object OutputFormat {

  def unapply(self: OutputFormat) = self match {
    case HTMLSource    => Some("html-source")
    case RenderedHTML  => Some("rendered-html")
    case ResolvedAST   => Some("resolved-ast")
    case UnresolvedAST => Some("unresolved-ast")
  }

  case object RenderedHTML extends OutputFormat
  case object HTMLSource extends OutputFormat
  case object ResolvedAST extends OutputFormat
  case object UnresolvedAST extends OutputFormat
}
