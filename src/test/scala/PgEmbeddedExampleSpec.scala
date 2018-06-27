import java.sql.ResultSet

import org.scalatest._
import org.junit._
import org.scalatest.junit.JUnitSuite
import scala.annotation.meta.getter
import com.opentable.db.postgres.junit.EmbeddedPostgresRules
import com.opentable.db.postgres.junit.SingleInstancePostgresRule
import com.example.DbExtractor.toStream

//https://stackoverflow.com/questions/32160549/using-junit-rule-with-scalatest-e-g-temporaryfolder
//http://www.scalatest.org/user_guide/using_matchers
class PgEmbeddedExampleSpec extends JUnitSuite with Matchers {

  @(Rule @getter)
  val pg: SingleInstancePostgresRule = EmbeddedPostgresRules.singleInstance

  @Test def shouldGiveDefaultDb() {
    val db = pg.getEmbeddedPostgres.getPostgresDatabase
    val cn = db.getConnection()
    val results = toStream(cn.createStatement().executeQuery(
      "SELECT datname FROM pg_database WHERE datistemplate = false"
    ))(_.getString(1))

    results should contain ("postgres")

    val tables = toStream(cn.createStatement().executeQuery(
      "SELECT table_schema,table_name FROM information_schema.tables ORDER BY table_schema,table_name"
    ))(r => r.getString(1) -> r.getString(2))

    tables should not be empty
  }

}
