package demo

import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.UUID.randomUUID


class ShapeTest {

  private val logger: Logger = LoggerFactory.getLogger(javaClass)

  private lateinit var entityManagerFactory: EntityManagerFactory
  private lateinit var entityManager: EntityManager

  @BeforeEach
  fun setup() {
    entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-kotlin")
    entityManager = entityManagerFactory.createEntityManager()
  }

  @AfterEach
  fun close() {
    entityManager.close()
    entityManagerFactory.close()
  }

  @Test
  fun persistAndRead() {

    val rectangle = Rectangle(
      id = randomUUID(),
      name = "Rectangle",
      color = Color.Red,
      properties = Properties("foo", "bar")
    )

    logger.info("rectangle: $rectangle")

    entityManager.transaction.begin()
    entityManager.persist(rectangle)
    entityManager.transaction.commit()

    entityManager.clear()

    val found = entityManager.find(Shape::class.java, rectangle.id)
    assertEquals(rectangle, found)

    val shapes = entityManager
      .createQuery("SELECT shape FROM Shape shape", Shape::class.java)
      .resultList
    logger.info("shapes: $shapes")

    assertEquals(1, shapes.size)
    assertEquals(rectangle, shapes.first())
  }

  private fun assertEquals(expected: Shape, actual: Shape) {
    assertEquals(expected.id, actual.id)
    assertEquals(expected.name, actual.name)
    assertEquals(expected.color, actual.color)
    assertEquals(expected.properties, actual.properties)
  }

}