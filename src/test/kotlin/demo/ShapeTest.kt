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

    persist(rectangle)
    clearThePersistenceContext()

    val shapes = findAllShapes()
    logger.info("shapes: $shapes")

    assertEquals(1, shapes.size)

    val firstShape = shapes.first()
    assertEquals(rectangle.id, firstShape.id)
    assertEquals(rectangle.name, firstShape.name)
    assertEquals(rectangle.color, firstShape.color)
    assertEquals(rectangle.properties, firstShape.properties)
  }

  private fun findAllShapes(): List<Shape> =
    entityManager
      .createQuery("SELECT shape FROM Shape shape", Shape::class.java)
      .resultList

  private fun persist(any: Any) {
    entityManager.transaction.begin()
    entityManager.persist(any)
    entityManager.transaction.commit()
  }

  private fun clearThePersistenceContext() {
    entityManager.clear()
  }

}