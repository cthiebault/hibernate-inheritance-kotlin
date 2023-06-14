package demo

import jakarta.persistence.Entity
import jakarta.persistence.PrimaryKeyJoinColumn
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "rectangle")
@PrimaryKeyJoinColumn(name = "shape_id")
data class Rectangle(
  override val id: UUID,
  override var name: String,
  override var color: Color,
  override var properties: Properties?
) : Shape(id, name, color, properties) {

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false
    return id == (other as Rectangle).id
  }

  override fun hashCode(): Int = id.hashCode()
}