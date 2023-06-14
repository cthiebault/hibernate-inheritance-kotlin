package demo

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class Properties(
  @Column(name = "foo", nullable = false, length = 50)
  var foo: String,

  @Column(name = "bar", nullable = false, length = 50)
  var bar: String,
)