package com.example

import java.sql.ResultSet

object DbExtractor {

  def toStream[T](resultSet: ResultSet)(extractFunction: ResultSet => T) = {
    new Iterator[T] {
      def hasNext: Boolean = resultSet.next()
      def next() = extractFunction(resultSet)
    }.toStream
  }
}
