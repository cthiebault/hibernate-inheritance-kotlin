# Hibernate Inheritance with Kotlin

Simple repository to try to reproduce the
issue [Quarkus 3/Hibernate ORM 6: entity inheritance does not work with Kotlin](https://github.com/quarkusio/quarkus/issues/33740)
without Quarkus.

Here, without Quarkus, Hibernate inheritance seems to work if we annotate the fields of the parent class
with `@Access(AccessType.PROPERTY)`.

- Hibernate version: 6.2.4.Final
- Kotlin version: 1.8.21

```shell
./gradlew clean build
```